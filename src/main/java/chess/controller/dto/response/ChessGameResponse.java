package chess.controller.dto.response;

import chess.domain.ChessGame;
import chess.domain.GameState;

public class ChessGameResponse {

    private final BoardResponse board;
    private final GameState gameState;

    public ChessGameResponse(ChessGame chessGame) {
        this.board = new BoardResponse(chessGame.getBoard());
        this.gameState = chessGame.getGameState();
    }

    public BoardResponse getBoard() {
        return board;
    }

    public GameState getGameState() {
        return gameState;
    }
}
