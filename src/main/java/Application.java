
import domain.board.BoardGenerator;
import domain.board.ChessBoard;
import domain.board.ChessBoardGenerator;
import domain.classification.Order;
import domain.dto.StatusDto;
import view.InputView;
import view.OutputView;

public final class Application {

    public static void main(final String[] args) {
        final BoardGenerator boardGenerator = new ChessBoardGenerator();
        final ChessBoard chessBoard = new ChessBoard(boardGenerator);

        Order order = null;
        startGame(chessBoard, order);
        playGame(chessBoard, order);
    }

    private static void startGame(final ChessBoard chessBoard, Order order) {
        try {
            order = InputView.responseUserStartCommand();
            OutputView.printBoard(chessBoard);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            startGame(chessBoard, order);
        }
    }

    private static void playGame(final ChessBoard chessBoard, Order order) {
        try {
            order = InputView.responseUserCommand();
            if (order.isEnd()){
                return;
            }
            if (order.isStatus()) {
                OutputView.printStatus(new StatusDto(chessBoard));
                playGame(chessBoard, order);
            }
            if (order.isMove()) {
                chessBoard.move(order.getSource(), order.getTarget());
                OutputView.printBoard(chessBoard);
                playGame(chessBoard, order);
            }
            if (!chessBoard.checkKingExist()){
                OutputView.printWinner(chessBoard.calculateWhoWinner());
                return;
            }

        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            playGame(chessBoard, order);
        }
    }
}
