package chess.domain.pieces;

import chess.domain.board.Position;
import chess.domain.strategy.Route;
import java.util.List;

public class Empty extends Piece {

    public Empty(Team team) {
        super(team);
    }

    @Override
    public void canMove(final Position start, final Position end, boolean isAttack) {
        throw new IllegalArgumentException("움직일 수 없는 말입니다.");
    }

    @Override
    public Route generateRoute(final Position source, final Position destination) {
        throw new IllegalArgumentException("움직일 수 없는 말입니다.");
    }
}
