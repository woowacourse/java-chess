package chess.service;

import chess.controller.dto.MessageDto;
import chess.domain.game.ChessGame;
import chess.repository.GameRepository;
import spark.Response;

import java.sql.SQLException;

public class SaveService {

    public static Object save(String gameId, Response response) {
        try {
            ChessGame chessGame = GameRepository.findByGameIdFromCache(gameId);
            saveGameToDB(gameId, chessGame);
        } catch (RuntimeException | SQLException e) {
            response.status(400);
            return new MessageDto(e.getMessage());
        }

        return new MessageDto("저장 완료");
    }

    private static void saveGameToDB(String gameId, ChessGame chessGame) throws SQLException {
        if(GameRepository.isGameIdExistingInDB(gameId)) {
            GameRepository.updateToDB(gameId, chessGame);
            return;
        }

        GameRepository.saveToDB(gameId, chessGame);
    }

}
