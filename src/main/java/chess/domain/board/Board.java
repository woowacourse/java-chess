package chess.domain.board;

import chess.domain.Camp;
import chess.domain.piece.NullPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceProperty;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public final class Board {

    private static final int TOTAL_KING_COUNT = 2;
    private static final String CANT_MOVE_TO_SAME_CAMP = "같은 팀 기물이 있는 위치로는 이동할 수 없습니다.";

    private final Map<Position, Piece> board;

    public Board() {
        this.board = BoardFactory.generate();
    }

    public boolean checkNotKnight(final Position position) {
        final Piece piece = board.get(position);
        return piece.getPieceProperty() != PieceProperty.KNIGHT;
    }

    public boolean isNotValidCamp(final Position position, final Camp camp) {
        return board.get(position).isNotSameCampWith(camp);
    }

    public boolean isBlankPosition(final Position position) {
        return board.get(position).isNullPiece();
    }

    public void move(Position beforePosition, Position afterPosition) {
        movePiece(beforePosition, afterPosition);
    }

    public void move(final Positions positions) {
        movePiece(positions);
    }

    private void movePiece(final Position beforePosition, final Position afterPosition) {
        Piece beforePiece = board.get(beforePosition);
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

    private void movePiece(final Positions positions) {
        Piece beforePiece = board.get(positions.before());
        if (isMoveToBlank(positions.after())) {
            beforePiece.move(positions, moveFunction(positions));
            return;
        }
        if (isMoveToOtherCampPiece(positions)) {
            beforePiece.capture(positions, moveFunction(positions));
            return;
        }
        throw new IllegalArgumentException(CANT_MOVE_TO_SAME_CAMP);
    }

    private boolean isMoveToBlank(Position position) {
        return board.get(position).isNullPiece();
    }

    private Consumer<Piece> moveFunction(Position beforePosition, Position afterPosition) {
        return (piece) -> {
            board.put(afterPosition, piece);
            board.put(beforePosition, new NullPiece(null));
        };
    }

    private Consumer<Piece> moveFunction(final Positions positions) {
        return (piece) -> {
            board.put(positions.after(), piece);
            board.put(positions.before(), new NullPiece(null));
        };
    }

    private boolean isMoveToOtherCampPiece(Position beforePosition, Position afterPosition) {
        Piece beforePiece = board.get(beforePosition);
        Piece afterPiece = board.get(afterPosition);
        return beforePiece.isNotSameCampWith(afterPiece);
    }

    private boolean isMoveToOtherCampPiece(final Positions positions) {
        Piece beforePiece = board.get(positions.before());
        Piece afterPiece = board.get(positions.after());
        return beforePiece.isNotSameCampWith(afterPiece);
    }

    public boolean hasKingCaptured() {
        return TOTAL_KING_COUNT != collectKing().size();
    }

    private List<Piece> collectKing() {
        return board.values()
            .stream()
            .filter(piece -> piece.getPieceProperty() == PieceProperty.KING)
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

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
