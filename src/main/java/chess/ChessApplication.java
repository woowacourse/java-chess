package chess;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardCreator;
import chess.domain.position.Position;
import chess.view.GameExecutionCommand;
import chess.view.InputView;
import chess.view.MoveCommand;
import chess.view.OutputView;

public class ChessApplication {
    //TODO: 컨트롤러 혹은 게임 객체 생각해보기
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

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
