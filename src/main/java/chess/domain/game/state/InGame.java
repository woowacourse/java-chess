package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.MoveResult;
import chess.domain.game.score.ScoreResult;
import chess.domain.piece.PieceColor;
import chess.domain.position.Position;

public abstract class InGame implements GameState {

    private final Board board;

    protected InGame(Board board) {
        this.board = board;
    }

    @Override
    public GameState start() {
        throw new IllegalStateException("이미 게임 중 이므로 게임을 시작할 수 없습니다.");
    }

    @Override
    public GameState move(Position from, Position to) {
        Board board = getBoard();
        MoveResult moveResult = board.executeCommand(from, to, getCurrentPieceColor());

        if (!moveResult.isMoveSuccess()) {
            throw new IllegalStateException("말을 이동하는 것에 실패했습니다.");
        }

        if (moveResult.equals(MoveResult.KILL_KING)) {
            return new ReadyToStart();
        }

        return getNextTurnState();
    }

    protected abstract PieceColor getCurrentPieceColor();

    protected abstract GameState getNextTurnState();

    @Override
    public ScoreResult status() {
        return new ScoreResult(board);
    }

    @Override
    public Board getBoard() {
        return board;
    }
}
