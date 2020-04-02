package chess.controller.command;

import chess.domain.gamestatus.GameStatus;
import chess.view.OutputView;

public class Move implements Command {

    public static final String command = "move";
    public static final int argumentCount = 2;

    private String fromPosition;
    private String toPosition;

    public Move(String fromPosition, String toPosition) {
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
    }

    @Override
    public GameStatus execute(GameStatus gameStatus) {
        GameStatus nextStatus = gameStatus.move(fromPosition, toPosition);
        OutputView.printChessBoard(nextStatus.getBoardString());

        return nextStatus;
    }
}
