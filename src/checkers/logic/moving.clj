(ns checkers.logic.moving
  (:use [checkers.gui.geometry])
  (:require [checkers.globals :as gl]))

(defn- move)

(defn- jump-over)

(defn- can-move?)

(defn move-or-jump-over)

(defn kill-enemy
  [enemy]
  (swap! gl/enemies disj enemy))

(defn move-enemy
  [enemy x y]
  (dosync
    (alter enemy update-in [:x] #(+ % x))
    (alter enemy update-in [:y] #(+ % y))))