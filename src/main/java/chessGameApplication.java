import chess.ChessBoard;
import chess.ChessGame;
import view.InputView;
import view.OutputView;

public class chessGameApplication {

    public static void main(String[] args) {
        startPhase();
    }

    private static void startPhase() {
        try {
            InputView.printGameStartMessage();
            ChessBoard chessBoard = ChessBoard.generateChessBoard();
            ChessGame chessGame = new ChessGame(chessBoard);
            OutputView.printChessBoard(chessBoard.getChessBoard());
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            startPhase();
        }
    }
}
