# Minesweeper Game in Java
This repository contains the source code for a Minesweeper game in Java. The game is a classic puzzle game in which the player must uncover all cells that do not contain mines, while avoiding those that do. The game is implemented using the Java Swing framework for GUI.

## Code Structure
The repository contains two code files:

1. **Main.java**: This is the main entry point for the application. It creates an instance of the `Minesweeper` class and starts the game.

2. **Minesweeper.java**: This is the implementation of the game logic. It extends the `JFrame` class and implements the game logic using the `ActionListener` interface.

## How to Play
1. Clone the repository to your local machine.
2. Compile and run the `Main.java` file.
3. The game window will appear on the screen, with 10x10 cells represented by buttons.
4. Click on the buttons to uncover cells. If a cell contains a mine, the game is lost. If a cell does not contain a mine, the number of surrounding mines will be displayed.
5. The goal of the game is to uncover all cells that do not contain mines. If all 90 cells are uncovered, the player wins the game.

## Conclusion
This Minesweeper game in Java is a fun and educational project for anyone interested in learning Java and game development. The project demonstrates the basics of GUI development in Java and the implementation of game logic.