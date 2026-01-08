(ns raylib-ext
  "Extended raylib bindings for missing functions"
  (:require
   [raylib.core]
   [raylib.structs :as rs]
   [coffi.mem :as mem :refer [defalias]]
   [coffi.ffi :refer [defcfn]]))

;; Missing struct definition
(defalias ::rectangle
  [::mem/struct
   [[:x ::mem/float]
    [:y ::mem/float]
    [:width ::mem/float]
    [:height ::mem/float]]])

;; Drawing functions
(defcfn draw-circle!
  "Draw a color-filled circle"
  {:arglists '([center-x center-y radius color])}
  "DrawCircle"
  [::mem/int ::mem/int ::mem/float ::rs/color] ::mem/void)

(defcfn draw-triangle!
  "Draw a color-filled triangle (vertex in counter-clockwise order!)"
  {:arglists '([v1 v2 v3 color])}
  "DrawTriangle"
  [::rs/vector-2 ::rs/vector-2 ::rs/vector-2 ::rs/color] ::mem/void)

;; Text functions
(defcfn measure-text
  "Measure string width for default font"
  {:arglists '([text font-size])}
  "MeasureText"
  [::mem/c-string ::mem/int] ::mem/int)

;; Collision detection
(defcfn check-collision-circle-rec?
  "Check collision between circle and rectangle"
  {:arglists '([center radius rec])}
  "CheckCollisionCircleRec"
  [::rs/vector-2 ::mem/float ::rectangle] ::mem/byte)

(defcfn check-collision-point-circle?
  "Check if point is inside circle"
  {:arglists '([point center radius])}
  "CheckCollisionPointCircle"
  [::rs/vector-2 ::rs/vector-2 ::mem/float] ::mem/byte)

(defcfn check-collision-circles?
  "Check collision between two circles"
  {:arglists '([center1 radius1 center2 radius2])}
  "CheckCollisionCircles"
  [::rs/vector-2 ::mem/float ::rs/vector-2 ::mem/float] ::mem/byte)
