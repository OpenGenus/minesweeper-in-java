import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class Minesweeper extends JFrame {
  private JButton[][] buttons;
  private boolean[][] mines;
  private int[][] surroundingMines;
  private int uncoveredCells;

  public Minesweeper() {
    setTitle("Minesweeper");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new GridLayout(10, 10));

    buttons = new JButton[10][10];
    mines = new boolean[10][10];
    surroundingMines = new int[10][10];
    uncoveredCells = 0;

    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        buttons[i][j] = new JButton();
        buttons[i][j].addActionListener(new CellClickListener(i, j));
        add(buttons[i][j]);
      }
    }

    placeMines();
    countSurroundingMines();

    pack();
    setVisible(true);
  }

  private void placeMines() {
    Random random = new Random();
    int placedMines = 0;
    while (placedMines < 10) {
      int i = random.nextInt(10);
      int j = random.nextInt(10);
      if (!mines[i][j]) {
        mines[i][j] = true;
        placedMines++;
      }
    }
  }

  private void countSurroundingMines() {
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        if (!mines[i][j]) {
          int count = 0;
          if (i > 0 && mines[i - 1][j]) count++;
          if (i < 9 && mines[i + 1][j]) count++;
          if (j > 0 && mines[i][j - 1]) count++;
          if (j < 9 && mines[i][j + 1]) count++;
          if (i > 0 && j > 0 && mines[i - 1][j - 1]) count++;
          if (i < 9 && j < 9 && mines[i + 1][j + 1]) count++;
          if (i > 0 && j < 9 && mines[i - 1][j + 1]) count++;
          if (i < 9 && j > 0 && mines[i + 1][j - 1]) count++;
          surroundingMines[i][j] = count;
        }
      }
    }
  }

  private void uncoverCell(int i, int j) {
    if (mines[i][j]) {
      loseGame();
    } else {
      buttons[i][j].setText(Integer.toString(surroundingMines[i][j]));
      buttons[i][j].setEnabled(false);
      uncoveredCells++;
      if (uncoveredCells == 90) {
        winGame();
      }
      if (surroundingMines[i][j] == 0) {
        uncoverSurroundingCells(i, j);
      }
    }
  }

  private void uncoverSurroundingCells(int i, int j) {
    if (i > 0 && buttons[i - 1][j].isEnabled()) uncoverCell(i - 1, j);
    if (i < 9 && buttons[i + 1][j].isEnabled()) uncoverCell(i + 1, j);
    if (j > 0 && buttons[i][j - 1].isEnabled()) uncoverCell(i, j - 1);
    if (j < 9 && buttons[i][j + 1].isEnabled()) uncoverCell(i, j + 1);
    if (i > 0 && j > 0 && buttons[i - 1][j - 1].isEnabled()) uncoverCell(
      i - 1,
      j - 1
    );
    if (i < 9 && j < 9 && buttons[i + 1][j + 1].isEnabled()) uncoverCell(
      i + 1,
      j + 1
    );
    if (i > 0 && j < 9 && buttons[i - 1][j + 1].isEnabled()) uncoverCell(
      i - 1,
      j + 1
    );
    if (i < 9 && j > 0 && buttons[i + 1][j - 1].isEnabled()) uncoverCell(
      i + 1,
      j - 1
    );
  }

  private void winGame() {
    JOptionPane.showMessageDialog(this, "You won!");
    System.exit(0);
  }

  private void loseGame() {
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        if (mines[i][j]) {
          buttons[i][j].setText("*");
        }
        buttons[i][j].setEnabled(false);
      }
    }
    JOptionPane.showMessageDialog(this, "You lost.");
    System.exit(0);
  }

  private class CellClickListener implements ActionListener {
    private int i;
    private int j;

    public CellClickListener(int i, int j) {
      this.i = i;
      this.j = j;
    }

    public void actionPerformed(ActionEvent e) {
      uncoverCell(i, j);
    }
  }
}
