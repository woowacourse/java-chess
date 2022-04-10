package chess.domain.dao;

import chess.dto.BoardDto;
import chess.dto.PieceDto;
import chess.domain.entity.Board;
import java.util.List;

public interface BoardDao {

    Long save(BoardDto boardDto);

    List<Board> findBoardById(Long id);

    void updateByPosition(Long gameId, String name, PieceDto pieceDto);
}

