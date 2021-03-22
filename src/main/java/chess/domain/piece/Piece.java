package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.position.MovePosition;

public interface Piece {
    void checkToMoveToTargetPosition(MovePosition movePosition, Board board);
    
    boolean isSameColorAs(Color color);
    
    boolean isBlank();
    
    boolean isPawn();
    
    boolean isKing();
    
    String getSymbol();
    
    double getScore();
}
