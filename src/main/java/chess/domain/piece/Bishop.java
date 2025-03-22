package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.Column;
import chess.domain.Direction;
import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.Row;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    private static final List<Position> INIT_BLACK_POSITION = List.of(new Position(Row.ONE, Column.C),
            new Position(Row.ONE, Column.F));
    private static final List<Position> INIT_WHITE_POSITION = List.of(new Position(Row.EIGHT, Column.C),
            new Position(Row.EIGHT, Column.F));

    public Bishop(final Position position, final Color color) {
        super(position, color, PieceType.BISHOP);
    }

    public static List<Piece> initBishop() {
        List<Piece> bishops = new ArrayList<>();
        INIT_BLACK_POSITION.forEach(position -> bishops.add(new Bishop(position, Color.BLACK)));
        INIT_WHITE_POSITION.forEach(position -> bishops.add(new Bishop(position, Color.WHITE)));
        return bishops;
    }

    @Override
    public Piece move(final Board board, final Position destination) {

        List<Movement> movements = Direction.calculateDirection(position, destination);

        if (!movements.stream().allMatch(Movement::isDiagonal)) {
            throw new IllegalArgumentException("규칙에 맞지 않은 움직임 입니다");
        }

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
