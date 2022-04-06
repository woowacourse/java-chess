package chess.domain.dao;

import chess.domain.dto.BoardDto;

public interface BoardDao {

    Long save(BoardDto boardDto);
}
