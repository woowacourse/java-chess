package chess.domain.game;

public interface Game2 extends ActionHandler2, BoardProvider {
    
    String GAME_ERROR_PREFIX = "[GAME ERROR] ";
    
    boolean isContinued();
    
    boolean isEnd();
    
    boolean isOver();
    
}
