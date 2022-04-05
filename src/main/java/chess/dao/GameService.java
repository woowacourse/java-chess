package chess.dao;

import chess.model.state.State;
import java.util.Map;

public class GameService {

    private final StateDao stateDao;
    private final SquareDao squareDao;

    public GameService() {
        this.stateDao = new StateDao();
        this.squareDao = new SquareDao();
    }

    public Map<String, String> loadBoard() {
        String stateName = stateDao.find();
        Map<String, String> squares = squareDao.find();
        State state = StateGenerator.generateFrom(stateName);
        return state.getSquares();
    }
}
