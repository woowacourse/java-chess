package model;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import model.piece.Bishop;
import model.piece.BlackPawn;
import model.piece.King;
import model.piece.Knight;
import model.piece.Piece;
import model.piece.Queen;
import model.piece.Rook;
import model.piece.WhitePawn;
import model.position.Column;
import model.position.Moving;
import model.position.Position;
import model.position.Row;

public class ChessGame {

    private static final Map<Column, Function<Camp, Piece>> initPosition = new EnumMap<>(Column.class);

    static {
        initPosition.put(Column.A, Rook::new);
        initPosition.put(Column.B, Knight::new);
        initPosition.put(Column.C, Bishop::new);
        initPosition.put(Column.D, Queen::new);
        initPosition.put(Column.E, King::new);
        initPosition.put(Column.F, Bishop::new);
        initPosition.put(Column.G, Knight::new);
        initPosition.put(Column.H, Rook::new);
    }

    private final Map<Position, Piece> board;
    private ChessStatus chessStatus;
    private Camp camp;

    public ChessGame() {
        this.board = new HashMap<>();
        this.chessStatus = ChessStatus.INIT;
    }

    public void start() {
        if (chessStatus == ChessStatus.INIT) {
            chessStatus = ChessStatus.RUNNING;
            this.camp = Camp.WHITE;
            setting();
            return;
        }
        throw new IllegalArgumentException("이미 게임이 진행중입니다.");
    }

    private void setting() {
        settingExceptPawn(Camp.BLACK, Row.EIGHTH);
        settingBlackPawn();
        settingWhitePawn();
        settingExceptPawn(Camp.WHITE, Row.FIRST);
    }

    private void settingExceptPawn(final Camp camp, Row row) {
        for (Column column : Column.values()) {
            board.put(new Position(column, row), initPosition.get(column).apply(camp));
        }
    }

    private void settingBlackPawn() {
        for (Column column : Column.values()) {
            board.put(new Position(column, Row.SEVENTH), new BlackPawn());
        }
    }

    private void settingWhitePawn() {
        for (Column column : Column.values()) {
            board.put(new Position(column, Row.SECOND), new WhitePawn());
        }
    }

    public void move(Moving moving) {
        if (chessStatus == ChessStatus.RUNNING) {
            validate(moving);

            Piece source = board.get(moving.getCurrentPosition());
            board.put(moving.getNextPosition(), source);
            board.remove(moving.getCurrentPosition());
            checkKing();
            camp = camp.toggle();
            return;
        }
        throw new IllegalArgumentException("start를 입력해야 게임이 시작됩니다.");
    }

    private void validate(Moving moving) {
        Position currentPosition = moving.getCurrentPosition();
        validateCurrentPositionIsEmpty(currentPosition);

        Piece piece = board.get(currentPosition);
        validateOtherCamp(piece);

        Set<Position> positions = getRoute(moving, piece);
        validateRouteIsContainPiece(positions);

        Piece target = board.get(moving.getNextPosition());
        validateTargetIsSameCamp(target, piece);
    }

    private void validateCurrentPositionIsEmpty(Position currentPosition) {
        if (!board.containsKey(currentPosition)) {
            throw new IllegalArgumentException("해당 위치에 기물이 없습니다.");
        }
    }

    private void validateOtherCamp(Piece piece) {
        if (!piece.isSameCamp(camp)) {
            throw new IllegalArgumentException("자신의 기물만 이동 가능합니다.");
        }
    }

    private Set<Position> getRoute(final Moving moving, final Piece piece) {
        if (board.containsKey(moving.getNextPosition())) {
            return piece.getAttackRoute(moving);
        }
        return piece.getMoveRoute(moving);
    }

    private void validateRouteIsContainPiece(Set<Position> positions) {
        for (Position position : positions) {
            validateContainPiece(position);
        }
    }

    private void validateContainPiece(Position position) {
        if (board.containsKey(position)) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 있습니다.");
        }
    }

    private void validateTargetIsSameCamp(Piece target, Piece piece) {
        if (target != null && target.isSameCamp(piece.getCamp())) {
            throw new IllegalArgumentException("도착 지점에 같은 진영의 기물이 있습니다.");
        }
    }

    private void checkKing() {
        if (isKingDie()) {
            end();
        }
    }

    public boolean isKingDie() {
        return 2 > board.values().stream()
                .filter(Piece::isKing)
                .count();
    }

    public ScoreCalculator status() {
        if (chessStatus == ChessStatus.RUNNING) {
            return calculateResult();
        }
        throw new IllegalArgumentException("start를 입력해야 게임이 시작됩니다.");
    }

    public ScoreCalculator calculateResult() {
        return new ScoreCalculator(Collections.unmodifiableMap(board));
    }

    public void end() {
        chessStatus = ChessStatus.END;
    }

    public boolean isNotEnd() {
        return chessStatus.isNotEnd();
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public Camp getCamp() {
        return camp;
    }
}
