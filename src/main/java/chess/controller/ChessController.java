package chess.controller;

import static chess.view.PositionConverter.convertToSourcePosition;
import static chess.view.PositionConverter.convertToTargetPosition;

import chess.view.GameCommand;
import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.Team;
import chess.view.InputView;
import chess.view.OutputView;

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
        ChessGame chessGame = new ChessGame(Board.init(), Team.getStartTeam());
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
            onGoing = !GameCommand.isEnd(command);
            movePiece(chessGame, command);
        }
    }

    private void movePiece(ChessGame chessGame, String command) {
        if (GameCommand.isEnd(command)) {
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

    private String inputCommand() {
        try {
            return inputView.readCommand();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            return inputCommand();
        }
    }
}
