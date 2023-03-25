package chess.domain.board;

import chess.domain.piece.Direction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings({"NonAsciiCharacters","SpellCheckingInspection"})
class PositionTest {

    @Test
    void 입력한_정보값과_일치하는_캐싱된_Position객체를_반환한다() {
        for(Rank rank : Rank.getOrderedRanks()) {
            for(Column column : Column.getOrderedColumns()) {
                assertDoesNotThrow(()-> Position.of(column, rank));
            }
        }
    }

    @ParameterizedTest
    @CsvSource(value = {"EIGHT,0","SEVEN,-1","SIX,-2","FIVE,-3","FOUR,-4","THREE,-5", "TWO,-6" ,"ONE,-7"})
    void 입력된_Position의_Rank인덱스값에서_현재_Position의_Rank인덱스값을_뺀_값을_반환한다(Rank otherRank, int expectedGap) {
        Position otherPosition = Position.of(Column.A, otherRank);
        Position currentPosition = Position.of(Column.A, Rank.EIGHT);

        assertThat(currentPosition.findGapOfRank(otherPosition))
                .isEqualTo(expectedGap);
    }

    @ParameterizedTest
    @CsvSource(value = {"A,0","B,1","C,2","D,3","E,4","F,5", "G,6" ,"H,7"})
    void 입력된_Position의_Column인덱스값에서_현재_Position의_Column인덱스값을_뺀_값을_반환한다(Column otherColumn, int expectedGap) {
        Position otherPosition = Position.of(otherColumn, Rank.EIGHT);
        Position currentPosition = Position.of(Column.A, Rank.EIGHT);

        assertThat(currentPosition.findGapOfColumn(otherPosition))
                .isEqualTo(expectedGap);
    }



    @Test
    void 위로_한칸이동한_Position을_반환한다() {
        Direction direction = Direction.TOP;
        Position currentPosition = Position.of(Column.D, Rank.FOUR);
        Position movedPostion = Position.of(Column.D, Rank.FIVE);

        assertThat(currentPosition.moveDirection(direction))
                .isEqualTo(movedPostion);
    }

    @Test
    void 아래로_한칸이동한_Position을_반환한다() {
        Direction direction = Direction.BOTTOM;
        Position currentPosition = Position.of(Column.D, Rank.FOUR);
        Position movedPostion = Position.of(Column.D, Rank.THREE);

        assertThat(currentPosition.moveDirection(direction))
                .isEqualTo(movedPostion);
    }


    @Test
    void 오른쪽으로_한칸이동한_Position을_반환한다() {
        Direction direction = Direction.RIGHT;
        Position currentPosition = Position.of(Column.D, Rank.FOUR);
        Position movedPostion = Position.of(Column.E, Rank.FOUR);

        assertThat(currentPosition.moveDirection(direction))
                .isEqualTo(movedPostion);
    }

    @Test
    void 왼쪽으로_한칸이동한_Position을_반환한다() {
        Direction direction = Direction.LEFT;
        Position currentPosition = Position.of(Column.D, Rank.FOUR);
        Position movedPostion = Position.of(Column.C, Rank.FOUR);

        assertThat(currentPosition.moveDirection(direction))
                .isEqualTo(movedPostion);
    }

    @Test
    void 왼쪽_위로_한칸이동한_Position을_반환한다() {
        Direction direction = Direction.TOP_LEFT;
        Position currentPosition = Position.of(Column.D, Rank.FOUR);
        Position movedPostion = Position.of(Column.C, Rank.FIVE);

        assertThat(currentPosition.moveDirection(direction))
                .isEqualTo(movedPostion);
    }

    @Test
    void 오른쪽_위로_한칸이동한_Position을_반환한다() {
        Direction direction = Direction.TOP_RIGHT;
        Position currentPosition = Position.of(Column.D, Rank.FOUR);
        Position movedPostion = Position.of(Column.E, Rank.FIVE);

        assertThat(currentPosition.moveDirection(direction))
                .isEqualTo(movedPostion);
    }

