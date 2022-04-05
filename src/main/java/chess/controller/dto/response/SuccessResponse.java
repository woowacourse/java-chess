package chess.controller.dto.response;

import chess.domain.ChessGame;
import chess.domain.GameState;

public class SuccessResponse implements ChessGameResponse {

    private final boolean isOk;
    private final BoardResponse board;
    private final GameState gameState;

    public SuccessResponse(ChessGame chessGame) {
        this.isOk = true;
        this.board = new BoardResponse(chessGame.getBoard());
        this.gameState = chessGame.getGameState();
    }
}
