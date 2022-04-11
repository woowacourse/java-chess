package chess.dao;

import chess.dto.BoardDto;
import chess.dto.CommandDto;

public interface BoardDao {
    BoardDto loadBoard();

    void updatePiecePosition(CommandDto commandDto);
}