    @Test
    void 왼쪽_아래로_한칸이동한_Position을_반환한다() {
        Direction direction = Direction.BOTTOM_LEFT;
        Position currentPosition = Position.of(Column.D, Rank.FOUR);
        Position movedPostion = Position.of(Column.C, Rank.THREE);

        assertThat(currentPosition.moveDirection(direction))
                .isEqualTo(movedPostion);
    }

    @Test
    void 오른쪽_아래로_한칸이동한_Position을_반환한다() {
        Direction direction = Direction.BOTTOM_RIGHT;
        Position currentPosition = Position.of(Column.D, Rank.FOUR);
        Position movedPostion = Position.of(Column.E, Rank.THREE);

        assertThat(currentPosition.moveDirection(direction))
                .isEqualTo(movedPostion);
    }

    @Test
    void 위로_두칸_왼쪽으로_한칸_이동한_Position을_반환한다() {
        Direction direction = Direction.KNIGHT_TOP_LEFT;
        Position currentPosition = Position.of(Column.D, Rank.FOUR);
        Position movedPostion = Position.of(Column.C, Rank.SIX);

        assertThat(currentPosition.moveDirection(direction))
                .isEqualTo(movedPostion);
    }

    @Test
    void 위로_두칸_오른쪽으로_한칸_이동한_Position을_반환한다() {
        Direction direction = Direction.KNIGHT_TOP_RIGHT;
        Position currentPosition = Position.of(Column.D, Rank.FOUR);
        Position movedPostion = Position.of(Column.E, Rank.SIX);

        assertThat(currentPosition.moveDirection(direction))
                .isEqualTo(movedPostion);
    }

    @Test
    void 위로_한칸_왼쪽으로_두칸_이동한_Position을_반환한다() {
        Direction direction = Direction.KNIGHT_LEFT_TOP;
        Position currentPosition = Position.of(Column.D, Rank.FOUR);
        Position movedPostion = Position.of(Column.B, Rank.FIVE);

        assertThat(currentPosition.moveDirection(direction))
                .isEqualTo(movedPostion);
    }

    @Test
    void 아래로_한칸_왼쪽으로_두칸_이동한_Position을_반환한다() {
        Direction direction = Direction.KNIGHT_LEFT_BOTTOM;
        Position currentPosition = Position.of(Column.D, Rank.FOUR);
        Position movedPostion = Position.of(Column.B, Rank.THREE);

        assertThat(currentPosition.moveDirection(direction))
                .isEqualTo(movedPostion);
    }

    @Test
    void 위로_한칸_오른쪽으로_두칸_이동한_Position을_반환한다() {
        Direction direction = Direction.KNIGHT_RIGHT_TOP;
        Position currentPosition = Position.of(Column.D, Rank.FOUR);
        Position movedPostion = Position.of(Column.F, Rank.FIVE);

        assertThat(currentPosition.moveDirection(direction))
                .isEqualTo(movedPostion);
    }

    @Test
    void 아래로_한칸_오른쪽으로_두칸_이동한_Position을_반환한다() {
        Direction direction = Direction.KNIGHT_RIGHT_BOTTOM;
        Position currentPosition = Position.of(Column.D, Rank.FOUR);
        Position movedPostion = Position.of(Column.F, Rank.THREE);

        assertThat(currentPosition.moveDirection(direction))
                .isEqualTo(movedPostion);
    }

    @Test
    void 아래로_두칸_왼쪽으로_한칸_이동한_Position을_반환한다() {
        Direction direction = Direction.KNIGHT_BOTTOM_LEFT;
        Position currentPosition = Position.of(Column.D, Rank.FOUR);
        Position movedPostion = Position.of(Column.C, Rank.TWO);

        assertThat(currentPosition.moveDirection(direction))
                .isEqualTo(movedPostion);
    }

    @Test
    void 아래로_두칸_오른쪽으로_한칸_이동한_Position을_반환한다() {
        Direction direction = Direction.KNIGHT_BOTTOM_RIGHT;
        Position currentPosition = Position.of(Column.D, Rank.FOUR);
        Position movedPostion = Position.of(Column.E, Rank.TWO);

        assertThat(currentPosition.moveDirection(direction))
                .isEqualTo(movedPostion);
    }
}