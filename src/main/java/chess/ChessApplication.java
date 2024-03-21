package chess;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardCreator;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessApplication {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        if (inputView.readGameCommand().equals("start")) {
            ChessBoardCreator chessBoardCreator = new ChessBoardCreator();
            ChessBoard chessBoard = chessBoardCreator.create();
            outputView.printChessBoardMessage(chessBoard);

            String gameCommand = inputView.readGameCommand();
            while (gameCommand.startsWith("move")) {

                String[] split = gameCommand.split(" ");

                String start = split[1];
                String destination = split[2];

                int startCol = start.charAt(0) - 'a';
                int startRow = 8 - (start.charAt(1) - '0');

                int destinationCol = destination.charAt(0) - 'a';
                int destinationRow = 8 - (destination.charAt(1) - '0');

                System.out.println("startRow = " + startRow);
                System.out.println("destinationRow = " + startCol);

                System.out.println("destinationRow = " + destinationRow);
                System.out.println("destinationCol = " + destinationCol);

                Position startPosition = Position.of(startRow, startCol);
                Position destinationPosition = Position.of(destinationRow, destinationCol);

                chessBoard.move(startPosition, destinationPosition);
                outputView.printChessBoardMessage(chessBoard);
                gameCommand = inputView.readGameCommand();
            }
        }
    }
}
