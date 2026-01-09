# Raylib Clojure Playground

A collection of game development experiments using Raylib in Clojure. This project uses coffi for FFI bindings to call Raylib's C library directly from Clojure.

## What You Need

- JDK 22 or newer (required for the Foreign Function API)
- Leiningen (Clojure build tool)

## Installing the Prerequisites

### JDK 22+

Clojure runs on the JVM, so you need Java installed. This project requires JDK 22 or later because we use the new Foreign Function API to call native code.

On macOS with Homebrew:

```bash
brew install openjdk@22
```

On Linux (Ubuntu/Debian):

```bash
sudo apt install openjdk-22-jdk
```

Alternatively, you can use SDKMAN which works on macOS, Linux, and Windows (WSL):

```bash
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install java 22.0.2-open
```

### Leiningen

Leiningen is the most common build tool for Clojure projects. It handles dependencies, runs your code, and manages the REPL.

On macOS with Homebrew:

```bash
brew install leiningen
```

On Linux, download the script directly:

```bash
curl -O https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein
chmod +x lein
sudo mv lein /usr/local/bin/
lein  # This will download the rest automatically
```

On Windows, download the installer from the Leiningen website or use Chocolatey:

```bash
choco install lein
```

You can verify everything is working by running:

```bash
lein version
```

## Getting Started

Clone this repository:

```bash
git clone https://github.com/yourusername/raylib-clojure-playground.git
cd raylib-clojure-playground
```

Run one of the examples:

```bash
lein run
```

If you have multiple Java versions installed, you may need to set JAVA_HOME:

```bash
export JAVA_HOME=/path/to/jdk-22
lein run
```

On macOS, the path is usually something like:

```bash
export JAVA_HOME=/Library/Java/JavaVirtualMachines/openjdk-22.jdk/Contents/Home
```

## IDE Setup

Clojure development is best experienced with a good editor that supports REPL integration. Here are two popular options.

### VS Code with Calva

Calva is a free extension that turns VS Code into a capable Clojure editor.

1. Install VS Code
2. Open the Extensions panel and search for "Calva"
3. Install the Calva extension
4. Open this project folder in VS Code
5. Press Ctrl+Alt+C followed by Ctrl+Alt+J (or Cmd on macOS) to start a REPL
6. Select "Leiningen" when prompted

Calva provides syntax highlighting, inline evaluation, and a connected REPL. You can evaluate code by placing your cursor on an expression and pressing Ctrl+Enter.

### IntelliJ IDEA with Cursive

Cursive is a plugin for IntelliJ IDEA that provides excellent Clojure support. It requires a license for commercial use but is free for open source and personal projects.

1. Install IntelliJ IDEA (Community or Ultimate edition)
2. Go to Settings/Preferences and then Plugins
3. Search for "Cursive" and install it
4. Restart the IDE
5. Open this project (File, Open, select the project folder)
6. Cursive will detect the project.clj file and set everything up

To start a REPL in Cursive, right-click on project.clj and select "Run REPL for raylib-clojure-playground".

## Running the Examples

The main example runs Asteroids by default:

```bash
lein run
```

To run a specific example:

```bash
lein run -m examples.hello-world
lein run -m examples.pong
lein run -m examples.asteroids
lein run -m examples.tetris
```

## REPL Development

One of the best things about Clojure is the REPL workflow. You can change code while the game is running and see changes immediately.

Start a REPL:

```bash
lein repl
```

Then load and run an example:

```clojure
(require '[examples.asteroids :as game])
(game/-main)
```

## Available Examples

- Hello World - A basic window with some text, good for testing your setup
- Pong - The classic two-player paddle game
- Asteroids - Shoot asteroids and try to survive
- Tetris - The block-stacking puzzle game

## Controls

Most examples share these common controls:

- F1 toggles the debug overlay which shows FPS and memory usage
- Q or closing the window exits the game

### Pong

- W and S move the left paddle
- K and J move the right paddle
- Enter starts the game

### Asteroids

- Left and Right arrow keys rotate the ship
- Up arrow thrusts forward
- Space shoots or restarts after dying

## Bundled Libraries

This project includes pre-built Raylib libraries for different platforms in the libs folder:

| Platform | Directory | Library |
|----------|-----------|---------|
| macOS | libs/macos | libraylib.5.5.0.dylib |
| Linux 64-bit | libs/linux_amd64 | libraylib.so.5.5.0 |
| Linux 32-bit | libs/linux_i386 | libraylib.a |
| Windows 64-bit | libs/win64_msvc16 | raylib.dll |
| Windows 32-bit | libs/win32_msvc16 | raylib.dll |

The correct library is loaded automatically based on your operating system.

### macOS Code Signing

On macOS, you might see a security warning about the library. You can fix this by signing it locally:

```bash
codesign --force --sign - libs/macos/libraylib.5.5.0.dylib
```

## Technical Details

### Why JDK 22?

This project uses coffi for calling native C code from Clojure. The newer versions of coffi require JDK 22 because that is when the Foreign Function and Memory API became stable (it was in preview in earlier versions).

### macOS Threading

OpenGL on macOS requires the main thread for rendering. The project.clj file includes the JVM flag -XstartOnFirstThread to handle this automatically.

## License

EPL-2.0
