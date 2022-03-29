package chess.domain.command;

import chess.domain.ChessGame;
import chess.view.OutputView;

public class Start extends Command {

    private static final Start INSTANCE = new Start();

    private Start() {
    }

    public static Start getInstance() {
        return INSTANCE;
    }

    @Override
    public void execute(final ChessGame chessGame) {
        try {
            checkBeforeStart(chessGame);
            chessGame.start();
            OutputView.printChessBoard(chessGame.findAllPiece());
        } catch (final IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }
    }

    private void checkBeforeStart(final ChessGame chessBoard) {
        if (chessBoard.isPlaying()) {
            throw new IllegalArgumentException("게임이 이미 시작되었습니다.");
        }
    }
}
