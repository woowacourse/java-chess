package chess.domain.piece;

import chess.domain.Movement;
import chess.domain.Position;
import java.util.List;

public class None implements ChessPiece {

    @Override
    public void validateCanMove(Position origin, Position destination) {
        throw new IllegalStateException("움직일 말이 존재하지 않습니다.");
    }

    @Override
    public List<Movement> findRoute(Position origin, Position destination) {
        throw new IllegalStateException("움직일 말이 존재하지 않습니다.");
    }

    @Override
    public void move() {
        throw new IllegalStateException("움직일 말이 존재하지 않습니다.");
    }

    @Override
    public String name() {
        return "-";
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
