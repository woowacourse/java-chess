package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public class ChessGame {

    private static final String CANNOT_IMPLEMENT_COMMAND = "현재 실행할 수 없는 명령입니다.";

    private final Board board;
    private GameState gameState;

    public ChessGame(Board board) {
        this.board = board;
        this.gameState = GameState.READY;
    }

    public ChessGame(Board board, GameState gameState) {
        this.board = board;
        this.gameState = gameState;
    }

    public void start() {
        if (gameState.isRunning() || gameState.isFinished()) {
            throw new IllegalArgumentException(CANNOT_IMPLEMENT_COMMAND);
        }
        gameState = GameState.WHITE_RUNNING;
    }

    public void move(Position start, Position target) {
        if (!gameState.isRunning()) {
            throw new IllegalArgumentException(CANNOT_IMPLEMENT_COMMAND);
        }
        board.move(start, target, gameState.color());
        if (board.isKingCaught(gameState.color().getOpposite())) {
            gameState = changeStateWhenKingCaught(gameState);
            return;
        }
        gameState = gameState.changeTurn();
    }

    private GameState changeStateWhenKingCaught(GameState gameState) {
        if (gameState == GameState.WHITE_RUNNING) {
            return GameState.WHITE_WIN;
        }
        if (gameState == GameState.BLACK_RUNNING) {
            return GameState.BLACK_WIN;
        }
        return gameState;
    }

    public void end() {
        gameState = GameState.FINISHED;
    }

    public Status createStatus() {
        if (gameState.isRunning() || gameState.isFinished()) {
            return new Status(board);
        }
        throw new IllegalArgumentException(CANNOT_IMPLEMENT_COMMAND);
    }

    public boolean isFinished() {
        return gameState.isFinished();
    }

    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }

    public GameState getGameState() {
        return gameState;
    }
}
