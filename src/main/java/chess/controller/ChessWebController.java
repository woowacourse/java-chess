package chess.controller;

import chess.domain.Game;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public class ChessWebController {

    private final Game game;

    public ChessWebController() {
        this.game = new Game();
    }

    public Map<Position, Piece> startedBoard() {
        game.init();
        return game.getBoard()
            .recentBoard();
    }
}
