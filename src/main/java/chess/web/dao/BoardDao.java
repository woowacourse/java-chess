package chess.web.dao;

import chess.domain.state.StateType;

public interface BoardDao {

    void save(StateType stateType);

    void update(StateType stateType);

    StateType selectState();

    void deleteAll();
}
