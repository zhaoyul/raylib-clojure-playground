(defproject raylib-clojure-playground "0.1.0-SNAPSHOT"
  :description "Raylib experiments in Clojure"
  :url "https://github.com/yourusername/raylib-clojure-playground"
  :license {:name "EPL-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}

  :dependencies [[org.clojure/clojure "1.12.4"]
                 [org.clojure/tools.logging "1.3.1"]
                 ;; Override coffi to use JDK 22+ compatible version
                 [org.suskalo/coffi "1.0.615"]
                 ;; insn is a dependency of coffi
                 [insn/insn "0.5.4"]
                 ;; Force newer ASM for ACC_OPEN support (needed by insn on newer JDKs)
                 [org.ow2.asm/asm "9.7.1"]]

  :source-paths ["src"]
  :resource-paths ["resources"]

  ;; JVM options for native access
  ;; The bundled raylib library is loaded from libs/
  :jvm-opts ["--enable-native-access=ALL-UNNAMED"
             "-XstartOnFirstThread"  ; Required for macOS GUI/OpenGL
             "-Djava.library.path=libs:/opt/homebrew/opt/raylib/lib:/opt/homebrew/lib:/usr/local/lib:/usr/lib"]

  :profiles {:dev {:dependencies [[nrepl "1.5.2"]]}}

  :main examples.asteroids
  :aot [examples.asteroids]

  :repl-options {:init-ns examples.asteroids})
