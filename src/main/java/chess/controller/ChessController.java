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

        while (chessGame.isNotEnd()) {
            OutputView.printCommandNotice();
            chessGame = new ChessGame(new Board());
            Commands commands = Commands.initCommands(chessGame);

            readyChess(chessGame, commands);
            runningChess(chessGame, commands);
            finishChess(chessGame, commands);
        }
    }

    private void readyChess(ChessGame chessGame, Commands commands) {
        while (chessGame.isInit()) {
            executeCommand(commands);
        }
    }

    private void runningChess(ChessGame chessGame, Commands commands) {
        while (chessGame.isRunning()) {
            OutputView.printBoard(DtoAssembler.board(chessGame.ranks()));
            executeCommand(commands);
        }
    }

    private void finishChess(ChessGame chessGame, Commands commands) {
        while (chessGame.isFinished()) {
            executeCommand(commands);
        }
    }

    private void executeCommand(final Commands commands) {
        try {
            String input = InputView.command();
            Command command = commands.matchedCommand(input);
            command.execution(input);
        } catch (IllegalStateException | IllegalArgumentException e) {
            OutputView.printErrorException(e.getMessage());
        }
    }

    private void printStatus(ChessGame chessGame, Command command) {
        if (command.isStatus()) {
            Status status = (Status) command;
            OutputView.printWinner(chessGame.winner());
            OutputView.printScoreStatus(status.totalWhiteScore(), status.totalBlackScore());
        }
    }
}
