package chess.domain.piece;

import chess.domain.move.enums.DiagonalMove;
import chess.domain.move.enums.MoveEnum;
import chess.domain.team.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class Bishop extends Piece {

    private static final List<MoveEnum> moves = new ArrayList<>();

    static {
        moves.addAll(List.of(DiagonalMove.values()));
    }

    public Bishop(final Team team) {
        super(team);
    }

    @Override
    public String name() {
        if (super.team().equals(Team.WHITE)) {
            return "b";
        }
        return "B";
    }

    @Override
    public boolean movable(final MoveEnum move) {
        return moves.contains(move);
    }

    @Override
    public boolean movableByCount(final int count) {
        return true;
    }
}
