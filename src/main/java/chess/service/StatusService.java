package chess.service;

import chess.domain.game.ChessGame;
import chess.repository.GameRepository;
import chess.web.dto.MessageDto;
import chess.web.dto.StatusDto;

public class StatusService {

    public Object getStatus(String gameId) {
        ChessGame chessGame;
        try {
            chessGame = GameRepository.findByGameIdFromCache(gameId);
        } catch (RuntimeException e) {
            return new MessageDto(e.getMessage());
        }

        double whiteScore = chessGame.getWhiteScore();
        double blackScore = chessGame.getBlackScore();

        return new StatusDto(whiteScore, blackScore);
    }

}
