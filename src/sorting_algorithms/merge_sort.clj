(ns sorting-algorithms.merge-sort
  "Sorting using merge sort algorithm.")

(defn explode
  "Explodes an vecotr with one or many items into multiples single item vectors."
  [items]
  (for [i items] [i]))

(defn merge-sort
  "Merges and sorts two arrays already sorted in reverse order."
  [items1 items2]
  (let [head1 (first items1)
        head2 (first items2)]
    (cond
     (empty? items1) items2
     (empty? items2) items1
     (< head1 head2) (cons head2 (merge-sort items1 (drop 1 items2)))
     :else (cons head1 (merge-sort (drop 1 items1) items2)))))

(defn process
  [items]
  (if (empty? (rest items))
    (first items)
    (->>  items
          (partition 2 2 [])
          (map #(merge-sort (first %) (second %)))
          process)))

(defn rsort
  "Sorts in reverse using the merge sort algorithm.
  Example:
  [1  4  2  3 ]
  [1] [4] [2] [3]
  [4 1]   [3 2]
  [4* _  _  _ ]
  [4* 3* _  _ ]
  [4* 3* 2* _ ]
  [4* 3* 2* 1*]"
  [items]
  (process (explode items)))