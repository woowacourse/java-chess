package chess.domain.grid;

import chess.domain.grid.gridStrategy.GridStrategy;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.stream.Collectors.toMap;

public final class Grid {
    private final Lines lines;
    private final Score score;

    public Grid(final GridStrategy gridStrategy) {
        List<Line> lineGroup = gridStrategy.linesInInitGrid();
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

    public void update(final Piece sourcePiece, final Piece targetPiece) {
        Position sourcePosition = sourcePiece.position();
        Position targetPosition = targetPiece.position();
        lines.assign(sourcePosition, new Empty(sourcePosition));
        lines.assign(targetPosition, sourcePiece);
    }

    public Map<String, String> pieceMap() {
        return lines
                .lines()
                .stream()
                .flatMap(line -> line
                        .pieces()
                        .stream()
                        .filter(piece -> !piece.isEmpty())
                        .map(piece -> new SimpleEntry<>(piece.position().positionToString(),
                                Character.toString(piece.name()))))
                .collect(toMap(Entry::getKey, Entry::getValue));
    }
}
