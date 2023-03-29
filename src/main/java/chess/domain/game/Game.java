package chess.domain.game;

public interface Game extends ActionHandler, BoardProvider {
    
    String GAME_ERROR_PREFIX = "[GAME ERROR] ";
    
    boolean isContinued();
    
    boolean isEnd();
    
    boolean isOver();
    
}
