package chess.domain.chessGame;

import chess.domain.location.Location;
import chess.domain.piece.Piece;
import java.util.Map;

public class InitialGame implements ChessGame {

    @Override
    public boolean isNotEnd() {
        return true;
    }

    @Override
    public ChessGame startGame() {
        return new PlayingGame();
    }

    @Override
    public ChessGame endGame() {
        return new EndGame();
    }

    @Override
    public void move(Location source, Location target) {
        throw new IllegalStateException("게임을 먼저 시작해야 합니다.");
    }

    @Override
    public Map<Location, Piece> getBoard() {
        throw new IllegalStateException("게임을 먼저 시작해야 합니다.");
    }
}
