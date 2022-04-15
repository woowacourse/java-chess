package chess.web.dao;

import chess.domain.state.StateType;

public class MockBoardStateDao implements BoardStateDao {

    private StateType stateType = null;

    @Override
    public void save(StateType stateType) {
        this.stateType = stateType;
    }

    @Override
    public void update(StateType stateType) {
        this.stateType = stateType;
    }

    @Override
    public void deleteAll() {
        stateType = null;
    }

    public StateType selectState() {
        return stateType;
    }
}
