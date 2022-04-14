package chess.web.dao;

import chess.domain.state.StateType;
import chess.web.dto.BoardStateDto;

public class MockBoardStateDao implements BoardStateDao {

    private BoardStateDto boardStateDto = null;

    @Override
    public void save(StateType stateType) {
        this.boardStateDto = new BoardStateDto(stateType);
    }

    @Override
    public void update(StateType stateType) {
        this.boardStateDto = new BoardStateDto(stateType);
    }

    @Override
    public void deleteAll() {
        boardStateDto = null;
    }

    public StateType selectState() {
        if (boardStateDto == null) {
            return null;
        }
        return boardStateDto.getStateType();
    }
}
