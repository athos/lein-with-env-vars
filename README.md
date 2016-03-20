# lein-with-env-vars

A Leiningen plugin for performing a task with environment variable settings loaded from project.clj

## Usage

First, put the spec below into the `:plugins` vector of an appropriate profile.

[![Clojars Project](https://img.shields.io/clojars/v/lein-with-env-vars.svg)](https://clojars.org/lein-with-env-vars)

And add `:env-vars` map to specify the environment variable settings in project.clj like the following:

```clj
(defproject foo-bar

  ...

  :env-vars {:ENV_VAR_FOO "foo/bar/baz"}

  ...  )
```

Then, you can run a task with `with-env-vars` to set the environment variables according to the settings:

    $ lein with-env-vars repl
    ...
    user=> (System/getenv "ENV_VAR_FOO")
    "foo/bar/baz"
    user=>

## Why not use Environ?

[Environ](https://github.com/weavejester/environ) (or lein-environ) is another tool for managing environment settings.

Though it supports environment variables as one of its setting sources, Environ does **not** actually set environment variables when staring a task process. So, for example, you want to use a library that requires a certain environment variable to be set, it won't work suitably.

## License

Copyright © 2016 OHTA Shogo

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
