package domain.game;

import domain.piece.Color;
import domain.piece.Piece;
import domain.position.Position;
import java.util.Map;

public class ChessGame implements Executable {
    private final ChessBoard chessBoard;
    private Color color;
    private GameState gameState;

    public ChessGame() {
        this.chessBoard = new ChessBoard();
        this.color = Color.WHITE;
        this.gameState = GameState.READY;
    }

    public boolean isNotEnd() {
        return gameState != GameState.END;
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard.getPiecesPosition();
    }

    @Override
    public void start() {
        if (gameState.isRunning()) {
            throw new IllegalStateException("이미 게임이 시작되었습니다.");
        }
        this.gameState = GameState.RUNNING;
    }

    @Override
    public void move(Position source, Position target) {
        if (gameState.isNotRunning()) {
            throw new IllegalStateException("게임 진행중이 아닙니다.");
        }
        chessBoard.checkRoute(source, target, color);
        chessBoard.move(source, target);

        color = Color.reverseColor(color);
    }

    @Override
    public void end() {
        if (gameState.isNotRunning()) {
            throw new IllegalStateException("게임 진행중이 아닙니다.");
        }
        gameState = GameState.END;
    }
}
