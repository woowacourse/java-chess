package chess.domain.board;

import static chess.domain.Camp.BLACK;
import static chess.domain.Camp.WHITE;

import chess.domain.Camp;
import chess.domain.piece.None;
import chess.domain.piece.Piece;
import chess.domain.piece.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public final class Board {
    private static final String ERROR_NO_SOURCE = "이동할 수 있는 기물이 없습니다.";
    private static final String ERROR_NOT_YOUR_TURN = "상대 진영의 차례입니다.";
    private static final String ERROR_NOT_BLANK_PATH = "경로에 기물이 있어 움직일 수 없습니다.";
    private static final String ERROR_SAME_CAMP_TARGET = "같은 팀 기물이 있는 위치로는 이동할 수 없습니다.";

    private static final int COUNT_INITIAL_KING = 2;

    private final Map<Position, Piece> squares;

    Board(Map<Position, Piece> squares) {
        this.squares = new TreeMap<>(squares);
    }

    public void move(Position sourcePosition, Position targetPosition) {
        Piece source = this.squares.get(sourcePosition);
        System.out.println("백색 진영 : " + source.isCamp(WHITE));
        System.out.println("백색 진영 isNotTurn : " + WHITE.isNotTurn());

        checkBlank(sourcePosition);
        checkTurn(source);
        checkPath(sourcePosition, targetPosition, source);

        Camp.switchTurn();
        move(sourcePosition, targetPosition, source);
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
        return COUNT_INITIAL_KING != collectKing().size();
    }

    public Camp winnerByKing() {
        if (this.hasKingOfCampCaptured(BLACK)) {
            return WHITE;
        }
        if (this.hasKingOfCampCaptured(WHITE)) {
            return BLACK;
        }
        return Camp.NONE;
    }

    private boolean hasKingOfCampCaptured(Camp camp) {
        return collectKing().stream()
                .noneMatch(piece -> piece.isCamp(camp));
    }

    private List<Piece> collectKing() {
        return this.squares.values()
                .stream()
                .filter(piece -> piece.isType(Type.KING))
                .collect(Collectors.toList());
    }

    public Camp winnerByScore() {
        double blackScore = calculateScoreOf(BLACK);
        double whiteScore = calculateScoreOf(WHITE);
        if (blackScore > whiteScore) {
            return BLACK;
        }
        if (whiteScore > blackScore) {
            return WHITE;
        }
        return Camp.NONE;
    }

    public double calculateScoreOf(Camp camp) {
        return Arrays.stream(Column.values())
                .map(column -> collectPiecesOfCampIn(column, camp))
                .mapToDouble(pieces -> calculatePawnScoreIn(pieces) + calculateScoreWithoutPawnIn(pieces))
                .sum();
    }

    private List<Piece> collectPiecesOfCampIn(Column column, Camp camp) {
        return Arrays.stream(Row.values())
                .map(row -> this.squares.get(Position.of(column, row)))
                .filter(piece -> piece.isCamp(camp))
                .collect(Collectors.toList());
    }

    private double calculateScoreWithoutPawnIn(List<Piece> pieces) {
        List<Piece> piecesWithoutPawn = collectPiecesWithoutPawnIn(pieces);
        return piecesWithoutPawn.stream()
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private List<Piece> collectPiecesWithoutPawnIn(List<Piece> pieces) {
        return pieces.stream()
                .filter(piece -> !piece.isType(Type.PAWN))
                .collect(Collectors.toList());
    }

    private double calculatePawnScoreIn(List<Piece> pieces) {
        List<Piece> pawns = collectPawnsIn(pieces);
        if (pawns.size() == 0) {
            return 0;
        }
        double pawnScore = pawns.get(0).getScore();
        if (pawns.size() > 1) {
            return pawnScore / 2.0 * pawns.size();
        }
        return pawnScore * pawns.size();
    }

    private List<Piece> collectPawnsIn(List<Piece> pieces) {
        return pieces.stream()
                .filter(piece -> piece.isType(Type.PAWN))
                .collect(Collectors.toList());
    }

    public Map<Position, Piece> getSquares() {
        return Collections.unmodifiableMap(squares);
    }
}
