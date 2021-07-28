# CS113-HW03-Polynomial
## HW #3 for CS113 - Polynomial ADT, Implementation, and Menu Program Demo

> An abstract data type (ADT) is an object with a generic description independent of implementation details.  Examples of abstract objects include mathematical objects (like numbers, polynomials, integrals, vectors), physical objects (like pulleys, floating bodies, missiles), animate objects (Cats, Velociraptor, People) and objects that are abstract even in the natural language sense. You do not see Java in Cats. Only when you want to simulate a living Cat, you would think of a computer language. Similarly, gravity is an abstract concept. When you want to model it and want to predict how a falling object works, you would think of writing a program in Java.

We can represent a polynomial as an ordered list of terms, where the terms are ordered by their exponents. To add two polynomials,  if the exponent of one is smaller than the exponent of the other, then the larger one comes before the smaller one.  If the exponents are equal, then sum the coefficients (no duplicate terms with same coefficient).  For example:

*3x^4^ + 2x^2^ + 3x + 7 added to 2x^3^ + 4x + 5 is equal to 3x^4^ + 2x^3^ + 2x^2^ + 7x + 12*

**Create an implementation of the ADT** described above to create and add polynomials.  Begin by defining a class `Term` that contains the exponent and coefficient.  This class should implement the `Comparable` interface by comparing the values of the exponents.  You should build a `Polynomial` class with a `LinkedList<Term>` instance variable, which specifically contains polynomial-related methods (like creating a polynomial, adding two polynomials, etc.).  You need not build your own linked list (homework for next week), use the built in `java.util.LinkedList`.  

**Write a driver program** to demonstrate your `Polynomial` ADT implementation, with a menu program that allows a user to:

- edit the first polynomial (clear, create, and add terms)
- edit the second polynomial (clear, create, and add terms)
- display the result of adding the current first and second polynomial (without deleting/modifying those two polynomials)
- exiting the program

The menu will allow the user to modify, clear, build, and add polynomials repeatedly until they choose to exit the program.  Also, the user should be able to enter terms for a polynomial in whatever order they’d like (ex: 2x^2^ then 3x^5^ then 5x; or if have user enter it all at once as 5x + 2x^2^ + 3x^5^) and the polynomial should be stored, output, and maintain proper order (ex: 3x^5^ + 2x^2^ + 5x).  You can assume the user will enter the terms/polynomials in whatever clear instructions you give them (*extra credit for not assuming this and using exception handling*).

**Provide full UML class relationship diagram for the project above as well as a UML sequence diagram for your driver**. Save both as images and place inside the `doc` package of the repository, then edit this markdown file to add the images in the placeholders below:


----------


Polynomial Program – UML Class Relationship Diagram

![UML_01](https://github.com/MiraCostaCS113/hw03-polynomial-CarlozSanchez/blob/master/doc/edu/miracosta/cs113/Homework%203%20-%20UML%2001.jpg)

Polynomial Program – UML Driver Sequence Diagram

![UML_02](https://github.com/MiraCostaCS113/hw03-polynomial-CarlozSanchez/blob/master/doc/edu/miracosta/cs113/Homework%203%20-%20UML%2002.jpg)
![UML_03](https://github.com/MiraCostaCS113/hw03-polynomial-CarlozSanchez/blob/master/doc/edu/miracosta/cs113/Homework%203%20-%20UML%2003.jpg)




----------


### Make sure to commit + push *before* the deadline to have your code be considered for grading.

>Pro-Tips:
>- Do the UML class/sequence diagrams first; it’ll help you see if you fully understand the project and its requirements.  ***Failure to plan is planning to fail!***
>- For the ADT implementations (basically all classes but the driver, for this program) make sure to create all the required methods: setters, getters, toString, equals, constructors, etc.
>- Also, make sure to look at the JUnit tests as a guide of what to implement, in case you're not sure.  Your code must pass all tests, but you can comment out tests as you build so you can build one method/part at a time! ***Build incrementally, writing a lot of code without knowing it works is not good strategy!***
>- Checkout the [Mastering Markdown guide](https://guides.github.com/features/mastering-markdown/) for help on how to insert images.  This markdown file, `README.md`, in the repository is the one you want to edit, and you can edit it in IntelliJ (it's just a text file)!
>- Think of your user! What is a simple, straightforward, easy way for them to input the polynomials into the program? What methods may help clean up your code and provide flexibility to your classes?  What data and in what format do you show to your user in the console output so that it is clear what is going on?