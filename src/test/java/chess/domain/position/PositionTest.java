package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.pieceinformations.TeamColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    @DisplayName("생성")
    void create() {
        assertThatCode(() -> Position.valueOf("a1")).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("잘못된 포지션 위치 - 벗어난 위치")
    void failChessBoardPosition() {
        assertThatThrownBy(() -> Position.valueOf("b9"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("잘못된 포지션 위치 - 길이초과")
    void failChessBoardPosition1() {
        assertThatThrownBy(() -> Position.valueOf("b10"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("올바른 포지션 위치")
    void successChessBoardPosition() {
        assertThatCode(() -> Position.valueOf("b1")).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("앞방향 이동 테스트 - 흑팀")
    void move_front_black() {
        Position position = Position.valueOf("c4");

        final Position movedPosition = position.moveFront(TeamColor.BLACK);

        assertThat(movedPosition).isEqualTo(Position.valueOf("c3"));
    }

    @Test
    @DisplayName("앞방향 이동 테스트 - 백팀")
    void move_front_white() {
        Position position = Position.valueOf("c4");

        final Position movedPosition = position.moveFront(TeamColor.WHITE);

        assertThat(movedPosition).isEqualTo(Position.valueOf("c5"));
    }

    @Test
    @DisplayName("이동 테스트")
    void move() {
        Position position = Position.valueOf("c4");

        final Position moved = position.move(1, 3);

        assertThat(moved).isEqualTo(Position.valueOf("d7"));
    }

    @Test
    @DisplayName("폰의 시작라인 테스트 - 백")
    void pawn_line() {
        Position position = Position.valueOf("d2");

        assertTrue(position.pawnLine(TeamColor.WHITE));
    }

    @Test
    @DisplayName("폰의 시작라인 테스트 - 흑")
    void pawn_line1() {
        Position position = Position.valueOf("d7");

        assertTrue(position.pawnLine(TeamColor.BLACK));
    }

    @Test
    @DisplayName("폰의 시작라인 테스트 실패")
    void pawn_line_fail() {
        Position position = Position.valueOf("d3");
        Position positionBlack = Position.valueOf("d2");
        Position positionWhite = Position.valueOf("d7");

        assertFalse(position.pawnLine(TeamColor.WHITE));
        assertFalse(positionBlack.pawnLine(TeamColor.BLACK));
        assertFalse(positionWhite.pawnLine(TeamColor.WHITE));
    }

    @Test
    @DisplayName("세로 좌표 가져오기")
    void getColumn() {
        Position position = Position.valueOf("c4");

        assertThat(position.getColumn()).isEqualTo('c');
    }

}
