package chess.command;

import chess.domain.ChessGame;
import chess.domain.board.Position;
import chess.view.OutputView;

public class Move implements Command {
    private final Position sourcePosition;
    private final Position targetPosition;

    protected Move(Position sourcePosition, Position targetPosition) {
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
    }

    @Override
    public void execute(ChessGame chessGame, OutputView outputView) {
        chessGame.move(this.sourcePosition, this.targetPosition);
        outputView.printBoard(chessGame.getBoard().getSquares());
        if (chessGame.isFinished()) {
            outputView.printScore(chessGame.scoreOfWhite(), chessGame.scoreOfBlack());
            outputView.printWinner(chessGame.getWinner());
        }
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
