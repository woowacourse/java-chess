package chess.service;

import chess.controller.dto.GameDto;
import chess.controller.dto.MessageDto;
import chess.domain.game.ChessGame;
import chess.repository.GameRepository;
import spark.Response;

public class LoadService {

    public static Object loadByGameId(String gameId, Response response) {
        ChessGame chessGame = null;

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
