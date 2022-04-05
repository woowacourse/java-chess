package chess.web;

import chess.GameManager;
import chess.web.dto.ChessDto;
import chess.web.dto.MoveDto;
import chess.web.dto.StatusDto;

public class GameService {

    private final GameManager gameManager = new GameManager();

    public ChessDto start() {
        return ChessDto.from(gameManager.getBoard());
    }

    public ChessDto move(MoveDto moveDto) {
        gameManager.move(moveDto.getSource(), moveDto.getDestination());
        return ChessDto.from(gameManager.getBoard());
    }

    public StatusDto status() {
        return StatusDto.from(gameManager.getScores(), gameManager.getResult());
    }

}
