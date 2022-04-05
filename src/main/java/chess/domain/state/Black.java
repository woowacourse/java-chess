package chess.domain.state;

import chess.domain.Board;
import chess.domain.piece.Team;
import chess.domain.postion.Position;

import java.util.List;

public final class Black extends Started {

    public Black(final Board board) {
        super(board);
    }

    @Override
    public State changeTurn(final List<Position> positions) {
        final Position source = positions.get(SOURCE_INDEX);
        final Position target = positions.get(TARGET_INDEX);

        final Board board = board().movePiece(source, target, Team.BLACK);

        return new White(board);
    }

    @Override
    public boolean isGameOver() {
        return (!board().isKingAlive(Team.BLACK));
    }

    @Override
    public String toString() {
        return "Black";
    }
}
