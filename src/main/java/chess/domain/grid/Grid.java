package chess.domain.grid;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private static final int FIRST_ROW = 1;
    private static final int SECOND_ROW = 2;
    private static final int THIRD_ROW = 3;
    private static final int SIXTH_ROW = 6;
    private static final int SEVENTH_ROW = 7;
    private static final int EIGHTH_ROW = 8;
    private static final int GAP_BETWEEN_INDEX_ACTUAL = 1;
    private static List<Line> lines;

    public Grid() {
        initialize();
    }

    public void initialize() {
        lines = new ArrayList<>();
        lines.add(Line.createGeneralLine(FIRST_ROW, false));
        lines.add(Line.createPawnLine(SECOND_ROW, false));
        for (int i = THIRD_ROW; i <= SIXTH_ROW; i++) {
            lines.add(Line.createEmptyLine(i));
        }
        lines.add(Line.createPawnLine(SEVENTH_ROW, true));
        lines.add(Line.createGeneralLine(EIGHTH_ROW, true));
    }

    public void move(Position source, Position target) {
        validateMovablePosition(source, target);

        Piece sourcePiece = findPiece(source);

        assignPiece(source, new Empty(source));
        assignPiece(target, sourcePiece);
    }

    private void validateMovablePosition(Position source, Position target) {
        validatePositionInGrid(source, target);
        validateSourcePiece(source);
        validateMoveDirectionAndStep(source, target);
    }

    private void validatePositionInGrid(Position source, Position target) {
        if (!source.validatePositionInGrid() || !target.validatePositionInGrid()) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    private void validateSourcePiece(Position source) {
        Piece sourcePiece = findPiece(source);
        if (sourcePiece.isEmpty()) {   // + 자신의 말 색깔일때
            throw new IllegalArgumentException("자신의 말만 옮길 수 있습니다.");
        }
    }

    // 이 부분을 전략으로 빼야돼!
    private void validateMoveDirectionAndStep(Position source, Position target) {
        Piece sourcePiece = findPiece(source);
        Piece targetPiece = findPiece(target);

        List<Position> movablePositions = new ArrayList<>();
        for (Direction direction : sourcePiece.getDirections()) {
            movablePositions.addAll(extractMovablePositionsByDirection(source, direction));
        }
        if (!movablePositions.contains(target)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
        if (!targetPiece.isEmpty() && sourcePiece.isSameColor(targetPiece)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    private List<Position> extractMovablePositionsByDirection(Position source, Direction direction) {
        List<Position> positions = new ArrayList<>();

        Piece sourcePiece = findPiece(source);
        for (int i = 1; i <= sourcePiece.getStepRange(); i++) {
            int xDegree = direction.getXDegree() * i;
            int yDegree = direction.getYDegree() * i;
            Position movedPosition = source.stepOn(xDegree, yDegree);
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

    private Piece findPiece(final Position position) {
        char x = position.getX();
        char y = position.getY();
        int yIndex = Character.getNumericValue(y) - GAP_BETWEEN_INDEX_ACTUAL;
        Line line = lines.get(yIndex);
        return line.findPiece(x);
    }

    private void assignPiece(Position position, Piece piece) {
        char x = position.getX();
        char y = position.getY();
        int yIndex = Character.getNumericValue(y) - GAP_BETWEEN_INDEX_ACTUAL;
        Line line = lines.get(yIndex);
        line.assignPiece(x, piece);
    }
}
