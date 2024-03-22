package chess.game.state;

public abstract class TurnState implements GameState {

    @Override
    public final GameState start() {
        throw new UnsupportedOperationException("게임이 이미 시작되었습니다.");
    }

    @Override
    public final GameState terminate() {
        return new TerminatedState();
    }

    @Override
    public final boolean isPlaying() {
        return true;
    }
}
