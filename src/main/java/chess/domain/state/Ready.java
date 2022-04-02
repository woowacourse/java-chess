package chess.domain.state;

import chess.domain.Turn;
import chess.domain.board.Chessboard;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.LinkedHashMap;
import java.util.Map;

public class Ready implements State {

    private static final String EXCEPTION_MOVE_IMPOSSIBLE = "Ready 상태에서 움직일 수 없습니다.";
    private static final String EXCEPTION_COMPUTE_SCORE_IMPOSSIBLE = "Ready 상태에서 점수를 계산할 수 없습니다.";

    private final Map<Position, Piece> chessboard;
    private final Turn turn;

    public Ready() {
        this.chessboard = new LinkedHashMap<>();
        this.turn = new Turn();
    }

    @Override
    public State start() {
        return Play.from(turn);
    }

    @Override
    public State move(Position source, Position target) {
        throw new UnsupportedOperationException(EXCEPTION_MOVE_IMPOSSIBLE);
    }

    @Override
    public State end() {
        return new Finish(new Chessboard(chessboard));
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double computeScore(Color color) {
        throw new UnsupportedOperationException(EXCEPTION_COMPUTE_SCORE_IMPOSSIBLE);
    }

    @Override
    public Chessboard getChessboard() {
        return new Chessboard(chessboard);
    }
}
