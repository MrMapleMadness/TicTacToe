# TicTacToe

This is a simple implementation of the game Tic Tac Toe,
it's implemented in Java and it's a learning tool for me.
With the introduction of functional programming into
Java recently, this program is a tool for me to 
learn about functional programming as well as to create
a project that I can complete and be proud of. This
is to supplement my education at The University of Kent
and to give me experience in projects and documentation.

### Extendability

I've implemented this game with the ability to extend it
further down the line. For example I may change some
of the methods later to include a GUI that is nicer
than commandline.

### Comments

I've heavily commented my code and included Javadoc
throughout. Please let me know if any of these comments
are confusing.

### Bugs

This is a program for me to learn, if you see any bugs
please report them to me, I can't learn if I don't know
where the bugs are!

### To Do's

Throughout the code I have many "TODO" comments, these
are for me to refer to so that I know how I want to extend
the program. I will also put them here and I will keep them
here even when they are removed from the code.

**@ TODO: 14/10/2017 Improve hasWinner by using lambdas and streams**
 
Massive Improvement already, changed the horizontal and vertical
checks to streams. Made the code much more readable and shorter.
Now I need to try to do diagonals, this will be harder or similar
to vertical as I need to create a 2D array of just those elements.
However, once that is complete I should be able to just stream that
array and check for equality.

I have now finished all the methods for creating the arrays and have
implemented the streams to check the diagonals.

*COMPLETE: 16/10/2017*
 
**@ TODO: 14/10/2017 Improve hasWinner code by reducing the repetition of the loops**
 
Done! Took loops out and moved them to helper methods, these methods
use loops much less than previously. All they do is create arrays so
that the streams can iterate over them.

*COMPLETE: 16/10/2017*
 
**@ TODO: 16/10/2017 Make hasWinner one return statement**
 
This is a difficult decision, I like to do things in one line, I find
that code done in one displays a level of polish that a lot of programmers
don't care about. However, the code often becomes less readable when 
it's so condensed. Blaise Pascal said - "If I had more time I would have
written a shorter letter". In the end I decided to do one return 
statement, it's shorter and removes lines from the code.

*COMPLETE: 16/10/2017*

**@ TODO: 14/10/2017 Make players more robust so that players can have custom names**

Players now choose their own names in the game. I also decided to add
a scoring system to the game so players know how many games they have
won.

*COMPLETE: 16/10/2017

**@ TODO: 14/10/2017 Improve generateCleanBoard using streams**

**@ TODO: 14/10/2017 Make printBoard a graphical interface**