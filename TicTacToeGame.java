package games.board;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeGame extends JFrame{
	
	private Board gb;
	private int turn;
	
	//Return Outcome
	public Outcome getOutcome() {
		int totalCross;
		int totalNought;
			/*Check Horizontal Win Condition*/
			for(int r = 0; r < 3; r++ ) {
				totalCross = 0;
				totalNought = 0;
				for(int c = 0; c < 3; c++ ) {
					if (gb.getCell(r, c).getContent() == Mark.CROSS){
						totalCross++;
						totalNought = 0;
						if (totalCross == 3) {return Outcome.PLAYER1_WIN;}
					}
					else if (gb.getCell(r, c).getContent() == Mark.NOUGHT){
						totalNought++;
						totalCross = 0;
						if (totalNought == 3) {return Outcome.PLAYER2_WIN;}
					}
					else if (gb.getCell(r, c).getContent() == Mark.EMPTY){
						totalNought = 0;
						totalCross = 0;
						
					}
					}
			}
				 
			/*Check Vertical Win Condition*/
			for(int r = 0; r < 3; r++ ) {
				totalCross = 0;
				totalNought = 0;
				for(int c = 0; c < 3; c++ ) {	
					if (gb.getCell(c, r).getContent() == Mark.CROSS){
						totalCross++;
						totalNought = 0;
						if (totalCross == 3) {return Outcome.PLAYER1_WIN;}
					}
					else if (gb.getCell(c, r).getContent() == Mark.NOUGHT){
						totalNought++;
						totalCross = 0;
						if (totalNought == 3) {return Outcome.PLAYER2_WIN;}
					}
					else if (gb.getCell(c, r).getContent() == Mark.EMPTY){
						totalNought = 0;
						totalCross = 0;
					}
					}
				} 
			
			/*check left-right diagonal win condition*/
			totalCross = 0;
			totalNought = 0;
			for(int r = 0; r < 3; r++ ) {
					if (gb.getCell(r, r).getContent() == Mark.CROSS) {
						totalCross++;
						totalNought = 0;
						if (totalCross == 3) {return Outcome.PLAYER1_WIN;}
				}
					else if (gb.getCell(r, r).getContent() == Mark.NOUGHT){
						totalNought++;
						totalCross = 0;
						if (totalNought == 3) {return Outcome.PLAYER2_WIN;}
					}
					else if (gb.getCell(r, r).getContent() == Mark.EMPTY) {
						totalNought = 0;
						totalCross = 0;
						}
			}
			
			
			/*check right-left diagonal win condition*/
			totalCross = 0;
			totalNought = 0;
			int c = 0;
			for(int r = 2; r >= 0; r--) {
					if (gb.getCell(c, r).getContent() == Mark.CROSS) {
						totalCross++;
						totalNought = 0;
						c++;
						if (totalCross == 3) {return Outcome.PLAYER1_WIN;}
				}
					else if (gb.getCell(c, r).getContent() == Mark.NOUGHT){
						totalNought++;
						totalCross = 0;
						c++;
						if (totalNought == 3) {return Outcome.PLAYER2_WIN;}
					}
					else if (gb.getCell(c, r).getContent() == Mark.EMPTY) {
						c++;
						return Outcome.CONTINUE;
						}
					
			}
					
			
			/*return tie*/
			return Outcome.TIE;
			}


	private void takeTurn(Cell c) {
		Mark curMark = (turn++ % 2 == 0)?
		Mark.NOUGHT: Mark.CROSS;
		gb.setCell(curMark,c.getRow(),c.getColumn());
		Outcome result = getOutcome();
		
		
		if (result == Outcome.PLAYER1_WIN) {
			JOptionPane.showMessageDialog (this,"Player 2 Wins");
			System.exit(0);
		}
		else if (result == Outcome.PLAYER2_WIN) {
				JOptionPane.showMessageDialog (this,"Player 1 Wins");
				System.exit(0);
		}
		else if (result == Outcome.TIE) {
				JOptionPane.showMessageDialog(this,"Tie Game");
				System.exit(0);
		
		}
	}
	
	private TicTacToeGame() {
		gb = new Board(3, 3, new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
		Cell c = (Cell) ae.getSource();
		takeTurn(c);
		}
		});
		this.add(gb);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("TIC-TAC-TOE");
		this.setSize(300, 300);
		this.setVisible(true);
		}
	
	public static void main(String args[])
	{
		
		SwingUtilities.invokeLater( new Runnable () {
			public void run() { new TicTacToeGame(); }
			});
	}	
}
	
