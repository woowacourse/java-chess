package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    @DisplayName("캐싱된 포지션을 가져온다.")
    void valueOf() {
        Position position = Position.of('a', 1);

        assertThat(position).isEqualTo(Position.of('a', 1));
    }

    @Test
    @DisplayName("Column 의 value 차이를 구한다.")
    void subtractColumn() {
        Position source = Position.of('a', 1);
        Position target = Position.of('b', 2);

        assertThat(source.subtractColumn(target)).isEqualTo(1);
    }

    @Test
    @DisplayName("Row 의 value 차이를 구한다.")
    void subtractRow() {
        Position source = Position.of('a', 1);
        Position target = Position.of('b', 2);

        assertThat(source.subtractRow(target)).isEqualTo(1);
    }

    @Test
    @DisplayName("Position 을 이동시킨다.")
    void move() {
        assertThat(Position.of('a', 7).move(0, -1)).isEqualTo(Position.of('a', 6));
    }

    @Test
    @DisplayName("흰 팀의 경우 pawn 의 시작지점을 확인한다.")
    void isStartPositionWhite() {
        assertThat(Position.of('a', 2).isPawnStartPosition(Team.WHITE)).isTrue();
    }

    @Test
    @DisplayName("검정 팀의 경우 pawn 의 시작지점을 확인한다.")
    void isStartPositionBlack() {
        assertThat(Position.of('a', 7).isPawnStartPosition(Team.BLACK)).isTrue();
    }

    @Test
    @DisplayName("더 작은 포지션을 반환한다.")
    void compareSmaller() {
        assertAll(
                () -> assertThat(Position.of('a', 8)
                        .compareSmaller(Position.of('a', 3)))
                        .isEqualTo(Position.of('a', 8)),
                () -> assertThat(Position.of('a', 8)
                        .compareSmaller(Position.of('h', 8)))
                        .isEqualTo(Position.of('a', 8))
        );
    }

    @Test
    @DisplayName("크기 비교에 성공한다.")
    void compareTo() {
        Position position = Position.of('a', 8);

        assertThat(position).isLessThan(Position.of('a', 7));
    }

    @Test
    @DisplayName("동일한 row 에서 크기 비교에 성공한다.")
    void compareTo_SameRow() {
        Position position = Position.of('a', 8);

        assertThat(position).isLessThan(Position.of('c', 8));
    }
}
