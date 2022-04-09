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
        return new ReadyToStart();
    }

    @Override
    public GameState move(Position from, Position to) {
        Board board = getBoard();
        validateKingDie(board);

        MoveResult moveResult = board.executeCommand(from, to, getCurrentPieceColor());
        if (!moveResult.isMoveSuccess()) {
            throw new IllegalStateException("말을 이동하는 것에 실패했습니다.");
        }

        return getNextTurnState();
    }

    private void validateKingDie(Board board) {
        if (!board.hasKing(PieceColor.WHITE) || !board.hasKing(PieceColor.BLACK)) {
            throw new IllegalStateException("킹이 죽었으므로 더이상 게임을 진행할 수 없습니다.");
        }
    }

    protected abstract PieceColor getCurrentPieceColor();

    protected abstract GameState getNextTurnState();

    @Override
    public PieceColor getWinColor() {
        if (!board.hasKing(PieceColor.WHITE)) {
            return PieceColor.BLACK;
        }

        if (!board.hasKing(PieceColor.BLACK)) {
            return PieceColor.WHITE;
        }

        throw new IllegalStateException("아직 양쪽의 킹이 모두 살아있습니다.");
    }

    @Override
    public ScoreResult status() {
        return new ScoreResult(board);
    }

    @Override
    public Board getBoard() {
        return board;
    }
}
