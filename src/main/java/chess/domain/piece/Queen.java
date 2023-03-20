package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.move.enums.DiagonalMove;
import chess.domain.move.enums.HorizontalMove;
import chess.domain.move.enums.MoveEnum;
import chess.domain.move.enums.VerticalMove;
import chess.domain.team.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class Queen extends Piece {
    private static final List<MoveEnum> moves = new ArrayList<>();

    static {
        moves.addAll(List.of(HorizontalMove.values()));
        moves.addAll(List.of(VerticalMove.values()));
        moves.addAll(List.of(DiagonalMove.values()));
    }

    public Queen(final Team team) {
        super(team);
    }

    @Override
    public String name() {
        if (super.team().equals(Team.WHITE)) {
            return "q";
        }
        return "Q";
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
