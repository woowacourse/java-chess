package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Command;
import chess.domain.piece.Team;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.Map;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        ChessGame chessGame = new ChessGame();

        outputView.printAnnounce();

        if (checkEnd(chessGame)) {
            return;
        }

        progress(chessGame);

        showWinTeam(chessGame);
    }

    private boolean checkEnd(ChessGame chessGame) {
        try {
            Command command = Command.from(inputView.inputCommand());
            chessGame.progress(command);

            return chessGame.isEnd();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());

            return checkEnd(chessGame);
        }
    }

    private void progress(ChessGame chessGame) {
        try {
            playChessGame(chessGame);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());

            progress(chessGame);
        }
    }

    private void playChessGame(ChessGame chessGame) {
        while (!chessGame.isEnd() && chessGame.isExistKing()) {
            printChessBoard(chessGame);

            Command command = Command.from(inputView.inputCommand());

            chessGame.progress(command);

            checkStatus(chessGame, command);
        }
    }

    private void printChessBoard(ChessGame chessGame) {
        List<String> symbols = chessGame.getSymbols();
        outputView.printChessBoard(symbols);
    }

    private void checkStatus(ChessGame chessGame, Command command) {
        if (command.isStatus()) {
            showStatus(chessGame);
        }
    }

    private void showStatus(ChessGame chessGame) {
        try {
            Map<Team, Double> teamScores = chessGame.calculateResult();

            outputView.printResult(teamScores);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());

            showStatus(chessGame);
        }
    }

    private void showWinTeam(ChessGame chessGame) {
        String winTeamName = chessGame.getWinTeamName();

        outputView.printWinTeam(winTeamName);

        showStatus(chessGame);
    }
}
