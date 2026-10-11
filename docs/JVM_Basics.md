# JVM Basics

## JDK, JRE, and JVM

- **JVM (Java Virtual Machine):** runs Java bytecode. It provides the runtime environment that allows compiled Java programs to execute.
- **JRE (Java Runtime Environment):** traditionally refers to the JVM plus the libraries and components needed to run Java applications. Modern JDK distributions may not provide a separate JRE download.
- **JDK (Java Development Kit):** contains development tools such as the Java compiler (`javac`) along with the runtime components needed to run Java applications.

## What is bytecode?

When a Java source file such as `HelloWorld.java` is compiled using `javac`, the compiler creates a `.class` file containing bytecode. Bytecode is an intermediate instruction format designed for the JVM rather than for one specific processor.

## What does “write once, run anywhere” mean?

Java source code can be compiled into bytecode once and, in general, that bytecode can run on any operating system or device that has a compatible JVM. This reduces the need to compile a separate native program for every operating system.

The phrase does not mean every program works without changes in every environment. Programs may still depend on operating-system features, native libraries, or particular Java versions. Portability is strongest when the application uses standard Java APIs and a compatible JVM is available.
