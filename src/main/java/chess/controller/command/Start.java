package chess.controller.command;

import chess.domain.gamestatus.GameStatus;
import chess.view.OutputView;

public class Start implements Command {

    public static final String command = "start";

    @Override
    public GameStatus execute(GameStatus gameStatus) {
        GameStatus nextStatus = gameStatus.start();
        OutputView.printChessBoard(nextStatus.getBoardString());

        return nextStatus;
    }
}
