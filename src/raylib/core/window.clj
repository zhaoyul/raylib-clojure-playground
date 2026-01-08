(ns raylib.core.window
  (:require
   [raylib.core]
   [raylib.internals :as ri]
   [coffi.mem :as mem]
   [coffi.ffi :refer [defcfn]]))

(defcfn init-window!
  "Initialize window and OpenGL context"
  {:arglists '([width height title])}
  "InitWindow"
  [::mem/int ::mem/int ::mem/c-string] ::mem/void)

(defcfn window-should-close?
  "Check if KEY_ESCAPE pressed or Close icon pressed"
  "WindowShouldClose"
  [] ::ri/bool)

(defcfn close-window!
  "Close window and unload OpenGL context"
  "CloseWindow" [] ::mem/void)

(defcfn is-window-ready?
  "Check if window has been initialized successfully"
  "IsWindowReady"
  [] ::ri/bool)

(defcfn is-window-fullscreen?
  "Check if window is currently fullscreen"
  "IsWindowFullscreen"
  [] ::ri/bool)

(defcfn is-window-hidden?
  "Check if window is currently hidden (only PLATFORM_DESKTOP)"
  "IsWindowHidden"
  [] ::ri/bool)

(defcfn is-window-minimized?
  "Check if window is currently minimized (only PLATFORM_DESKTOP)"
  "IsWindowMinimized"
  [] ::ri/bool)

(defcfn is-window-maximized?
  "Check if window is currently maximized (only PLATFORM_DESKTOP)"
  "IsWindowMaximized"
  [] ::ri/bool)

(defcfn is-window-focused?
  "Check if window is currently focused (only PLATFORM_DESKTOP)"
  "IsWindowFocused"
  [] ::ri/bool)

(defcfn is-window-resized?
  "Check if window has been resized last frame"
  "IsWindowResized"
  [] ::ri/bool)

; ...

(defcfn get-screen-width
  "Get current screen width"
  "GetScreenWidth"
  [] ::mem/int)

(defcfn get-screen-height
  "Get current screen height"
  "GetScreenHeight"
  [] ::mem/int)

(defcfn get-render-width
  "Get current render width (considers HiDPI)"
  "GetRenderWidth"
  [] ::mem/int)

(defcfn get-render-height
  "Get current render height (considers HiDPI)"
  "GetRenderHeight"
  [] ::mem/int)

(defcfn set-config-flags!
  "Setup init configuration flags (view FLAGS)"
  {:arglists '([flags])}
  "SetConfigFlags"
  [::mem/int] ::mem/void)

(defcfn toggle-fullscreen!
  "Toggle window state: fullscreen/windowed (only PLATFORM_DESKTOP)"
  "ToggleFullscreen"
  [] ::mem/void)

(defcfn toggle-borderless-windowed!
  "Toggle window state: borderless windowed (only PLATFORM_DESKTOP)"
  "ToggleBorderlessWindowed"
  [] ::mem/void)

(defcfn set-window-size!
  "Set window dimensions"
  {:arglists '([width height])}
  "SetWindowSize"
  [::mem/int ::mem/int] ::mem/void)

(defcfn get-current-monitor
  "Get current connected monitor"
  "GetCurrentMonitor"
  [] ::mem/int)

(defcfn get-monitor-width
  "Get specified monitor width (current video mode used by monitor)"
  {:arglists '([monitor])}
  "GetMonitorWidth"
  [::mem/int] ::mem/int)

(defcfn get-monitor-height
  "Get specified monitor height (current video mode used by monitor)"
  {:arglists '([monitor])}
  "GetMonitorHeight"
  [::mem/int] ::mem/int)

; ...
