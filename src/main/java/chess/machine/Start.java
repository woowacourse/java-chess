package chess.machine;

import chess.domain.chessGame.ChessGame;
import chess.view.OutputView;

public class Start implements Command {

    private static final String INPUT_COMMAND = "start";

    private Start(String input) {
        if (!input.equals(INPUT_COMMAND)) {
            throw new IllegalArgumentException("시작 명령이 아닙니다");
        }
    }

    public static Start of(String input) {
        return new Start(input);
    }

    @Override
    public void conductCommand(ChessGame chessGame, OutputView outputView) {
        chessGame.startGame();
        outputView.printChessBoard(chessGame);
    }
}
