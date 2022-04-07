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

    @Override
    public void deleteAll() {
        boardDto = null;
    }

    public StateType selectState() {
        if (boardDto == null) {
            return null;
        }
        return boardDto.getStateType();
    }
}
