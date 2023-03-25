package chess.domain.chessgame;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.board.Board;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PlayingChessGame extends ChessGame {

    PlayingChessGame(final Board board, final Color currentTurnColor) {
        super(board, currentTurnColor);
    }

    @Override
    public ChessGame start() {
        throw new UnsupportedOperationException("게임이 이미 시작되었습니다.");
    }

    @Override
    public ChessGame move(final Position currentPosition, final Position targetPosition) {
        board.move(currentTurnColor, currentPosition, targetPosition);
        return new PlayingChessGame(board, currentTurnColor.getOppositeColor());
    }

    @Override
    public boolean isPlaying() {
        return true;
    }

    @Override
    public boolean isGameOver() {
        return !board.hasTwoKings();
    }

    @Override
    public ChessGame end() {
        return new EndChessGame(board, currentTurnColor);
    }

    @Override
    public Map<Color, Double> calculateScoreByColor() {
        return board.calculateScoreByColor();
    }

    @Override
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

    @Override
    public List<Piece> getPieces() {
        return board.getPieces();
    }
}
