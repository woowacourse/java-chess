package chess.dao;

import chess.service.dto.BoardDto;
import chess.service.dto.PieceWithSquareDto;

public interface BoardDao {
    void initBoard(int gameId);

    BoardDto getBoardByGameId(int id);

    void remove(int id);

    void update(PieceWithSquareDto piece, int gameId);
}
