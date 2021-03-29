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
        ChessGame chessGame = getChessGameByGameId(gameId);

        try {
            chessGame.end();
        } catch (RuntimeException e) {
            return new MessageDto(e.getMessage());
        }

        return new MessageDto("finished");
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
