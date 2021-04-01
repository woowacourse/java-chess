package chess.controller;

import chess.domain.Game;
import chess.domain.board.Position;
import chess.domain.command.Command;
import chess.domain.command.End;
import chess.domain.command.Move;
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

    public String getTurn() {
        return this.game.turn();
    }

    public String move(String rawCommand) {
        Move move = new Move();
        try {
            Command command = move.run(game, rawCommand);
            return "true";
        } catch (RuntimeException runtimeException) {
            return runtimeException.getMessage();
        }
    }

    public void end() {
        String rawCommand = "";
        End end = new End();
        end.run(game, rawCommand);
    }

    public boolean isEnd() {
        return game.isEnd();
    }

    public String winnerColor() {
        return game.winnerColor()
            .getName();
    }

    public String whiteScore() {
        double rawWhiteScore = game.computeWhitePoint();
        return String.valueOf(rawWhiteScore);
    }

    public String blackScore() {
        double rawBlackScore = game.computeBlackPoint();
        return String.valueOf(rawBlackScore);
    }
}
