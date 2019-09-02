# lein-with-env-vars
[![Clojars Project](https://img.shields.io/clojars/v/lein-with-env-vars.svg)](https://clojars.org/lein-with-env-vars)

A Leiningen plugin for performing a task with environment variable settings loaded from project.clj

## Installation

Add the following line into the `:plugins` vector of an appropriate profile.

[![Clojars Project](https://clojars.org/lein-with-env-vars/latest-version.svg)](https://clojars.org/lein-with-env-vars)

## Usage

First, add `:env-vars` map to specify the environment variable settings in your `project.clj`
like the following:

```clj
(defproject foo-bar

  ...

  :env-vars {:ENV_VAR_FOO "foo/bar/baz"}

  ...  )
```

And then, run a task with `with-env-vars` to set the environment variables according to the settings:

```clj
$ lein with-env-vars repl
...
user=> (System/getenv "ENV_VAR_FOO")
"foo/bar/baz"
user=>
```

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

### Leiningen hooks

This plugin also provides Leiningen hooks to inject environment variables automatically.

To enable the hook, add `leiningen.with-env-vars/auto-inject` to `:hooks` key:

```clj
(defproject foo-bar

  ...

  :hooks [leiningen.with-env-vars/auto-inject]

  ... )
```

Once you specify the hook, no need to use the `with-env-vars` higher-order task explicitly.
Every time you run a Leiningen task, the specified environment variables will be injected automatically.

**Note** This hook mechanism is useful, but could also be problematic in some cases.
If the hook is enabled and a file name is specified to the `:env-vars` key, the hook always
checks whether the file exists (and if not, it will fail) every time you run a Leiningen task.

See also [the example project](examples/example) to see how to use the plugin in a practical project.

## Why not use Environ?

[Environ](https://github.com/weavejester/environ) (or lein-environ) is another tool for managing environment settings.

Though it supports environment variables as one of its setting sources, Environ does **NOT** actually set environment variables when starting a task process. So, for example, if you want to use a library that requires a certain environment variable to be set, it wouldn't work suitably.

## License

Copyright © 2016 OHTA Shogo

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
