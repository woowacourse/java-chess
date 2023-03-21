package chess.domain.piece;

import chess.domain.piece.property.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static chess.domain.piece.property.Color.BLACK;
import static chess.domain.piece.property.Color.WHITE;
import static chess.domain.position.File.B;
import static chess.domain.position.File.C;
import static chess.domain.position.Rank.EIGHT;
import static chess.domain.position.Rank.SIX;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightTest {

    @ParameterizedTest
    @CsvSource({"C, SIX", "D, SEVEN"})
    @DisplayName("지나갈 경로를 얻는다.")
    void get_passing_path_test(final File file, final Rank rank) {
        final Piece knight = new Knight(Position.of(B, EIGHT), Color.BLACK);

        final List<Position> path = knight.getPassingPositions(Position.of(file, rank));

        assertThat(path).isEmpty();
    }


    @ParameterizedTest
    @CsvSource({"C, EIGHT", "B, FIVE"})
    @DisplayName("이동할 수 없는 위치가 입력되면, 예외가 발생한다.")
    void invalid_target_position_throw_exception(final File file, final Rank rank) {
        final Piece knight = new Knight(Position.of(C, EIGHT), Color.BLACK);

        assertThatThrownBy(() -> knight.getPassingPositions(Position.of(file, rank)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 이동할 수 없습니다.");
    }

    @ParameterizedTest
    @MethodSource("providePieceInTargetPosition")
    @DisplayName("말을 이동시킨다.")
    void move_test(final Piece pieceInTargetPosition) {
        final Piece originalKnight = new Knight(Position.of(B, EIGHT), BLACK);

        final Piece movedKnight = originalKnight.move(pieceInTargetPosition);

        assertThat(movedKnight.getPosition()).isEqualTo(pieceInTargetPosition.getPosition());
    }

    private static Stream<Arguments> providePieceInTargetPosition() {
        return Stream.of(
                Arguments.of(new BlankPiece(Position.of(C, SIX))),
                Arguments.of(new Pawn(Position.of(C, SIX), WHITE))
        );
    }

    @Test
    @DisplayName("목표 위치에 같은 색 말이 있다면, 예외가 발생한다")
    void catch_same_color_throw_exception() {
        final Piece originalKnight = new Knight(Position.of(B, EIGHT), BLACK);
        final Piece sameColorPiece = new Pawn(Position.of(C, SIX), BLACK);

        assertThatThrownBy(() -> originalKnight.move(sameColorPiece))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 색 말은 잡을 수 없습니다.");
    }
}
