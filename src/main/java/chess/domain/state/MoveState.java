package chess.domain.state;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.position.Position;

public class MoveState implements GameState {
    private final Color color;

    public MoveState(Color color) {
        this.color = color;
    }

    @Override
    public GameState start() {
        throw new UnsupportedOperationException("이미 시작한 상태입니다.");
    }

    @Override
    public GameState move(Board board, Position source, Position target) {
        board.move(source, target, color);
        Color nextTurnColor = color.getOpposite();

        return new MoveState(nextTurnColor);
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
