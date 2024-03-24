package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardCreator;
import chess.domain.position.Position;
import chess.view.GameExecutionCommand;
import chess.view.InputView;
import chess.view.MoveCommand;
import chess.view.OutputView;

import static chess.view.GameExecutionCommand.*;

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
        // TODO end 명령어 이외에는 종료되지 않도록
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
        ChessBoardCreator chessBoardCreator = ChessBoardCreator.normalGameCreator();
        ChessBoard chessBoard = chessBoardCreator.create();
        outputView.printChessBoardMessage(chessBoard);
        return chessBoard;
    }

    private boolean startGame() {
        return inputView.readGameCommand().equals(START.getCode());
    }
}
