import chess.ChessBoard;
import chess.ChessGame;
import view.InputView;
import view.OutputView;

import java.util.List;

public class chessGameApplication {

    public static void main(String[] args) {
        startPhase();
        executePhase();
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

    private static void executePhase() {
        try {
            List<String> command = InputView.readPlayGameCommand();
            if (command.size() == 1) {
                System.out.println("end");
            } else if (command.size() == 3) {
                System.out.println("move");
                // 무브 나머지 커맨드들이 들어왔는지 확인
            }
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            executePhase();
        }
    }
}
