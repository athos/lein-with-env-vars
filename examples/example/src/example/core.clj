(ns example.core)

(defn -main []
  (printf "Hello, %s!\n" (System/getenv "EXAMPLE_NAME")))
