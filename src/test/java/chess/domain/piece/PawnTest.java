package chess.domain.piece;

import static chess.domain.piece.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.position.Position;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class PawnTest {

    @ParameterizedTest
    @DisplayName("이동 경로에 이동할 수 있는지 반환한다.")
    @MethodSource("provideTargetPositionAndOtherPositions")
    void canMove(final Position targetPosition, final List<Position> otherPositions, final boolean expected) {
        //given
        final Position sourcePosition = Position.from("a2");
        final Piece piece = new Pawn(WHITE);
        //when
        final boolean actual = piece.canMove(sourcePosition, targetPosition, otherPositions);
        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideTargetPositionAndOtherPositions() {
        return Stream.of(
                Arguments.of(Position.from("a3"), Collections.singletonList(Position.from("a3")), false),
                Arguments.of(Position.from("b3"), Collections.singletonList(Position.from("b3")), true),
                Arguments.of(Position.from("a1"), Collections.emptyList(), false)
        );
    }

    @ParameterizedTest
    @DisplayName("첫번째 움직임에선 1칸 또는 2칸 전진할 수 있다.")
    @ValueSource(strings = {"a3", "a4"})
    void moveFirst(final String target) {
        //given
        final Piece pawn = new Pawn(WHITE);
        final Position sourcePosition = Position.from("a2");
        final Position targetPosition = Position.from(target);
        //when
        boolean actual = pawn.canMove(sourcePosition, targetPosition, Collections.emptyList());
        //then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("두번째 움직임부터는 2칸 움직일 수 없다.")
    void moveNotFirst() {
        //given
        final Piece pawn = new Pawn(WHITE);
        final Position firstPosition = Position.from("a2");
        final Position secondPosition = Position.from("a3");
        final Position thirdPosition = Position.from("a5");
        pawn.canMove(firstPosition, secondPosition, Collections.emptyList());
        //when
        boolean actual = pawn.canMove(secondPosition, thirdPosition, Collections.emptyList());
        //then
        assertThat(actual).isFalse();
    }

    ////todo 프로모션 형식 pawn.canPromote(sourcePosition) {
    //
    //@ParameterizedTest
    //@DisplayName("프로모션 할 수 있는지 반환한다.")
    //@CsvSource({"WHITE, a8, true", "WHITE, a7, false", "BLACK, a1, true", "BLACK, a2, false"})
    //void canPromote(final TeamColor teamColor, final String position, final boolean expected) {
    //    //given
    //    final Pawn pawn = new Pawn(teamColor, Position.from(position));
    //    //when
    //    boolean actual = pawn.canPromote();
    //    //then
    //    assertThat(actual).isEqualTo(expected);
    //}
    //
    //@ParameterizedTest
    //@DisplayName("프로모션할 수 있다.")
    //@MethodSource("providePromotionTypeAndExpected")
    //void promote(final String promotionType, final Class<? extends Piece> expected) {
    //    //given
    //    final Pawn pawn = new Pawn(WHITE, Position.from("a8"));
    //    //actual
    //    final Piece promotedPiece = pawn.promote(promotionType);
    //    //then
    //    assertThat(promotedPiece).isInstanceOf(expected);
    //}
    //
    //private static Stream<Arguments> providePromotionTypeAndExpected() {
    //    return Stream.of(
    //            Arguments.of("q", Queen.class),
    //            Arguments.of("b", Bishop.class),
    //            Arguments.of("n", Knight.class),
    //            Arguments.of("r", Rook.class)
    //    );
    //}
}
