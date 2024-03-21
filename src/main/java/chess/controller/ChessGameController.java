package chess.controller;

import static chess.view.GameExecutionCommand.END;
import static chess.view.GameExecutionCommand.MOVE;
import static chess.view.GameExecutionCommand.START;
import static chess.view.GameExecutionCommand.from;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardCreator;
import chess.domain.position.Position;
import chess.view.GameExecutionCommand;
import chess.view.InputView;
import chess.view.MoveCommand;
import chess.view.OutputView;

public class ChessGameController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {

        outputView.printStartMessage();
        if (!startGame()) {
            return;
        }

        ChessBoard chessBoard = initializeChessBoard();

        while (true) {
            String inputCommand = inputView.readGameCommand();
            GameExecutionCommand gameCommand = from(inputCommand);

            if (gameCommand == END) {
                break;
            }

            if (gameCommand == MOVE) {
                executeMoveCommand(inputCommand, chessBoard);
            }
        }
    }

    private void executeMoveCommand(String inputCommand, ChessBoard chessBoard) {
        MoveCommand moveCommand = MoveCommand.of(inputCommand);
        Position startPosition = moveCommand.getStart();
        Position destinationPosition = moveCommand.getDestination();

        chessBoard.move(startPosition, destinationPosition);
        outputView.printChessBoardMessage(chessBoard);
    }

    private ChessBoard initializeChessBoard() {
        ChessBoardCreator chessBoardCreator = new ChessBoardCreator();
        ChessBoard chessBoard = chessBoardCreator.create();
        outputView.printChessBoardMessage(chessBoard);
        return chessBoard;
    }

    private boolean startGame() {
        return inputView.readGameCommand().equals(START.getCode());
    }
}
