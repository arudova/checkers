(ns checkers.logic.level
  (:use [checkers.globals])
  (:require [checkers.globals :as gl]))

(defn define-square
  [x y width height color]
  {:x x :y y :width width :height height :color color})

(defn define-man
  [x y radius color]
  {:x x :y y :radius radius :color color})

(defn merge-defined
  [& args]
  (vec (apply concat args)))

(defn- load-squares
  [squares]
  (doseq [square squares]
    (swap! gl/squares conj square)))

(defn- load-men
  [men]
  (doseq [man men]
    (swap! gl/men conj man)))

(defn- load-men-count
  [men-count]
  (reset! gl/men-count men-count))

(def men-count 12)

(defn generate-texture-maps
  ([first-x-start first-y-start second-x-start second-y-start]
    (into
      (for [x (range first-x-start 640 160)
            y (range first-y-start 640 160)]
        [x y])
      (for [x (range second-x-start 640 160)
            y (range second-y-start 640 160)]
        [x y])))
  ([first-x-start first-y-start second-x-start second-y-start end-of-red-men]
    (into
      (for [x (range first-x-start 640 160)
            y (range first-y-start end-of-red-men 160)]
        [x y])
      (for [x (range second-x-start 640 160)
            y (range second-y-start end-of-red-men 160)]
        [x y]))))

(defn squares [first-x-start
               first-y-start
               second-x-start
               second-y-start
               color]
  (for [[x y] (generate-texture-maps first-x-start
                                     first-y-start
                                     second-x-start
                                     second-y-start)]
    (define-square x y 80 80 color)))

(defn men [first-x-start
           first-y-start
           second-x-start
           second-y-start
           color
           end-of-red-men]
  (for [[x y] (generate-texture-maps first-x-start
                                     first-y-start
                                     second-x-start
                                     second-y-start
                                     end-of-red-men)]
    (define-man x y 30 color)))

(def white-squares (squares 0 0 80 80 "#FFFFFF"))

(def black-squares (squares 80 0 0 80 "#000000"))

(def white-men (men 40 440 120 520 "#FFFFFF" 640))

(def red-men (men 120 40 40 120 "#FF0000" 240))

(def squares (merge-defined white-squares black-squares))

(def men (merge-defined white-men red-men))

(defn load-level
  [squares
   men
   men-count]
  (load-squares squares)
  (load-men men)
  (load-men-count men-count))
