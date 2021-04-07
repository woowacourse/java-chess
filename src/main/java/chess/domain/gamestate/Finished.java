package chess.domain.gamestate;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.board.Team;
import chess.domain.chessgame.Turn;
import java.util.ArrayList;
import java.util.List;

public class Finished implements GameState {

    private static final IllegalArgumentException EXCEPTION =
        new IllegalArgumentException("올바르지 않은 입력입니다.");

    private final Team winner;
    private final Board board;

    public Finished(Board board, Team winner) {
        this.board = board;
        this.winner = winner;
    }

    @Override
    public GameState start() {
        return new Ready(board).start();
    }

    @Override
    public GameState move(Point source, Point destination, Turn turn) {
        throw EXCEPTION;
    }

    @Override
    public GameState end() {
        throw EXCEPTION;
    }

    @Override
    public GameState status() {
        throw EXCEPTION;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public Team winner() {
        return winner;
    }

    @Override
    public List<Point> movablePoints(Point currentPoint, Turn turn) {
        return new ArrayList<>();
    }
}
