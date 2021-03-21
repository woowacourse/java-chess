package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.board.Coordinate;
import chess.domain.board.Result;
import chess.domain.piece.TeamType;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

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
        chessBoard.initializeDefaultPieces();
        OutputView.printBoard(chessBoard);
        startChessGame();
        if (chessBoard.isKingCheckmate()) {
            OutputView.printWinner(chessBoard.winner());
        }
    }

    private Command inputFirstCommand() {
        InputView.printGameStartMessage();
        List<String> playerCommand = InputView.inputPlayerCommand();
        validatePlayerCommand(playerCommand);
        return Command.findCommand(playerCommand.get(0));
    }

    private void validatePlayerCommand(List<String> playerCommand) {
        int commandSize = playerCommand.size();
        if (commandSize != 1 && commandSize != 3) {
            throw new IllegalArgumentException("명령어를 잘 못 입력했습니다.");
        }
    }

    private void startChessGame() {
        Command command = Command.START;
        while (command != Command.END && !chessBoard.isKingCheckmate()) {
            List<String> playerCommand = InputView.inputPlayerCommand();
            validatePlayerCommand(playerCommand);
            command = Command.findCommand(playerCommand.get(0));
            executeCommand(command, playerCommand);
            OutputView.printBoard(chessBoard);
        }
    }

    private void executeCommand(Command command, List<String> playerCommand) {
        if (command == Command.MOVE) {
            executeMoveCommand(playerCommand);
            return;
        }
        if (command == Command.STATUS) {
            Result result = chessBoard.calculateScores();
            OutputView.printScoreResult(result);
        }
    }

    private void executeMoveCommand(List<String> playerCommand) {
        Coordinate current = Coordinate.from(playerCommand.get(1));
        Coordinate destination = Coordinate.from(playerCommand.get(2));
        chessBoard.move(current, destination, teamType);
        teamType = teamType.getOppositeTeam();
    }
}
