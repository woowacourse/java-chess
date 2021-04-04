package chess.service;

import chess.web.dto.MessageDto;
import chess.domain.game.ChessGame;
import chess.repository.GameRepository;

public class EndService {

    public static Object end(String gameId) {
        ChessGame chessGame = GameRepository.findByGameIdFromCache(gameId);

        try {
            chessGame.end();
        } catch (RuntimeException e) {
            return new MessageDto(e.getMessage());
        }

        return new MessageDto("finished");
    }

}
