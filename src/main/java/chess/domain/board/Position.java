
package chess.domain.board;

import chess.domain.piece.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Position {
    private final Rank rank;
    private final Column column;
    private static final List<Position> cache;

    /** todo 4: 질문4
     * Position에서 사용하는 값은 정해져 있어서 캐싱을 이용했는데요,
     * stream filter을 이용하면, 객체를 생성할때마다 모든 List<Position> cache를 순회하는데,
     * 성능적으로 이게 맞는 캐싱인지 의문이 듭니다.
     *
     * 이에 대한 리뷰어님의 의견이 궁금합니다!
     */

    /** todo: 질문5
     * 뷰에서 포지션에대한 정보를 문자열로 제공 했을 때,
     * 예) a1
     * a와 1에 대한 각각 Column과 Rank에서 유효성을 검사하고
     * Position객체에서는 필드 자체(Column과 Rank)의 유효성 검사를 할 필요 없다고 생각하였는데,
     *
     * Position객체의 생성 시 인자를 문자열로 받아, 객체 내부에서 필드의 유효성 검사를 하는 것이 맞을까요?
     * 예) Position of(String column, String rank) {
     *     ~
     * }
     *
     * 이에 대한 리뷰어님의 의견이 궁금합니다
     */
    static {
        cache = Column.getOrderedColumns().stream()
                .flatMap(column -> Rank.getOrderedRanks().stream()
                        .map(rank -> new Position(column,rank)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Position(Column column, Rank rank) {
        this.column = column;
        this.rank = rank;
    }

    public static Position of(Column column, Rank rank) {
        return cache.stream()
                .filter(position -> position.column == column && position.rank == rank)
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 Position입니다"));
    }


    public int findGapOfRank(Position destination) {
        return destination.rank.getIndex() - rank.getIndex();
    }

    public int findGapOfColumn(Position destination) {
        return destination.column.getIndex() - column.getIndex();
    }

    public Position moveDirection(Direction direction) {
        Column newColumn = Column.findColumnByIndex(column.getIndex() + direction.getX());
        Rank newRank = Rank.findRankByIndex(rank.getIndex() + direction.getY());

        return new Position(newColumn, newRank);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return rank == position.rank && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, column);
    }
}
