package chess.controller.command;

import chess.domain.ChessGame;
import chess.domain.Score;
import chess.domain.position.Position;
import chess.view.OutputView;

public class Move implements Command {

    private final Position from;
    private final Position to;

    public Move(final String from, final String to) {
        this.from = Position.from(from);
        this.to = Position.from(to);
    }

    @Override
    public void execute(final ChessGame chessGame) {
        try {
            chessGame.move(from, to);
            checkKingDie(chessGame);
        } catch (final IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }
    }

    private void checkKingDie(final ChessGame chessGame) {
        OutputView.printChessBoard(chessGame.findAllPiece());

        if (chessGame.isFinish()) {
            OutputView.printResult(new Score(chessGame.findAllPiece()));
        }
    }
}
