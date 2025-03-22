package chess.piece;

import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Pawn extends Piece {

    private static final Row WHITE_PAWN_ROW_POSITION = Row.TWO;
    private static final Row BLACK_PAWN_ROW_POSITION = Row.SEVEN;

    private static final Map<Color, List<Function<Position, Position>>> ONE_STEP_MOVEMENTS = Map.of(
            Color.WHITE, List.of(
                    Position::moveUp
            ),
            Color.BLACK, List.of(
                    Position::moveDown
            )
    );

    private static final Map<Color, Function<Position, Position>> TWO_STEP_MOVEMENTS = Map.of(
            Color.WHITE, position -> position.moveUp().moveUp (),
            Color.BLACK, position -> position.moveDown().moveDown()
    );

    private static final Map<Color, List<Function<Position, Position>>> DIAGONAL_STEP_MOVEMENTS = Map.of(
            Color.WHITE, List.of(
                    Position::moveLeftUp, Position::moveRightUp
            ),
            Color.BLACK, List.of(
                    Position::moveLeftDown, Position::moveRightDown
            )
    );


    private boolean isMoved;

    public Pawn(final Position position, final Color color, final boolean isMoved) {
        super(position, color);
        this.isMoved = isMoved;
    }

    public static List<Piece> initialize() {
        final List<Piece> pawns = new ArrayList<>();
        for (Column column : Column.values()) {
            pawns.add(new Pawn(new Position(WHITE_PAWN_ROW_POSITION, column), Color.WHITE, false));
            pawns.add(new Pawn(new Position(BLACK_PAWN_ROW_POSITION, column), Color.BLACK, false));
        }
        return pawns;
    }

    @Override
    public List<Position> calculateAvailablePositions(final Board board) {
        final List<Position> positions = new ArrayList<>();
        Position movedPosition;
        for (Function<Position, Position> function : ONE_STEP_MOVEMENTS.get(getColor())) {
            try {
                movedPosition = function.apply(getPosition());
            } catch (IllegalStateException e) {
                continue;
            }
            if (!isAvailablePosition(board, movedPosition)) {
                continue;
            }
            positions.add(movedPosition);
        }

        for (Function<Position, Position> function : DIAGONAL_STEP_MOVEMENTS.get(getColor())) {
            try {
                movedPosition = function.apply(getPosition());
            } catch (IllegalStateException e) {
                continue;
            }
            if (!board.isEnemy(movedPosition, this)) {
                continue;
            }
            positions.add(movedPosition);
        }

        final Function<Position, Position> function = TWO_STEP_MOVEMENTS.get(getColor());
        try {
            movedPosition = function.apply(getPosition());
            if (!isMoved && isAvailablePosition(board, movedPosition)) {
                positions.add(movedPosition);
            }
        } catch (IllegalStateException ignored) {
        }
        return positions;
    }

    @Override
    public Piece copyOf(final Position position) {
        return new Pawn(position, getColor(), true);
    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }
}
