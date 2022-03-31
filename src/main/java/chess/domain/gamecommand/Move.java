package chess.domain.gamecommand;

import chess.domain.ChessGame;
import chess.domain.board.Positions;
import chess.view.OutputView;

public class Move implements GameCommand {

    private final ChessGame chessGame;

    public Move(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public void execute(String rawInputCommand) {
        final Positions movePositions = Positions.from(rawInputCommand);

        chessGame.move(movePositions.get(0), movePositions.get(1));

        OutputView.printBoard(chessGame.board().getValue());
        if (chessGame.isNotRunning()) {
            OutputView.printFinishMessage();
            OutputView.printStatus(chessGame.statusOfWhite(), chessGame.statusOfBlack());
            OutputView.printResultMessage(chessGame.getResultMessage());
        }
    }
}
