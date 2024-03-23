package domain.game;

import domain.piece.Color;
import domain.piece.Piece;
import domain.position.Position;
import java.util.Map;

public class ChessGame implements Execute {
    private final ChessBoard chessBoard;
    private Color color;
    private State state;

    public ChessGame() {
        this.chessBoard = new ChessBoard();
        this.color = Color.WHITE;
        this.state = State.READY;
    }

    public boolean isNotEnd() {
        return state != State.END;
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard.getPieceByPosition();
    }

    @Override
    public void start() {
        if (state != State.READY) {
            throw new IllegalStateException("이미 게임이 시작되었습니다.");
        }
        this.state = State.RUNNING;
    }

    @Override
    public void move(Position source, Position target) {
        if (state != State.RUNNING) {
            throw new IllegalStateException("게임 진행중이 아닙니다.");
        }
        chessBoard.checkColor(source, color);
        chessBoard.checkRoute(source, target);
        chessBoard.move(source, target);

        color = Color.reverseColor(color);
    }

    @Override
    public void end() {
        if (state!= State.RUNNING) {
            throw new IllegalStateException("게임 진행중이 아닙니다.");
        }
        state = State.END;
    }
}
