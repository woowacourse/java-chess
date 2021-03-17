package chess.domain.piece;

public interface Piece {
    String getSymbol();
    
    double getScore();
    
    boolean isSameColor(Color color);
    
    Piece move(Position position);
}
