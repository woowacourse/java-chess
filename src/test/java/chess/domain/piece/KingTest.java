package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static chess.domain.File.E;
import static chess.domain.Rank.EIGHT;
import static chess.domain.Rank.SEVEN;
import static chess.domain.Rank.SIX;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KingTest {

    @Test
    @DisplayName("지나갈 경로를 얻는다.")
    void getPassingPathTest() {
        final Piece king = new King(E, Rank.EIGHT, Color.BLACK);

        final List<Position> path = king.getPassingPositions(new Position(E, Rank.SEVEN));

        assertThat(path).isEmpty();
    }

    @Test
    @DisplayName("이동할 수 없는 위치가 입력되면, 예외가 발생한다.")
    void getPassingPathFailTest() {
        final Piece king = new King(E, Rank.EIGHT, Color.BLACK);

        assertThatThrownBy(() -> king.getPassingPositions(new Position(E, SIX)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 이동할 수 없습니다.");
    }

    @ParameterizedTest
    @MethodSource("providePieceInTargetPosition")
    @DisplayName("말을 이동시킨다.")
    void moveTest(final Piece pieceInTargetPosition) {
        final Piece originalKing = new King(E, EIGHT, BLACK);

        final Piece movedKing = originalKing.move(pieceInTargetPosition);

        assertThat(movedKing.getPosition()).isEqualTo(pieceInTargetPosition.getPosition());
    }

    private static Stream<Arguments> providePieceInTargetPosition() {
        return Stream.of(
                Arguments.of(new BlankPiece(E, SEVEN)),
                Arguments.of(new Pawn(E, SEVEN, WHITE))
        );
    }

    @Test
    @DisplayName("목표 위치에 같은 색 말이 있다면, 예외가 발생한다")
    void throws_exception_if_there_is_same_color_piece_in_target_position() {
        final Piece originalKing = new King(E, EIGHT, BLACK);
        final Piece sameColorPiece = new Pawn(E, SEVEN, BLACK);

        assertThatThrownBy(() -> originalKing.move(sameColorPiece))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 색 말은 잡을 수 없습니다.");
    }
}
