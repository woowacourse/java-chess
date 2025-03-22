package chess.piece;

import chess.Board;
import chess.Color;
import chess.Column;
import chess.Movement;
import chess.Position;
import chess.Row;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pawn extends Piece {

    private static final List<Movement> MOVEMENTS = List.of(Movement.UP, Movement.UP_UP,
            Movement.DOWN, Movement.DOWN_DOWN,
            Movement.LEFT_DOWN, Movement.LEFT_UP,
            Movement.RIGHT_DOWN, Movement.RIGHT_UP);
    private static final Row INIT_BLACK_ROW = Row.TWO;
    private static final Row INIT_WHITE_ROW = Row.SEVEN;

    private final boolean firstNotMoved;

    public Pawn(final Position position, final Color color, boolean firstNotMoved) {
        super(position, color, PieceType.PAWN);
        this.firstNotMoved = firstNotMoved;
    }

    public static List<Piece> initPawn() {
        List<Piece> initPawn = new ArrayList<>();
        Arrays.stream(Column.values())
                .forEach(column -> initPawn.add(new Pawn(new Position(INIT_BLACK_ROW, column), Color.BLACK, true)));
        Arrays.stream(Column.values())
                .forEach(column -> initPawn.add(new Pawn(new Position(INIT_WHITE_ROW, column), Color.WHITE, true)));
        return initPawn;
    }

    @Override
    public Piece move(final Board board, final Position destination) {
        Movement movement = findMovement(destination, MOVEMENTS);

        if (movement.isDiagonal() && !board.isEnemy(destination, color)) {
            throw new IllegalArgumentException("폰은 적을 잡을 때에만 대각선으로 이동 가능합니다");
        }

        if (movement.isVertical() && board.isExist(destination)) {
            throw new IllegalArgumentException("기물이 존재합니다");
        }

        if (!firstNotMoved && (movement == Movement.UP_UP || movement == Movement.DOWN_DOWN)) {
            throw new IllegalArgumentException("폰은 처음 이동일 경우에만 2칸 이동 가능합니다");
        }

        if (((movement == Movement.UP_UP || movement == Movement.UP) && color.isWhite())
                || ((movement == Movement.DOWN_DOWN || movement == Movement.DOWN) && color.isBlack())) {
            throw new IllegalArgumentException("폰은 본진을 향해 움직일 수 없습니다");
        }
        return new Pawn(position.move(movement), color, false);
    }
}
