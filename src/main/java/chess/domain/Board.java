package chess.domain;

import chess.domain.exception.EmptySquareException;
import chess.domain.exception.WrongDirectionException;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceDirection;
import chess.domain.square.Direction;
import chess.domain.square.Square;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Square, Piece> board;

    private Board(final Map<Square, Piece> board) {
        this.board = board;
    }

    public static Board create() {
        final BoardFactory boardFactory = new BoardFactory();
        return new Board(boardFactory.make());
    }

    public void reset() {
        board.clear();
        final BoardFactory boardFactory = new BoardFactory();
        board.putAll(boardFactory.make());
    }

    public void move(final Square current, final Square destination) {
        final Direction direction = getDirectionOfPiece(current, destination);
        validateRoute(current, destination, direction);
        if (!isEmptySquare(destination)) {
            checkEnemy(current, destination);
            return;
        }
        validatePawnDiagonalMove(current, direction);
        movePiece(current, destination);
    }

    private Direction getDirectionOfPiece(final Square current, final Square destination) {
        final Piece piece = getPiece(current);
        return piece.findDirection(current, destination);
    }

    private Piece getPiece(final Square current) {
        if (isEmptySquare(current)) {
            throw new EmptySquareException();
        }
        return board.get(current);
    }

    private boolean isEmptySquare(final Square square) {
        return !board.containsKey(square);
    }

    private void validateRoute(final Square current, final Square destination, final Direction direction) {
        final Square next = current.move(direction);
        if (next.equals(destination)) {
            return;
        }
        validateNonBlocked(next);
        validateRoute(next, destination, direction);
    }

    private void validateNonBlocked(final Square square) {
        if (isEmptySquare(square)) {
            return;
        }
        throw new WrongDirectionException();
    }

    private void checkEnemy(Square current, Square destination) {
        if (isEnemy(current, destination)) {
            catchEnemy(current, destination);
            return;
        }
        throw new WrongDirectionException();
    }

    private boolean isEnemy(final Square current, final Square destination) {
        final Piece currentPiece = getPiece(current);
        final Piece destinationPiece = getPiece(destination);
        return currentPiece.isEnemy(destinationPiece);
    }

    private void catchEnemy(final Square current, final Square destination) {
        board.remove(destination);
        movePiece(current, destination);
    }

    private void validatePawnDiagonalMove(final Square current, final Direction direction) {
        if (board.get(current).isPawn() && PieceDirection.DIAGONAL.contains(direction)) {
            throw new WrongDirectionException();
        }
    }

    private void movePiece(final Square current, final Square destination) {
        board.put(destination, getPiece(current));
        board.remove(current);
    }

    public Map<Square, Piece> getBoard() {
        return new HashMap<>(board);
    }
}
