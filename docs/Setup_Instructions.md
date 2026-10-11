# Setup Instructions

## JDK version

Recommended version: **JDK 17 or later**. LearnTrack uses modern switch labels (`case 1 ->`) supported by Java 14+; JDK 17 is a stable long-term-support choice.

## Verify installation

Open a terminal and run:

```bash
java -version
javac -version
```

Both commands should display a version. If `javac` is not recognized, install a full JDK and add its `bin` directory to your PATH.

## Hello World check

Create `HelloWorld.java`:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

Compile and run:

```bash
javac HelloWorld.java
java HelloWorld
```

Expected output:

```text
Hello, World!
```

For the submission, add a screenshot from your own machine showing the command and output, or retain this explanation if screenshots are not required.

## Compile LearnTrack

From the project root:

**Windows PowerShell**
```powershell
New-Item -ItemType Directory -Force out
javac -d out (Get-ChildItem -Recurse src -Filter *.java | ForEach-Object { $_.FullName })
java -cp out com.airtribe.learntrack.ui.Main
```

**macOS / Linux**
```bash
mkdir -p out
javac -d out $(find src -name "*.java")
java -cp out com.airtribe.learntrack.ui.Main
```
