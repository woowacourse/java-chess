package chess.dao;

import chess.model.board.Board;
import chess.model.state.State;
import chess.service.converter.StateToStringConverter;
import chess.service.converter.StringToStateConverter;

public class FakeStateDao implements StateDao {

    private String name;

    @Override
    public void save(State state) {
        this.name = StateToStringConverter.convert(state);
    }

    @Override
    public State find(Board board) {
        return StringToStateConverter.convert(name, board);
    }

    @Override
    public void delete() {
        name = null;
    }

    @Override
    public void update(State now, State next) {
        this.name = StateToStringConverter.convert(next);
    }
}
