package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.square.Direction;
import chess.domain.square.Square;

import java.util.Map;

public class Board {

    private final Map<Square, Piece> board;

    private Board(final Map<Square, Piece> board) {
        this.board = board;
    }

    public static Board create() {
        BoardMaker boardMaker = new BoardMaker();
        return new Board(boardMaker.make());
    }

    public void move(Square current, Square destination) {
        Piece piece = getPiece(current);
        Direction direction = piece.findDirection(current, destination);
        validateRoute(current, destination, direction);

        if (!isEmptySquare(destination)) {
            checkEnemy(current, destination);
        }

        movePiece(current, destination);
    }

    private void validateRoute(Square current, Square destination, Direction direction) {
        Square next = current.move(direction);
        if (next.equals(destination)) {
            return;
        }
        if (!isEmptySquare(next)) {
            throw new IllegalArgumentException("길막 중입니다.");
        }
        validateRoute(next, destination, direction);
    }

    private void checkEnemy(Square current, Square destination) {
        if (isEnemy(current, destination)) {
            catchEnemy(current, destination);
            return;
        }
        throw new IllegalArgumentException("길막 중입니다.");
    }

    private boolean isEnemy(Square current, Square destination) {
        Piece currentPiece = getPiece(current);
        Piece destinationPiece = getPiece(destination);
        return currentPiece.isEnemy(destinationPiece);
    }

    private void catchEnemy(final Square current, final Square destination) {
        board.remove(destination);
        movePiece(current, destination);
    }

    private Piece getPiece(final Square current) {
        if (isEmptySquare(current)) {
            throw new IllegalArgumentException("해당 칸에 말이 존재하지 않습니다.");
        }
        return board.get(current);
    }

    private boolean isEmptySquare(final Square square) {
        return !board.containsKey(square);
    }

    private void movePiece(final Square current, final Square destination) {
        board.put(destination, getPiece(current));
        board.remove(current);
    }
}
