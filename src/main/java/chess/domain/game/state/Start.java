package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.piece.Piece;
import java.util.Map;

public class Start implements State {

    @Override
    public State start() {
        return new WhiteTurn(Board.create());
    }

    @Override
    public State move(Coordinate from, Coordinate to) {
        throw new IllegalStateException("게임을 시작하지 않았습니다.");
    }

    @Override
    public State end() {
        throw new IllegalStateException("게임을 시작하지 않았습니다.");
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Map<Coordinate, Piece> getValue() {
        throw new IllegalStateException("게임을 시작하지 않았습니다.");
    }
}
