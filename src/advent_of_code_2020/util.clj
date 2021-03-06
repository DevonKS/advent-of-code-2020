(ns advent-of-code-2020.util
  (:require [clojure.java.io :as io]))

(defn challenge-filename
  [day]
  (format "day%02d.txt" day))

(defn read-challenge-file!
  [day]
  (some-> (challenge-filename day)
          io/resource
          slurp))

(defn challenge-file-lines!
  "Returns a lazy sequence of the challenge file lines."
  [day]
  (some-> (challenge-filename day)
          io/resource
          io/reader
          line-seq))

(defn mapping
  [key-fn val-fn coll]
  (persistent!
   (reduce
    (fn [m x] (assoc! m (key-fn x) (val-fn x)))
    (transient {})
    coll)))

(defn map-to
  [val-fn coll]
  (mapping identity val-fn coll))

(defn map-by
  [key-fn coll]
  (mapping key-fn identity coll))

(defn boolean->bit
  [b]
  (if b 2r1 2r0))

(defn xor
  "This is a terrible way to implement xor but it's interesting and it works."
  [x y]
  (= 1 (bit-xor (boolean->bit x) (boolean->bit y))))

(defn first-match
  [pred coll]
  (first (filter pred coll)))

(defn count-if
  [pred coll]
  (count (filter pred coll)))

(defn parse-int
  [x]
  (try (Integer/parseInt x)
       (catch Exception _ nil)))

(defn parse-long
  [x]
  (try (Long/parseLong x)
       (catch Exception _ nil)))
