package chess.domain.grid;

import chess.domain.grid.gridStrategy.GridStrategy;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.GameState;

import java.util.List;

public final class Grid {
    private Lines lines;
    private Score score;
    private Color turn;
    private GameState gameState;

    public Grid(GridStrategy gridStrategy) {
        List<Line> lineGroup = gridStrategy.linesInInitGrid();
        this.lines = new Lines(lineGroup);
        this.score = new Score(lines);
        this.turn = Color.WHITE;
        this.gameState = gridStrategy.initGameState();
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
        this.gameState = gameState.move(this, sourcePiece.position(), targetPiece.position());
    }

    public void start() {
        this.gameState = gameState.start();
    }

    public void end() {
        this.gameState = gameState.end();
    }

    public void status() {
        this.gameState = gameState.status();
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

    public void update(final Piece sourcePiece, final Piece targetPiece) {
        Position sourcePosition = sourcePiece.position();
        Position targetPosition = targetPiece.position();
        lines.assign(sourcePosition, new Empty(sourcePosition));
        lines.assign(targetPosition, sourcePiece);
    }

    public boolean isFinished() {
        return gameState.isFinished();
    }

    public void catchKing(Color colorOfCaughtKing) {
        score.catchKing(colorOfCaughtKing);
    }

    public Color winnerColor() {
        return score.winnerColor();
    }

    public boolean isKingCaught() {
        return score.isKingCaught();
    }
}
