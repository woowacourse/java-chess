package chess.web.dao;

import chess.web.dto.BoardDto;

public class BoardDaoForTest implements BoardDao {

    private BoardDto boards = null;

    @Override
    public void save(BoardDto boardDto) {
        boards = boardDto;
    }

    @Override
    public void update(BoardDto boardDto) {
        boards = boardDto;
    }

    public String selectState() {
        return boards.getState();
    }
}
