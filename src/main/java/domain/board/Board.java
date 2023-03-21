package domain.board;

import domain.piece.Piece;
import domain.position.Position;
import domain.position.Positions;
import java.util.Collections;
import java.util.Map;

public final class Board {

    private static final String NOT_EXIST_SOURCE = "해당 위치에 말이 존재하지 않습니다.";
    private static final String INVALID_MOVEMENT = "해당 위치로 말을 이동할 수 없습니다.";

    private final Map<Position, Piece> board;

    private Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board create(ChessAlignment chessStrategy) {
        return new Board(chessStrategy.init());
    }

    public void move(Position source, Position destination) {
        Piece piece = getPiece(source);
        validateRoute(source, destination, piece);

        if (board.containsKey(destination)) {
            killDestination(source, destination, piece);
        }
        if (!board.containsKey(destination)) {
            moveDestination(source, destination, piece);
        }
    }

    private Piece getPiece(final Position source) {
        if (!board.containsKey(source)) {
            throw new IllegalArgumentException(NOT_EXIST_SOURCE);
        }

        return board.get(source);
    }

    private void validateRoute(final Position source, final Position destination, final Piece piece) {
        if (pieceInRoute(source, destination)) {
            throw new IllegalArgumentException(INVALID_MOVEMENT);
        }

        if (teamOnDestination(destination, piece)) {
            throw new IllegalArgumentException(INVALID_MOVEMENT);
        }
    }

    private boolean pieceInRoute(final Position source, final Position destination) {
        return Positions.between(source, destination).stream()
                .anyMatch(board::containsKey);
    }

    private boolean teamOnDestination(final Position destination, final Piece piece) {
        return board.containsKey(destination) &&
                piece.isBlack() == board.get(destination).isBlack();
    }

    private void killDestination(final Position source, final Position destination, final Piece piece) {
        if (piece.isEatable(source, destination)) {
            board.put(destination, board.remove(source));
            return;
        }

        throw new IllegalArgumentException(INVALID_MOVEMENT);
    }

    private void moveDestination(final Position source, final Position destination, final Piece piece) {
        if (piece.isMovable(source, destination)) {
            board.put(destination, board.remove(source));
            return;
        }

        throw new IllegalArgumentException(INVALID_MOVEMENT);
    }

    public Map<Position, Piece> getPieces() {
        return Collections.unmodifiableMap(board);
    }
}
