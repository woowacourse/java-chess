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
    private static final int SAME_COLUMN_BOUND = 2;
    private static final int DIVIDER_FOR_PAWN_SCORE = 2;
    private static final int LINE_COUNT = 8;
    private static final String ROW_REFERENCE = "87654321";
    private static final char MIN_X_POSITION = 'a';
    private static List<Line> lines;

    public Grid() {
        initialize();
    }

    public void move(final Piece sourcePiece, final Piece targetPiece) {
        sourcePiece.validateSourceAndTargetBeforeMove(sourcePiece, targetPiece);

        if (sourcePiece instanceof Pawn) {
            validatePawnSteps((Pawn) sourcePiece, targetPiece);
            ((Pawn) sourcePiece).moved();
        }
        if (!(sourcePiece instanceof Pawn)) {
            validateGeneralSteps(sourcePiece, targetPiece);
        }

        update(sourcePiece, targetPiece);
    }

    public Piece piece(final Position position) {
        char x = position.x();
        char y = position.y();
        Line line = line(y);
        return line.findPiece(x);
    }

    public List<Line> lines() {
        return lines;
    }

    public double score(final boolean isBlack) {
        return totalScore(isBlack) - pawnScoreInSameColumn(isBlack);
    }

    private void initialize() {
        lines = new ArrayList<>();
        lines.add(Line.general(EIGHTH_ROW, true));
        lines.add(Line.pawn(SEVENTH_ROW, true));
        for (int i = SIXTH_ROW; i >= THIRD_ROW; i--) {
            lines.add(Line.empty(i));
        }
        lines.add(Line.pawn(SECOND_ROW, false));
        lines.add(Line.general(FIRST_ROW, false));
    }

    private Line line(final char y) {
        int index = ROW_REFERENCE.indexOf(y);
        return lines.get(index);
    }

    private double pawnScoreInSameColumn(final boolean isBlack) {
        double pawnScoreToDeduct = 0;
        for (int i = 0; i < LINE_COUNT; i++) {
            pawnScoreToDeduct += pawnCountInSameColumn(isBlack, i);
        }
        return pawnScoreToDeduct / DIVIDER_FOR_PAWN_SCORE;
    }

    private double pawnCountInSameColumn(final boolean isBlack, final int i) {
        double result = 0;
        char x = (char) (MIN_X_POSITION + i);
        int pawnCountInSameColumn =
                (int) lines.stream()
                        .map(line -> line.findPiece(x))
                        .filter(piece -> (piece instanceof Pawn && piece.isBlack() == isBlack))
                        .count();
        if (pawnCountInSameColumn >= SAME_COLUMN_BOUND) {
            result += pawnCountInSameColumn;
        }
        return result;
    }

    private double totalScore(final boolean isBlack) {
        return lines.stream()
                .flatMap(line -> line.getPieces()
                        .stream()
                        .filter(piece -> !piece.isEmpty())
                        .filter(piece -> piece.isBlack() == isBlack)
                        .map(Piece::score))
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    private void assign(final Position position, final Piece piece) {
        char x = position.x();
        char y = position.y();
        Line line = line(y);
        line.assignPiece(x, piece);
        piece(position).moveTo(position);
    }

    private void validateGeneralSteps(final Piece sourcePiece, final Piece targetPiece) {
        List<Position> movablePositions = new ArrayList<>();
        for (Direction direction : sourcePiece.directions()) {
            movablePositions.addAll(route(sourcePiece, direction, sourcePiece.stepRange()));
        }
        targetPiece.validateTargetInMovablePositions(movablePositions);
    }

    private void validatePawnSteps(final Pawn sourcePiece, final Piece targetPiece) {
        List<Position> movablePositions = new ArrayList<>();
        for (Direction direction : sourcePiece.directions()) {
            movablePositions.addAll(route(sourcePiece, direction, sourcePiece.stepRange()));
        }
        for (Direction direction : sourcePiece.twoStepDirections()) {
            movablePositions.addAll(route(sourcePiece, direction, sourcePiece.twoStepRange()));
        }
        movablePositions = movablePositions.stream()
                .distinct()
                .collect(Collectors.toList());

        sourcePiece.validatePawnMove(targetPiece);
        targetPiece.validateTargetInMovablePositions(movablePositions);
    }

    private List<Position> route(final Piece sourcePiece, final Direction direction, final int stepRange) {
        List<Position> positions = new ArrayList<>();
        Position sourcePosition = sourcePiece.position();

        for (int i = 1; i <= stepRange; i++) {
            Position movedPosition = sourcePosition.next(direction.getXDegree() * i, direction.getYDegree() * i);
            if (!movedPosition.isInGridRange()) {
                break;
            }
            positions.add(movedPosition);
            if (!piece(movedPosition).isEmpty()) {
                break;
            }
        }
        return positions;
    }

    private void update(final Piece sourcePiece, final Piece targetPiece) {
        Position sourcePosition = sourcePiece.position();
        Position targetPosition = targetPiece.position();

        assign(sourcePosition, new Empty(sourcePosition.x(), sourcePosition.y()));
        assign(targetPosition, sourcePiece);
    }
}
