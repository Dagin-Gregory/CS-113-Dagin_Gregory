# CS113-HW07-MorseCodeTree
## HW #7 for CS113 - Morse Code translator + extending Binary Tree

**[//Insert Build Status Image//]**

**International Morse Code, A-Z**

| Letter A-G | Code | Letter H-N | Code | Letter O-U | Code | Letter V-Z | Code |
|---|---|---|---|---|---|---|---|
| A | • − | H | • • • • | O | − − − | V | • • • −	 |
| B | − • • • | I | • • | P | • − − • | W | • − − |
| C | − • − • | J | • − − − | Q | − − • − | X | − • • − |
| D | − • • | K | − • − | R | • − • | Y | − • − − |
| E | • | L | • − • • | S | • • • | Z | − − • • |
| F |• • − • | M | − − | T | − |
| G | − − • | N | − • | U | • • − |

> Morse code was invented by Samuel Morse in 1832. Letters and numbers are represented by a series of dots and dashes - a dash sound lasts three times longer than a dot sound.


## Morse Code Translator:
Morse code is a common code that is used to encode messages consisting of letters and digits (this project only handles letters). Each letter consists of a series of dots (using asterisks for ease of use here) and dashes; for example, the code for the letter ***a*** is `*-` and the code for the letter ***b*** is `-***`.

 - Store each letter of the alphabet in a node of a binary tree of height 5. The root node is at level 1 and stores no letter. The left node at level 2 stores the letter ***e*** (code is `*`), and the right node stores letter ***t*** (code is `-`).  The 4 nodes at level 3, from left-to-right, store the letters with codes (`**`, `*-`, `-*`, `--`).  If your tree is visualized, it would look like:
![morse code tree of A-Z letters](https://i.imgur.com/v8Lr6Nl.png)
 - To build the tree above, read a text file in which **each line consists of a letter followed by its code** (i.e., `b -***`) and add the node appropriately to the tree using the morse code. The letters should be ordered by tree level, from left-to-right (i.e., e, t, i, a, n, m, etc. where each letter is on its own line with the code as described previously).
 - To find the position to add for a letter in the tree, use the code and branch left for a dot 
 (`*`) and branch right for a dash (`-`).
 - To decode the message using the `MorseCodeTree`, translate each letter using the tree (branch left for a dot, branch right for a dash). Make sure you use a space (` `) as a delimiter between coded letters (i.e., `**** * *-** *-** --- *-- --- *-* *-** -**`  translates to `helloworld`).

## Implementation Notes:
- You will need to complete the generic implementation of the `BinaryTree` data structure first (see slides). Once it is tested and complete, proceed to solve the problem above.
- Build the text file EXACTLY as specified above (if not, it becomes much more difficult and will break code that follows)
- Create a `MorseCodeTree` that extends `BinaryTree`, adding methods that specifically do the translation/reading described above. Starter code and documentation for translating Morse code has been provided for you. 
	- Note: Do not override `readBinaryTree()` method, as that works completely differently! Instead, create a `readMorseCodeTree()` method (it is simpler, doesn't need to be recursive as you can see from the description above).
- JUnit tests that test every letter, A-Z, is translated from morse code using the tree correctly, as well as a few multi-letter tests (other tests besides `**** * *-** *-** --- *-- --- *-* *-** -**`  translating to `helloworld`, etc.) have been provided in `MorseCodeTreeTest`. Given this, consider how your `MorseCodeTree` methods deal with errors like invalid characters (characters other than * or -)?
- **Provide a driver menu program** that builds the `MorseCodeTree` and asks the user if they want to 1) test output for all morse code letters with their respective translated alphabet letters (make sure you are using the `MorseCodeTree` to do the actual translation, output as a nicely formatted table. Doesn't have to be alphabetized but that would be nice for the user :smiley:), 2) enter an input textfile name with morse code to decode and output the translated text to the console, and 3) enter in a line of morse code through the console to decode morse code and output the translated text to the console.


> ***NOTE:*** Read the instructions carefully, already know the code from the slides for reading from a text file and doing a preorder traversal cannot directly solve this homework!  The preorder traversal method may help error check to see if your tree is being built correctly, however!  Also, you need not implement `Serializable` for your `MorseCodeTree` but you're encouraged to, since the code is straightforward and very much like the slides.
