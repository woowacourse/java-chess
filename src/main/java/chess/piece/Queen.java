package chess.piece;

import chess.Board;
import chess.Color;
import chess.Column;
import chess.Movement;
import chess.Position;
import chess.Row;
import java.util.List;

public class Queen extends Piece {

    private static final Position INIT_BLACK_POSITION = new Position(Row.ONE, Column.D);
    private static final Position INIT_WHITE_POSITION = new Position(Row.EIGHT, Column.D);

    public Queen(final Position position, final Color color) {
        super(position, color, PieceType.QUEEN);
    }

    public static List<Piece> initQueen() {
        return List.of(new Queen(INIT_BLACK_POSITION, Color.BLACK), new Queen(INIT_WHITE_POSITION, Color.WHITE));
    }

    @Override
    public Piece move(final Board board, final Position destination) {

        List<Movement> movements = destination.calculateDirection(position);

        if (position.equals(destination)) {
            throw new IllegalArgumentException("같은 위치입니다");
        }

        if (board.isAlly(destination, color)) {
            throw new IllegalArgumentException("아군 기물이 존재합니다");
        }

        Position currentPosition = position;
        Movement lastMovement = movements.removeLast();
        for (Movement current : movements) {
            if (!currentPosition.canMove(current)) {
                throw new IllegalArgumentException("이동할 수 없습니다");
            }
            currentPosition = currentPosition.move(current);
            if (board.isExist(currentPosition)) {
                throw new IllegalArgumentException("기물이 존재합니다");
            }
        }
        return new Bishop(currentPosition.move(lastMovement), color);
    }
}
