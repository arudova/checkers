(ns checkers.logic.main
  (:use [checkers.logic moving
                        level]
        [checkers.gui.main]
        [seesaw.core])
  (:require [checkers.globals :as gl]))

(defn lost?
  []
  (<= @gl/white-men-count 0))

(defn won?
  []
  (<= @gl/black-men-count 0))

(defn reset-world
  []
  (dosync
    (reset! gl/white-squares [])
    (reset! gl/black-squares [])
    (reset! gl/white-men [])
    (reset! gl/black-men [])
    (reset! gl/white-men-count 0)
    (reset! gl/black-men-count 0)
    (reset! gl/world {:x 0 :y 0 :width 0 :height 0})))

(defn load-level-01
  []
  (load-level world
              paths
              defend-points
              build-areas
              spawners
              lives
              funds
              time-limit))

(defn play
  []
  (load-level squares men men-count)
  (create-gui)
  (future (redraw))
  (loop []
    (when (won?)
      (reset! gl/playing? false)
      (alert "You win!")
      (reset-world)
      (load-level squares men men-count))
    (when (lost?)
      (reset! gl/playing? false)
      (alert "Losers gonna lose!")
      (reset-world)
      (load-level squares men men-count))
    (when @gl/playing?
      (println "Hello, World!"))
    (Thread/sleep 6)
    (recur)))
