package chess.domain.chessGame;

import chess.domain.position.Position;
import java.util.Map;

public class EndChessGame implements ChessGame {
    @Override
    public ChessGame start() {
        throw new IllegalArgumentException("이미 종료된 게임입니다.");
    }

    @Override
    public ChessGame move(String currentPosition, String nextPosition) {
        throw new IllegalArgumentException("이미 종료된 게임입니다.");
    }

    @Override
    public ChessGame end() {
        throw new IllegalArgumentException("이미 종료된 게임입니다.");
    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public Map<Position, String> getPrintingBoard() {
        throw new IllegalArgumentException("이미 종료된 게임입니다.");
    }
}
