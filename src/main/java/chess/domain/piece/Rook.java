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

public class Rook extends Piece {

    private static final List<Position> INIT_BLACK_POSITION = List.of(new Position(Row.ONE, Column.A),
            new Position(Row.ONE, Column.H));
    private static final List<Position> INIT_WHITE_POSITION = List.of(new Position(Row.EIGHT, Column.A),
            new Position(Row.EIGHT, Column.H));

    public Rook(final Position position, final Color color) {
        super(position, color, PieceType.ROOK);
    }

    public static List<Piece> initRook() {
        List<Piece> rooks = new ArrayList<>();
        INIT_BLACK_POSITION.forEach(position -> rooks.add(new Rook(position, Color.BLACK)));
        INIT_WHITE_POSITION.forEach(position -> rooks.add(new Rook(position, Color.WHITE)));
        return rooks;
    }

    @Override
    public Piece move(final Board board, final Position destination) {

        List<Movement> movements = Direction.calculateDirection(position, destination);

        if (!movements.stream().allMatch(movement -> movement.isVertical() || movement.isHorizontal())) {
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
        return new Rook(currentPosition.move(lastMovement), color);
    }
}
