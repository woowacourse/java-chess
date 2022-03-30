package chess.domain.gamestate;

import chess.domain.Camp;
import chess.domain.Result;
import chess.domain.board.Board;
import chess.domain.board.BoardStatusCalculator;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.List;

public class Running implements State {
    private static final String DONT_START_WHEN_RUNNING = "진행 중일 때는 시작할 수 없습니다.";
    private static final String CANT_GET_RESULT_WHEN_NOW = "아직 승패를 판정할 수 없습니다.";
    private static final String NO_PIECE_TO_MOVE = "이동할 수 있는 기물이 없습니다.";
    private static final String TURN_OPPOSITE_CAMP = "상대 진영의 차례입니다.";
    private static final String CANT_MOVE_WHEN_OBSTACLE_IN_PATH = "경로에 기물이 있어 움직일 수 없습니다.";

    private final Board board;
    private final Camp camp;

    public Running(Board board) {
        this(board, Camp.WHITE);
    }

    public Running(final Board board, final Camp camp) {
        this.board = board;
        this.camp = camp;
    }

    @Override
    public State start() {
        throw new IllegalStateException(DONT_START_WHEN_RUNNING);
    }

    @Override
    public State move(Position beforePosition, Position afterPosition) {
        checkValidPosition(beforePosition, afterPosition);

        this.board.move(beforePosition, afterPosition);

        if (this.board.hasKingCaptured()) {
            return new Finished(this.board);
        }
        return new Running(this.board, this.switchCamp());
    }

    private void checkValidPosition(final Position beforePosition, final Position afterPosition) {
        checkValidPiece(beforePosition);
        checkValidTurn(beforePosition);
        checkObstacles(beforePosition, afterPosition);
    }

    private void checkValidPiece(final Position position) {
        if (this.board.isBlankPosition(position)) {
            throw new IllegalArgumentException(NO_PIECE_TO_MOVE);
        }
    }

    private void checkValidTurn(final Position position) {
        if (isNotValidCamp(position)) {
            throw new IllegalArgumentException(TURN_OPPOSITE_CAMP);
        }
    }

    private boolean isNotValidCamp(final Position position) {
        return this.board.isNotValidCamp(position, this.camp);
    }

    private void checkObstacles(final Position beforePosition, final Position afterPosition) {
        if (isNotKnight(beforePosition) && containObstacleInPath(beforePosition, afterPosition)) {
            throw new IllegalArgumentException(CANT_MOVE_WHEN_OBSTACLE_IN_PATH);
        }
    }

    private boolean containObstacleInPath(final Position beforePosition, final Position afterPosition) {
        List<Position> path = beforePosition.pathTo(afterPosition);
        return !path.stream()
            .allMatch(this::isBlankPosition);
    }

    private boolean isBlankPosition(final Position position) {
        return this.board.isBlankPosition(position);
    }

    private boolean isNotKnight(final Position beforePosition) {
        return this.board.checkNotKnight(beforePosition);
    }

    @Override
    public Camp switchCamp() {
        return this.camp.switchCamp();
    }

    @Override
    public State end() {
        return new Finished(this.board);
    }

    @Override
    public double statusOfBlack() {
        return new BoardStatusCalculator(board).calculate(Piece::isBlack);
    }

    @Override
    public double statusOfWhite() {
        return new BoardStatusCalculator(board).calculate(piece -> !piece.isBlack());
    }

    @Override
    public Result getResult() {
        throw new IllegalStateException(CANT_GET_RESULT_WHEN_NOW);
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public Board getBoard() {
        return this.board;
    }
}
