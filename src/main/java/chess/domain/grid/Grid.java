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
    private static final String ROW_REFERENCE = "87654321";
    private static List<Line> lines;

    public Grid() {
        initialize();
    }

    public void initialize() {
        lines = new ArrayList<>();
        lines.add(Line.createGeneralLine(EIGHTH_ROW, true));
        lines.add(Line.createPawnLine(SEVENTH_ROW, true));
        for (int i = SIXTH_ROW; i >= THIRD_ROW; i--) {
            lines.add(Line.createEmptyLine(i));
        }
        lines.add(Line.createPawnLine(SECOND_ROW, false));
        lines.add(Line.createGeneralLine(FIRST_ROW, false));
    }

    public void move(Piece sourcePiece, Piece targetPiece) {
        sourcePiece.validateSourceAndTargetBeforeMove(targetPiece);

        if (sourcePiece instanceof Pawn) {
            validatePawnSteps((Pawn)sourcePiece, targetPiece);
            ((Pawn) sourcePiece).moved();
        }
        if (!(sourcePiece instanceof Pawn)) {
            validateGeneralSteps(sourcePiece, targetPiece);
        }

        Position sourcePosition = sourcePiece.getPosition();
        Position targetPosition = targetPiece.getPosition();
        assignPiece(sourcePosition, new Empty(sourcePosition));
        assignPiece(targetPosition, sourcePiece);
    }

    private void validateGeneralSteps(Piece sourcePiece, Piece targetPiece) {
        Position sourcePosition = sourcePiece.getPosition();
        Position targetPosition = targetPiece.getPosition();

        List<Position> movablePositions = new ArrayList<>();
        for (Direction direction : sourcePiece.getDirections()) {
            movablePositions.addAll(extractMovablePositionsByDirection(sourcePosition, direction, sourcePiece.getStepRange()));
        }

        validateTargetInMovablePositions(movablePositions, targetPosition);
    }

    private void validatePawnSteps(Pawn sourcePiece, Piece targetPiece) {
        Position sourcePosition = sourcePiece.getPosition();
        Position targetPosition = targetPiece.getPosition();

        List<Position> movablePositions = new ArrayList<>();
        // 1칸 이동하는 경우
        for (Direction direction : sourcePiece.getDirections()) {
            movablePositions.addAll(extractMovablePositionsByDirection(sourcePosition, direction, sourcePiece.getStepRange()));
        }
        // 2칸 이동하는 경우
        for (Direction direction : sourcePiece.getDirectionsOnTwoStep()) {
            movablePositions.addAll(extractMovablePositionsByDirection(sourcePosition, direction, 2));
        }
        movablePositions = movablePositions.stream()
                .distinct()
                .collect(Collectors.toList());

        if (sourcePiece.hasMoved() && Math.abs(targetPosition.getY() - sourcePosition.getY()) == 2) {
            throw new IllegalArgumentException("폰은 초기 자리에서만 두칸 이동 가능합니다.");
        }

        if (Math.abs(targetPosition.getY() - sourcePosition.getY()) == 1 &&
                Math.abs(targetPosition.getX() - sourcePosition.getX()) == 1 && targetPiece.isEmpty()) {
            throw new IllegalArgumentException("폰은 상대 말을 먹을 때만 대각선으로 이동이 가능합니다.");
        }

        if (Math.abs(targetPosition.getY() - sourcePosition.getY()) == 1 && Math.abs(targetPosition.getX() - sourcePosition.getX()) == 0 && !targetPiece.isEmpty()) {
            throw new IllegalArgumentException("폰은 한칸 앞 말이 있으면 가지 못합니다.");
        }

        validateTargetInMovablePositions(movablePositions, targetPosition);
    }

    private void validateTargetInMovablePositions(List<Position> movablePositions, Position target) {
        if (!movablePositions.contains(target)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    private List<Position> extractMovablePositionsByDirection(Position source, Direction direction, int stepRange) {
        List<Position> positions = new ArrayList<>();

        for (int i = 1; i <= stepRange; i++) {
            int xDegree = direction.getXDegree() * i;
            int yDegree = direction.getYDegree() * i;
            Position movedPosition = source.stepOn(xDegree, yDegree);
            if (!movedPosition.isInGridRange()) {
                break;
            }
            positions.add(movedPosition);
            if (!findPiece(movedPosition).isEmpty()) {
                break;
            }
        }
        return positions;
    }

    public Piece findPiece(final Position position) {
        char x = position.getX();
        char y = position.getY();
        Line line = findLineByYPosition(y);
        return line.findPiece(x);
    }

    private void assignPiece(Position position, Piece piece) {
        char x = position.getX();
        char y = position.getY();
        Line line = findLineByYPosition(y);
        line.assignPiece(x, piece);
        findPiece(position).moveTo(position);
    }

    public List<Line> getLines() {
        return lines;
    }

    public Line findLineByYPosition(char y) {
        int index = ROW_REFERENCE.indexOf(y);
        return lines.get(index);
    }
}
