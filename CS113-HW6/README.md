# RecursionTrees
## Recursion and Tree Problems

**[//Insert Build Status Image//]**

## [Recursive] Change Calculator (based on Programming Project #7, pg. 291):

>Make sure to use the `edu.miracosta.cs113.change` to store your solution (files described below are within this package).

Using the provided ChangeCalculator class, implement the recursive method `calculateChange(int)` which will dispense change for a given amount of money. The method will **display and return** the total number of combinations of quarters, dimes, nickels, and pennies that equal the desired amount and all of the combinations as well. Avoid duplication.

If you choose to use a data structure, it must be one that we've covered and you must thoroughly justify why it was the best choice (based on run-time efficiency): **[//here//]**

Next, you will implement the method `printCombinationsToFile(int)`, which should contain a call to the recursive solution that you created. Creating a text file in the program's directory named `"CoinCombinations.txt"`, this method will write each combination produced to separate lines. This file will be read by the tester class to verify that your recursive solution avoids duplicate values.

**_NOTE:_** _Your program should dispense the highest coin first (quarters, then dimes, then nickels, then pennies)._ The format for printing each combination is otherwise up to you- as long as the output is consistent. Valid String values include "1 quarter/s, 2 dime/s, 3 nickel/s, 4 penny/ies", "1Q 2D 3N 4P", and "[1, 2, 3, 4]". For example, the generated text file would then contain the following contents, where the given input is 10 cents:

`CoinCombinations.txt`

```
[0, 1, 0, 0]
[0, 0, 2, 0]
[0, 0, 1, 5]
[0, 0, 0, 10]
```

The two methods will be tested using 5 cent increments between 5 cents and 30 cents, larger tests for 75 cents through 100, and values that are not multiples of 5 (3 through 101 cents). For example, 75 cents equates to 121 unique combinations.

## Tree Intro problems (based on Self-Check problems on p. 303):

>For the following problems, create a `doc` folder to store images in and insert them into the spaces provided using markdown.  You can scan low-resolution images, use websites like draw.io, or any software of your choosing as long as it is legible.

1. Draw binary expression trees for the following infix expressions.  Your trees should enforce the Java rules for operator evaluation (higher-precedence operators before lower-precedence operators and left associativity.) Note: they should also preserve the order seen in the expressions.
  - `x / y + a - b * c`

  **[//Insert image here//]**
  - `(x * a) - y / b * (c + d)`
  
  **[//Insert image here//]**
  
  - `(x + (a * (b - c)))/ d`
  
  **[//Insert image here//]**
  
2. Using the Huffman tree in the figure below:

![Image of huffman code tree](doc/problemImages/huffman.png)

  - Write the binary string for the message "scissors cuts paper":
    - Binary String = `insert here`
  - Decode the following binary string using the tree above:
  `1100010001010001001011101100011111110001101010111101101001`
    - Decoded String = `insert here`

3. For each of the two trees shown below, answer these questions:
- What is its height?
- Is it a full tree?
- Is it a complete tree?
- Is it a binary search tree? If not, make it into a binary seach tree.

![Image of tree for problem 3A](doc/problemImages/problem3A.png)

`insert answers here`

![Image of tree for problem 3B](doc/problemImages/problem3B.png)

`insert answers here`

4. For the binary trees shown below, indicat whether each tree is full, perfect, complete, or none of the above (note that a tree could be more than one of these!)

![Image of tree for problem 4A](doc/problemImages/problem4A.png)

`insert answers here`

![Image of tree for problem 4B](doc/problemImages/problem4B.png)

`insert answers here`

![Image of tree for problem 4C](doc/problemImages/problem4C.png)

`insert answers here`

![Image of tree for problem 4A](doc/problemImages/huffman.png)

`insert answers here`

5. Represent the general tree below as a binary tree

![Image of general tree for problem 5](doc/problemImages/problem5-general.png)

**[//Insert image here//]**


----------

### Make sure to commit + push *before* the deadline to have your code be considered for grading.

>Pro-Tip:

>- Refer to the given documentation in the ChangeCalculator class as you plan and design your implementation of its methods.
