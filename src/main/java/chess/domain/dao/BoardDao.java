package chess.domain.dao;

import chess.domain.dto.BoardDto;
import chess.domain.dto.PieceDto;
import chess.domain.entity.Board;
import java.util.List;

public interface BoardDao {

    Long save(BoardDto boardDto);

    List<Board> findBoardById(Long id);

    void updateByPosition(Long gameId, String name, PieceDto pieceDto);
}

