package chess.domain.piece;

import chess.domain.piece.property.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static chess.domain.piece.property.Color.BLACK;
import static chess.domain.piece.property.Color.WHITE;
import static chess.domain.position.File.A;
import static chess.domain.position.File.B;
import static chess.domain.position.File.C;
import static chess.domain.position.Rank.FIVE;
import static chess.domain.position.Rank.FOUR;
import static chess.domain.position.Rank.SEVEN;
import static chess.domain.position.Rank.SIX;
import static chess.domain.position.Rank.THREE;
import static chess.domain.position.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnTest {

    @Nested
    @DisplayName("Black Pawn 테스트")
    class BlackPawnTest {

        @Test
        @DisplayName("초기 위치에서 두 칸을 전진할 경우, 지나갈 경로를 얻는다.")
        void get_passing_path_form_initial_2_moving_test() {
            final Piece pawn = new Pawn(Position.of(B, SEVEN), Color.BLACK);

            final List<Position> path = pawn.getPassingPositions(Position.of(B, Rank.FIVE));

            assertThat(path).containsExactly(Position.of(B, SIX));
        }

        @Test
        @DisplayName("초기 위치가 아닐 때 한 칸을 전진할 경우, 지나갈 경로를 얻는다.")
        void get_passing_path_from_non_initial_1_moving_test() {
            final Piece pawn = new Pawn(Position.of(B, SIX), Color.BLACK);

            final List<Position> path = pawn.getPassingPositions(Position.of(B, Rank.FIVE));

            assertThat(path).isEmpty();
        }

        @Test
        @DisplayName("초기 위치가 아닐 때 대각선 한 칸을 이동할 경우, 지나갈 경로를 얻는다.")
        void get_passing_path_non_initial_diagonal_1_moving_test() {
            final Piece pawn = new Pawn(Position.of(B, SIX), Color.BLACK);

            final List<Position> path = pawn.getPassingPositions(Position.of(C, Rank.FIVE));

            assertThat(path).isEmpty();
        }

        @ParameterizedTest
        @CsvSource({"E, SIX", "C, SEVEN", "B, EIGHT"})
        @DisplayName("초기 위치에서 이동할 수 없는 위치가 입력되면, 예외가 발생한다.")
        void invalid_target_position_from_initial_throw_exception(final File file, final Rank rank) {
            final Piece pawn = new Pawn(Position.of(B, SEVEN), Color.BLACK);

            assertThatThrownBy(() -> pawn.getPassingPositions(Position.of(file, rank)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 위치로 이동할 수 없습니다.");
        }

        @ParameterizedTest
        @CsvSource({"B, FOUR", "C, SIX", "B, SEVEN"})
        @DisplayName("초기 위치가 아닐 때 이동할 수 없는 위치가 입력되면, 예외가 발생한다.")
        void invalid_target_position_from_non_initial_throw_exception(final File file, final Rank rank) {
            final Piece pawn = new Pawn(Position.of(B, SIX), Color.BLACK);

            assertThatThrownBy(() -> pawn.getPassingPositions(Position.of(file, rank)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 위치로 이동할 수 없습니다.");
        }
    }

    @Nested
    @DisplayName("White Pawn 테스트")
    class WhitePawnTest {

        @Test
        @DisplayName("초기 위치에서 두 칸을 전진할 경우, 지나갈 경로를 얻는다.")
        void get_passing_path_form_initial_2_moving_test() {
            final Piece pawn = new Pawn(Position.of(B, TWO), Color.WHITE);

            final List<Position> path = pawn.getPassingPositions(Position.of(B, FOUR));

            assertThat(path).containsExactly(Position.of(B, THREE));
        }

        @Test
        @DisplayName("초기 위치가 아닐 때 한 칸을 전진할 경우, 지나갈 경로를 얻는다.")
        void get_passing_path_from_non_initial_1_moving_test() {
            final Piece pawn = new Pawn(Position.of(B, THREE), Color.WHITE);

            final List<Position> path = pawn.getPassingPositions(Position.of(B, FOUR));

            assertThat(path).isEmpty();
        }

        @Test
        @DisplayName("초기 위치가 아닐 때 대각선 한 칸을 이동할 경우, 지나갈 경로를 얻는다.")
        void get_passing_path_non_initial_diagonal_1_moving_test() {
            final Piece pawn = new Pawn(Position.of(B, THREE), Color.WHITE);

            final List<Position> path = pawn.getPassingPositions(Position.of(C, FOUR));

            assertThat(path).isEmpty();
        }

        @ParameterizedTest
        @CsvSource({"E, SIX", "C, TWO", "B, ONE"})
        @DisplayName("초기 위치에서 이동할 수 없는 위치가 입력되면, 예외가 발생한다.")
        void invalid_target_position_from_initial_throw_exception(final File file, final Rank rank) {
            final Piece pawn = new Pawn(Position.of(B, TWO), Color.WHITE);

            assertThatThrownBy(() -> pawn.getPassingPositions(Position.of(file, rank)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 위치로 이동할 수 없습니다.");
        }

        @ParameterizedTest
        @CsvSource({"B, FIVE", "C, THREE", "B, TWO"})
        @DisplayName("초기 위치가 아닐 때 이동할 수 없는 위치가 입력되면, 예외가 발생한다.")
        void invalid_target_position_from_non_initial_throw_exception(final File file, final Rank rank) {
            final Piece pawn = new Pawn(Position.of(B, THREE), Color.WHITE);

            assertThatThrownBy(() -> pawn.getPassingPositions(Position.of(file, rank)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 위치로 이동할 수 없습니다.");
        }
    }

    @ParameterizedTest
    @MethodSource("providePieceInTargetPosition")
    @DisplayName("말을 이동시킨다.")
    void move_test(final Piece pieceInTargetPosition) {
        final Piece originalPawn = new Pawn(Position.of(A, SIX), BLACK);

        final Piece movedRook = originalPawn.move(pieceInTargetPosition);

        assertThat(movedRook.getPosition()).isEqualTo(pieceInTargetPosition.getPosition());
    }

    private static Stream<Arguments> providePieceInTargetPosition() {
        return Stream.of(
                Arguments.of(new BlankPiece(Position.of(A, FIVE))),
                Arguments.of(new Pawn(Position.of(B, FIVE), WHITE))
        );
    }

    @ParameterizedTest
    @CsvSource("WHITE, BLACK")
    @DisplayName("말이 있는 전방으로 이동하면, 예외가 발생한다")
    void cross_adjacent_position_with_piece_throw_exception(final Color color) {
        final Piece originalPawn = new Pawn(Position.of(A, SIX), BLACK);
        final Piece sameColorPiece = new Pawn(Position.of(A, FIVE), color);

        assertThatThrownBy(() -> originalPawn.move(sameColorPiece))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 이동할 수 없습니다.");
    }

    @ParameterizedTest
    @MethodSource("provideDiagonalPieceInTargetPosition")
    @DisplayName("대각선 위치에 같은색 말이 있거나 아무 말도 없으면, 예외를 발생한다")
    void invalid_diagonal_adjacent_position_throw_exception(final Piece pieceInTargetPosition) {
        final Piece originalPawn = new Pawn(Position.of(A, SIX), BLACK);

        assertThatThrownBy(() -> originalPawn.move(pieceInTargetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 이동할 수 없습니다.");
    }

    private static Stream<Arguments> provideDiagonalPieceInTargetPosition() {
        return Stream.of(
                Arguments.of(new BlankPiece(Position.of(B, FIVE))),
                Arguments.of(new Pawn(Position.of(B, FIVE), BLACK))
        );
    }
}
