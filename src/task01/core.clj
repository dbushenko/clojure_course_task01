(ns task01.core
  (:require [pl.danieljanus.tagsoup :refer :all])
  (:gen-class))


(defn get-links []
  (let [data (vec (parse "clojure_google.html"))]
;; defn определяет "top level" объект, так что лучше ее вынести за пределы функции 
;; (get-links...)
;; если же имеется необходимость определить функцию внутри другой функции, то можно воспользоваться (let [func (fn [...))
;; или letfn.
    (defn get-class-content [data accum]
;; при использовании reduce, для читабельности удобней пользоваться (fn [arg1 arg2] - тогда легче будет понимать код
      (reduce #(if (coll? %2)
               (if-not (nil? (some #{{:class "r"}} %2))
                  [(get %2 2)]
                  (reduce conj % (get-class-content %2 %))))
      accum data))
    (map #(get-in % [1 :href]) (get-class-content data []))))

(defn -main []
  (println (str "Found " (count (get-links)) " links!")))
