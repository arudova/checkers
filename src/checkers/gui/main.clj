(ns checkers.gui.main
  (:use [seesaw core color graphics behave]
        [checkers.logic.main :only [move-or-jump-over]])
  (:require [seesaw.mouse :as mouse]
            [checkers.globals :as gl]))

(defn display [fr content]
  (config! fr :content content)
  content)

(defn draw-squares
  [g]
  (doseq [square @gl/squares]
    (let [{:keys [x y width height color]} square]
      (draw g
            (rect x y width height)
            (style :background color)))))

(defn draw-men
  [g]
  (doseq [man @gl/men]
    (let [{:keys [x y radius color]}  man]
      (draw g
            (circle x y adius)
            (style :background color)))))

(defn draw-world
  [c g]
  (draw-squares g)
  (draw-men g)

(defn make-panel []
  (border-panel
    :center (canvas :paint draw-world
                    :class :world
                    :background :black
                    :cursor :hand)))

(defn redisplay
  [root]
    (dosync
      (config! (select root [:.world]) :paint draw-world)))

(defn listeners
  []
  (listen (select gl/main-frame [:.world])
          :mouse-clicked
          (fn [e] (let [[x y] (mouse/location e)]
                    (move-or-jump-over x y)))))

(defn create-gui
  []
  (-> gl/main-frame show!)
  (display gl/main-frame (make-panel))
  (listeners))

(defn redraw
  []
  (redisplay gl/main-frame))
