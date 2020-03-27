import chess.game.ChessGame;
import chess.result.ChessResult;
import chess.progress.Progress;
import chess.team.Team;
import view.InputView;
import view.OutputView;

public class ChessController {
    public static void main(String[] args) {
        OutputView.printInformation();
        Progress progress = Progress.CONTINUE;
        ChessGame chessGame = new ChessGame();
        Team turn = Team.WHITE;
        while (!progress.isEnd()) {
            progress = getProgress(chessGame, turn);
            turn = turn.changeTurn();
            OutputView.printPresentPlayer(turn);
        }
        printResult(chessGame);
    }

    private static Progress getProgress(ChessGame chessGame, Team turn) {
        Progress progress;
        String command = InputView.inputCommand();
        progress = chessGame.doOneCommand(command, turn);
        while (progress.isError()) {
            OutputView.printMoveErrorMessage();
            command = InputView.inputCommand();
            progress = chessGame.doOneCommand(command, turn);
        }
        if (!progress.isEnd()) {
            OutputView.printBoard(chessGame.getChessBoard());
        }
        return progress;
    }

    private static void printResult(ChessGame chessGame) {
        ChessResult chessResult = chessGame.findWinner();
        OutputView.print(chessResult);
    }
}
