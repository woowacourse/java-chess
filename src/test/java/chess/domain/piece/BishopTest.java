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
import static chess.domain.position.File.C;
import static chess.domain.position.File.D;
import static chess.domain.position.File.E;
import static chess.domain.position.File.F;
import static chess.domain.position.Rank.EIGHT;
import static chess.domain.position.Rank.FIVE;
import static chess.domain.position.Rank.SEVEN;
import static chess.domain.position.Rank.SIX;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BishopTest {

    @Test
    @DisplayName("지나갈 경로를 얻는다.")
    void get_passing_path_test() {
        final Piece bishop = new Bishop(C, EIGHT, Color.BLACK);

        final List<Position> path = bishop.getPassingPositions(new Position(F, FIVE));

        assertThat(path).containsExactly(new Position(D, SEVEN), new Position(E, SIX));
    }


    @ParameterizedTest
    @CsvSource({"E, FOUR", "C, EIGHT"})
    @DisplayName("이동할 수 없는 위치가 입력되면, 예외가 발생한다.")
    void invalid_target_position_throw_exception(final File file, final Rank rank) {
        final Piece bishop = new Bishop(C, EIGHT, Color.BLACK);

        assertThatThrownBy(() -> bishop.getPassingPositions(new Position(file, rank)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 이동할 수 없습니다.");
    }

    @ParameterizedTest
    @MethodSource("providePieceInTargetPosition")
    @DisplayName("말을 이동시킨다.")
    void move_test(final Piece pieceInTargetPosition) {
        final Piece originalBishop = new Bishop(C, EIGHT, BLACK);

        final Piece movedBishop = originalBishop.move(pieceInTargetPosition);

        assertThat(movedBishop.getPosition()).isEqualTo(pieceInTargetPosition.getPosition());
    }

    private static Stream<Arguments> providePieceInTargetPosition() {
        return Stream.of(
                Arguments.of(new BlankPiece(F, FIVE)),
                Arguments.of(new Pawn(F, FIVE, WHITE))
        );
    }

    @Test
    @DisplayName("목표 위치에 같은 색 말이 있다면, 예외가 발생한다")
    void catch_same_color_throw_exception() {
        final Piece originalBishop = new Bishop(F, EIGHT, BLACK);
        final Piece sameColorPiece = new Pawn(D, SIX, BLACK);

        assertThatThrownBy(() -> originalBishop.move(sameColorPiece))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 색 말은 잡을 수 없습니다.");
    }
}
