package chess.web;

import chess.GameManager;
import chess.web.dto.ChessDto;
import chess.web.dto.MoveDto;

public class GameService {

    private final GameManager gameManager = new GameManager();

    public ChessDto start() {
        gameManager.start();
        return ChessDto.from(gameManager.getBoard());
    }

    public ChessDto move(MoveDto moveDto) {
        gameManager.move(moveDto.getSource(), moveDto.getDestination());
        return ChessDto.from(gameManager.getBoard());
    }

}
