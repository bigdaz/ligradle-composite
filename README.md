# ligradle-composite
Demonstration of composite build used to develop plugins with cyclic dependencies. The `ligradle-jvm` plugin project 
has a buildscript dependency on an older version of itself.

To make testing of this repository easier, the `ligradle-jvm-0.1` build is separate from the under-development `ligradle-jvm`
project. Note that the `ligradle-jvm` plugin adds a dummy 'precompile' task to any Java project.

## Development without a composite

To build these projects without a composite:

1. Execute `gradle publish` in the `ligradle-jvm-0.1` build. This publishes `ligradle-jvm` to a local file repository.
2. Execute `gradle publish` in the `ligradle-core` build. This consumes `ligradle-jvm-0.1` and publishes `ligradle-core`.
3. Execute `gradle publish` in the `ligradle-jvm` build. This consumes `ligradle-jvm-0.1` as a buildscript dependency,
and `ligradle-core-0.2` as a compile dependency. A new version of the plugin `ligradle-jvm-0.2` is published.

You could then make changes to `ligradle-core`, publish (step 2), and make corresponding changes to `ligradle-jvm`
before building (step 3).

## Development with a composite

The `ligradle-composite` build is a test application that consumes the `ligradle-jvm-0.2` plugin. This can be used to
make and test changes in `ligradle-jvm` and `ligradle-core`.

To see this in action:

1. Execute `gradle run` in the `ligradle-composite` build. You'll see the plugin projects being built as 
part of configuring the build.
2. Execute `gradle idea` in the `ligradle-composite` build. The generated IDEA project allows refactoring of `ligradle-core`
and `ligradle-jvm` together.
