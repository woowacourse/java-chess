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
    private static final int DIVIDER_FOR_PAWN_SCORE = 2;
    private static final int LINE_COUNT = 8;
    private Lines lines;

    public Grid() {
        initialize();
    }

    public void move(final Piece sourcePiece, final Piece targetPiece) {
        sourcePiece.validateSourceAndTargetBeforeMove(sourcePiece, targetPiece);

        if (sourcePiece instanceof Pawn) {
            validatePawnSteps((Pawn) sourcePiece, targetPiece);
            ((Pawn) sourcePiece).setMoved();
        }
        if (!(sourcePiece instanceof Pawn)) {
            validateGeneralSteps(sourcePiece, targetPiece);
        }

        update(sourcePiece, targetPiece);
    }

    public double score(final boolean isBlack) {
        return lines.totalScore(isBlack) - pawnScoreInSameColumn(isBlack);
    }

    private void initialize() {
        List<Line> lineGroup = new ArrayList<>();
        lineGroup.add(Line.general(EIGHTH_ROW, true));
        lineGroup.add(Line.pawn(SEVENTH_ROW, true));
        for (int i = SIXTH_ROW; i >= THIRD_ROW; i--) {
            lineGroup.add(Line.empty(i));
        }
        lineGroup.add(Line.pawn(SECOND_ROW, false));
        lineGroup.add(Line.general(FIRST_ROW, false));
        lines = new Lines(lineGroup);
    }

    private double pawnScoreInSameColumn(final boolean isBlack) {
        double pawnScoreToDeduct = 0;
        for (int i = 0; i < LINE_COUNT; i++) {
            pawnScoreToDeduct += lines.pawnCountInSameColumn(isBlack, i);
        }
        return pawnScoreToDeduct / DIVIDER_FOR_PAWN_SCORE;
    }

    private void validateGeneralSteps(final Piece sourcePiece, final Piece targetPiece) {
        List<Position> movablePositions = new ArrayList<>();
        for (Direction direction : sourcePiece.directions()) {
            movablePositions.addAll(lines.route(sourcePiece, direction, sourcePiece.stepRange()));
        }
        targetPiece.validateTargetInMovablePositions(movablePositions);
    }

    private void validatePawnSteps(final Pawn sourcePiece, final Piece targetPiece) {
        List<Position> movablePositions = new ArrayList<>();
        for (Direction direction : sourcePiece.directions()) {
            movablePositions.addAll(lines.route(sourcePiece, direction, sourcePiece.stepRange()));
        }
        for (Direction direction : sourcePiece.twoStepDirections()) {
            movablePositions.addAll(lines.route(sourcePiece, direction, sourcePiece.twoStepRange()));
        }
        movablePositions = movablePositions.stream()
                .distinct()
                .collect(Collectors.toList());

        sourcePiece.validatePawnMove(targetPiece);
        targetPiece.validateTargetInMovablePositions(movablePositions);
    }

    private void update(final Piece sourcePiece, final Piece targetPiece) {
        Position sourcePosition = sourcePiece.position();
        Position targetPosition = targetPiece.position();

        lines.assign(sourcePosition, new Empty(sourcePosition.x(), sourcePosition.y()));
        lines.assign(targetPosition, sourcePiece);
    }
}
