package chess.controller;

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
        if (inputView.readGameCommand().equals("start")) {
            ChessBoardCreator chessBoardCreator = new ChessBoardCreator();
            ChessBoard chessBoard = chessBoardCreator.create();
            outputView.printChessBoardMessage(chessBoard);

            String inputCommand = inputView.readGameCommand();
            GameExecutionCommand gameCommand = GameExecutionCommand.from(inputCommand);

            while (gameCommand != GameExecutionCommand.END) {
                if (gameCommand == GameExecutionCommand.MOVE) {
                    MoveCommand moveCommand = MoveCommand.of(inputCommand);
                    Position startPosition = moveCommand.getStart();
                    Position destinationPosition = moveCommand.getDestination();

                    chessBoard.move(startPosition, destinationPosition);
                    outputView.printChessBoardMessage(chessBoard);
                }

                inputCommand = inputView.readGameCommand();
                gameCommand = GameExecutionCommand.from(inputCommand);
            }
        }
    }
}
