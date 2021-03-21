package chess;

import chess.domain.ChessBoard;
import chess.view.Commands;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        OutputView.printInitMessage();
        final ChessBoard chessBoard = new ChessBoard();
        while (true) {
//                    return Commands.getInstance(sc.nextLine().trim());
            List<String> command = InputView.command();

            if (Commands.getInstance(command.get(0)) == Commands.START) {
                OutputView.printBoard(chessBoard.getChessBoard());
            }
            if (Commands.getInstance(command.get(0)) == Commands.MOVE) {
                //TODO
            }
            if (Commands.getInstance(command.get(0)) == Commands.STATUS) {
                //TODO
            }
            if (Commands.getInstance(command.get(0)) == Commands.END) {
                //TODO
                break;
            }

        }


    }

}
