package chess.domain.piece;

import chess.domain.move.enums.KnightMove;
import chess.domain.move.enums.MoveEnum;
import chess.domain.team.Team;

import java.util.ArrayList;
import java.util.List;

public final class Knight extends Piece {

    private static final List<MoveEnum> moves = new ArrayList<>();

    static {
        moves.addAll(List.of(KnightMove.values()));
    }

    public Knight(final Team team) {
        super(team);
    }

    @Override
    public String name() {
        if (super.team().equals(Team.WHITE)) {
            return "n";
        }
        return "N";
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
