# Polynomial-Evaluator

## Project description

Polynomial Evaluator is a **Java** project that was designed to add, subtract, multiply, and evaluate polynomials using object-oriented programming. This was one of my first programs in any coding langauge and it served as a simple exercise to learn about object-oriented-programming and linked lists. 

## How can I view this project?

You can download the src folder and then using your preferred IDE, you can compile all files, Node.java, Term.java, Poly.java and Polytest.java. To run this, you need to run Polytest.java on a terminal since it has the driver of the program is on Polytest.java. Once running, it will ask you a prompt for a path to a .txt file that contains the coefficients of the first polynomial. Note the storage format of the polynomial on the .txt file must be like so:
```
<coeff> <degree>
<coeff> <degree>
...
<coeff> <degree>
```
where the degrees must be in a descending order. For example, if this was the .txt file:

```
4 5
-2 3
2 1
3 0
```
which represents the polynomial:

```4*x^5 - 2*x^3 + 2*x + 3```

You can make two such .txt files and either add, multiply two polynomials or evaluate a single polynomial at a particular value of x. There are two sample .txt files, [ptest1.txt](ptest1.txt) and [ptest2.txt](ptest2.txt), made for you to check out. 

Software needed:

   - Java 
   - IDE (like Eclipse)  
