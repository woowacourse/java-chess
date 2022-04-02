package chess.dao;

import chess.domain.state.StateGenerator;

public interface TurnDao {

    void save(String state);

    StateGenerator findLastSaved();
}
