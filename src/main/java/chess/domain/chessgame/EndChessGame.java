package chess.domain.chessgame;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.board.Board;

import java.util.Map;

public class EndChessGame extends ChessGame {

    EndChessGame(final Board board, final Color currentTurnColor) {
        super(board, currentTurnColor);
    }

    @Override
    public ChessGame start() {
        throw new UnsupportedOperationException("게임이 이미 끝났습니다.");
    }

    @Override
    public ChessGame move(final Position currentPosition, final Position targetPosition) {
        throw new UnsupportedOperationException("게임이 이미 끝났습니다.");
    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public boolean isGameOver() {
        return true;
    }

    @Override
    public ChessGame end() {
        throw new UnsupportedOperationException("게임이 이미 끝났습니다.");
    }

    @Override
    public Map<Color, Double> calculateScoreByColor() {
        throw new UnsupportedOperationException("게임이 이미 끝났습니다.");
    }

    @Override
    public Color findScoreWinner() {
        throw new UnsupportedOperationException("게임이 이미 끝났습니다.");
    }
}
