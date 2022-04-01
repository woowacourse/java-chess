package chess.domain.gamestate;

import chess.domain.Camp;
import chess.domain.StatusScore;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
    public State move(final Positions positions) {
        checkValidPosition(positions);

        board.move(positions);

        if (board.hasKingCaptured()) {
            return new Finished(board);
        }

        return new Running(board, switchCamp());
    }

    @Override
    public State move(final Position before, final Position after) {
        return move(new Positions(List.of(before, after)));
    }

    private void checkValidPosition(final Positions positions) {
        checkValidPiece(positions.before());
        checkValidTurn(positions.before());
        checkObstacles(positions);
    }

    private void checkValidPiece(final Position position) {
        if (board.isBlankPosition(position)) {
            throw new IllegalArgumentException(NO_PIECE_TO_MOVE);
        }
    }

    private void checkValidTurn(final Position position) {
        if (isNotValidCamp(position)) {
            throw new IllegalArgumentException(TURN_OPPOSITE_CAMP);
        }
    }

    private boolean isNotValidCamp(final Position position) {
        return board.isNotValidCamp(position, camp);
    }

    private void checkObstacles(final Positions positions) {
        if (isNotKnight(positions.before()) && containObstacleInPath(positions)) {
            throw new IllegalArgumentException(CANT_MOVE_WHEN_OBSTACLE_IN_PATH);
        }
    }

    private boolean containObstacleInPath(final Positions positions) {
        List<Position> path = positions.calculatePath();
        return !path.stream()
            .allMatch(this::isBlankPosition);
    }

    private boolean isBlankPosition(final Position position) {
        return board.isBlankPosition(position);
    }

    private boolean isNotKnight(final Position beforePosition) {
        return board.checkNotKnight(beforePosition);
    }

    @Override
    public Camp switchCamp() {
        return camp.switchCamp();
    }

    @Override
    public State end() {
        return new Finished(board);
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public StatusScore calculateStatus() {
        return StatusScore.from(board);
    }

    @Override
    public Map<Position, Piece> getBoard() {
        return new TreeMap<>(board.getBoard());
    }

    @Override
    public State status() {
        return new Status(board, camp);
    }

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public State returnState() {
        throw new IllegalStateException();
    }
}
