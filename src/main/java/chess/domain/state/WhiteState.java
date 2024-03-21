package chess.domain.state;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.position.Position;

public class WhiteState implements GameState {
    @Override
    public GameState start() {
        throw new UnsupportedOperationException("이미 시작한 상태입니다.");
    }

    @Override
    public GameState move(Board board, Position source, Position target) {
        board.move(source, target, Color.WHITE);

        return new BlackState();
    }

    @Override
    public GameState end() {
        return new EndState();
    }

    @Override
    public boolean isPlaying() {
        return true;
    }
}
