package chess.controller;

import static chess.view.PositionConverter.convertToSourcePosition;
import static chess.view.PositionConverter.convertToTargetPosition;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.Score;
import chess.domain.Team;
import chess.view.GameCommand;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Map;
import java.util.Optional;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        inputStartCommand();
        ChessGame chessGame = ChessGame.createGame();
        progress(chessGame);
    }

    private void inputStartCommand() {
        try {
            inputView.readStart();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            inputStartCommand();
        }
    }

    private void progress(ChessGame chessGame) {
        boolean onGoing = true;
        while (onGoing) {
            outputView.printBoard(chessGame.getBoard());
            String command = inputCommand();
            play(chessGame, command);
            onGoing = !isGameOver(chessGame) && !GameCommand.isEnd(command);
        }
    }

    private void play(ChessGame chessGame, String command) {
        if (GameCommand.isEnd(command)) {
            return;
        }
        if (GameCommand.isStatus(command)) {
            Map<Team, Score> scores = chessGame.getScoreAllTeam();
            Optional<Team> winner = chessGame.findWinner(scores);
            outputView.printStatus(scores, winner);
            return;
        }

        Position source = convertToSourcePosition(command);
        Position target = convertToTargetPosition(command);
        movePiece(chessGame, source, target);
    }

    private void movePiece(ChessGame chessGame, Position source, Position target) {
        try {
            chessGame.movePiece(source, target);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
        }
    }

    private boolean isGameOver(ChessGame chessGame) {
        if (chessGame.isOver()) {
            Map<Team, Score> scores = chessGame.getScoreAllTeam();
            Optional<Team> winner = chessGame.findWinner(scores);
            outputView.printWinner(winner);
            return true;
        }
        return false;
    }

    private String inputCommand() {
        try {
            return inputView.readCommand();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            return inputCommand();
        }
    }
}
