package chess.domain.piece;

import chess.domain.Color;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static chess.domain.File.A;
import static chess.domain.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class RookTest {

    @Test
    void 지나갈_경로를_얻는다() {
        final Piece rook = new Rook(A, EIGHT, Color.BLACK);

        final List<Position> path = rook.getPassingPositions(new Position(A, FIVE));

        assertThat(path).containsExactly(new Position(A, SEVEN), new Position(A, SIX));
    }


    @ParameterizedTest
    @CsvSource({"A, EIGHT", "E, FOUR"})
    void 이동할_수_없는_위치가_입력되면_예외가_발생한다(final File file, final Rank rank) {
        final Piece rook = new Rook(A, EIGHT, Color.BLACK);

        assertThatThrownBy(() -> rook.getPassingPositions(new Position(file, rank)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 이동할 수 없습니다.");
    }

    @ParameterizedTest
    @MethodSource("providePieceInTargetPosition")
    void 말을_이동시킨다(final Piece pieceInTargetPosition) {
        final Piece originalRook = new Rook(A, EIGHT, BLACK);

        final Piece movedRook = originalRook.move(pieceInTargetPosition);

        assertThat(movedRook.getPosition()).isEqualTo(pieceInTargetPosition.getPosition());
    }

    private static Stream<Arguments> providePieceInTargetPosition() {
        return Stream.of(
                Arguments.of(new BlankPiece(A, FIVE)),
                Arguments.of(new Pawn(A, FIVE, WHITE))
        );
    }

    @Test
    void 목표_위치에_같은_색_말이_있다면_예외가_발생한다() {
        final Piece originalRook = new Rook(A, EIGHT, BLACK);
        final Piece sameColorPiece = new Pawn(A, FIVE, BLACK);

        assertThatThrownBy(() -> originalRook.move(sameColorPiece))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 색 말은 잡을 수 없습니다.");
    }

    @Test
    void 왕인지_확인한다() {
        final Piece rook = new Rook(A, EIGHT, BLACK);

        final boolean actual = rook.isKing();

        assertThat(actual).isFalse();
    }

    @Test
    void 폰인지_확인한다() {
        final Piece rook = new Rook(A, EIGHT, BLACK);

        final boolean actual = rook.isPawn();

        assertThat(actual).isFalse();
    }
}
