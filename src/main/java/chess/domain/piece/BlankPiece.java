package chess.domain.piece;

import chess.domain.piece.property.Color;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlankPiece extends Piece {

    static final String BLANK_PIECE_EXCEPTION_MESSAGE = "유효하지 않은 체스말 사용입니다.";

    private static final Map<Position, BlankPiece> blankPieceCache = new HashMap<>();

    private BlankPiece(final Position position) {
        super(position, Color.BLANK);
    }

    public static BlankPiece of(final Position position) {
        if (blankPieceCache.containsKey(position)) {
            return blankPieceCache.get(position);
        }
        final BlankPiece blankPiece = new BlankPiece(position);
        blankPieceCache.put(position, blankPiece);
        return blankPiece;
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
