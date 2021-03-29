package chess.domain.command;

import chess.controller.dto.StatusDto;
import chess.domain.game.ChessGame;

public class StatusService {

    private final ChessGame chessGame;

    public StatusService(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public StatusDto getStatus() {
        double whiteScore = chessGame.getWhiteScore();
        double blackScore = chessGame.getBlackScore();

        return new StatusDto(whiteScore, blackScore);
    }

}
