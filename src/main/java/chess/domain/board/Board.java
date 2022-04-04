package chess.domain.board;

import chess.domain.Camp;
import chess.domain.piece.NullPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceProperty;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.function.Consumer;
import java.util.function.Predicate;
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
            .anyMatch(oppositePieceEntry -> canMoveOppositePieceToKing(currentKingPosition, oppositePieceEntry));
    }

    public List<Position> getKingCheckmatedPositions(final Camp camp) {
        final SortedMap<UnitDirectVector, List<Position>> movableKingPositions = findMovableKingPositionsBy(camp);

        final List<Entry<Position, Piece>> oppositeEntry = board.entrySet()
            .stream()
            .filter(it -> it.getValue().isSameCampWith(camp.switchCamp()))
            .collect(Collectors.toList());

        final List<Position> flattedMovableKingPositions = movableKingPositions.values().stream().flatMap(List::stream)
            .collect(Collectors.toList());

        return flattedMovableKingPositions.stream()
            .filter(position -> oppositeEntry.stream()
                .anyMatch(opposite -> {
                    if (canMoveOppositePieceToKing(position, opposite)) {
//                        System.out.println(""
//                            + camp.switchCamp() + "진영" +
//                            opposite.getValue().getCharacter() + "에 의해 " + position
//                            + "가 KingCheckmatedPositions로 등록될 예정");  ////
                        return true;
                    }
                    return false;
                }))
            .collect(Collectors.toList());

//        return movableKingPositions.values()
//            .stream()
//            .map(positions -> positions.stream()
//                .filter(kingPosition -> oppositeEntry.stream()
//                    .anyMatch(opposite -> canMoveOppositePieceToKing(kingPosition, opposite)))
//                .collect(Collectors.toList()))
//            .flatMap(List::stream)
//            .collect(Collectors.toList());
    }

    private SortedMap<UnitDirectVector, List<Position>> findMovableKingPositionsBy(final Camp camp) {
        final Position currentKingPosition = findCurrentKingPosition(camp);

        return findMovablePositionsFrom(currentKingPosition);
    }


    private boolean isAnyPositionCheckmatedIn(final Camp camp, final List<Position> flattedKingPositions) {
        return board.entrySet()
            .stream()
            .filter(it -> it.getValue().isSameCampWith(camp.switchCamp()))
            .anyMatch(oppositePieceEntry -> isAnyPositionCheckmated(flattedKingPositions, oppositePieceEntry));
    }

    private boolean isAnyPositionCheckmated(final List<Position> flattedKingPositions,
                                            final Entry<Position, Piece> oppositePieceEntry) {
        for (final Position kingPosition : flattedKingPositions) {
            if (canMoveOppositePieceToKing(kingPosition, oppositePieceEntry)) {
//                System.out.println(camp.name() + "킹이 갈 예정인 곳" + kingPosition + "이 모두 체크된 체크메이트입니다.");
                return true;
            }
        }
        return false;
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

    private boolean canMoveOppositePieceToKing(final Position kingPosition,
                                               final Entry<Position, Piece> oppositeEntry) {
//            && isNullPieceInitToNextPosition(isNullPiece, initPosition, nextPosition) &&
//            //11. 중간경로뿐만 아니라 마지막 그 위치next도 확인해야한다 ( pathTo는 사이경로)
//            isNullPiece.apply(nextPosition) &&

        final Piece oppositePiece = oppositeEntry.getValue();
        final Position oppositePosition = oppositeEntry.getKey();
        final Positions fromOppositeTokingPositions = new Positions(oppositePosition, kingPosition);

        return oppositePiece.checkCanMoveByDistance(fromOppositeTokingPositions)
            && isNullPieceFromOppositeToKing(fromOppositeTokingPositions);
        // 여기서는 마지막 king이 있는 위치 검사는 안해도됨. 안쪽에서는 king의 예상경로라서 비었는지 체크함.
    }

    private boolean isNullPieceFromOppositeToKing(final Positions positions) {
        return positions.before().pathTo(positions.after())
            .stream()
            .allMatch(position -> isNullPiece().test(position));
    }

    public SortedMap<UnitDirectVector, List<Position>> findMovablePositionsFrom(final Position position) {
// 4-1 beforePiece를 받아 움직이기 전, 가능한 움직임을 검사해본다.
        // -> piece마다 get가능한방향()을 써야하므로 piece에 메세지를 보낸다.
        // -> 가려는 벡터의 방향 vs get 가능한방향을 비교해야하므로 -> positions도 같이 넘겨준다.
        Piece beforePiece = board.get(position);

        //7-1. piece를 타객체(this)로 board.isBlankPosition(position)기능을 넘기자
        // -> board.get(position).isNullPiece();
//        isNullPiece()

        return beforePiece.findMovablePositionsByDirection(position, isNullPiece());
        //4-4 afterPosition을 만들다보니 -> 유효한 position인지 검사는 state(Running)에서 해야한다?    }
    }

    private Predicate<Position> isNullPiece() {
        return (position) -> board.get(position).isNullPiece();
    }

    public boolean isAllKingCheckmated(final Camp camp, final List<Position> positions) {
        final SortedMap<UnitDirectVector, List<Position>> movableKingPositions = findMovableKingPositionsBy(camp);

        final List<Position> flattedMovableKingPositions = movableKingPositions.values().stream().flatMap(List::stream)
            .collect(
                Collectors.toList());
//        for (Position flattedMovableKingPosition : flattedMovableKingPositions) {
////            System.out.println("checkmate된 킹이 현재 갈 수 있는 곳 : " + flattedMovableKingPosition);
//        }
        return positions.containsAll(flattedMovableKingPositions);
    }
}

