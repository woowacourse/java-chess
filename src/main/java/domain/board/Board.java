package domain.board;

import domain.piece.Piece;
import domain.position.Position;

import java.util.Collections;
import java.util.Map;

public final class Board {

    private static final String NOT_EXIST_SOURCE = "해당 위치에 말이 존재하지 않습니다.";
    private static final String INVALID_MOVEMENT = "해당 위치로 말을 이동할 수 없습니다.";

    private final Map<Position, Piece> board;

    private Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board create(final ChessAlignment alignment) {
        return new Board(alignment.makeInitialPieces());
    }

    public void move(final Position source, final Position destination) {
        validateRoute(source, destination);

        if (board.containsKey(destination)) {
            captureDestination(source, destination);
        }
        if (!board.containsKey(destination)) {
            moveDestination(source, destination);
        }
    }

    private void validateRoute(final Position source, final Position destination) {
        if (pieceInRoute(source, destination)) {
            throw new IllegalArgumentException(INVALID_MOVEMENT);
        }

        if (isSameTeamOnDestination(destination, getPiece(source))) {
            throw new IllegalArgumentException(INVALID_MOVEMENT);
        }
    }

    private boolean pieceInRoute(final Position source, final Position destination) {
        return source.between(destination).stream()
                .anyMatch(board::containsKey);
    }

    private boolean isSameTeamOnDestination(final Position destination, final Piece sourcePiece) {
        return board.containsKey(destination) &&
                sourcePiece.isBlack() == getPiece(destination).isBlack();
    }

    private Piece getPiece(final Position source) {
        if (!board.containsKey(source)) {
            throw new IllegalArgumentException(NOT_EXIST_SOURCE);
        }

        return board.get(source);
    }

    private void captureDestination(final Position source, final Position destination) {
        if (getPiece(source).isCapturable(source, destination)) {
            board.put(destination, board.remove(source));
            return;
        }

        throw new IllegalArgumentException(INVALID_MOVEMENT);
    }

    private void moveDestination(final Position source, final Position destination) {
        if (getPiece(source).isMovable(source, destination)) {
            board.put(destination, board.remove(source));
            return;
        }

        throw new IllegalArgumentException(INVALID_MOVEMENT);
    }

    public boolean isBlack(final Position position) {
        return getPiece(position).isBlack();
    }

    public Map<Position, Piece> getPieces() {
        return Collections.unmodifiableMap(board);
    }
}
