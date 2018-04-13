# example

This is an example project to demonstrate how to use the `lein-with-env-vars` plugin.

## Usage

Type in:

```sh
$ lein with-env-vars run -m example.core
```

Then you'll see the result message `Hello, Rich Hickey!`.

You can modify the message by changing the value for `:EXAMPLE_NAME` key of the `:env-vars` map located in the project.clj.

## License

Copyright © 2018 Shogo Ohta

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
