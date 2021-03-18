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
    private static final int LINE_COUNT = 8;
    private static final char MIN_X_POSITION = 'a';
    private static final int SAME_COLUMN_BOUND = 2;
    private static final int DIVIDER_FOR_PAWN_SCORE = 2;
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
            validatePawnSteps((Pawn) sourcePiece, targetPiece);
            ((Pawn) sourcePiece).moved();
        }
        if (!(sourcePiece instanceof Pawn)) {
            validateGeneralSteps(sourcePiece, targetPiece);
        }

        updatePiecePosition(sourcePiece, targetPiece);
    }

    public Piece findPiece(final Position position) {
        char x = position.getX();
        char y = position.getY();
        Line line = findLineByYPosition(y);
        return line.findPiece(x);
    }

    public List<Line> getLines() {
        return lines;
    }

    public Line findLineByYPosition(char y) {
        int index = ROW_REFERENCE.indexOf(y);
        return lines.get(index);
    }

    public double calculateScore(boolean isBlack) {
        return calculateTotalScore(isBlack) - deductPawnScoreInSameColumn(isBlack);
    }

    private double deductPawnScoreInSameColumn(boolean isBlack) {
        double pawnScoreToDeduct = 0;
        for (int i = 0; i < LINE_COUNT; i++) {
            pawnScoreToDeduct += calculatePawnCountInSameColumn(isBlack, i);
        }
        return pawnScoreToDeduct / DIVIDER_FOR_PAWN_SCORE;
    }

    private double calculatePawnCountInSameColumn(boolean isBlack, int i) {
        double result = 0;
        char x = (char)(MIN_X_POSITION + i);
        int pawnCountInSameColumn = (int)lines.stream().map(line -> line.findPiece(x))
                .filter(piece -> (piece instanceof Pawn && piece.isBlack() == isBlack))
                .count();
        if (pawnCountInSameColumn >= SAME_COLUMN_BOUND) {
            result += pawnCountInSameColumn;
        }
        return result;
    }

    private double calculateTotalScore(boolean isBlack) {
        return lines.stream()
                .flatMap(line -> line.getPieces()
                        .stream()
                        .filter(piece -> !piece.isEmpty())
                        .filter(piece -> piece.isBlack() == isBlack)
                        .map(Piece::getScore))
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    private void assignPiece(Position position, Piece piece) {
        char x = position.getX();
        char y = position.getY();
        Line line = findLineByYPosition(y);
        line.assignPiece(x, piece);
        findPiece(position).moveTo(position);
    }

    private void validateGeneralSteps(Piece sourcePiece, Piece targetPiece) {
        List<Position> movablePositions = new ArrayList<>();
        for (Direction direction : sourcePiece.getDirections()) {
            movablePositions.addAll(extractRoute(sourcePiece, direction, sourcePiece.getStepRange()));
        }
        targetPiece.validateTargetInMovablePositions(movablePositions);
    }

    private void validatePawnSteps(Pawn sourcePiece, Piece targetPiece) {
        List<Position> movablePositions = new ArrayList<>();
        for (Direction direction : sourcePiece.getDirections()) {
            movablePositions.addAll(extractRoute(sourcePiece, direction, sourcePiece.getStepRange()));
        }
        for (Direction direction : sourcePiece.getDirectionsOnTwoStep()) {
            movablePositions.addAll(extractRoute(sourcePiece, direction, sourcePiece.getTwoStepRange()));
        }
        movablePositions = movablePositions.stream()
                .distinct()
                .collect(Collectors.toList());

        sourcePiece.validatePawnMove(targetPiece);
        targetPiece.validateTargetInMovablePositions(movablePositions);
    }

    private List<Position> extractRoute(Piece sourcePiece, Direction direction, int stepRange) {
        List<Position> positions = new ArrayList<>();
        Position sourcePosition = sourcePiece.getPosition();

        for (int i = 1; i <= stepRange; i++) {
            Position movedPosition = sourcePosition.stepOn(direction.getXDegree() * i, direction.getYDegree() * i);
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

    private void updatePiecePosition(Piece sourcePiece, Piece targetPiece) {
        Position sourcePosition = sourcePiece.getPosition();
        Position targetPosition = targetPiece.getPosition();

        assignPiece(sourcePosition, new Empty(sourcePosition));
        assignPiece(targetPosition, sourcePiece);
    }
}
