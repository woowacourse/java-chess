package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public class State {
    private boolean finish;
    private Color turn;
    private Color winner;
    
    public State() {
        finish = false;
        turn = Color.WHITE;
        winner = Color.BLANK;
    }
    
    public boolean isFinish() {
        return finish;
    }
    
    public void finish() {
        finish = true;
    }
    
    public void nextTurn() {
        turn = turn.next();
    }
    
    public boolean isSameColor(final Piece piece) {
        return piece.isSameColor(turn);
    }
    
    public void setWinner() {
        winner = turn;
    }
    
    public Color winner() {
        return winner;
    }
}
