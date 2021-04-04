package chess.service;

import chess.domain.game.ChessGame;
import chess.repository.GameRepository;
import chess.web.dto.GameDto;
import chess.web.dto.MessageDto;
import spark.Response;

public class LoadService {

    public Object loadByGameId(String gameId, Response response) {
        ChessGame chessGame;

        try {
            chessGame = GameRepository.findByGameIdFromDB(gameId);
            GameRepository.saveToCache(gameId, chessGame);
        } catch (RuntimeException e) {
            response.status(400);
            return new MessageDto(e.getMessage());
        }

        return new GameDto(chessGame);
    }

}
