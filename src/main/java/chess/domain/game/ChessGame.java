package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.game.state.Ready;
import chess.domain.game.state.State;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.point.Point;

import java.util.Map;

public class ChessGame {
    private final Board board;
    private State state;

    public ChessGame(Map<Point, Piece> board) {
        this.board = new Board(board);
        this.state = new Ready(Team.WHITE);
    }

    public boolean isPlayable() {
        return !state.isEnd();
    }

    public void finish() {
        state = state.finish();
    }

    public void start() {
        state = state.start();
    }

    public void move(Point departure, Point destination) {
        state = state.move(board, departure, destination);
    }

    public Map<Point, Piece> getBoard() {
        return board.getBoard();
    }
}
