package chess.domain.gamestate;

import chess.domain.Camp;
import chess.domain.StatusScore;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;

public class Running implements State {

    private static final String NO_PIECE_TO_MOVE = "이동할 수 있는 기물이 없습니다.";
    private static final String TURN_OPPOSITE_CAMP = "상대 진영의 차례입니다.";
    private static final String CANT_MOVE_WHEN_OBSTACLE_IN_PATH = "경로에 기물이 있어 움직일 수 없습니다.";
    private static final String CANT_READY_WHEN_NOT_RUNNING = "게임 종료가 아닐때는 시작상태로 돌아갈 수 없습니다.";
    private static final String PAWN_CANT_MOVE_DIAGONAL_WHEN_BLANK = "폰이 대각선으로 움직이는 경우, 타겟이 있을 때만 가능합니다.";
    private static final String PAWN_CANT_MOVE_LINEAR_WHEN_TARGET_PIECE_EXIST = "폰이 앞으로 움직이는 경우, 타겟이 없을 때만 가능합니다.";

    private Board board;
    private final Camp camp;

    public Running(Board board) {
        this(board, Camp.WHITE);
    }

    public Running(final Board board, final Camp camp) {
        this.board = board;
        this.camp = camp;
    }

    @Override
    public State run() {
        return new Running(new Board());
    }

    @Override
    public State move(final Position before, final Position after) {
        return move(new Positions(List.of(before, after)));
    }

    @Override
    public State move(final Positions positions) {
        checkValidPosition(positions);
        board.moveIfValidPiece(positions);

        if (board.hasKingCaptured()) {
            return new Finished(board);
        }

        return new Running(board, switchCamp());
    }

    private void checkValidPosition(final Positions positions) {
        checkValidBeforePiece(positions.before());
        checkValidBeforePieceTurn(positions.before());
        checkObstaclesFromBeforeToAfterPosition(positions);
        checkValidPawnMove(positions);
    }

    private void checkValidPawnMove(final Positions positions) {
        if (board.isPawn(positions.before()) && positions.isDiagonalMove()) {
            checkPawnValidCapturing(positions);
        }
        if (board.isPawn(positions.before()) && !positions.isDiagonalMove()) {
            checkPawnValidMoving(positions);
        }
    }

    private void checkPawnValidCapturing(final Positions positions) {
        if (board.isBlankPosition(positions.after())) {
            throw new IllegalStateException(PAWN_CANT_MOVE_DIAGONAL_WHEN_BLANK);
        }
    }

    private void checkPawnValidMoving(final Positions positions) {
        if (!board.isBlankPosition(positions.after())) {
            throw new IllegalStateException(PAWN_CANT_MOVE_LINEAR_WHEN_TARGET_PIECE_EXIST);
        }
    }

    private void checkValidBeforePiece(final Position position) {
        if (board.isBlankPosition(position)) {
            throw new IllegalArgumentException(NO_PIECE_TO_MOVE);
        }
    }

    private void checkValidBeforePieceTurn(final Position position) {
        if (isNotValidCamp(position)) {
            throw new IllegalArgumentException(TURN_OPPOSITE_CAMP);
        }
    }

    private boolean isNotValidCamp(final Position position) {
        return board.isNotValidCamp(position, camp);
    }

    private void checkObstaclesFromBeforeToAfterPosition(final Positions positions) {
        if (isNotKnight(positions.before()) && containObstacleInPath(positions)) {
            throw new IllegalArgumentException(CANT_MOVE_WHEN_OBSTACLE_IN_PATH);
        }
    }

    private boolean isNotKnight(final Position beforePosition) {
        return board.checkNotKnight(beforePosition);
    }

    private boolean containObstacleInPath(final Positions positions) {
        List<Position> path = positions.calculatePath();
        return !path.stream()
            .allMatch(this::isBlankPosition);
    }

    private boolean isBlankPosition(final Position position) {
        return board.isBlankPosition(position);
    }

    @Override
    public Camp switchCamp() {
        return camp.switchCamp();
    }

    @Override
    public State status() {
        return new Status(board, camp);
    }

    @Override
    public StatusScore calculateStatus() {
        return StatusScore.from(board);
    }

    @Override
    public State end() {
        return new Finished(board);
    }

    @Override
    public State ready() {
        throw new IllegalStateException(CANT_READY_WHEN_NOT_RUNNING);
    }

    @Override
    public boolean isKingChecked() {
        return board.isKingChecked(camp);
    }

    @Override
    public List<Position> getKingCheckmatedPositions() {
        return board.getKingCheckmatedPositions(camp);
    }

    @Override
    public boolean isAllKingCheckmated(final List<Position> positions) {
        return board.isAllKingCheckmated(camp, positions);
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }

    @Override
    public Camp getCamp() {
        return camp;
    }

    @Override
    public void changeBoard(final Map<Position, Piece> board, final String camp) {
        this.board = this.board.changeBoard(board);
    }

    @Override
    public State runWithCurrentState() {
        return new Running(board, camp);
    }
}
