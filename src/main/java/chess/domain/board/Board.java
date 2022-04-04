package chess.domain.board;

import chess.domain.Camp;
import chess.domain.piece.NullPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceProperty;
import com.google.common.base.Predicate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
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

    public void moveIfValidPiece(final Positions positions) {
        if (isAfterPieceSameCamp(positions)) {
            throw new IllegalArgumentException(CANT_MOVE_TO_SAME_CAMP);
        }

        Piece beforePiece = board.get(positions.before());
        beforePiece.move(positions, moveFunction(positions));
    }

    public void moveIfValidPiece(Position beforePosition, Position afterPosition) {
        moveIfValidPiece(new Positions(beforePosition, afterPosition));
    }

    private boolean isAfterPieceSameCamp(final Positions positions) {
        return board.get(positions.before())
            .isSameCampWith(board.get(positions.after()));
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

    public SortedMap<UnitDirectVector, List<Position>> findMovablePositions(final Positions positions) {
// 4-1 beforePiece를 받아 움직이기 전, 가능한 움직임을 검사해본다.
        // -> piece마다 get가능한방향()을 써야하므로 piece에 메세지를 보낸다.
        // -> 가려는 벡터의 방향 vs get 가능한방향을 비교해야하므로 -> positions도 같이 넘겨준다.
        Piece beforePiece = board.get(positions.before());
        final SortedMap<UnitDirectVector, List<Position>> movablePositions = beforePiece.findMovableDirections(
            positions, isNullPiece());

        //7-1. piece를 타객체(this)로 board.isBlankPosition(position)기능을 넘기자
        // -> board.get(position).isNullPiece();
//        isNullPiece()

        return movablePositions;
        //4-4 afterPosition을 만들다보니 -> 유효한 position인지 검사는 state(Running)에서 해야한다?    }
    }

    private Predicate<Position> isNullPiece() {
        return (position) -> board.get(position).isNullPiece();
    }
}
