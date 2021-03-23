package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.board.Coordinate;
import chess.domain.command.Command;
import chess.domain.command.CommandTokens;
import chess.domain.piece.TeamType;
import chess.domain.result.Result;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final ChessBoard chessBoard;
    private TeamType teamType = TeamType.WHITE;

    public ChessController(ChessBoard chessBoard) {
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
        CommandTokens commandTokens = new CommandTokens(InputView.inputPlayerCommand());
        return Command.findCommand(commandTokens.findMainCommandToken());
    }

    private void startChessGame() {
        Command command = Command.START;
        while (command != Command.END && !chessBoard.isKingCheckmate()) {
            CommandTokens commandTokens = new CommandTokens(InputView.inputPlayerCommand());
            command = Command.findCommand(commandTokens.findMainCommandToken());
            executeCommand(command, commandTokens);
            OutputView.printChessBoard(chessBoard);
        }
    }

    private void executeCommand(Command command, CommandTokens commandTokens) {
        if (command == Command.MOVE) {
            executeMoveCommand(commandTokens);
            return;
        }
        if (command == Command.STATUS) {
            Result result = chessBoard.calculateScores();
            OutputView.printScoreStatus(result);
        }
    }

    private void executeMoveCommand(CommandTokens commandTokens) {
        Coordinate current = Coordinate.from(commandTokens.findCurrentCoordinateToken());
        Coordinate destination = Coordinate.from(commandTokens.findDestinationCoordinateToken());
        chessBoard.move(current, destination, teamType);
        teamType = teamType.findOppositeTeam();
    }
}
