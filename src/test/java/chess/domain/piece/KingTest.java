package chess.domain.piece;

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
import static chess.domain.File.E;
import static chess.domain.Rank.EIGHT;
import static chess.domain.Rank.SEVEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class KingTest {

    @Test
    void 지나갈_경로를_얻는다() {
        //given
        final Piece king = new King(E, EIGHT, BLACK);

        //when
        final List<Position> path = king.getPassingPositions(new Position(E, SEVEN));

        //then
        assertThat(path).isEmpty();
    }

    @ParameterizedTest
    @CsvSource({"E, SIX", "E, EIGHT"})
    void 이동할_수_없는_위치가_입력되면_예외가_발생한다(final File file, final Rank rank) {
        //given
        final Piece king = new King(E, EIGHT, BLACK);

        //when
        //then
        assertThatThrownBy(() -> king.getPassingPositions(new Position(file, rank)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 이동할 수 없습니다.");
    }

    @ParameterizedTest
    @MethodSource("providePieceInTargetPosition")
    void 말을_이동시킨다(final Piece pieceInTargetPosition) {
        //given
        final Piece originalKing = new King(E, EIGHT, BLACK);

        //when
        final Piece movedKing = originalKing.move(pieceInTargetPosition);

        //then
        assertThat(movedKing.getPosition()).isEqualTo(pieceInTargetPosition.getPosition());
    }

    private static Stream<Arguments> providePieceInTargetPosition() {
        return Stream.of(
                Arguments.of(new BlankPiece(E, SEVEN)),
                Arguments.of(new Pawn(E, SEVEN, WHITE))
        );
    }

    @Test
    void 목표_위치에_같은_색_말이_있다면_예외가_발생한다() {
        //given
        final Piece originalKing = new King(E, EIGHT, BLACK);
        final Piece sameColorPiece = new Pawn(E, SEVEN, BLACK);

        //when
        //then
        assertThatThrownBy(() -> originalKing.move(sameColorPiece))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 색 말은 잡을 수 없습니다.");
    }

    @Test
    void 왕인지_확인한다() {
        //given
        final Piece king = new King(E, EIGHT, BLACK);

        //when
        final boolean actual = king.isKing();

        //then
        assertThat(actual).isTrue();
    }

    @Test
    void 폰인지_확인한다() {
        //given
        final Piece king = new King(E, EIGHT, BLACK);

        //when
        final boolean actual = king.isPawn();

        //then
        assertThat(actual).isFalse();
    }
}
