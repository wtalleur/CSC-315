# Conway's Game of Life

## Background
> Conways game of life is a cellular automaton and zero-player game devised by the British mathematician John Horton Conway in 1970. 
> Cells have two states; alive or dead, and each cell interacts with its eight neighbors taking into account the following ruleset:
> 1) Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
> 2) Any live cell with two or three live neighbours lives on to the next generation.
> 3) Any live cell with more than three live neighbours dies, as if by overpopulation.
> 4) Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

> On top of the basic game, I also implemented different cell shapes (Glider, Exploder, Blinker, Pulsar, and Spaceship), different cell generation speeds (slow, fast, hyper), and basic saving/loading of games to a text file. 

> TODO: Implement wrapping so once the cells hit a border they bounce off and continue generation, move rules to ConwayRules class and implement CustomRules class

### Libraries
* Java 1.8 core libraries
* MigLayout 3.7
* Substance LAF

## Screenshots
![alt text](https://i.gyazo.com/7e689d82e0e416be9e0a3488e1dd4bcf.png "")

![alt text](https://i.gyazo.com/1a0987391415fd204534ff7f10ad08b3.png "")
