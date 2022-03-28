package chess.domain.piece;

import static chess.domain.piece.vo.TeamColor.BLACK;
import static chess.domain.piece.vo.TeamColor.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Position;
import chess.domain.piece.vo.TeamColor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class PawnTest {

    @ParameterizedTest
    @DisplayName("첫번째 움직임에선 1칸 또는 2칸 전진할 수 있다.")
    @CsvSource({"b7, BLACK, b6", "b7, BLACK, b5", "b2, WHITE, b3", "b2, WHITE, b3"})
    void moveFirst(final String initialPosition, final TeamColor teamColor, final String targetPosition) {
        //given
        final Piece pawn = new Pawn(teamColor, Position.from(initialPosition));
        final Piece moved = pawn.move(new ArrayList<>(), Position.from(targetPosition));
        //when
        boolean actual = moved.hasPosition(Position.from(targetPosition));
        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @DisplayName("두번째 움직임부터는 1칸 움직일 수 있다.")
    @CsvSource({"BLACK, b7, b6, b5", "WHITE, b2, b3, b4"})
    void moveFirst(final TeamColor teamColor, final String initialPosition,
                   final String firstTargetPosition, final String secondTargetPosition) {
        //given
        final Piece pawn = new Pawn(teamColor, Position.from(initialPosition));
        final Piece firstMoved = pawn.move(new ArrayList<>(), Position.from(firstTargetPosition));
        final Piece secondMoved = firstMoved.move(new ArrayList<>(), Position.from(secondTargetPosition));
        //when
        boolean actual = secondMoved.hasPosition(Position.from(secondTargetPosition));
        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @DisplayName("첫번째 움직임이 아닌데, 두 칸 이동하려 하면 예외를 발생시킨다.")
    @CsvSource({"BLACK, b7, b6, b4", "WHITE, b2, b3, b5"})
    void moveExceptionNotFirst(final TeamColor teamColor, final String initialPosition,
                               final String firstTargetPosition, final String secondTargetPosition) {
        //given
        final Piece pawn = new Pawn(teamColor, Position.from(initialPosition));
        final Piece firstMoved = pawn.move(new ArrayList<>(), Position.from(firstTargetPosition));
        //when, then
        assertThatThrownBy(() -> firstMoved.move(new ArrayList<>(), Position.from(secondTargetPosition)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 위치입니다.");
    }

    @ParameterizedTest
    @DisplayName("적 기물이 대각선 앞에 있으면 적 기물을 제거하면서 이동할 수 있다.")
    @CsvSource({"BLACK, b7, WHITE, c6", "WHITE, b2, BLACK, c3"})
    void moveDiagonal(final TeamColor teamColor, final String initialPosition,
                      final TeamColor enemyTeamColor, final String targetPosition) {
        //given
        final Piece pawn = new Pawn(teamColor, Position.from(initialPosition));
        final Piece enemy = new Rook(enemyTeamColor, Position.from(targetPosition));
        final Piece moved = pawn.move(Collections.singletonList(enemy), Position.from(targetPosition));
        //when
        boolean actual = moved.hasPosition(Position.from(targetPosition));
        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @DisplayName("적 기물이 대각선 앞에 없는데 대각선으로 이동하려고 하면 예외를 발생시킨다.")
    @CsvSource({"BLACK, b7, c6", "WHITE, b2, c3"})
    void moveDiagonalException(final TeamColor teamColor, final String initialPosition, final String targetPosition) {
        //given
        final Piece pawn = new Pawn(teamColor, Position.from(initialPosition));
        //when, then
        assertThatThrownBy(() -> pawn.move(new ArrayList<>(), Position.from(targetPosition)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 위치입니다.");
    }

    @Test
    @DisplayName("적 기물이 있는 곳으로 전진하려하면 예외를 발생시킨다.")
    void moveExceptionByEnemyInTargetPosition() {
        //given
        final Position targetPosition = Position.from("b4");
        final Piece pawn = new Pawn(WHITE, Position.from("b2"));
        final Piece enemy = new Pawn(BLACK, targetPosition);
        //when, then
        assertThatThrownBy(() -> pawn.move(Collections.singletonList(enemy), targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 위치입니다.");
    }

    @ParameterizedTest
    @DisplayName("프로모션 할 수 있는지 반환한다.")
    @CsvSource({"WHITE, a8, true", "WHITE, a7, false", "BLACK, a1, true", "BLACK, a2, false"})
    void canPromote(final TeamColor teamColor, final String position, final boolean expected) {
        //given
        final Pawn pawn = new Pawn(teamColor, Position.from(position));
        //when
        boolean actual = pawn.canPromote();
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("프로모션할 수 있다.")
    @MethodSource("providePromotionTypeAndExpected")
    void promote(final String promotionType, final Class<? extends Piece> expected) {
        //given
        final Pawn pawn = new Pawn(WHITE, Position.from("a8"));
        //actual
        final Piece promotedPiece = pawn.promote(promotionType);
        //then
        assertThat(promotedPiece).isInstanceOf(expected);
    }

    private static Stream<Arguments> providePromotionTypeAndExpected() {
        return Stream.of(
                Arguments.of("q", Queen.class),
                Arguments.of("b", Bishop.class),
                Arguments.of("n", Knight.class),
                Arguments.of("r", Rook.class)
        );
    }
}
