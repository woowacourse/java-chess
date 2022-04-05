package chess.web;

import chess.GameManager;
import chess.web.dto.ChessDto;
import chess.web.dto.MoveDto;
import chess.web.dto.StatusDto;

public class GameService {

    private GameManager gameManager;

    public ChessDto start() {
        gameManager = new GameManager();
        return ChessDto.from(gameManager);
    }

    public ChessDto move(MoveDto moveDto) {
        gameManager.move(moveDto.getSource(), moveDto.getDestination());
        return ChessDto.from(gameManager);
    }

    public StatusDto status() {
        return StatusDto.from(gameManager.getScores(), gameManager.getResult());
    }
}
