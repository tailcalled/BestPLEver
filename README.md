BestPLEver
==========

This is totally the best programming language in the history of PLT. It has all the required features of a modern programming language: basic arithmetic (floating point numbers, `+`, `*`), strings and the ability to print previously mentioned things.

Syntax
------

The syntax is based on LISP and can be written as follows:

    EXPR ::= "(" <id> EXPR* ")"
    EXPR ::= <id>

where identifiers are any characters that are not whitespace or parantheses. A standalone identifier, say, `hello`, is simply a shorthand for sorrounding the character with parantheses: `(hello)`. This means that any expression is represented by an identifier and a list of expressions. The initial identifier is called the operator of an expression, and the list of expressions is called the subexpressions or the arguments.

Semantics
---------

The semantics of evaluating an expression is simple:

 * Look up the variable named by the operator.
 * Apply this value to the subexpressions. This is done even if there are no subexpressions.

These are of course complicated operations and can be broken down into smaller steps.

An important feature of BestPLEver is that it has released itself from the idea that only strings can be variable names. Instead, **any value within BestPLEver can be a variable name**. To store the variables, there is a global dictionary which maps BestPLEver values to BestPLEver values. The lookup operation happens as follows:

 * If there already is an entry in the dictionary, return said entry.
 * Otherwise, ask the BestPLEver value how to look it up and return said result. The way this is done will in the future depend on the tag of the BestPLEver value. In the current version, it is simply done by returning the value itself.

This has an important implication: a single-word string does not need any special syntax to be used as long as there is no variable that has it as name. This is lucky, because there is no syntax yet to write strings.

Applying a value is also an operation. At the current time, there are only two kinds of values: strings and operators. Applying a string simply returns the string, and applying an operation obviously simply applies the operation.

Interpreter
-----------

The interpreter already has three variables set:

    print        prints all of its arguments
    +            approximately the sum of all of its arguments
    *            approximately the product of all of its arguments

The arithmetic operators are not as exact as possible. They add a little bit of randomness in order to discourage checking floating point equality, which is a very unstable operation.

These operations allow the writing of all interesting programs, but we may add more operations in the future for the sake of convenience. It should be noted that because of a lack of preset operators, there is no way to set new variables, and the name of all variables are therefore strings. This will probably be remedied in the future.
