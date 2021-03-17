package chess.domain.grid;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Grid {
    private static final int FIRST_ROW = 1;
    private static final int SECOND_ROW = 2;
    private static final int THIRD_ROW = 3;
    private static final int SIXTH_ROW = 6;
    private static final int SEVENTH_ROW = 7;
    private static final int EIGHTH_ROW = 8;
    private static final int GAP_BETWEEN_INDEX_ACTUAL = 1;
    private static List<Line> lines;

    static {
        initialize();
    }

    public static void initialize() {
        lines = new ArrayList<>();
        lines.add(Line.createGeneralLine(FIRST_ROW, false));
        lines.add(Line.createPawnLine(SECOND_ROW, false));
        for (int i = THIRD_ROW; i <= SIXTH_ROW; i++) {
            lines.add(Line.createEmptyLine(i));
        }
        lines.add(Line.createPawnLine(SEVENTH_ROW, true));
        lines.add(Line.createGeneralLine(EIGHTH_ROW, true));
    }

    public static boolean isOccupied(final Position position) {
        return findPiece(position) instanceof Empty;
    }

    public static void move(Position source, Position target) {
        validateMovablePosition(source, target);

        Piece sourcePiece = findPiece(source);
        Piece targetPiece = findPiece(target);
        if (targetPiece.isEmpty()) {
            changePiece(source, new Empty(source));
            return;
        }
        changePiece(target, sourcePiece);
    }

    private static void validateMovablePosition(Position source, Position target) {
        validatePositionInGrid(source, target);
        validateSourcePieceEmpty(source);
        validateMoveDirectionAndStep(source, target);
    }

    private static void validatePositionInGrid(Position source, Position target) {
        if (!source.validatePositionInGrid() || !target.validatePositionInGrid()) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    private static void validateSourcePieceEmpty(Position source) {
        Piece sourcePiece = findPiece(source);
        if (sourcePiece.isEmpty()) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    // 이 부분을 전략으로 빼야돼!
    private static void validateMoveDirectionAndStep(Position source, Position target) {
        Piece sourcePiece = findPiece(source);
        Piece targetPiece = findPiece(target);

        List<Position> movablePositions = new ArrayList<>();
        for (Direction direction : sourcePiece.getDirections()) {
            movablePositions.addAll(extractMovablePositionsInOneDirection(source, direction));
        }
        if (!movablePositions.contains(target)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
        if (!targetPiece.isEmpty() && sourcePiece.isSameColor(targetPiece)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    private static List<Position> extractMovablePositionsInOneDirection(Position source, Direction direction) {
        List<Position> positions = new ArrayList<>();

        Piece sourcePiece = findPiece(source);
        for (int i = 1; i <= sourcePiece.getStepRange(); i++) {
            int xDegree = direction.getXDegree() * i;
            int yDegree = direction.getYDegree() * i;
            Position movedPosition = source.moveBy(xDegree, yDegree);
            if (!movedPosition.validatePositionInGrid()) {
                break;
            }
            positions.add(movedPosition);
            if (!findPiece(movedPosition).isEmpty()) {
                break;
            }
        }
        return positions;
    }

    private static Piece findPiece(final Position position) {
        char x = position.getX();
        char y = position.getY();
        int yIndex = Character.getNumericValue(y) - GAP_BETWEEN_INDEX_ACTUAL;
        Line line = lines.get(yIndex);
        return line.findPiece(x);
    }

    private static void changePiece(Position position, Piece piece) {
        char x = position.getX();
        char y = position.getY();
        int yIndex = Character.getNumericValue(y) - GAP_BETWEEN_INDEX_ACTUAL;
        Line line = lines.get(yIndex);
        line.changePiece(x, piece);
    }
}
