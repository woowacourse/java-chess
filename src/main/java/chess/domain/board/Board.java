package chess.domain.board;

import chess.domain.Camp;
import chess.domain.piece.NullPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceProperty;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
        return !board.get(position).isSameCampWith(camp);
    }

    public boolean isBlankPosition(final Position position) {
        return board.get(position).isNullPiece();
    }

    public void move(final Positions positions) {
        if (board.get(positions.after()).isNullPiece() || !board.get(positions.before())
            .isSameCampWith(board.get(positions.after()))) {
            movePiece(positions);
            return;
        }
        throw new IllegalArgumentException(CANT_MOVE_TO_SAME_CAMP);
    }

    public void move(Position beforePosition, Position afterPosition) {
        move(new Positions(beforePosition, afterPosition));
    }

    private void movePiece(final Positions positions) {
        Piece beforePiece = board.get(positions.before());
        beforePiece.move(positions, moveFunction(positions));
    }

    private Consumer<Piece> moveFunction(final Positions positions) {
        return (piece) -> {
            board.put(positions.after(), piece);
            board.put(positions.before(), new NullPiece(null));
        };
    }

    public boolean hasKingCaptured() {
        return TOTAL_KING_COUNT != collectKing().size();
    }

    private List<Piece> collectKing() {
        return board.values()
            .stream()
            .filter(this::isKing)
            .collect(Collectors.toList());
    }

    private boolean isKing(final Piece piece) {
        return piece.getPieceProperty() == PieceProperty.KING;
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

    public boolean isKingChecked(final Camp camp) {
        final Position currentKingPosition = findCurrentKingPosition(camp);

        return board.entrySet()
            .stream()
            .filter(it -> it.getValue().isSameCampWith(camp.switchCamp()))
            .anyMatch(it -> canMoveOppositePieceToKing(currentKingPosition, it));
    }

    public Position findCurrentKingPosition(final Camp camp) {
        return board.entrySet()
            .stream()
            .filter(it -> it.getValue().isSameCampWith(camp))
            .filter(it -> isKing(it.getValue()))
            .map(Entry::getKey)
            .findFirst()
            .orElseThrow(IllegalStateException::new);
    }

    private boolean canMoveOppositePieceToKing(final Position currentKingPosition, final Entry<Position, Piece> it) {
        final Piece oppositePiece = it.getValue();
        final Position oppositePieceBeforePosition = it.getKey();
        return oppositePiece.canMove(new Positions(oppositePieceBeforePosition, currentKingPosition));
    }
}
