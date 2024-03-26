package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.square.Square;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private static final String ERROR_SAME_SQUARE = "기물의 출발지와 목적지는 달라야 합니다.";
    private static final String ERROR_NOT_EXIST_PIECE = "해당 위치에 기물이 존재하지 않습니다.";
    private static final String ERROR_MOVE_NOT_AVAILABLE = "해당 위치로 기물을 이동할 수 없습니다.";
    private static final String ERROR_IS_NOT_TURN = "본인 팀의 기물만 이동할 수 있습니다.";

    private final Map<Square, Piece> pieces;

    public Board(final Map<Square, Piece> pieces) {
        this.pieces = new HashMap<>(pieces);
    }

    public void move(final Square source, final Square target, final PieceColor turn) {
        validateIsSameSquare(source, target);
        validateIsNonExistentPiece(source);

        final Piece sourcePiece = findPieceBySquare(source);
        validateIsNotTurn(sourcePiece, turn);
        validateCannotMove(source, target, sourcePiece);
        validateNotExistObstacleOnPath(source, target);
        pieces.remove(source);
        pieces.put(target, sourcePiece);
    }

    private void validateIsSameSquare(final Square source, final Square target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException(ERROR_SAME_SQUARE);
        }
    }

    private void validateIsNonExistentPiece(final Square square) {
        if (!pieces.containsKey(square)) {
            throw new IllegalArgumentException(ERROR_NOT_EXIST_PIECE);
        }
    }

    private Piece findPieceBySquare(final Square square) {
        return pieces.get(square);
    }

    private void validateIsNotTurn(final Piece source, final PieceColor turn) {
        if (!source.isSameColor(turn)) {
            throw new IllegalArgumentException(ERROR_IS_NOT_TURN);
        }
    }

    private void validateCannotMove(final Square source, final Square target, final Piece sourcePiece) {
        if (!sourcePiece.canMove(source, target)) {
            throw new IllegalArgumentException(ERROR_MOVE_NOT_AVAILABLE);
        }
    }

    private void validateNotExistObstacleOnPath(final Square source, final Square target) {
        final List<Square> path = source.findPath(target);
        for (Square square : path) {
            checkIsNotEmpty(square);
        }
    }

    private void checkIsNotEmpty(final Square square) {
        if (pieces.containsKey(square)) {
            throw new IllegalArgumentException(ERROR_MOVE_NOT_AVAILABLE);
        }
    }

    public Map<Square, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }
}
