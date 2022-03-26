package chess.domain.game.gamestate;

import chess.domain.board.Board;
import chess.domain.board.MoveResult;
import chess.domain.piece.PieceColor;
import chess.domain.position.Position;

class RunningWhite extends Running {

    public RunningWhite(Board board) {
        super(board);
    }

    @Override
    public State move(Position from, Position to) {
        Board board = getBoard();
        MoveResult moveResult = board.executeCommand(from, to, PieceColor.WHITE);

        if (moveResult.isMoveResult(MoveResult.KILL_KING)) {
            return new FinishedKing(getBoard());
        }

        if (moveResult.isMoveSuccess()) {
            return new RunningBlack(getBoard());
        }

        return this;
    }
}
