package chess.web.dao;

import chess.domain.state.StateType;
import chess.web.dto.BoardDto;

public class BoardDaoForTest implements BoardDao {

    private BoardDto boardDto = null;

    @Override
    public void save(StateType stateType) {
        this.boardDto = new BoardDto(stateType);
    }

    @Override
    public void update(StateType stateType) {
        this.boardDto = new BoardDto(stateType);
    }

    public StateType selectState() {
        return boardDto.getStateType();
    }
}
