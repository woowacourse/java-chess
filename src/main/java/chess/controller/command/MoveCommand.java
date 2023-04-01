package chess.controller.command;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.view.OutputView;

public class MoveCommand implements Command {

    private final Position sourcePosition;
    private final Position targetPosition;

    public MoveCommand(Position sourcePosition, Position targetPosition) {
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
    }

    @Override
    public void execute(ChessGame chessGame) {
        chessGame.move(sourcePosition, targetPosition);
        OutputView.printChessBoard(chessGame.getChessBoard());
    }
}
