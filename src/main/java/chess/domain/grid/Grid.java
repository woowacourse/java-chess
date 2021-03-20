package chess.domain.grid;

import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
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

    private final Lines lines;
    private final Route route;
    private final Score score;

    public Grid() {
        List<Line> lineGroup = new ArrayList<>();
        lineGroup.add(Line.general(EIGHTH_ROW, true));
        lineGroup.add(Line.pawn(SEVENTH_ROW, true));
        for (int i = SIXTH_ROW; i >= THIRD_ROW; i--) {
            lineGroup.add(Line.empty(i));
        }
        lineGroup.add(Line.pawn(SECOND_ROW, false));
        lineGroup.add(Line.general(FIRST_ROW, false));
        lines = new Lines(lineGroup);
        route = new Route(lines);
        score = new Score(lines);
    }

    public Lines lines() {
        return lines;
    }

    public Score score() {
        return score;
    }

    public void move(final Piece sourcePiece, final Piece targetPiece) {
        sourcePiece.validateSourceAndTargetBeforeMove(sourcePiece, targetPiece);

        if (sourcePiece instanceof Pawn) {
            route.validatePawnSteps((Pawn) sourcePiece, targetPiece);
            ((Pawn) sourcePiece).setMoved();
        }
        if (!(sourcePiece instanceof Pawn)) {
            route.validateGeneralSteps(sourcePiece, targetPiece);
        }

        update(sourcePiece, targetPiece);
    }

    private void update(final Piece sourcePiece, final Piece targetPiece) {
        Position sourcePosition = sourcePiece.position();
        Position targetPosition = targetPiece.position();

        lines.assign(sourcePosition, new Empty(sourcePosition.x(), sourcePosition.y()));
        lines.assign(targetPosition, sourcePiece);
    }
}
