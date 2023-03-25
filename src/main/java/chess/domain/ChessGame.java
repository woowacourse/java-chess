package chess.domain;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piecesfactory.PiecesFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChessGame {
    private final Board board;
    private final Color currentTurnColor;

    private ChessGame(final Board board, final Color currentTurnColor) {
        this.board = board;
        this.currentTurnColor = currentTurnColor;
    }

    private ChessGame(final Board board) {
        this(board, Color.WHITE);
    }

    public static ChessGame from(final PiecesFactory piecesFactory) {
        return new ChessGame(Board.from(piecesFactory));
    }

    public ChessGame move(final Position currentPosition, final Position targetPosition) {
        board.move(currentTurnColor, currentPosition, targetPosition);
        return new ChessGame(board, currentTurnColor.getOppositeColor());
    }

    public boolean isPlaying() {
        return board.hasPieces();
    }

    public boolean isGameOver() {
        return !board.hasTwoKings();
    }

    public Map<Color, Double> calculateScoreByColor() {
        return board.calculateScoreByColor();
    }

    public Color findScoreWinner() {
        final Map<Color, Double> scoreByColor = board.calculateScoreByColor();
        return getWinnerColor(scoreByColor);
    }

    private Color getWinnerColor(final Map<Color, Double> scoreByColor) {
        if (isDraw(scoreByColor)) {
            return Color.NOTHING;
        }
        return findWinnerColor(scoreByColor);
    }

    private boolean isDraw(final Map<Color, Double> scoreByColor) {
        return 1 == scoreByColor.values()
                .stream()
                .distinct()
                .count();
    }

    private Color findWinnerColor(final Map<Color, Double> scoreByColor) {
        final Double winnerScore = getWinnerScore(scoreByColor);
        return scoreByColor.keySet()
                .stream()
                .filter(color -> Objects.equals(scoreByColor.get(color), winnerScore))
                .findAny()
                .orElseThrow(IllegalStateException::new);
    }

    private Double getWinnerScore(final Map<Color, Double> scoreByColor) {
        return scoreByColor.values()
                .stream()
                .max(Double::compareTo)
                .orElseThrow(IllegalStateException::new);
    }

    public List<Piece> getExistingPieces() {
        return board.getPieces();
    }
}
