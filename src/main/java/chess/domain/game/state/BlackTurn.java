package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.MoveResult;
import chess.domain.piece.PieceColor;
import chess.domain.position.Position;

public class BlackTurn extends InGame {

    protected BlackTurn(Board board) {
        super(board);
    }

    @Override
    public GameState move(Position from, Position to) {
        Board board = getBoard();
        MoveResult moveResult = board.executeCommand(from, to, PieceColor.BLACK);

        if (!moveResult.isMoveSuccess()) {
            throw new IllegalStateException("말을 이동하는 것에 실패했습니다.");
        }

        if (moveResult.isMoveResult(MoveResult.KILL_KING)) {
            return new ReadyToStart();
        }

        return new WhiteTurn(board);
    }
}
