(ns task01.core
  (:require [pl.danieljanus.tagsoup :refer :all])
  (:gen-class))

(defn traverse-and-collect [data acc]
  (reduce (fn [acc' v]
            (if (and (= :a (first v))
                     (= "l" (:class (get v 1))))
              (conj acc'
                    (:href (get v 1)))
              (traverse-and-collect v acc')))
          acc
          (filter vector? data)))

(defn get-links []
" 1) Find all elements containing {:class \"r\"}.

Example:
[:h3 {:class \"r\"} [:a {:shape \"rect\", :class \"l\",
                         :href \"https://github.com/clojure/clojure\",
                         :onmousedown \"return rwt(this,'','','','4','AFQjCNFlSngH8Q4cB8TMqb710dD6ZkDSJg','','0CFYQFjAD','','',event)\"}
                     [:em {} \"clojure\"] \"/\" [:em {} \"clojure\"] \" · GitHub\"]]

   2) Extract href from the element :a.

The link from the example above is 'https://github.com/clojure/clojure'.

  3) Return vector of all 10 links.

Example: ['https://github.com/clojure/clojure', 'http://clojure.com/', . . .]
"
  (let [data (parse "clojure_google.html")]
    (traverse-and-collect data [])))

(defn -main []
  (println (str "Found " (count (get-links)) " links!")))


