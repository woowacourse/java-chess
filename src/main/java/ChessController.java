public class ChessController {

    public void play() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        OutputView.printBoard(chessBoard);
    }
}
