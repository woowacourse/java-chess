package chess.domain.chessGame;

import chess.domain.Board;
import chess.domain.position.Position;
import java.util.Map;

public class ReadyChessGame implements ChessGame {
    @Override
    public ChessGame start() {
        Board board = new Board();
        return new PlayingChessGame(board);
    }

    @Override
    public ChessGame move(String currentPosition, String nextPosition) {
        throw new IllegalArgumentException("아직 게임을 시작하지 않았습니다.");
    }

    @Override
    public ChessGame end() {
        throw new IllegalArgumentException("아직 게임을 시작하지 않았습니다.");
    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public Map<Position, String> getPrintingBoard() {
        throw new IllegalArgumentException("아직 게임을 시작하지 않았습니다.");
    }
}
