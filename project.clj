(defproject raylib-clojure-playground "0.1.0-SNAPSHOT"
  :description "Raylib experiments in Clojure"
  :url "https://github.com/yourusername/raylib-clojure-playground"
  :license {:name "EPL-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}

  :dependencies [[org.clojure/clojure "1.11.1"]
                 ;; Override coffi to use JDK 22+ compatible version
                 [org.suskalo/coffi "1.0.615"]
                 ;; insn is a dependency of coffi
                 [insn/insn "0.5.4"]]

  ;; Include raylib-clj source from local checkout
  ;; Run: git clone https://github.com/kiranshila/raylib-clj.git vendor/raylib-clj
  :source-paths ["src" "vendor/raylib-clj/src"]
  :resource-paths ["resources"]

  ;; JVM options for native access
  ;; Note: The bundled raylib library is loaded automatically from vendor/raylib-clj/libs/
  ;; The java.library.path is kept as fallback for system library on other platforms
  :jvm-opts ["--enable-native-access=ALL-UNNAMED"
             "-XstartOnFirstThread"  ; Required for macOS GUI/OpenGL
             ;; Fallback library path (used if bundled lib not found)
             "-Djava.library.path=/opt/homebrew/opt/raylib/lib:/opt/homebrew/lib:/usr/local/lib:/usr/lib"]

  :profiles {:dev {:dependencies [[nrepl/nrepl "1.0.0"]
                                  [cider/cider-nrepl "0.40.0"]
                                  [djblue/portal "0.48.0"]
                                  [integrant/repl "0.3.3"]
                                  [refactor-nrepl/refactor-nrepl "3.9.0"]]}}

  :main ^:skip-aot examples.hello-world

  :repl-options {:init-ns examples.hello-world})
