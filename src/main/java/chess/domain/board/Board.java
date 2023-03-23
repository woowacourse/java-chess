package chess.domain.board;

public interface Board extends PieceProvider, MoveHandler {
    
    boolean isKingDead();
    
}
