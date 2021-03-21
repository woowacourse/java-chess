package chess.domain.piece;

import chess.domain.game.Board;

public interface Piece {
    String getSymbol();
    
    double getScore();
    
    boolean isSameColorAs(Color color);
    
    Piece move(Position sourcePosition, Position targetPosition, Board board);
}
