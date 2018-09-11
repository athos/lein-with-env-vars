# lein-with-env-vars
[![Clojars Project](https://img.shields.io/clojars/v/lein-with-env-vars.svg)](https://clojars.org/lein-with-env-vars)

A Leiningen plugin for performing a task with environment variable settings loaded from project.clj

## Install

Add the following line into the `:plugins` vector of an appropriate profile.

[![Clojars Project](https://clojars.org/lein-with-env-vars/latest-version.svg)](https://clojars.org/lein-with-env-vars)

## Usage

First, add `:env-vars` map to specify the environment variable settings in project.clj like the following:

```clj
(defproject foo-bar

  ...

  :env-vars {:ENV_VAR_FOO "foo/bar/baz"}

  ...  )
```

And then, you can run a task with `with-env-vars` to set the environment variables according to the settings:

    $ lein with-env-vars repl
    ...
    user=> (System/getenv "ENV_VAR_FOO")
    "foo/bar/baz"
    user=>

You can also specify a string or a vector of strings instead of a map to the `:env-vars` key.
In that case, those will be interpreted as the name of files containing environment variable settings.

For instance, say you have a file named `.env-vars` whose content is as follows:

    ```clj
    {:ENV_VAR_FOO "foo/bar/baz"}
    ```

And if you specify `[".env-vars"]` to the `:env-vars` key in `project.clj`,
then `lein with-env-vars repl` will work exactly as the above example:

```clj
(defproject foo-bar

  ...

  :env-vars [".env-vars"]

  ...  )
```

See also [the example project](examples/example) to see how to use the plugin in a practical project.

## Why not use Environ?

[Environ](https://github.com/weavejester/environ) (or lein-environ) is another tool for managing environment settings.

Though it supports environment variables as one of its setting sources, Environ does **NOT** actually set environment variables when starting a task process. So, for example, if you want to use a library that requires a certain environment variable to be set, it wouldn't work suitably.

## License

Copyright © 2016 OHTA Shogo

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
