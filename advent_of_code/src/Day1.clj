(ns Day1)

(def input "L5, R1, L5, L1, R5, R1, R1, L4, L1, L3, R2, R4, L4, L1, L1, R2, R4, R3, L1, R4, L4, L5, L4, R4, L5, R1, R5, L2, R1, R3, L2, L4, L4, R1, L192, R5, R1, R4, L5, L4, R5, L1, L1, R48, R5, R5, L2, R4, R4, R1, R3, L1, L4, L5, R1, L4, L2, L5, R5, L2, R74, R4, L1, R188, R5, L4, L2, R5, R2, L4, R4, R3, R3, R2, R1, L3, L2, L5, L5, L2, L1, R1, R5, R4, L3, R5, L1, L3, R4, L1, L3, L2, R1, R3, R2, R5, L3, L1, L1, R5, L4, L5, R5, R2, L5, R2, L1, L5, L3, L5, L5, L1, R1, L4, L3, L1, R2, R5, L1, L3, R4, R5, L4, L1, R5, L1, R5, R5, R5, R2, R1, R2, L5, L5, L5, R4, L5, L4, L4, R5, L2, R1, R5, L1, L5, R4, L3, R4, L2, R3, R3, R3, L2, L2, L2, L1, L4, R3, L4, L2, R2, R5, L1, R2")


(def direction-list
  "List to represent direction, current direction is first element"
  [:n :e :s :w])

(def start-path
  "Denotes the current path taken"
  [direction-list {:n 0 :e 0 :s 0 :w 0 }])

(defn turn-right [list]
  (concat
    (rest list)
    [(first list)]))

(defn turn-left [list]
  (concat
    [(last list)]
    (drop-last list)))

(defn decide-direction [direction path]
  ((get {:L turn-left :R turn-right} direction) path))

(defn walk-forwards [direction distance current-path]
  (assoc
    current-path
    direction
    (+ distance (get current-path direction))))

(defn take-turn [path pair]
  (let [current-direction
    (decide-direction
      (first pair)
      (first path))]
    [current-direction
     (walk-forwards
      (first current-direction)
      (last pair)
      (last path))]))

(defn get-full-path [start-path command-list]
  (reduce take-turn start-path command-list))

(defn find-shortest-distance [full-path]
  (Math/abs (-
    (+ (get full-path :n) (get full-path :e))
    (+ (get full-path :s) (get full-path :w))
    )))

(defn char-to-keyword [char]
  (keyword (str char)))

(defn string-to-list [string]
  [(char-to-keyword (first string))
   (Integer. (apply str (rest string)))])

(defn parse-input [input]
  (map string-to-list (clojure.string/split input #", ")))


(defn -main
  "First task of advent of code"
  [& args]
  (println "Day 1. No time for a Taxicab")
  (printf "## Answer: distance is %d\n" (find-shortest-distance
    (last
      (get-full-path start-path (parse-input input))))))
