package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.command.Command;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.Map;

import chess.domain.ChessScore;
import chess.domain.position.Position;

public abstract class State {

    private static final String CANNOT_GENERATE_SCORE = "현재 상태에서는 점수를 불러올 수 없습니다.";
    private static final String CANNOT_GET_COLOR = "현재 상태에서는 색을 확인할 수 없습니다.";

    protected Board board;

    public static State create() {
        return new Ready(BoardInitializer.generate());
    }

    public static State create(Map<Position, Piece> board, Color color) {
        return new Running(board, color);
    }

    public static State create(Map<Position, Piece> board) {
        return new Ready(board);
    }

    public abstract State proceed(Command command);

    public boolean isFinished() {
        return false;
    }

    public ChessScore generateScore() {
        throw new IllegalStateException(CANNOT_GENERATE_SCORE);
    }

    public boolean isRunning() {
        return false;
    }

    public boolean isWhite() {
        throw new IllegalStateException(CANNOT_GET_COLOR);
    }

    public Board getBoard() {
        return board;
    }

    public Color getColor() {
        throw new IllegalStateException(CANNOT_GET_COLOR);
    }
}
