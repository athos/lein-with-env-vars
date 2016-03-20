(ns leiningen.with-env
  (:require [leiningen.core
             [main :as main]
             [eval :as eval]]))

(defn ^:no-project-needed ^:higher-order with-env
  [project task-name & args]
  (try
    (let [task-name (main/lookup-alias task-name project)]
      (binding [eval/*env* (:jvm-env project {})]
        (main/apply-task task-name project args)))
    (catch Exception e
      (main/info (format "Error encountered performing task '%s'" task-name))
      (if (and (:exit-code (ex-data e)) (not main/*debug*))
        (main/info (.getMessage e))
        (.printStackTrace e)))))
