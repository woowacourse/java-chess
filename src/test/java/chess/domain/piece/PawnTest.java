package chess.domain.piece;

import chess.constant.ExceptionCode;
import chess.domain.piece.property.Color;
import chess.domain.position.File;
import chess.domain.position.Path;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.PositionFixture.A5;
import static chess.PositionFixture.A6;
import static chess.PositionFixture.B2;
import static chess.PositionFixture.B3;
import static chess.PositionFixture.B4;
import static chess.PositionFixture.B5;
import static chess.PositionFixture.B6;
import static chess.PositionFixture.B7;
import static chess.PositionFixture.C4;
import static chess.PositionFixture.C5;
import static chess.domain.piece.property.Color.BLACK;
import static chess.domain.piece.property.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnTest {

    @Nested
    @DisplayName("Black Pawn 테스트")
    class BlackPawnTest {

        @Test
        @DisplayName("초기 위치에서 두 칸을 전진할 경우, 지나갈 경로를 얻는다.")
        void get_passing_path_form_initial_2_moving_test() {
            final Piece pawn = new Pawn(B7, Color.BLACK);

            final Path path = pawn.getPassingPositions(B5);

            assertThat(path).extracting("positions", InstanceOfAssertFactories.list(Position.class))
                    .containsExactly(B6);
        }

        @Test
        @DisplayName("초기 위치가 아닐 때 한 칸을 전진할 경우, 지나갈 경로를 얻는다.")
        void get_passing_path_from_non_initial_1_moving_test() {
            final Piece pawn = new Pawn(B6, Color.BLACK);

            final Path path = pawn.getPassingPositions(B5);

            assertThat(path).extracting("positions", InstanceOfAssertFactories.list(Position.class))
                    .isEmpty();
        }

        @Test
        @DisplayName("초기 위치가 아닐 때 대각선 한 칸을 이동할 경우, 지나갈 경로를 얻는다.")
        void get_passing_path_non_initial_diagonal_1_moving_test() {
            final Piece pawn = new Pawn(B6, Color.BLACK);

            final Path path = pawn.getPassingPositions(C5);

            assertThat(path).extracting("positions", InstanceOfAssertFactories.list(Position.class))
                    .isEmpty();
        }

        @ParameterizedTest
        @CsvSource({"E, SIX", "C, SEVEN", "B, EIGHT"})
        @DisplayName("초기 위치에서 이동할 수 없는 위치가 입력되면, 예외가 발생한다.")
        void invalid_target_position_from_initial_throw_exception(final File file, final Rank rank) {
            final Piece pawn = new Pawn(B7, Color.BLACK);

            assertThatThrownBy(() -> pawn.getPassingPositions(Position.of(file, rank)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ExceptionCode.INVALID_DESTINATION.name());
        }

        @ParameterizedTest
        @CsvSource({"B, FOUR", "C, SIX", "B, SEVEN"})
        @DisplayName("초기 위치가 아닐 때 이동할 수 없는 위치가 입력되면, 예외가 발생한다.")
        void invalid_target_position_from_non_initial_throw_exception(final File file, final Rank rank) {
            final Piece pawn = new Pawn(B6, Color.BLACK);

            assertThatThrownBy(() -> pawn.getPassingPositions(Position.of(file, rank)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ExceptionCode.INVALID_DESTINATION.name());
        }
    }

    @Nested
    @DisplayName("White Pawn 테스트")
    class WhitePawnTest {

        @Test
        @DisplayName("초기 위치에서 두 칸을 전진할 경우, 지나갈 경로를 얻는다.")
        void get_passing_path_form_initial_2_moving_test() {
            final Piece pawn = new Pawn(B2, Color.WHITE);

            final Path path = pawn.getPassingPositions(B4);

            assertThat(path).extracting("positions", InstanceOfAssertFactories.list(Position.class))
                    .containsExactly(B3);
        }

        @Test
        @DisplayName("초기 위치가 아닐 때 한 칸을 전진할 경우, 지나갈 경로를 얻는다.")
        void get_passing_path_from_non_initial_1_moving_test() {
            final Piece pawn = new Pawn(B3, Color.WHITE);

            final Path path = pawn.getPassingPositions(B4);

            assertThat(path).extracting("positions", InstanceOfAssertFactories.list(Position.class))
                    .isEmpty();
        }

        @Test
        @DisplayName("초기 위치가 아닐 때 대각선 한 칸을 이동할 경우, 지나갈 경로를 얻는다.")
        void get_passing_path_non_initial_diagonal_1_moving_test() {
            final Piece pawn = new Pawn(B3, Color.WHITE);

            final Path path = pawn.getPassingPositions(C4);

            assertThat(path).extracting("positions", InstanceOfAssertFactories.list(Position.class))
                    .isEmpty();
        }

        @ParameterizedTest
        @CsvSource({"E, SIX", "C, TWO", "B, ONE"})
        @DisplayName("초기 위치에서 이동할 수 없는 위치가 입력되면, 예외가 발생한다.")
        void invalid_target_position_from_initial_throw_exception(final File file, final Rank rank) {
            final Piece pawn = new Pawn(B2, Color.WHITE);

            assertThatThrownBy(() -> pawn.getPassingPositions(Position.of(file, rank)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ExceptionCode.INVALID_DESTINATION.name());
        }

        @ParameterizedTest
        @CsvSource({"B, FIVE", "C, THREE", "B, TWO"})
        @DisplayName("초기 위치가 아닐 때 이동할 수 없는 위치가 입력되면, 예외가 발생한다.")
        void invalid_target_position_from_non_initial_throw_exception(final File file, final Rank rank) {
            final Piece pawn = new Pawn(B3, Color.WHITE);

            assertThatThrownBy(() -> pawn.getPassingPositions(Position.of(file, rank)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ExceptionCode.INVALID_DESTINATION.name());
        }
    }

    @ParameterizedTest
    @MethodSource("providePieceInTargetPosition")
    @DisplayName("말을 이동시킨다.")
    void move_test(final Piece pieceInTargetPosition) {
        final Piece originalPawn = new Pawn(A6, BLACK);

        final Piece movedRook = originalPawn.move(pieceInTargetPosition);

        assertThat(movedRook.getPosition()).isEqualTo(pieceInTargetPosition.getPosition());
    }

    private static Stream<Arguments> providePieceInTargetPosition() {
        return Stream.of(
                Arguments.of(BlankPiece.of(A5)),
                Arguments.of(new Pawn(B5, WHITE))
        );
    }

    @ParameterizedTest
    @CsvSource("WHITE, BLACK")
    @DisplayName("말이 있는 전방으로 이동하면, 예외가 발생한다")
    void cross_adjacent_position_with_piece_throw_exception(final Color color) {
        final Piece originalPawn = new Pawn(A6, BLACK);
        final Piece sameColorPiece = new Pawn(A5, color);

        assertThatThrownBy(() -> originalPawn.move(sameColorPiece))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionCode.INVALID_DESTINATION.name());
    }

    @ParameterizedTest
    @MethodSource("provideDiagonalPieceInTargetPosition")
    @DisplayName("대각선 위치에 같은색 말이 있거나 아무 말도 없으면, 예외를 발생한다")
    void invalid_diagonal_adjacent_position_throw_exception(final Piece pieceInTargetPosition) {
        final Piece originalPawn = new Pawn(A6, BLACK);

        assertThatThrownBy(() -> originalPawn.move(pieceInTargetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionCode.INVALID_DESTINATION.name());
    }

    private static Stream<Arguments> provideDiagonalPieceInTargetPosition() {
        return Stream.of(
                Arguments.of(BlankPiece.of(B5)),
                Arguments.of(new Pawn(B5, BLACK))
        );
    }
}
