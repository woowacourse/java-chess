package chess.service;

import chess.controller.dto.MessageDto;
import chess.domain.game.ChessGame;
import chess.repository.GameRepository;

public class EndService {

    private final String gameId;

    public EndService(String gameId) {
        this.gameId = gameId;
    }

    public Object end() {
        ChessGame chessGame = GameRepository.findByGameIdFromCache(gameId);

        try {
            chessGame.end();
        } catch (RuntimeException e) {
            return new MessageDto(e.getMessage());
        }

        return new MessageDto("finished");
    }

}
