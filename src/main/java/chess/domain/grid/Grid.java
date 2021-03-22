package chess.domain.grid;

import chess.domain.grid.gridStrategy.GridStrategy;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.List;

public final class Grid {
    private Lines lines;
    private Score score;
    private Color turn;

    public Grid(GridStrategy gridStrategy) {
        List<Line> lineGroup = gridStrategy.linesInInitGrid();
        this.lines = new Lines(lineGroup);
        this.score = new Score(lines);
        this.turn = Color.WHITE;
    }

    public Lines lines() {
        return lines;
    }

    public Piece piece(final Position position) {
        return lines.piece(position);
    }

    public double score(final Color color) {
        return score.score(color);
    }

    public void move(final Piece sourcePiece, final Piece targetPiece) {
        sourcePiece.validateRoute(targetPiece, lines);
        changeTurn();
        update(sourcePiece, targetPiece);
    }

    public void changeTurn() {
        if (turn == Color.WHITE) {
            turn = Color.BLACK;
            return;
        }
        turn = Color.WHITE;
    }

    public boolean isMyTurn(Color color) {
        return turn == color;
    }

    private void update(final Piece sourcePiece, final Piece targetPiece) {
        Position sourcePosition = sourcePiece.position();
        Position targetPosition = targetPiece.position();
        lines.assign(sourcePosition, new Empty(sourcePosition));
        lines.assign(targetPosition, sourcePiece);
    }
}
