package chess.controller.command;

import chess.domain.ChessGame;
import chess.domain.position.Position;
import chess.view.OutputView;

public class MoveCommand implements Command {
    private final Position start;
    private final Position destination;

    public MoveCommand(Position start, Position destination) {
        this.start = start;
        this.destination = destination;
    }

    @Override
    public void execute(ChessGame chessGame, OutputView outputView) {
        chessGame.move(start, destination);
        outputView.printChessBoardMessage(chessGame.getChessBoard());
    }

    @Override
    public boolean isNotEndCommand() {
        return true;
    }

    @Override
    public boolean isNotStartCommand() {
        return true;
    }
}
