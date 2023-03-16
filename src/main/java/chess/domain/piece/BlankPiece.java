package chess.domain.piece;

import chess.domain.Color;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;

import java.util.List;

public class BlankPiece extends Piece {

    private static final String BLANK_PIECE_EXCEPTION_MESSAGE = "유효하지 않은 체스말 사용입니다.";

    public BlankPiece(final File file, final Rank rank) {
        super(file, rank, Color.BLANK);
    }

    @Override
    protected boolean canMove(final Position targetPosition) {
        throw new IllegalStateException(BLANK_PIECE_EXCEPTION_MESSAGE);
    }

    @Override
    public Piece move(final Piece pieceInTargetPosition) {
        throw new IllegalStateException(BLANK_PIECE_EXCEPTION_MESSAGE);
    }

    @Override
    public List<Position> getPassingPositions(final Position targetPosition) {
        throw new IllegalStateException(BLANK_PIECE_EXCEPTION_MESSAGE);
    }
}
