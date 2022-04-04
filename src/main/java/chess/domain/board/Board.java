package chess.domain.board;

import chess.domain.Camp;
import chess.domain.piece.None;
import chess.domain.piece.Piece;
import chess.domain.piece.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;

public final class Board {
    private static final String ERROR_NO_SOURCE = "이동할 수 있는 기물이 없습니다.";
    private static final String ERROR_NOT_YOUR_TURN = "상대 진영의 차례입니다.";
    private static final String ERROR_NOT_BLANK_PATH = "경로에 기물이 있어 움직일 수 없습니다.";
    private static final String ERROR_SAME_CAMP_TARGET = "같은 팀 기물이 있는 위치로는 이동할 수 없습니다.";

    private static final int COUNT_INITIAL_KING = 2;

    private final Map<Position, Piece> squares;

    public Board(Map<Position, Piece> squares) {
        this.squares = new TreeMap<>(squares);
    }

    public void move(Position sourcePosition, Position targetPosition) {
        Piece source = this.squares.get(sourcePosition);

        checkBlank(sourcePosition);
        checkTurn(source);
        checkPath(sourcePosition, targetPosition, source);

        move(sourcePosition, targetPosition, source);
        Camp.switchTurn();
    }

    private void checkBlank(Position sourcePosition) {
        if (isBlank(sourcePosition)) {
            throw new IllegalArgumentException(ERROR_NO_SOURCE);
        }
    }

    private void checkTurn(Piece source) {
        if (source.isNotTurn()) {
            throw new IllegalArgumentException(ERROR_NOT_YOUR_TURN);
        }
    }

    private void checkPath(Position sourcePosition, Position targetPosition, Piece source) {
        if (source.isType(Type.KNIGHT)) {
            return;
        }
        boolean blankPath = sourcePosition.pathTo(targetPosition).stream()
                .allMatch(this::isBlank);
        if (!blankPath) {
            throw new IllegalArgumentException(ERROR_NOT_BLANK_PATH);
        }
    }

    private void move(Position sourcePosition, Position targetPosition, Piece source) {
        if (isBlank(targetPosition)) {
            source.move(sourcePosition, targetPosition, moveFunction(sourcePosition, targetPosition));
            return;
        }
        checkTargetCamp(targetPosition, source);
        source.capture(sourcePosition, targetPosition, moveFunction(sourcePosition, targetPosition));
    }

    private void checkTargetCamp(Position targetPosition, Piece source) {
        if (source.isSameCampWith(this.squares.get(targetPosition))) {
            throw new IllegalArgumentException(ERROR_SAME_CAMP_TARGET);
        }
    }

    private Consumer<Piece> moveFunction(Position sourcePosition, Position targetPosition) {
        return (piece) -> {
            this.squares.put(targetPosition, piece);
            this.squares.put(sourcePosition, new None());
        };
    }

    private boolean isBlank(Position position) {
        return squares.get(position).isType(Type.NONE);
    }

    public boolean hasKingCaptured() {
        int kingCount = countPiecesOf(Camp.WHITE, Type.KING) + countPiecesOf(Camp.BLACK, Type.KING);
        return COUNT_INITIAL_KING > kingCount;
    }

    public int countPiecesOf(Camp camp, Type type) {
        return (int) this.squares.values().stream()
                .filter(piece -> piece.isType(type))
                .filter(piece -> piece.isCamp(camp))
                .count();
    }

    public int countPiecesOfCampIn(Column column, Camp camp, Type type) {
        return (int) Arrays.stream(Row.values())
                .map(row -> this.squares.get(Position.of(column, row)))
                .filter(piece -> piece.isType(type))
                .filter(piece -> piece.isCamp(camp))
                .count();
    }

    public Map<Position, Piece> getSquares() {
        return Collections.unmodifiableMap(squares);
    }
}
