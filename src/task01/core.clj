(ns task01.core
  (:require [pl.danieljanus.tagsoup :refer :all])
  (:gen-class))


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
    (letfn [(find-r [acc x]
              (cond (not (and (vector? x) (map? (x 1)))) acc
                    (= "r" (:class (x 1))) (conj acc x)
                    :else (doall (reduce find-r acc (subvec x 2)))))
            (extract-href [x]
              (:href ((x 2) 1)))]
      (map extract-href (find-r [] data)))))

(defn -main []
  (println (str "Found " (count (get-links)) " links!")))

  