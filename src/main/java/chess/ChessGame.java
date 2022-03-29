package chess;

import chess.domain.Status;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;

public class ChessGame {

    private static final String CANNOT_IMPLEMENT_COMMAND = "현재 실행할 수 없는 명령입니다.";

    private final Board board;
    private GameState gameState;

    public ChessGame(Board board) {
        this.board = board;
        gameState = GameState.READY;
    }

    public boolean isNotEnd() {
        return !gameState.isFinished();
    }

    public void start() {
        gameState = GameState.WHITE_RUNNING;
    }

    public void move(Position start, Position target) {
        if (!gameState.isRunning()) {
            throw new IllegalArgumentException(CANNOT_IMPLEMENT_COMMAND);
        }
        if (board.move(start, target, getCurrentColor()).isSamePiece(PieceType.KING)) {
            gameState = GameState.END;
        }
        gameState = gameState.getOpposite();
    }

    private Color getCurrentColor() {
        if (gameState == GameState.BLACK_RUNNING) {
            return Color.BLACK;
        }
        return Color.WHITE;
    }

    public void end() {
        gameState = GameState.END;
    }

    public Board getBoard() {
        return board;
    }

    public Status getStatus() {
        if (!gameState.isRunning() && !gameState.isFinished()) {
            throw new IllegalArgumentException(CANNOT_IMPLEMENT_COMMAND);
        }
        return new Status(board);
    }
}
