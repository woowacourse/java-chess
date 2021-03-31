package chess.service;

import chess.controller.dto.MessageDto;
import chess.domain.game.ChessGame;
import chess.repository.GameRepository;
import spark.Response;

import java.sql.SQLException;

public class SaveService {

    private final String gameId;
    private final Response response;

    public SaveService(String gameId, Response response) {
        this.gameId = gameId;
        this.response = response;
    }

    public Object save() {

        try {
            ChessGame chessGame = GameRepository.findByGameIdFromCache(gameId);
            GameRepository.updateToDB(gameId, chessGame);
        } catch (RuntimeException | SQLException e) {
            response.status(400);
            return new MessageDto(e.getMessage());
        }

        return new MessageDto("저장 완료");
    }

}
