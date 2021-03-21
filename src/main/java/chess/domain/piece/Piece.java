package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.position.MovePosition;

public interface Piece {
    String getSymbol();
    
    double getScore();
    
    boolean isSameColorAs(Color color);
    
    void checkToMoveToTargetPosition(MovePosition movePosition, Board board);
    
    boolean isBlank();
}
