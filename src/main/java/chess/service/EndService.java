package chess.service;

import chess.domain.game.ChessGame;
import chess.repository.GameRepository;
import chess.web.dto.MessageDto;

public class EndService {

    public Object end(String gameId) {
        ChessGame chessGame = GameRepository.findByGameIdFromCache(gameId);

        try {
            chessGame.end();
        } catch (RuntimeException e) {
            return new MessageDto(e.getMessage());
        }

        return new MessageDto("finished");
    }

}
