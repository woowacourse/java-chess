package chess.domain.piece;

import java.util.List;

public interface Piece {
    String getSymbol();
    
    double getScore();
    
    boolean isSameColor(Color color);
    
    Piece move(Position position, List<List<Piece>> board);
}
