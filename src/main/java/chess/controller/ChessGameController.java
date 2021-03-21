package chess.controller;
import chess.domain.board.Board;
import chess.domain.board.InitBoardGenerator;
import chess.domain.command.Command;
import chess.domain.command.Commands;
import chess.domain.game.ChessGame;
import chess.view.InputView;

public class ChessGameController {
    public void run() {
        ChessGame chessGame = new ChessGame(new Board(InitBoardGenerator.initLines()));
        Commands commands = Commands.initCommands(chessGame);
        while (chessGame.isRunning()) {
            String input = InputView.command();
            Command command = commands.matchedCommand(input);
            command.execution(input);
        }
    }
}
