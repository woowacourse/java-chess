package chess.controller;

import chess.command.Command;
import chess.command.CommandType;
import chess.domain.game.ChessGame;
import chess.domain.position.TerminalPosition;
import chess.util.ExceptionRetryHandler;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.TerminalPositionView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        Command command = receiveStartCommandUntilValid();
        validateStartOrEnd(command.getType());

        if (command.getType() == CommandType.START) {
            startGame();
        }
    }

    private Command receiveStartCommandUntilValid() {
        return ExceptionRetryHandler.handle(inputView::readCommand);
    }

    private static void validateStartOrEnd(CommandType commandType) {
        if (commandType != CommandType.START && commandType != CommandType.END) {
            throw new IllegalArgumentException("게임을 시작하거나 끝내는 것만 가능합니다.");
        }
    }

    private void startGame() {
        ChessGame chessGame = ChessGame.createOnStart();
        outputView.printChessBoard(chessGame.getPieces());

        processGameUntilValid(chessGame);
    }

    private void processGameUntilValid(ChessGame chessGame) {
        ExceptionRetryHandler.handle(() -> processGame(chessGame));
    }

    private void processGame(ChessGame chessGame) {
        Command command = receiveProcessCommand();

        while (command.getType() != CommandType.END) {
            processTurn(command, chessGame);
            command = receiveProcessCommand();
        }
    }

    private Command receiveProcessCommand() {
        Command command = inputView.readCommand();
        validateNotStart(command.getType());
        return command;
    }

    private void validateNotStart(CommandType commandType) {
        if (commandType == CommandType.START) {
            throw new IllegalArgumentException("게임이 이미 진행중입니다.");
        }
    }

    private void processTurn(Command command, ChessGame chessGame) {
        if (command.getType() == CommandType.MOVE) {
            TerminalPosition terminalPosition = TerminalPositionView.of(command.getArguments());
            chessGame.movePiece(terminalPosition);
            outputView.printChessBoard(chessGame.getPieces());
        }
    }
}
