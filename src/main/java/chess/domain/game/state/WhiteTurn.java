package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.MoveResult;
import chess.domain.piece.PieceColor;
import chess.domain.position.Position;

public class WhiteTurn extends InGame {

    protected WhiteTurn() {
        super(Board.createInitializedBoard());
    }

    protected WhiteTurn(Board board) {
        super(board);
    }

    @Override
    public GameState move(Position from, Position to) {
        Board board = getBoard();
        MoveResult moveResult = board.executeCommand(from, to, PieceColor.WHITE);

        if (!moveResult.isMoveSuccess()) {
            throw new IllegalStateException("말을 이동하는 것에 실패했습니다.");
        }

        if (moveResult.equals(MoveResult.KILL_KING)) {
            return new ReadyToStart();
        }

        return new BlackTurn(board);
    }
}
