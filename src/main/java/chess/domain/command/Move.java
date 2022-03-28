package chess.domain.command;

import chess.domain.ChessGame;
import chess.domain.board.Positions;
import chess.view.OutputView;

public class Move implements Command {
    private final ChessGame chessGame;

    public Move(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public void execute(String rawInputCommand, final OutputView outputView) {
        final Positions movePositions = Positions.from(rawInputCommand);
        chessGame.move(movePositions.get(0), movePositions.get(1));
        outputView.printBoard(chessGame.board().getValue());
        if (chessGame.isNotRunning()) {

            outputView.printFinishMessage();
            outputView.printStatus(chessGame.statusOfWhite(), chessGame.statusOfBlack());
            if (chessGame.hasBlackWon() > 0) {
                outputView.printBlackWin();
            }
            if (chessGame.hasBlackWon() < 0) {
                outputView.printWhiteWin();
            }
            if (chessGame.hasBlackWon() == 0) {
                outputView.printDraw();
            }

        }
    }
}
