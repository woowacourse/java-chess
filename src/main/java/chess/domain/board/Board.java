package chess.domain.board;

public interface Board extends PieceProvider, MoveHandler {
    
    String BOARD_ERROR_PREFIX = "[BOARD ERROR] ";
    
    boolean isKingDead();
    
}
