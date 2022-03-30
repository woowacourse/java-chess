package chessrefactor;

public class Main {

    public static void main(String[] args) {
        ChessController chessController = new ChessController();
        InputView.inputStartOrEndGame();
        BoardDto boardDto = chessController.startGame();
        OutputView.announce(boardDto);
    }
}
