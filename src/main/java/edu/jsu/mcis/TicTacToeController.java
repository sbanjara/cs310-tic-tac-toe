package edu.jsu.mcis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class TicTacToeController implements ActionListener{
    
    private final TicTacToeModel model;
    private final TicTacToeView view;
    
    /* CONSTRUCTOR */

    public TicTacToeController(int width) {
        
        /* Initialize model, view, and width */

        model = new TicTacToeModel(width);
        view = new TicTacToeView(this, width);
        
    }
    
    public String getMarkAsString(int row, int col) {        
        return (model.getMark(row, col).toString());        
    }
    
    public TicTacToeView getView() {        
        return view;        
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if( e.getSource() instanceof JButton) {
           
            JButton button = (JButton) (e.getSource());
            String name = button.getName();
            int row = Integer.parseInt(name.substring(6, 7));
            int col = Integer.parseInt(name.substring(7));
            boolean markMade = model.makeMark(row, col);
            
            if(markMade) {
                
                view.updateSquares();
				
                String result = model.getResult().toString();
                if( result.equals("X")) {
                    view.showResult("X");
                }
                else if( result.equals("O")) {
                    view.showResult("O");
                }
                else if( result.equals("TIE")) {
                    view.showResult("TIE");
                }
				else if( result.equals("NONE") ) {
					view.clearResult();
				}
                if(model.isGameover()) {
                    view.disableSquares();
                }
                
            }
			
        }
		
    }
       
}
