package chess.controller;

import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.command.Commands;
import chess.domain.command.Status;
import chess.domain.game.ChessGame;
import chess.utils.DtoAssembler;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        ChessGame chessGame = new ChessGame(new Board());
        OutputView.printCommandNotice();

        while (chessGame.isNotEnd()) {
            chessGame = new ChessGame(new Board());
            Commands commands = Commands.initCommands(chessGame);
            readyChess(chessGame, commands);
            runningChess(chessGame, commands);
            finishedChess(chessGame, commands);
        }
    }

    private void readyChess(ChessGame chessGame, Commands commands) {
        OutputView.printRequestInputStart();
        while (chessGame.isInit()) {
            executeCommand(commands);
        }
    }

    private void runningChess(ChessGame chessGame, Commands commands) {
        OutputView.printChessStarted();
        while (chessGame.isRunning()) {
            OutputView.printChessBoard(DtoAssembler.board(chessGame.board()));
            executeCommand(commands);
        }
    }

    private void finishedChess(ChessGame chessGame, Commands commands) {
        OutputView.printFinishWithReason(chessGame.winner());
        while (chessGame.isFinished()) {
            executeCommand(commands);
        }
    }

    private void executeCommand(final Commands commands) {
        try {
            String input = InputView.command();
            Command command = commands.matchedCommand(input);
            command.execution(input);
            printStatus(command);
        } catch (IllegalStateException | IllegalArgumentException e) {
            OutputView.printErrorException(e.getMessage());
        }
    }

    private void printStatus(Command command) {
        if (command.isStatus()) {
            Status status = (Status) command;
            OutputView.printWinner(status.winner());
            OutputView.printScoreStatus(status.totalWhiteScore(), status.totalBlackScore());
        }
    }
}
