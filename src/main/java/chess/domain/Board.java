package chess.domain;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceDirection;
import chess.domain.piece.exception.WrongDirectionException;
import chess.domain.square.Direction;
import chess.domain.square.Square;

public class Board {

    private final Map<Square, Piece> board;

    private Board(final Map<Square, Piece> board) {
        this.board = board;
    }

    public static Board create() {
        BoardMaker boardMaker = new BoardMaker();
        return new Board(boardMaker.make());
    }

    public boolean isPieceTurn(Square current, Color color) {
        Piece piece = getPiece(current);
        return piece.isAlly(color);
    }

    public void move(Square current, Square destination) {
        Piece piece = getPiece(current);
        Direction direction = piece.findDirection(current, destination);

        validateRoute(current, destination, direction);
        checkDestination(current, destination, direction);
        movePiece(current, destination);
    }

    private Piece getPiece(final Square current) {
        if (isEmptySquare(current)) {
            throw new IllegalArgumentException("해당 칸에 말이 존재하지 않습니다.");
        }
        return board.get(current);
    }

    private boolean isEmptySquare(final Square square) {
        return !hasPiece(square);
    }

    private void validateRoute(Square current, Square destination, Direction direction) {
        Square next = current.move(direction);
        if (next.equals(destination)) {
            return;
        }
        if (hasPiece(next)) {
            throw new IllegalArgumentException("해당 기물은 다른 기물을 뛰어 넘을 수 없습니다.");
        }
        validateRoute(next, destination, direction);
    }

    private boolean hasPiece(final Square square) {
        return board.containsKey(square);
    }

    private void checkDestination(Square current, Square destination, Direction direction) {
        if (hasPiece(destination)) {
            checkEnemy(current, destination);
            killEnemy(destination);
            return;
        }
        checkPawn(current, direction);
    }

    private void checkEnemy(Square current, Square destination) {
        if (isAlly(current, destination)) {
            throw new IllegalArgumentException("아군 기물이 있는 곳으로 이동할 수 없습니다.");
        }
    }

    private boolean isAlly(Square current, Square destination) {
        Piece currentPiece = getPiece(current);
        Piece destinationPiece = getPiece(destination);
        return !currentPiece.isEnemy(destinationPiece);
    }

    private void killEnemy(final Square destination) {
        board.remove(destination);
    }

    private void checkPawn(Square current, Direction direction) {
        if (isPawn(current)) {
            checkDiagonal(direction);
        }
    }

    private boolean isPawn(Square square) {
        Piece piece = getPiece(square);
        return piece.isPawn();
    }

    private void checkDiagonal(Direction direction) {
        if (PieceDirection.DIAGONAL.contains(direction)) {
            throw new IllegalArgumentException("폰은 적이 있을 때만 대각선으로 이동할 수 있습니다.");
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
