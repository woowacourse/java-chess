package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    @DisplayName("포지션 좌표를 String 으로 받아 가져온다.")
    void constructor() {
        assertThat(Position.valueOf("a1")).isInstanceOf(Position.class);
    }

    @Test
    @DisplayName("동일한 row에서 크기 비교에 성공한다.")
    void compareToSameRow() {
        Position position = Position.valueOf("a8");
        assertThat(position).isLessThan(Position.valueOf("c8"));
    }

    @Test
    @DisplayName("크기 비교에 성공한다.")
    void compareTo() {
        Position position = Position.valueOf("a8");
        assertThat(position).isLessThan(Position.valueOf("a7"));
    }

    @Test
    @DisplayName("캐싱된 포지션을 가져온다.")
    void valueOf() {
        Position position = Position.valueOf("a1");
        assertThat(position).isEqualTo(Position.valueOf("a1"));
    }

    @Test
    @DisplayName("Column 의 value 차이를 구한다.")
    void subtractColumn() {
        Position source = Position.valueOf("a1");
        Position target = Position.valueOf("b2");
        assertThat(source.subtractColumn(target)).isEqualTo(1);
    }

    @Test
    @DisplayName("Row 의 value 차이를 구한다.")
    void subtractRow() {
        Position source = Position.valueOf("a1");
        Position target = Position.valueOf("b2");
        assertThat(source.subtractRow(target)).isEqualTo(1);
    }

    @Test
    @DisplayName("흰 팀의 경우 pawn의 시작지점을 확인한다.")
    void isStartPositionWhite() {
        assertThat(Position.valueOf("a2").isPawnStartPosition(Team.WHITE)).isTrue();
    }

    @Test
    @DisplayName("검정 팀의 경우 pawn의 시작지점을 확인한다.")
    void isStartPositionBlack() {
        assertThat(Position.valueOf("a7").isPawnStartPosition(Team.BLACK)).isTrue();
    }

    @Test
    @DisplayName("Position을 이동한다.")
    void move() {
        assertThat(Position.valueOf("a7").move(0, -1)).isEqualTo(Position.valueOf("a6"));
    }

    @Test
    @DisplayName("더 작은 포지션을 반환한다.")
    void compareSmaller() {
        assertAll(
                () -> assertThat(Position.valueOf("a8")
                        .compareSmaller(Position.valueOf("a3")))
                        .isEqualTo(Position.valueOf("a8")),
                () -> assertThat(Position.valueOf("a8")
                        .compareSmaller(Position.valueOf("h8")))
                        .isEqualTo(Position.valueOf("a8"))
        );
    }
}
