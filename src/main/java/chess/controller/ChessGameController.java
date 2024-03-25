package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardCreator;
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

    public void run() {
        GameCommand gameCommand;

        outputView.printStartMessage();
        retryUntilNoError(() -> readStartCommand());
        ChessBoard chessBoard = initializeChessBoard();

        do {
            String inputCommand = retryUntilNoError(this::readCommand);
            gameCommand = from(inputCommand);

            if (gameCommand == MOVE) {
                executeMoveCommand(inputCommand, chessBoard);
            }
        } while(gameCommand != END);
    }


    private void executeMoveCommand(String inputCommand, ChessBoard chessBoard) {
        MoveCommand moveCommand = MoveCommand.of(inputCommand);
        Position startPosition = moveCommand.getStart();
        Position destinationPosition = moveCommand.getDestination();

        chessBoard.move(startPosition, destinationPosition);
        outputView.printChessBoardMessage(chessBoard);
    }

    private ChessBoard initializeChessBoard() {
        ChessBoardCreator chessBoardCreator = ChessBoardCreator.normalGameCreator();
        ChessBoard chessBoard = chessBoardCreator.create();
        outputView.printChessBoardMessage(chessBoard);
        return chessBoard;
    }

    private boolean readStartCommand() {
        if (inputView.readGameCommand().equals(START.getCode())) {
            return true;
        }
        throw new IllegalArgumentException(START.getCode() + "를 입력해야 게임이 시작됩니다.");
    }

    private String readCommand(){
        String inputCommand = inputView.readGameCommand();
        from(inputCommand);
        return inputCommand;
    }
}
