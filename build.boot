(set-env!
  :source-paths #{"advent_of_code/src"}
  :dependencies '[[org.clojure/clojure "1.8.0"]
                  [proto-repl "0.3.1"]
                  [proto-repl-charts "0.3.1"]])


(defn day-0 [args] (apply (resolve 'Day0/-main) args))
(defn day-1 [args] (apply (resolve 'Day1/-main) args))
(defn day-2 [args] (apply (resolve 'Day2/-main) args))


(deftask dev
  "Setting dev for atom console to run REPL"
  []
  (repl))

(deftask run
  "Running a main entry point to start all the other minor apps"
  [a args ARG [str] "the arguments for the application."]
  (require 'Day0)
  (require 'Day1)
  (require 'Day2)
  (day-0 args)
  (day-1 args)
  (day-2 args))
