package chess.piece;

import chess.Board;
import chess.Color;
import chess.Column;
import chess.Movement;
import chess.Position;
import chess.Row;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    private static final List<Position> INIT_BLACK_POSITION = List.of(new Position(Row.ONE, Column.B),
            new Position(Row.ONE, Column.G));
    private static final List<Position> INIT_WHITE_POSITION = List.of(new Position(Row.EIGHT, Column.B),
            new Position(Row.EIGHT, Column.G));

    public Knight(final Position position, final Color color) {
        super(position, color, PieceType.KNIGHT);
    }

    public static List<Piece> initKnight() {
        List<Piece> knights = new ArrayList<>();
        INIT_BLACK_POSITION.forEach(position -> knights.add(new Knight(position, Color.BLACK)));
        INIT_WHITE_POSITION.forEach(position -> knights.add(new Knight(position, Color.WHITE)));
        return knights;
    }

    @Override
    public Piece move(final Board board, final Position destination) {

        List<Movement> movements = destination.calculateDirection(position);

        if (movements.size() > 1) {
            throw new IllegalArgumentException("규칙에 맞지 않은 움직임 입니다");
        }

        if (position.equals(destination)) {
            throw new IllegalArgumentException("같은 위치입니다");
        }

        if (board.isAlly(destination, color)) {
            throw new IllegalArgumentException("아군 기물이 존재합니다");
        }

        return new Knight(position.move(movements.getFirst()), color);
    }
}
