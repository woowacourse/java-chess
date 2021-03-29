package chess.service;

import chess.controller.dto.GameDto;
import chess.controller.dto.MessageDto;
import chess.domain.game.ChessGame;
import chess.repository.GameRepository;
import spark.Response;

public class LoadService {

    private final String gameId;
    private final Response response;

    public LoadService(String gameId, Response response) {
        this.gameId = gameId;
        this.response = response;
    }

    public Object loadByGameId() {
        ChessGame chessGame = null;

        try {
            chessGame = getChessGameByGameId(gameId);
        } catch (RuntimeException e) {
            response.status(400);
            return new MessageDto(e.getMessage());
        }

        return new GameDto(chessGame);
    }

    private ChessGame getChessGameByGameId(String gameId) {
        validateChessGameIdExist(gameId);

        return GameRepository.findByGameId(gameId).get();
    }

    private void validateChessGameIdExist(String gameId) {
        if (!GameRepository.findByGameId(gameId).isPresent()) {
            throw new IllegalArgumentException("게임 ID가 존재하지 않습니다.");
        }
    }

}
