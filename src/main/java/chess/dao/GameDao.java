package chess.dao;

import chess.domain.ChessGame;
import chess.dto.GameInformationDto;

public interface GameDao {

    void saveGame(GameInformationDto gameInformationDto);

    GameInformationDto getGameData(int gameId);

    void deleteGameData(int gameId);
}
