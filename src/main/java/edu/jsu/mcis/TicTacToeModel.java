package edu.jsu.mcis;

public class TicTacToeModel {
    
    private Mark[][] board; /* Game board */
    private boolean xTurn;  /* True if X is current player */
    private int width;      /* Size of game board */
    
    /* ENUM TYPE DEFINITIONS */
    
    /* Mark (represents X, O, or an empty square */
    
    public enum Mark {
        
        X("X"), 
        O("O"), 
        EMPTY(" ");

        private String message;
        
        private Mark(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* Result (represents the final state of the game: X wins, O wins, a TIE,
       or NONE if the game is not yet over) */
    
    public enum Result {
        
        X("X"), 
        O("O"), 
        TIE("TIE"), 
        NONE("NONE");

        private String message;
        
        private Result(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel() {
        
        this(TicTacToe.DEFAULT_WIDTH);
        
    }
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel(int width) {
        
        /* Initializes width; X goes first */
        
        this.width = width;
        xTurn = true;
        
        /* Creates board (width x width) as a 2D Mark array */
        
        board = new Mark[width][width];

        /* Initializes board by filling every square with empty marks */
		
        for(int row = 0; row < width; ++row) {
            for(int col = 0; col < width; ++col) {
                board[row][col] = Mark.EMPTY;
            }
        }
        
    }
	
    public boolean makeMark(int row, int col) {
        
        /* Uses "isValidSquare()" to check if the specified location is in range,
           and uses "isSquareMarked()" to see if the square is empty!  If the
           specified location is valid, makes a mark for the current player, then
           toggles "xTurn" from true to false (or vice-versa) to switch to the
           other player before returning TRUE.  Otherwise, returns FALSE. */
		
	boolean markMade = false;
        if( (isValidSquare(row, col)) && (!isSquareMarked(row, col)) ) {
            if(xTurn) {
                board[row][col] = Mark.X;
                xTurn = false;
            }
            else {
                board[row][col] = Mark.O;
                xTurn = true;
            }
            markMade = true;
        }
        return markMade;
		
    }
	
    private boolean isValidSquare(int row, int col) {
        
        /* Returns TRUE if the specified location is within the bounds of the board */
		
	boolean isValidSquare = false;
        if( (row >= 0 && row < width) && (col >= 0 && col < width) ) {
            isValidSquare = true;
        }
        return isValidSquare;
        
    }
	
    private boolean isSquareMarked(int row, int col) {
        
        /* Returns TRUE if the square at specified location is marked */
		
	boolean isSquareMarked = false;
        if( (getMark(row, col) == Mark.X) || (getMark(row, col) == Mark.O) ) {
            isSquareMarked = true;
        }
        return isSquareMarked;
            
    }
	
    public Mark getMark(int row, int col) {
        
        /* Returns the mark from the square at the specified location */
		
        return board[row][col];
            
    }
	
    public Result getResult() {
        
        /* Calls "isMarkWin()" to see if X or O is the winner, if the game is a
           TIE, or if the game is not over.  Returns the corresponding Result
           value */
		
	if(isMarkWin(Mark.X))
            return Result.X;
        else if(isMarkWin(Mark.O))
            return Result.O;
        else if(isTie())
            return Result.TIE;
        else
            return Result.NONE;
        
    }
	
    private boolean isMarkWin(Mark mark) {
        
        /* Checks the squares of the board to see if the specified mark is the
           winner */
		
	boolean isMarkWin = false;
        String userMarks = "";
        int count = width-1;
        String mark1 = "", mark2 = "", mark3 = "",mark4 = "";
        
        for(int j = 0; j < width; ++j) {
            userMarks += mark;
            
        }
        
        for(int row = 0; row < width; ++row) {
            for(int col = 0; col < width; ++col) {
                mark1 += board[row][col];
                mark2 += board[col][row];   
            }
            mark3 += board[row][row];
            mark4 += board[row][count];
            count--;
            if( ( userMarks.equals(mark1) ) || ( userMarks.equals(mark2) ) ||
                ( userMarks.equals(mark3) ) || ( userMarks.equals(mark4) ) ) {
                isMarkWin = true;
                break;
            }
            else {
                isMarkWin = false;
            }
            mark1 = "";
            mark2 = "";
                
        }
		
        return isMarkWin;
		
    }
	
    private boolean isTie() {
        
        /* Checks the squares of the board to see if the game is a tie */
		
	boolean isTie = false;
        boolean emptySquares = false;  
        
        for(int row = 0; row < width; ++row) {
            for(int col = 0; col < width; ++col) {
                if( isSquareMarked(row, col) == false ) {
                    emptySquares = true;
                    break;
                }
            }
        }
        if( (isMarkWin(Mark.X) == false) && (isMarkWin(Mark.O) == false) && 
            (emptySquares == false) ) {
            isTie = true;		 
        }
        
        return isTie;
        
    }

    public boolean isGameover() {
        
        /* Returns TRUE if the game is over */
        
        return (Result.NONE != getResult());
        
    }

    public boolean isXTurn() {
        
        /* Getter for xTurn */
        
        return xTurn;
        
    }

    public int getWidth() {
        
        /* Getter for width */
        
        return width;
        
    }
    
}
