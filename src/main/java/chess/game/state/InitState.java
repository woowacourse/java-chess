package chess.game.state;

import chess.board.Board;
import chess.position.Position;

public class InitState implements GameState {

    @Override
    public GameState start() {
        return new WhiteTurn();
    }

    @Override
    public GameState proceedTurn(Board board, Position source, Position destination) {
        throw new UnsupportedOperationException("게임이 시작되지 않았습니다.");
    }

    @Override
    public GameState terminate() {
        return new TerminatedState();
    }

    @Override
    public boolean isPlaying() {
        return false;
    }
}
