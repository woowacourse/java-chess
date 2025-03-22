package chess;

import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessApplication {

    public static void main(String[] args) {
        Board board = new Board();
        OutputView outputView = new OutputView();
        InputView inputView = new InputView();

        outputView.displayBoard(board.getBoardMap());
        System.out.println("게임 시작!");
        Color turnColor = Color.WHITE;

        while (true) {
            System.out.println("지금은 니 차례야 " + turnColor);
            List<String> moveInfo = inputView.readMoveCommand();
            String fromColumn = moveInfo.get(0);
            String fromRow = moveInfo.get(1);

            String toColumn = moveInfo.get(2);
            String toRow = moveInfo.get(3);
            board.movePiece(Position.from(fromColumn, fromRow), Position.from(toColumn, toRow), turnColor);

            outputView.displayBoard(board.getBoardMap());
            turnColor = turnColor.opposite();
        }


    }
}
