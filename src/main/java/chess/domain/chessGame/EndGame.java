package chess.domain.chessGame;

import chess.domain.location.Location;
import chess.domain.piece.Piece;
import java.util.Map;
import java.util.function.Supplier;

public class EndGame implements ChessGame {

    @Override
    public boolean isNotEnd() {
        return false;
    }

    @Override
    public ChessGame startGame(Supplier<Boolean> checkRestart) {
        throw new IllegalStateException("이미 게임이 종료되었습니다.");
    }

    @Override
    public ChessGame endGame() {
        throw new IllegalStateException("이미 게임이 종료되었습니다.");
    }

    @Override
    public void move(Location source, Location target) {
        throw new IllegalStateException("이미 게임이 종료되었습니다.");
    }

    @Override
    public Map<Location, Piece> getBoard() {
        throw new IllegalStateException("이미 게임이 종료되었습니다.");
    }
}
