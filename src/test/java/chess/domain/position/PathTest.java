package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PathTest {

    private static final Position A1 = new Position(File.A, Rank.ONE);
    private static final Position E1 = new Position(File.E, Rank.ONE);
    private static final Position A6 = new Position(File.A, Rank.SIX);
    private static final Position C1 = new Position(File.C, Rank.ONE);
    private static final Position C3 = new Position(File.C, Rank.THREE);
    private static final Position D4 = new Position(File.D, Rank.FOUR);

    @Test
    @DisplayName("직선 경로인지 검증한다.")
    void isStraight_True() {
        //given
        Path path = Path.of(A1, A6);
        Path path2 = Path.of(A1, C1);
        //when
        boolean straight = path.isStraight();
        boolean straight2 = path2.isStraight();
        //then
        assertAll(
                () -> assertThat(straight).isTrue(),
                () -> assertThat(straight2).isTrue()
        );
    }

    @Test
    @DisplayName("직선 경로가 아닌 것을 검증한다.")
    void isStraight_False() {
        //given
        Path path = Path.of(A1, D4);
        Path path2 = Path.of(A6, C1);
        //when
        boolean straight = path.isStraight();
        boolean straight2 = path2.isStraight();
        //then
        assertAll(
                () -> assertThat(straight).isFalse(),
                () -> assertThat(straight2).isFalse()
        );
    }

    @Test
    @DisplayName("대각선 경로를 검증한다")
    void isDiagonal_True() {
        //given
        Path path = Path.of(A1, C3);
        Path path2 = Path.of(E1, C3);
        //when
        boolean diagonal = path.isDiagonal();
        boolean diagonal2 = path.isDiagonal();
        //then
        assertAll(
                () -> assertThat(diagonal).isTrue(),
                () -> assertThat(diagonal2).isTrue()
        );
    }
}
