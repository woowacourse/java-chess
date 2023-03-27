package chess.domain.chessGame;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public class ReadyChessGameStatus implements ChessGameStatus {
    @Override
    public ChessGameStatus start() {
        return new PlayingChessGameStatus();
    }

    @Override
    public void validateMove(String currentPosition, String nextPosition, Piece movingPiece) {
        throw new IllegalArgumentException("아직 게임을 시작하지 않았습니다.");
    }

    @Override
    public ChessGameStatus end() {
        throw new IllegalArgumentException("아직 게임을 시작하지 않았습니다.");
    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Map<Position, String> getPrintingBoard(Board board) {
        throw new IllegalArgumentException("아직 게임을 시작하지 않았습니다.");
    }

    @Override
    public Map<Color, Double> getScores(Board board) {
        throw new IllegalArgumentException("아직 게임을 시작하지 않았습니다.");
    }

    @Override
    public String getName() {
        return "ready";
    }
}
