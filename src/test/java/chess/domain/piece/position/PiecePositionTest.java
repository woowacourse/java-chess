package chess.domain.piece.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("PiecePosition 은")
class PiecePositionTest {

    @Test
    void Rank_와_File_을_받아_생성된다() {
        // when & then
        assertDoesNotThrow(() -> PiecePosition.of('a', 1));
    }

    @ParameterizedTest
    @CsvSource({
            "1,a",
            "3,h",
            "3,g",
            "4,c"
    })
    void 값이_같으면_동등하다(final int rank, final char file) {
        // given
        final PiecePosition position1 = PiecePosition.of(file, rank);
        final PiecePosition position2 = PiecePosition.of(file, rank);

        // when & then
        assertThat(position1).isEqualTo(position2);
    }

    /**
     * File 에서도 동일한 테스트가 존재하는데 이곳에도 있어야 할까요?
     * 둘 중 하나가 없어도 된다면, 둘 중 어느 부분의 테스트를 지워야 할까요?
     * <p>
     * TDD 로 구현하다 보면 FileTest 에 존재하게 되겠지만, 상위 계층의 동작이 하위 계층의 구현에 관계없이
     * 올바르게 작동함을 확인하기 위해서는  PiecePositionTest 에 있어야 한다고 생각하는데, 리뷰어님의 생각이 궁금합니다.
     */
    @ParameterizedTest(name = "File 사이의 간격을 구할 수 있다. 현재[{0}]인 경우 목적지인 [{1}] 과의 차이는 [{2}] 이다.")
    @CsvSource({
            "b,a,-1",
            "c,a,-2",
            "a,c,2",
            "a,b,1",
            "a,a,0",
            "c,c,0",
    })
    void File_사이의_간격을_구할_수_있다(final char currentFile, final char destination, final int distance) {
        // given
        final PiecePosition from = PiecePosition.of(currentFile, 1);
        final PiecePosition dest = PiecePosition.of(destination, 2);

        // when & then
        assertThat(from.fileDistance(dest)).isEqualTo(distance);
    }

    @Test
    void 방위를_받아_해당_방향으로_이동한다() {
        // given
        final PiecePosition a = PiecePosition.of('a', 1);

        // when
        final PiecePosition move = a.move(Direction.NORTHEAST);

        // then
        assertThat(move).isEqualTo(PiecePosition.of('b', 2));
    }

    @Test
    void 이동_시_범위를_벗어나면_오류가_발생한다() {
        // given
        final PiecePosition position = PiecePosition.of('a', 1);

        // when & then
        assertThatThrownBy(() -> position.move(Direction.SOUTHWEST))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 목적지가_주어졌을때_방위를_확인할_수_있다() {
        // given
        final PiecePosition position = PiecePosition.of('e', 4);
        final PiecePosition destination = PiecePosition.of('a', 7);

        // when & then
        assertThat(position.direction(destination)).isEqualTo(Direction.NORTHWEST);
    }

    @Test
    void 어느_File에_위치해있는지_알_수_있다() {
        // given
        final PiecePosition position = PiecePosition.of('a', 7);
        final File file = new File('a');

        // when & then
        assertThat(position.file()).isEqualTo(file);
    }
}
