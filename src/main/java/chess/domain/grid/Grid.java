package chess.domain.grid;

import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        validatePositionInGrid(source, target);
        validateSourcePiece(source);
        Piece sourcePiece = findPiece(source);

        if (sourcePiece instanceof Pawn) {
            validatePawnSteps(source, target);
        }
        if (!(sourcePiece instanceof Pawn)){
            validateGeneralSteps(source, target);
        }

        assignPiece(source, new Empty(source));
        assignPiece(target, sourcePiece);
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
    private void validateGeneralSteps(Position source, Position target) {
        Piece sourcePiece = findPiece(source);
        Piece targetPiece = findPiece(target);

        List<Position> movablePositions = new ArrayList<>();
        for (Direction direction : sourcePiece.getDirections()) {
            movablePositions.addAll(extractMovablePositionsByDirection(source, direction, sourcePiece.getStepRange()));
        }
        if (!movablePositions.contains(target)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
        if (!targetPiece.isEmpty() && sourcePiece.isSameColor(targetPiece)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    private void validatePawnSteps(Position source, Position target) {
        Piece sourcePiece = findPiece(source);
        Piece targetPiece = findPiece(target);

        List<Position> movablePositions = new ArrayList<>();
        if (sourcePiece.isBlack()) {
            // 검정 말일 때 - 1칸 이동하는 경우
            for (Direction direction : Direction.blackPawnDirection()) {
                movablePositions.addAll(extractMovablePositionsByDirection(source, direction, sourcePiece.getStepRange()));
            }
            // 검정 말일 때 - 2칸 이동하는 경우
            for (Direction direction : Direction.blackPawnLinearDirection()) {
                movablePositions.addAll(extractMovablePositionsByDirection(source, direction, 2));
            }
        }
        if (!sourcePiece.isBlack()) {
            // 하얀 말일 때 - 1칸 이동하는 경우
            for (Direction direction : Direction.whitePawnDirection()) {
                movablePositions.addAll(extractMovablePositionsByDirection(source, direction, sourcePiece.getStepRange()));
            }
            // 하얀 말일 때 - 2칸 이동하는 경우
            for (Direction direction : Direction.whitePawnLinearDirection()) {
                movablePositions.addAll(extractMovablePositionsByDirection(source, direction, 2));
            }
        }

        // 중복 없애기
        movablePositions = movablePositions.stream().distinct().collect(Collectors.toList());

        // 동일한 부분
        if (!movablePositions.contains(target)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
        if (!targetPiece.isEmpty() && sourcePiece.isSameColor(targetPiece)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    private List<Position> extractMovablePositionsByDirection(Position source, Direction direction, int stepRange) {
        List<Position> positions = new ArrayList<>();

        for (int i = 1; i <= stepRange; i++) {
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
