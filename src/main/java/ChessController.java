public class ChessController {

    public void play() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        String input = InputView.responseUserCommand();

        if (input.equals(InputView.START)) {
            OutputView.printBoard(chessBoard);
        }

        if (input.equals(InputView.END)) {
        }
    }
}
