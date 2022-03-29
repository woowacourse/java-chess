package chess.command;

import chess.domain.board.Position;
import chess.domain.state.ChessState;
import chess.view.OutputView;

public class Move implements Command {

    private final Position start;
    private final Position target;

    public Move(Position start, Position target) {
        this.start = start;
        this.target = target;
    }

    @Override
    public ChessState execute(ChessState chessState) {
        ChessState movedState = chessState.move(start, target);
        OutputView.printBoard(movedState.getPieces());
        if (movedState.isFinished()) {
            OutputView.printStatus(movedState.createStatus());
        }
        return movedState;
    }
}
