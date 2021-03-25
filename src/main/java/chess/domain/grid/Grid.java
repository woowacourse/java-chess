package chess.domain.grid;

import chess.domain.grid.gridStrategy.GridStrategy;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.List;

public final class Grid {
    private final Lines lines;
    private final Score score;

    public Grid(final GridStrategy gridStrategy) {
        List<Line> lineGroup = gridStrategy.LinesInInitGrid();
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
