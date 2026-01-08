(ns raylib.core
  (:require
   [coffi.ffi :as ffi]
   [clojure.string :as str])
  (:import [java.io File]))

;; OS Detection
(defn- get-os-name []
  (let [os (System/getProperty "os.name")]
    (cond
      (.contains os "Mac") :macos
      (.contains os "Linux") :linux
      (.contains os "Windows") :windows
      :else :unknown)))

;; Get library filename for current OS
(defn- get-lib-name []
  (case (get-os-name)
    :macos "libraylib.5.5.0.dylib"
    :linux "libraylib.so.5.5.0"
    :windows "raylib.dll"
    nil))

;; Find bundled library path - check multiple locations
(defn- find-bundled-lib []
  (let [lib-name (get-lib-name)
        cwd (System/getProperty "user.dir")
        lib-path (System/getProperty "java.library.path")
        ;; Candidate directories to search
        search-dirs (concat
                     ;; java.library.path directories (for packaged apps)
                     (when lib-path (str/split lib-path (re-pattern File/pathSeparator)))
                     ;; Development paths
                     [(str cwd "/vendor/raylib-clj/libs")
                      (str cwd "/libs")])]
    (when lib-name
      (->> search-dirs
           (map #(str % "/" lib-name))
           (filter #(.exists (File. ^String %)))
           first))))

;; Load raylib - prefer bundled, fallback to system
(let [bundled-path (find-bundled-lib)]
  (if bundled-path
    (do
      (println "[raylib] Loading bundled library:" bundled-path)
      (ffi/load-library bundled-path))
    (do
      (println "[raylib] Loading system library")
      (ffi/load-system-library "raylib"))))
