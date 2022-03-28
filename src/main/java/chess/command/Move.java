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
        outputView.printBoard(chessGame.getBoard().getValue());
        if (chessGame.isFinished()) {
            outputView.printStatus(chessGame.statusOfWhite(), chessGame.statusOfBlack());
            outputView.printWinner(chessGame.getWinner());
        }
    }

    public Position getSourcePosition() {
        return sourcePosition;
    }

    public Position getTargetPosition() {
        return targetPosition;
    }

    @Override
    public boolean isStart() {
        return false;
    }

    @Override
    public boolean isMove() {
        return true;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean isStatus() {
        return false;
    }
}
