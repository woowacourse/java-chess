package chess.domain.board;

import chess.domain.Camp;
import chess.domain.piece.NullPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceName;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public final class Board {
    private static final int TOTAL_KING_COUNT = 2;
    private static final String CANT_MOVE_TO_SAME_CAMP = "같은 팀 기물이 있는 위치로는 이동할 수 없습니다.";

    private final Map<Position, Piece> value;

    public Board() {
        this.value = BoardFactory.generate();
    }

    public void move(Position beforePosition, Position afterPosition) {
        movePiece(beforePosition, afterPosition);
    }

    public boolean checkNotKnight(final Position position) {
        final Piece piece = this.value.get(position);
        return piece.pieceName() != PieceName.KNIGHT;
    }

    private void movePiece(final Position beforePosition, final Position afterPosition) {
        Piece beforePiece = this.value.get(beforePosition);
        if (isMoveToBlank(afterPosition)) {
            beforePiece.move(beforePosition, afterPosition, moveFunction(beforePosition, afterPosition));
            return;
        }
        if (isMoveToOtherCampPiece(beforePosition, afterPosition)) {
            beforePiece.capture(beforePosition, afterPosition, moveFunction(beforePosition, afterPosition));
            return;
        }
        throw new IllegalArgumentException(CANT_MOVE_TO_SAME_CAMP);
    }

    public boolean isBlankPosition(final Position position) {
        return this.value.get(position).isNullPiece();
    }

    private boolean isMoveToBlank(Position position) {
        return value.get(position).isNullPiece();
    }

    private Consumer<Piece> moveFunction(Position beforePosition, Position afterPosition) {
        return (piece) -> {
            this.value.put(afterPosition, piece);
            this.value.put(beforePosition, new NullPiece(null));
        };
    }

    private boolean isMoveToOtherCampPiece(Position beforePosition, Position afterPosition) {
        Piece beforePiece = this.value.get(beforePosition);
        Piece afterPiece = this.value.get(afterPosition);
        return !beforePiece.isSameCampWith(afterPiece);
    }

    public boolean hasKingCaptured() {
        return TOTAL_KING_COUNT != collectKing().size();
    }

    private List<Piece> collectKing() {
        return this.value.values()
            .stream()
            .filter(piece -> piece.pieceName() == PieceName.KING)
            .collect(Collectors.toList());
    }

    public boolean hasBlackKingCaptured() {
        return collectKing().stream()
            .noneMatch(Piece::isBlack);
    }

    public boolean hasWhiteKingCaptured() {
        return collectKing().stream()
            .allMatch(Piece::isBlack);
    }

    public Map<Position, Piece> getValue() {
        return Collections.unmodifiableMap(value);
    }

    public boolean isNotValidCamp(final Position position, final Camp camp) {
        return !this.value.get(position).isSameCampWith(camp);
    }
}
