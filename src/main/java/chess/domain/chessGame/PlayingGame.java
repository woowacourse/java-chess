package chess.domain.chessGame;

import chess.domain.board.Board;
import chess.domain.location.Location;
import chess.domain.piece.Piece;
import java.util.Map;
import java.util.function.Supplier;

public class PlayingGame implements ChessGame {
    private final Board board;

    protected PlayingGame() {
        this.board = new Board();
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }

    @Override
    public ChessGame startGame(Supplier<Boolean> checkRestart) {
        if (checkRestart.get()) {
            return new PlayingGame();
        }
        return this;
    }

    @Override
    public ChessGame endGame() {
        return new EndGame();
    }

    @Override
    public void move(Location source, Location target) {
        board.move(source, target);
    }

    @Override
    public Map<Location, Piece> getBoard() {
        return board.getBoard();
    }
}
