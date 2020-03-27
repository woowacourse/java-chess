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
        // TODO : King의 유무 혹은 Score보고 승자 결정하기.
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
}
