package chess.console;

import chess.domain.board.ChessBoard;
import chess.domain.command.Command;
import chess.domain.command.CommandRequest;
import chess.domain.command.CommandTokens;
import chess.domain.piece.TeamType;
import chess.domain.result.Scores;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleChessController {

    private final ChessBoard chessBoard;
    private TeamType teamType = TeamType.WHITE;

    public ConsoleChessController(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void run() {
        Command command = inputFirstCommand();
        if (command != Command.START) {
            return;
        }
        OutputView.printChessBoard(chessBoard);
        startChessGame();
        if (chessBoard.isKingCheckmate()) {
            OutputView.printWinnerTeam(chessBoard.findWinnerTeam());
        }
    }

    private Command inputFirstCommand() {
        InputView.printGameStartMessage();
        CommandTokens commandTokens = CommandTokens.from(InputView.inputPlayerCommand());
        return Command.findCommand(commandTokens.findMainCommandToken());
    }

    private void startChessGame() {
        Command command = Command.START;
        while (isPlayable(command)) {
            CommandTokens commandTokens = CommandTokens.from(InputView.inputPlayerCommand());
            command = Command.findCommand(commandTokens.findMainCommandToken());
            CommandRequest commandRequest = new CommandRequest(teamType, commandTokens);
            executeCommand(command, commandRequest);
            OutputView.printChessBoard(chessBoard);
        }
    }

    private boolean isPlayable(Command command) {
        return command != Command.END && !chessBoard.isKingCheckmate();
    }

    private void executeCommand(Command command, CommandRequest commandRequest) {
        if (command == Command.MOVE) {
            command.execute(chessBoard, commandRequest);
            teamType = teamType.findOppositeTeam();
            return;
        }
        if (command == Command.STATUS) {
            Scores scores = command.execute(chessBoard, commandRequest);
            OutputView.printScoreStatus(scores);
        }
    }
}
