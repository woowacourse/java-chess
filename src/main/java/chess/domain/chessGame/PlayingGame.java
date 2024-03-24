package chess.domain.chessGame;

import chess.domain.board.Board;
import chess.domain.location.Location;
import chess.domain.piece.Piece;
import java.util.Map;

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
    public ChessGame startGame() {
        throw new IllegalStateException("게임이 진행중입니다. 게임을 재시작 할 수 없습니다.");
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
