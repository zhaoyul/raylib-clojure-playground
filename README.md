# Raylib Clojure Playground

Experiments with [Raylib](https://www.raylib.com/) game development library in Clojure, using [raylib-clj](https://github.com/kiranshila/raylib-clj).

## Prerequisites

- **JDK 22+** (required for the modern Foreign Function API)
- **Leiningen** build tool

### Installing JDK 22+

If you don't have JDK 22+, you can install it via:

**macOS:**
```bash
brew install openjdk@22
```

**Or use [SDKMAN](https://sdkman.io/):**
```bash
sdk install java 22.0.2-open
```

## Setup

1. **Clone this repository:**
   ```bash
   git clone https://github.com/yourusername/raylib-clojure-playground.git
   cd raylib-clojure-playground
   ```

2. **Clone raylib-clj dependency:**
   ```bash
   git clone https://github.com/kiranshila/raylib-clj.git vendor/raylib-clj
   cd vendor/raylib-clj && git checkout 71ea1997b5e7d49bfeb1b497cc4dc4079f08f0ee
   cd ../..
   ```

3. **Sign the bundled library (macOS only):**
   ```bash
   codesign --force --sign - vendor/raylib-clj/libs/libraylib.5.5.0.dylib
   ```

## Bundled Raylib Library

This project includes a bundled raylib 5.5 library for macOS (arm64/x64). The library is automatically loaded from `vendor/raylib-clj/libs/` based on your OS:

| OS      | Library File              |
|---------|---------------------------|
| macOS   | `libraylib.5.5.0.dylib`   |
| Linux   | `libraylib.so.5.5.0`      |
| Windows | `raylib.dll`              |

If the bundled library is not found, it falls back to the system-installed raylib.

### Adding Libraries for Other Platforms

To add support for Linux or Windows:
1. Download or compile raylib for your platform
2. Place the library in `vendor/raylib-clj/libs/`
3. Name it according to the table above

## Running

Make sure to set `JAVA_HOME` to JDK 22+ before running:

```bash
# Set JAVA_HOME (adjust path for your system)
export JAVA_HOME=/path/to/jdk-22

# Run the hello world example
lein run

# Or run a specific example
lein run -m examples.hello-world
lein run -m examples.pong
lein run -m examples.asteroids
```

**macOS example:**
```bash
JAVA_HOME=/Users/$(whoami)/Library/Java/JavaVirtualMachines/openjdk-22.0.2/Contents/Home lein run
```

## REPL Development

```bash
JAVA_HOME=/path/to/jdk-22 lein repl
```

Then in the REPL:
```clojure
(start)  ; Start the game loop
```

## Debug Stats Overlay

All examples include a debug stats overlay. Press **F1** to toggle it on/off.

The overlay shows:
- FPS and frame timing
- JVM memory usage  
- Frame count
- Custom game-specific stats

To add debug stats to your own game, see `src/debug_stats.clj`.

## Examples

- [Hello World](./src/examples/hello_world.clj) - Basic window with text
- [Pong](./src/examples/pong.clj) - Classic Pong game
- [Asteroids](./src/examples/asteroids.clj) - Asteroids clone

## Controls

### Hello World
- **Q** - Exit
- **F1** - Toggle debug stats

### Pong
- **ENTER** - Start game
- **W/S** - Player 1 paddle (left)
- **K/J** - Player 2 paddle (right)
- **F1** - Toggle debug stats

### Asteroids
- **ENTER** - Start game
- **Arrow Keys** - Rotate (LEFT/RIGHT), Thrust (UP/DOWN)
- **SPACE** - Shoot / Restart when dead
- **F1** - Toggle debug stats

## Technical Notes

### Why JDK 22+?

This project uses [coffi](https://github.com/IGJoshua/coffi) for Clojure FFI bindings. The newer coffi (1.0.x) requires JDK 22+ as it uses the stable `java.lang.foreign` API (graduated from incubator status).

### macOS Specific

The `-XstartOnFirstThread` JVM option is required on macOS for OpenGL/GLFW applications. This is already configured in `project.clj`.

### Code Signing (macOS)

If you get a "code signature not valid" error, re-sign the bundled library:
```bash
codesign --force --sign - vendor/raylib-clj/libs/libraylib.5.5.0.dylib
```

## Project Structure

```
├── project.clj              # Leiningen project file
├── src/
│   ├── debug_stats.clj      # Debug overlay plugin
│   ├── raylib_ext.clj       # Extended raylib FFI bindings
│   ├── native_loader.clj    # Native library loader
│   └── examples/
│       ├── hello_world.clj
│       ├── pong.clj
│       └── asteroids.clj
└── vendor/
    └── raylib-clj/          # raylib-clj library (git clone)
        ├── src/raylib/      # Clojure bindings
        └── libs/            # Bundled native libraries
```

## Built With

- Clojure 1.11.1
- Raylib 5.5
- [raylib-clj](https://github.com/kiranshila/raylib-clj) - Clojure bindings for Raylib
- [coffi](https://github.com/IGJoshua/coffi) 1.0.615 - Clojure Foreign Function Interface

## License

EPL-2.0
