package chess.machine;

import chess.domain.chessGame.ChessGame;
import chess.view.OutputView;

public class End implements Command {

    private static final String INPUT_COMMAND = "end";

    private End(String input) {
        if (!input.equals(INPUT_COMMAND)) {
            throw new IllegalArgumentException("종료 명령이 아닙니다");
        }
    }

    public static End of(String input) {
        return new End(input);
    }

    @Override
    public void conductCommand(ChessGame chessGame, OutputView outputView) {
        chessGame.endGame();
        outputView.printChessBoard(chessGame);
    }
}
