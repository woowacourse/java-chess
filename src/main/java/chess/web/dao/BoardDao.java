package chess.web.dao;

import chess.web.dto.BoardDto;

public interface BoardDao {

    void save(BoardDto boardDto);

    void update(BoardDto boardDto);

    String selectState();
}
