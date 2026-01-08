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

  :source-paths ["src"]
  :resource-paths ["resources"]

  ;; JVM options for native access
  ;; The bundled raylib library is loaded from libs/
  :jvm-opts ["--enable-native-access=ALL-UNNAMED"
             "-XstartOnFirstThread"  ; Required for macOS GUI/OpenGL
             "-Djava.library.path=libs:/opt/homebrew/opt/raylib/lib:/opt/homebrew/lib:/usr/local/lib:/usr/lib"]

  :profiles {:dev {:dependencies [[nrepl/nrepl "1.0.0"]
                                  [cider/cider-nrepl "0.40.0"]
                                  [djblue/portal "0.48.0"]
                                  [integrant/repl "0.3.3"]
                                  [refactor-nrepl/refactor-nrepl "3.9.0"]]}}

  :main examples.asteroids
  :aot [examples.asteroids]

  :repl-options {:init-ns examples.asteroids})
