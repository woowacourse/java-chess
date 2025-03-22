package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.Column;
import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.Row;
import java.util.List;

public class King extends Piece {

    private static final List<Movement> MOVEMENTS = List.of(Movement.UP, Movement.DOWN, Movement.LEFT_DOWN,
            Movement.LEFT_UP, Movement.RIGHT_DOWN, Movement.RIGHT_UP);
    private static final Position INIT_BLACK_POSITION = new Position(Row.ONE, Column.E);
    private static final Position INIT_WHITE_POSITION = new Position(Row.EIGHT, Column.E);

    public King(final Position position, final Color color) {
        super(position, color, PieceType.KING);
    }

    public static List<Piece> initKing() {
        return List.of(new King(INIT_BLACK_POSITION, Color.BLACK), new King(INIT_WHITE_POSITION, Color.WHITE));
    }

    @Override
    public Piece move(final Board board, final Position destination) {
        Movement movement = findMovement(destination, MOVEMENTS);
        if (board.isAlly(destination, color)) {
            throw new IllegalArgumentException("아군 기물이 존재합니다");
        }
        return new King(position.move(movement), color);
    }
}
