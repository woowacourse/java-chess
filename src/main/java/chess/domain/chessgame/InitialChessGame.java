package chess.domain.chessgame;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.board.Board;

import java.util.Map;

public class InitialChessGame extends ChessGame {

    InitialChessGame(final Board board, final Color color) {
        super(board, color);
    }

    InitialChessGame(final Board board) {
        this(board, Color.WHITE);
    }

    @Override
    public ChessGame start() {
        return new PlayingChessGame(board, currentTurnColor);
    }

    @Override
    public ChessGame move(final Position currentPosition, final Position targetPosition) {
        throw new UnsupportedOperationException("게임이 시작되지 않았습니다.");
    }

    @Override
    public boolean isPlaying() {
        return false;
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
        throw new UnsupportedOperationException("게임이 시작되지 않았습니다.");
    }

    @Override
    public Color findScoreWinner() {
        throw new UnsupportedOperationException("게임이 시작되지 않았습니다.");
    }
}
