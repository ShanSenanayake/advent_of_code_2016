(ns Day4)

(def input (clojure.string/split-lines (slurp "advent_of_code/inputs/day4.input")))

(defn parse-line [line]
  (rest (re-matches #"(^[^\d]*)(\d+)\[([a-zA-Z]+)\]$" line)))

(defn parse-input [input]
  (map parse-line input))

(defn filter-dashes [string]
  (filter (fn [x] (not= x \- )) string))

(defn mmap [f m]
  (into {} (for [[k v] m] [k (f v)])))

(defn create-common-map [list]
  (mmap count (group-by identity list)))

(defn compare-size-lexi [[k1 v1] [k2 v2]]
  (if (= v1 v2) (compare k1 k2) (compare v2 v1)))

(defn get-most-common [string]
  (take 5 (sort compare-size-lexi (create-common-map (filter-dashes string)))))

(defn decrypt [[encrypted id checksum]]
  [ encrypted
    (apply str (map first (get-most-common encrypted)))
    (Integer. id)
    checksum])

(defn not-decoy? [[a b c d]]
  (= b d))

(defn remove-decoys [list]
  (filter not-decoy? (map decrypt list)))

(defn -main
  "Fourth task of advent of code"
  [& args]
  (println "Day 4. Security Through Obscurity")
  (printf "##Answer: sum of valid checksums %d\n"
    (apply + (for [[a b c d] (remove-decoys (parse-input input))] c))))
