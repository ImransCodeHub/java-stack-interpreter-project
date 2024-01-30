
# CP2561 Project - Stack-Based Interpreter

This project is a completed stack-based interpreter for a simple stack-based language based on the Forth programming language. It was developed as part of the CP2561 course and achieved the objectives outlined in the project instructions.

## Overview

The project, when compiled and run, the stack-based interpreter reads a text file containing a program written in the stack-based language and executes the program accordingly. It confirms that the file contains a valid program. If it does, it executes the program. Programs consist of several "Words" executed immediately, fitting into one of five categories: "Numbers", "Quotes", "Definitions", "Stack-Operations", and "I/O".

## Features

- Parses and validates programs written in the stack-based language.
- Executes programs by interpreting the stack-based instructions.
- Supports predefined words such as numbers, quotes, definitions, stack operations, and I/O operations.
- Handles errors and prints error messages when necessary.

## Example Programs

Here are a few example programs that the interpreter can execute:

- `2 3 4 * + out` - Prints the result of adding the product of 3 and 4 to 2.
- `: fifteen 3 3 4 * + : fifteen fifteen + out` - Defines a word "fifteen" that adds 3 and the product of 3 and 4, and then prints the result.
- `' Hello World!' out` - Prints the string "Hello World!".
- `in out` - Reads input from the user and echoes it back.
- `5 - out` - Prints the result of subtracting 5 from the top of the stack.
- `' Hello' ' World!' + out` - Concatenates the strings "Hello" and "World!" and prints the result.
- `' Hello World!' - out` - Reverses the string "Hello World!" and prints the result.

## Technologies and Tools Used
- Java: The programming language used for developing the interpreter.
- Command-Line Interface (CLI): Used for running the interpreter and providing input files.
- File Input/Output: Java's file handling capabilities are utilized to read and parse the input program files.
- Error Handling: Implemented to handle errors and print error messages when necessary.
- Stack Data Structure: Used to store and manipulate data during program execution, following the stack-based paradigm.
- String Manipulation: Used for processing and manipulating strings, such as parsing program instructions and handling quotes.
- Standard Input/Output (I/O): Used for reading input from the user and printing output during program execution.
- Regular Expressions: Possibly used for parsing program instructions, especially in cases involving string manipulation and pattern matching.
- Git: Version control system used for managing project files and collaboration.
- GitHub: Hosting platform used for storing the project repository and facilitating collaboration and version control.

## Usage

To use the stack-based interpreter, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/ImransCodeHub/java-stack-interpreter-project.git
   ```

2. Navigate to the project directory:

   ```bash
   cd stack-based-interpreter
   ```

3. Run the interpreter with a text file containing the stack-based program:

   ```bash
   python interpreter.py program.txt
   ```

   Replace `program.txt` with the path to your program file.

## Acknowledgements

This project was completed as part of the CP2561 course. Special thanks to [Scott R. Young](https://github.com/scottryoung) for providing the project instructions and guidance throughout the development process.


