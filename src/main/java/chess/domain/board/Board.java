package chess.domain.board;

import chess.domain.piece.NullPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceName;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public final class Board {

    private final Map<Position, Piece> value;
    private boolean whiteTurn;

    public Board() {
        this.whiteTurn = true;
        this.value = BoardFactory.generate();
    }

    public void move(Position beforePosition, Position afterPosition) {
        Piece piece = this.value.get(beforePosition);

        if (isBlank(beforePosition)) {
            throw new IllegalArgumentException("이동할 수 있는 기물이 없습니다.");
        }
        if (piece.isBlack() == whiteTurn) {
            throw new IllegalArgumentException("상대 진영의 차례입니다.");
        }
        if (!piece.isKnight() && !PathCheck.check(beforePosition, afterPosition, this::isBlank)) {
            throw new IllegalArgumentException("경로에 기물이 있어 움직일 수 없습니다.");
        }

        this.whiteTurn = !whiteTurn;
        if (isBlank(afterPosition)) {
            piece.move(beforePosition, afterPosition, moveFunction(beforePosition, afterPosition));
            return;
        }
        if (isCapturing(piece, afterPosition)) {
            piece.capture(beforePosition, afterPosition, moveFunction(beforePosition, afterPosition));
            return;
        }

        throw new IllegalArgumentException("같은 팀 기물이 있는 위치로는 이동할 수 없습니다.");
    }

    private Consumer<Piece> moveFunction(Position beforePosition, Position afterPosition) {
        return (piece) -> {
            this.value.put(afterPosition, piece);
            this.value.put(beforePosition, new NullPiece(null));
        };
    }

    private boolean isBlank(Position afterPosition) {
        return value.get(afterPosition).isNullPiece();
    }

    private boolean isCapturing(Piece piece, Position afterPosition) {
        return !piece.isSameCampWith(value.get(afterPosition));
    }

    public boolean hasKingCaptured() {
        return 2 != collectKing().size();
    }

    private List<Piece> collectKing() {
        return this.value.values()
            .stream()
            .filter(piece -> piece.pieceName() == PieceName.KING)
            .collect(Collectors.toList());
    }

    public double calculateScoreOfBlack() {
        return Arrays.stream(Column.values())
            .map(this::collectBlackPiecesIn)
            .mapToDouble(pieces -> calculatePawnScoreIn(pieces) + calculateScoreWithoutPawnIn(pieces))
            .sum();
    }

    private List<Piece> collectBlackPiecesIn(Column column) {
        return Arrays.stream(Row.values())
            .map(row -> this.value.get(Position.of(column, row)))
            .filter(Objects::nonNull)
            .filter(Piece::isBlack)
            .collect(Collectors.toList());
    }

    public double calculateScoreOfWhite() {
        return Arrays.stream(Column.values())
            .map(this::collectWhitePiecesIn)
            .mapToDouble(pieces -> calculatePawnScoreIn(pieces) + calculateScoreWithoutPawnIn(pieces))
            .sum();
    }

    private List<Piece> collectWhitePiecesIn(Column column) {
        return Arrays.stream(Row.values())
            .map(row -> this.value.get(Position.of(column, row)))
            .filter(Objects::nonNull)
            .filter(piece -> !piece.isBlack())
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
            .filter(piece -> !piece.isPawn())
            .collect(Collectors.toList());
    }

    private double calculatePawnScoreIn(List<Piece> pieces) {
        List<Piece> pawns = collectPawnsIn(pieces);
        if (pawns.size() == 0) {
            return 0;
        }
        if (pawns.size() > 1) {
            return 0.5 * pawns.size();
        }
        return pawns.get(0).getScore() * pawns.size();
    }

    private List<Piece> collectPawnsIn(List<Piece> pieces) {
        return pieces.stream()
            .filter(Piece::isPawn)
            .collect(Collectors.toList());
    }

    public Map<Position, Piece> getValue() {
        return Collections.unmodifiableMap(value);
    }

    public boolean hasBlackKingCaptured() {
        return collectKing().stream()
            .noneMatch(Piece::isBlack);
    }

    public boolean hasWhiteKingCaptured() {
        return collectKing().stream()
            .allMatch(Piece::isBlack);
    }
}
