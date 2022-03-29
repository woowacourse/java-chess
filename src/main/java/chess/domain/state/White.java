package chess.domain.state;

import chess.domain.Board;
import chess.domain.piece.Team;
import chess.domain.postion.Position;

public final class White extends Started {

    public White(final Board board) {
        super(board);
    }

    @Override
    public State changeTurn(final Position source, final Position target) {
        final Board board = board().movePiece(source, target, Team.WHITE);

        return new Black(board);
    }
}
