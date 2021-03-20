package chess.domain.grid;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public final class Grid {
    private static final int FIRST_ROW = 1;
    private static final int SECOND_ROW = 2;
    private static final int THIRD_ROW = 3;
    private static final int SIXTH_ROW = 6;
    private static final int SEVENTH_ROW = 7;
    private static final int EIGHTH_ROW = 8;

    private final Lines lines;
    private final Score score;

    public Grid() {
        List<Line> lineGroup = new ArrayList<>();
        lineGroup.add(Line.general(EIGHTH_ROW, Color.BLACK));
        lineGroup.add(Line.pawn(SEVENTH_ROW, Color.BLACK));
        for (int i = SIXTH_ROW; i >= THIRD_ROW; i--) {
            lineGroup.add(Line.empty(i));
        }
        lineGroup.add(Line.pawn(SECOND_ROW, Color.WHITE));
        lineGroup.add(Line.general(FIRST_ROW, Color.WHITE));
        lines = new Lines(lineGroup);
        score = new Score(lines);
    }

    public final Lines lines() {
        return lines;
    }

    public final Piece piece(final Position position) {
        return lines.piece(position);
    }

    public final double score(final Color color) {
        return score.score(color);
    }

    public final void move(final Piece sourcePiece, final Piece targetPiece) {
        sourcePiece.validateRoute(targetPiece, lines);
        update(sourcePiece, targetPiece);
    }

    private void update(final Piece sourcePiece, final Piece targetPiece) {
        Position sourcePosition = sourcePiece.position();
        Position targetPosition = targetPiece.position();
        lines.assign(sourcePosition, new Empty(sourcePosition));
        lines.assign(targetPosition, sourcePiece);
    }
}
