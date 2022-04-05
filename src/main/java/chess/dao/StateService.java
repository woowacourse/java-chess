package chess.dao;

import chess.model.piece.Piece;
import chess.model.position.Position;
import chess.model.state.State;
import java.util.Map;

public class StateService {

    private final StateDao stateDao;
    private final BoardDao boardDao;

    public StateService() {
        this.stateDao = new StateDao();
        this.boardDao = new BoardDao();
    }

    public Map<String, Piece> loadBoard() {
        String stateName = stateDao.findState();
        State state = StateGenerator.generateFrom(stateName);
        return state.getBoardForWeb();
    }
}
