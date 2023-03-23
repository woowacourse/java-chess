package chess.domain.game;

public interface Game extends ActionHandler, BoardProvider {
    
    boolean isNotEnd();
    
}
