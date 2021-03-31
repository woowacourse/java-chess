package chess;

import chess.controller.ChessAction;
import chess.controller.GameStatus;
import chess.controller.Option;
import chess.domain.game.ChessException;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ChessAction chessAction = ChessAction.getInstance();
        OutputView.printStartGame();
        playChess(chessAction);
    }

    private static void playChess(ChessAction chessAction) {
        try {
            String command = InputView.option();
            Option option = Option.selectedOption(command.split(" ")[0]);
            GameStatus gameStatus = option.execute(chessAction, command);
            restartIfNotEnd(gameStatus, chessAction);
        } catch (ChessException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            playChess(chessAction);
        }
    }

    private static void restartIfNotEnd(GameStatus gameStatus, ChessAction chessAction) {
        if (gameStatus == GameStatus.RUN) {
            playChess(chessAction);
        }
    }
}
