(ns leiningen.with-env-vars
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [leiningen.core.eval :as eval]
            [leiningen.core.main :as main]))

(defn- read-env-vars [file]
  (edn/read-string (slurp (io/file file))))

(defn- collect-env-vars [{:keys [env-vars] :or {env-vars {}}}]
  (cond (string? env-vars)
        (read-env-vars env-vars)

        (vector? env-vars)
        (->> (map read-env-vars env-vars)
             (reduce merge {}))

        :else env-vars))

(defn ^:no-project-needed ^:higher-order with-env-vars
  "Perform a task with environment variable settings loaded from project.clj.

Specify your environment variables with :env-vars key in the project.clj.
If you instead specify a string or vector of strings to that key, they will be
viewed as the name of files containing environment variable settings."
  [project task-name & args]
  (try
    (let [task-name (main/lookup-alias task-name project)]
      (binding [eval/*env* (collect-env-vars project)]
        (main/apply-task task-name project args)))
    (catch Exception e
      (main/info (format "Error encountered performing task '%s'" task-name))
      (if (and (:exit-code (ex-data e)) (not main/*debug*))
        (main/info (.getMessage e))
        (.printStackTrace e)))))
