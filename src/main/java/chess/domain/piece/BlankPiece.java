package chess.domain.piece;

import chess.constant.ExceptionCode;
import chess.domain.piece.property.Color;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlankPiece extends Piece {

    private static final Map<Position, BlankPiece> blankPieceCache = new HashMap<>();

    private BlankPiece(final Position position) {
        super(position, Color.BLANK);
    }

    public static BlankPiece of(final Position position) {
        blankPieceCache.putIfAbsent(position, new BlankPiece(position));
        return blankPieceCache.get(position);
    }

    @Override
    protected boolean canMove(final Position targetPosition) {
        throw new IllegalStateException(ExceptionCode.ACCESS_BLANK_PIECE.name());
    }

    @Override
    public Piece move(final Piece pieceInTargetPosition) {
        throw new IllegalStateException(ExceptionCode.ACCESS_BLANK_PIECE.name());
    }

    @Override
    public List<Position> getPassingPositions(final Position targetPosition) {
        throw new IllegalStateException(ExceptionCode.ACCESS_BLANK_PIECE.name());
    }
}
