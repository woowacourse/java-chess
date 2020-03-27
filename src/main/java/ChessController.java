import chess.team.Team;
import view.InputView;
import view.OutputView;

public class ChessController {
    public static void main(String[] args) {
        OutputView.printInformation();
        Progress progress = Progress.CONTINUE;
        ChessGame chessGame = new ChessGame();
        while (!progress.isEnd()) {
            progress = getProgress(chessGame);
        }
    }

    private static Progress getProgress(ChessGame chessGame) {
        Progress progress;
        String command = InputView.inputCommand();
        progress = chessGame.doOneCommand(command);
        while (progress.isError()) {
            OutputView.printMoveErrorMessage();
            command = InputView.inputCommand();
            progress = chessGame.doOneCommand(command);
        }
        if (!progress.isEnd()) {
            OutputView.printBoard(chessGame.getChessBoard());
        }
        return progress;
    }
}
