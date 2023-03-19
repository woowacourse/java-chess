package chess.game.state;

public class GameEndState extends ChessGameState {
    public GameEndState() {
        super(null);
    }
    
    @Override
    public ChessGameState next() {
        return null;
    }
    
    @Override
    public boolean runnable() {
        return false;
    }
}
