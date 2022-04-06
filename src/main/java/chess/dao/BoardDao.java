package chess.dao;

import chess.dto.ChessBoardDto;

public interface BoardDao {

    void updateAll(int gameId, ChessBoardDto chessBoardDto);

    ChessBoardDto findAll(int gameId);
}
