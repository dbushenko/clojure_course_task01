(ns task01.core
  (:require [pl.danieljanus.tagsoup :refer :all])
  (:gen-class))


(defn get-links []
(let [data (vec (parse "clojure_google.html"))]
(defn get-class-r [data accum]
  (reduce #(if (coll? %2)
                (if-not (nil? (some #{{:class "r"}} %2))
                    [(get %2 2)]
                    (reduce conj % (get-class-r %2 %))))
          accum data))
(map #(get-in % [1 :href]) (get-class-r data []))))

(defn -main []
  (println (str "Found " (count (get-links)) " links!")))