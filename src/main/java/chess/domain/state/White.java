package chess.domain.state;

import chess.domain.Board;
import chess.domain.piece.Team;
import chess.domain.postion.Position;

import java.util.List;

public final class White extends Started {

    public White(final Board board) {
        super(board);
    }

    @Override
    public State changeTurn(final List<Position> positions) {
        final Position source = positions.get(SOURCE_INDEX);
        final Position target = positions.get(TARGET_INDEX);

        final Board board = board().movePiece(source, target, Team.WHITE);

        return new Black(board);
    }

    @Override
    public boolean isGameOver() {
        return (!board().isKingAlive(Team.WHITE));
    }

    @Override
    public String toString() {
        return "White";
    }
}
