package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.Rank;
import chess.domain.command.Command;
import chess.domain.command.Command3;
import chess.domain.command.Status;
import chess.domain.game.ChessGame;
import chess.utils.DtoAssembler;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGameController {

    public void run() {
        ChessGame chessGame = new ChessGame(new Board());

        while (chessGame.isNotEnd()) {
            OutputView.printCommandNotice();
            chessGame = new ChessGame(new Board());

            readyChessBoard(chessGame);
            runningChess(chessGame);
            finishedChess(chessGame);
        }
    }

    private void runningChess(ChessGame chessGame) {
        while (chessGame.isRunning()) {
            executeCommand(chessGame);
        }
    }

    private void readyChessBoard(ChessGame chessGame) {
        while (chessGame.isInit()) {
            executeCommand(chessGame);
        }
    }

    private void finishedChess(ChessGame chessGame) {
        while (chessGame.isFinished()) {
            executeCommand(chessGame);
        }
    }

    private void executeCommand(ChessGame chessGame) {
        try {
            String input = InputView.command();
            Command command = Command.matchedCommand(input);
            List<Rank> ranks = command.execution(chessGame, input);
            OutputView.printBoard(DtoAssembler.board(ranks));
        } catch (IllegalStateException | IllegalArgumentException e) {
            OutputView.printErrorException(e.getMessage());
        }
    }

    private void printStatus(ChessGame chessGame, Command3 command) {
        if (command.isStatus()) {
            Status status = (Status) command;
            OutputView.printWinner(chessGame.winner());
            OutputView.printScoreStatus(status.totalWhiteScore(), status.totalBlackScore());
        }
    }
}
