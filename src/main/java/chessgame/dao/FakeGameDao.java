package chessgame.dao;

import chessgame.domain.Board;
import chessgame.domain.Game;
import chessgame.domain.piece.Piece;
import chessgame.domain.point.Point;
import chessgame.domain.state.State;

import java.util.HashMap;
import java.util.Map;

public class FakeGameDao implements GameDao {
    State turn;
    Map<Point, Piece> repository = new HashMap<>();

    @Override
    public void save(Board board, String gameName, State turn) {
        this.turn = turn;
        for (Point point : board.getBoard().keySet()) {
            repository.put(point, board.getBoard().get(point));
        }
    }

    @Override
    public Game read(String gameName) {
        return new Game(new Board(repository), gameName);
    }

    @Override
    public void remove(String gameName) {
        repository.clear();
    }

    @Override
    public String findTurnByGame(String gameName) {
        return turn.getClass().toString();
    }
}
