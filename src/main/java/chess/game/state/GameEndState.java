package chess.game.state;

public class GameEndState extends ChessGameState {
    public GameEndState() {
        super(null, null);
    }
    
    @Override
    public ChessGameState next() {
        throw new UnsupportedOperationException("게임이 끝난 상황에선 사용할 수 없는 기능입니다.");
    }
    
    @Override
    public boolean runnable() {
        return false;
    }
}
