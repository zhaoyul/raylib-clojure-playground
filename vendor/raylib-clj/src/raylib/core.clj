(ns raylib.core
  (:require
   [coffi.ffi :as ffi])
  (:import [java.io File]))

;; OS Detection
(defn- get-os-name []
  (let [os (System/getProperty "os.name")]
    (cond
      (.contains os "Mac") :macos
      (.contains os "Linux") :linux
      (.contains os "Windows") :windows
      :else :unknown)))

;; Find bundled library path
(defn- find-bundled-lib []
  (let [os (get-os-name)
        cwd (System/getProperty "user.dir")
        libs-dir (str cwd "/vendor/raylib-clj/libs")
        lib-name (case os
                   :macos "libraylib.5.5.0.dylib"
                   :linux "libraylib.so.5.5.0"
                   :windows "raylib.dll"
                   nil)
        lib-path (when lib-name (str libs-dir "/" lib-name))]
    (when (and lib-path (.exists (File. lib-path)))
      lib-path)))

;; Load raylib - prefer bundled, fallback to system
(let [bundled-path (find-bundled-lib)]
  (if bundled-path
    (do
      (println "[raylib] Loading bundled library:" bundled-path)
      (ffi/load-library bundled-path))
    (do
      (println "[raylib] Loading system library")
      (ffi/load-system-library "raylib"))))
