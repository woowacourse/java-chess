package chess.command;

import chess.domain.ChessGame;
import chess.domain.board.Position;
import chess.view.OutputView;

public class Move implements Command {

    private final Position start;
    private final Position target;

    public Move(Position start, Position target) {
        this.start = start;
        this.target = target;
    }

    @Override
    public void execute(final ChessGame chessGame) {
        chessGame.move(start, target);
        OutputView.printBoard(chessGame.getBoard());
        if (chessGame.isFinished()) {
            OutputView.printStatus(chessGame.createStatus());
        }
    }
}
