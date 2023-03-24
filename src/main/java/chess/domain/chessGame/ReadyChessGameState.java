package chess.domain.chessGame;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public class ReadyChessGameState implements ChessGameState {
    @Override
    public ChessGameState start() {
        return new PlayingChessGameState();
    }

    @Override
    public void validateMove(String currentPosition, String nextPosition, Piece movingPiece) {
        throw new IllegalArgumentException("아직 게임을 시작하지 않았습니다.");
    }

    @Override
    public ChessGameState end() {
        throw new IllegalArgumentException("아직 게임을 시작하지 않았습니다.");
    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public Map<Position, String> getPrintingBoard(Board board) {
        throw new IllegalArgumentException("아직 게임을 시작하지 않았습니다.");
    }
}
