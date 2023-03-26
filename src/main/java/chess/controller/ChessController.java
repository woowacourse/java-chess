package chess.controller;

import static chess.view.PositionConverter.convertToSourcePosition;
import static chess.view.PositionConverter.convertToTargetPosition;

import chess.dao.DbChessBoardDao;
import chess.dao.DbChessGameDao;
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
        outputView.printGameList(ChessGame.showProgressGame(new DbChessGameDao()));
        String gameCommand = inputGameCommand();
        ChessGame chessGame = startChessGame(gameCommand);
        progress(chessGame);
    }

    private ChessGame startChessGame(String gameCommand) {
        if (GameCommand.isNew(gameCommand)) {
            ChessGame chessGame = ChessGame.createGame(new DbChessGameDao(), new DbChessBoardDao());
            outputView.printGameId(chessGame.getId());
            return chessGame;
        }
        return ChessGame.continueGame(Integer.parseInt(gameCommand), new DbChessGameDao());
    }

    private void inputStartCommand() {
        try {
            inputView.readStart();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            inputStartCommand();
        }
    }

    private String inputGameCommand() {
        try {
            return inputView.readGame();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            return inputGameCommand();
        }
    }

    private void progress(ChessGame chessGame) {
        boolean onGoing = true;
        while (onGoing) {
            outputView.printCurrentTurn(chessGame.getTurn());
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

        move(chessGame, command);
    }

    private void move(ChessGame chessGame, String command) {
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
