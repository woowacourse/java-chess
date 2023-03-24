package chess.domain;

import chess.domain.exception.EmptySquareException;
import chess.domain.exception.InvalidTurnException;
import chess.domain.exception.WrongDirectionException;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceDirection;
import chess.domain.square.Direction;
import chess.domain.square.Square;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private static final int KING_NUMBER = 2;

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

    public void validateTurn(final Turn turn, final Square current) {
        final Piece piece = getPiece(current);
        if (turn.isWhite() && piece.isBlack()) {
            throw new InvalidTurnException(Color.WHITE);
        }
        if (turn.isBlack() && piece.isWhite()) {
            throw new InvalidTurnException(Color.BLACK);
        }
    }

    public void move(final Square current, final Square destination) {
        final Direction direction = getDirectionOfPiece(current, destination);
        validateRoute(current, destination, direction);
        if (isEmptySquare(destination)) {
            validatePawnDiagonalMove(current, direction);
            movePiece(current, destination);
            return;
        }
        checkEnemy(current, destination);
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
        if (canPawnMoveDiagonal(current, direction)) {
            throw new WrongDirectionException();
        }
    }

    private boolean canPawnMoveDiagonal(final Square current, final Direction direction) {
        final boolean isPawn = board.get(current).isPawn();
        final boolean correctDirection = PieceDirection.DIAGONAL.contains(direction);
        return isPawn && correctDirection;
    }

    private void movePiece(final Square current, final Square destination) {
        board.put(destination, getPiece(current));
        board.remove(current);
    }

    public boolean isKingCaught() {
        return board.values().stream()
                .filter(Piece::isKing)
                .count() < 2;
    }

    public Map<Square, Piece> getBoard() {
        return new HashMap<>(board);
    }
}
