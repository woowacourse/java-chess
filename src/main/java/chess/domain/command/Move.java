package chess.domain.command;

import chess.domain.ChessGame;
import chess.domain.board.Positions;
import chess.view.OutputView;

public final class Move implements CommandStrategy {
    @Override
    public void execute(final String command, final ChessGame chessGame) {
        final Positions movePositions = Positions.from(command);
        chessGame.move(movePositions);

        OutputView.printBoard(chessGame.getBoard());
        if (chessGame.isNotRunning()) {
            OutputView.printFinishMessage();
            OutputView.printStatus(chessGame.calculateStatus());
            OutputView.printResultMessage(chessGame.getResultMessage());
        }
    }
}
