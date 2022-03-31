import static domain.classification.OrderCase.*;

import domain.board.BoardGenerator;
import domain.board.ChessBoard;
import domain.board.ChessBoardGenerator;
import domain.classification.Order;
import domain.dto.StatusDto;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        final BoardGenerator boardGenerator = new ChessBoardGenerator();
        final ChessBoard chessBoard = new ChessBoard(boardGenerator);

        Order order = Order.of(ELSE);
        while (!order.isStart()) {
            try {
                order = InputView.responseUserStartCommand();
                OutputView.printBoard(chessBoard);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }

        while (!order.isEnd()) {
            try {
                order = InputView.responseUserCommand();
                if (order.isStatus()) {
                    OutputView.printStatus(new StatusDto(chessBoard));
                }
                if (order.isMove()) {
                    chessBoard.move(order.getSource(), order.getTarget());
                    OutputView.printBoard(chessBoard);
                }
                if (!chessBoard.checkKingExist()){
                    OutputView.printWinner(chessBoard.calculateWhoWinner());
                    break;
                }
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
