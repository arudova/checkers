(ns checkers.level_test
  (:use [clojure.test]
        [checkers.logic.level])
  (:require [checkers.globals :as gl]))

(deftest generate-texture-maps-test
  (is (= [[620 620] [620 460] [460 620] [460 460] [470 470] [470 630] [630 470] [630 630]]
         (into [] (generate-texture-maps 470 470 460 460)))))

(deftest define-squares
  (is (= [{:x 620, :y 620, :width 80, :height 80, :color "#FFFFFF"}
          {:x 620, :y 460, :width 80, :height 80, :color "#FFFFFF"}
          {:x 460, :y 620, :width 80, :height 80, :color "#FFFFFF"}
          {:x 460, :y 460, :width 80, :height 80, :color "#FFFFFF"}
          {:x 470, :y 470, :width 80, :height 80, :color "#FFFFFF"}
          {:x 470, :y 630, :width 80, :height 80, :color "#FFFFFF"}
          {:x 630, :y 470, :width 80, :height 80, :color "#FFFFFF"}
          {:x 630, :y 630, :width 80, :height 80, :color "#FFFFFF"}]
         (into [] (squares 460 460 470 470 "#FFFFFF")))))

(deftest define-men-test
  (is (= [{:x 600, :y 520, :radius 30, :color "FFFFFF"}
          {:x 420, :y 440, :radius 30, :color "FFFFFF"}
          {:x 420, :y 600, :radius 30, :color "FFFFFF"}
          {:x 580, :y 440, :radius 30, :color "FFFFFF"}
          {:x 580, :y 600, :radius 30, :color "FFFFFF"}]
         (into [] (men 420 440 600 520 "FFFFFF" 640)))))
