package chess.dao;

import chess.service.dto.ChessGameDto;
import chess.service.dto.GamesDto;
import chess.service.dto.StatusDto;

public interface GameDao {
    void update(ChessGameDto dto);

    ChessGameDto findById(int id);

    void updateStatus(StatusDto statusDto, int id);

    GamesDto findAll();

    void createGame(String name);
}
