package chess.domain.board;

import chess.domain.Movement;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.square.Square;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class Board {
    private static final String INVALID_TURN = "헤당 색의 턴이 아닙니다.";
    private static final String INVALID_PIECE_MOVEMENT = "해당 기물은 위치로 이동할 수 없습니다.";
    private static final String NO_PIECE_EXCEPTION = "해당 위치에 기물이 없습니다.";

    private final Map<Square, Piece> pieces;

    public Board(final Map<Square, Piece> pieces) {
        this.pieces = pieces;
    }

    public void move(final Square from, final Square to, final Color turn) {
        Piece sourcePiece = pieces.get(from);
        validateSourcePiece(sourcePiece, turn);
        Piece destinationPiece = pieces.get(to);
        validateDestinationColor(sourcePiece, destinationPiece);

        Movement movement = new Movement(from, to);
        validate(sourcePiece, destinationPiece, movement);

        pieces.remove(from);
        pieces.put(to, sourcePiece);
    }

    private void validateSourcePiece(final Piece piece, final Color turn) {
        if (piece == null) {
            throw new IllegalArgumentException(NO_PIECE_EXCEPTION);
        }

        if (!piece.isSameColor(turn)) {
            throw new IllegalArgumentException(INVALID_TURN);
        }
    }

    private void validateDestinationColor(final Piece sourcePiece, final Piece destinationPiece) {
        if (sourcePiece.isSameColor(destinationPiece)) {
            throw new IllegalArgumentException(INVALID_PIECE_MOVEMENT);
        }
    }

    private void validate(final Piece sourcePiece, final Piece destinationPiece, final Movement movement) {
        checkCanMove(sourcePiece, destinationPiece, movement);
        checkRoute(movement);
    }

    private void checkCanMove(final Piece sourcePiece, final Piece destinationPiece, final Movement movement) {
        if (!sourcePiece.canMove(movement, destinationPiece)) {
            throw new IllegalArgumentException(INVALID_PIECE_MOVEMENT);
        }
    }

    private void checkRoute(final Movement movement) {
        Set<Square> squares = movement.findRoute();
        for (Square square : squares) {
            checkIsEmpty(square);
        }
    }

    private void checkIsEmpty(final Square square) {
        if (pieces.containsKey(square)) {
            throw new IllegalArgumentException(INVALID_PIECE_MOVEMENT);
        }
    }

    public Map<Square, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }
}
