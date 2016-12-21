(ns Day3)

(def input (clojure.string/split-lines (slurp "advent_of_code/inputs/day3.input")))


(defn compare-sides [[x y z]]
  (if (< x (+ y z)) 1 0))

(defn check-valid-triangle [triangle]
  (compare-sides (sort > triangle)))

(defn sum-valid-triangles [sum triangle]
  (+ sum (check-valid-triangle triangle)))


(defn string-to-int-list [string]
  (map read-string (clojure.string/split (clojure.string/trim string) #" +")))

(defn parse-input [input]
  (map string-to-int-list input))

(defn transpose [ls]
  (apply map list ls))

(defn transform [ls]
  (partition 3(flatten (transpose ls))))

(defn -main
  "Third task of advent of code"
  [& args]
  (println "Day 3. Squares With Three Sides")
  (printf "##Answer: Number of valid triangles %d\n"
    (reduce sum-valid-triangles 0 (parse-input input)))
  (println "Part 2")
  (printf "##Answer: Number of valid triangles %d\n"
    (reduce sum-valid-triangles 0 (transform (parse-input input)))))
