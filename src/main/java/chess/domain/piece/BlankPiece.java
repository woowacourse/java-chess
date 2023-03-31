package chess.domain.piece;

import chess.domain.piece.property.Color;
import chess.domain.position.Path;
import chess.domain.position.Position;
import chess.exception.ChessException;
import chess.exception.ExceptionCode;

import java.util.HashMap;
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
        throw new ChessException(ExceptionCode.ACCESS_BLANK_PIECE);
    }

    @Override
    public Piece move(final Piece pieceInTargetPosition) {
        throw new ChessException(ExceptionCode.ACCESS_BLANK_PIECE);
    }

    @Override
    public Path getPassingPositions(final Position targetPosition) {
        throw new ChessException(ExceptionCode.ACCESS_BLANK_PIECE);
    }
}
