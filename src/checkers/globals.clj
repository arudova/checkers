(ns checkers.globals
  (:require [seesaw.core :as sc]))

(def sqaures (atom []))
(def men (atom []))

(def window-width 640)
(def window-height 640)
(def square-size 80.0)
(def man-radius 30.0)
(def main-frame (sc/frame :title "Checkers"
                          :on-close :exit
                          :size [window-width :by window-height]))
(def current-man (atom {:pos-x 0 :pos-y 0 :color color}))
(def next-to-play (atom {:color color}))
(def playing? (atom false))
(def men-count (atom 0))
(def world (atom {:x 0 :y 0 :width 0 :height 0}))
