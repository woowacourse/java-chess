package chess.controller;

import chess.domain.game.ChessGame;
import chess.domain.position.Position;
import chess.view.GameCommand;
import chess.view.InputView;
import chess.view.MoveCommand;
import chess.view.OutputView;

import static chess.util.retryHelper.retryUntilNoError;
import static chess.view.GameCommand.*;

public class ChessGameController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }
    //TODO command 객체를 만들어 다형성을 이용할 수 있는 방안 고민해보기

    public void run() {
        GameCommand gameCommand;

        outputView.printStartMessage();
        retryUntilNoError(this::readStartCommand);
        ChessGame game = initializeChessGame();

        do {
            String inputCommand = retryUntilNoError(this::readCommand);
            gameCommand = from(inputCommand);

            if (gameCommand == MOVE) {
                executeMoveCommand(MoveCommand.of(inputCommand), game);
            }
        } while (gameCommand != END);
    }


    private void executeMoveCommand(MoveCommand moveCommand, ChessGame game) {
        Position startPosition = moveCommand.getStart();
        Position destinationPosition = moveCommand.getDestination();

        game.playTurn(startPosition, destinationPosition);
        outputView.printChessBoardMessage(game.getBoard());
    }

    private ChessGame initializeChessGame() {
        ChessGame game = ChessGame.newGame();
        outputView.printChessBoardMessage(game.getBoard());
        return game;
    }

    private boolean readStartCommand() {
        if (inputView.readGameCommand().equals(START.getCode())) {
            return true;
        }
        throw new IllegalArgumentException(START.getCode() + "를 입력해야 게임이 시작됩니다.");
    }

    private String readCommand() {
        String inputCommand = inputView.readGameCommand();
        from(inputCommand);
        return inputCommand;
    }
}
