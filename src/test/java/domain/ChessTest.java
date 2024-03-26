package domain;

import static domain.piece.PositionFixture.A1;
import static domain.piece.PositionFixture.A2;
import static domain.piece.PositionFixture.A3;
import static domain.piece.PositionFixture.A4;
import static domain.piece.PositionFixture.A5;
import static domain.piece.PositionFixture.A6;
import static domain.piece.PositionFixture.A7;
import static domain.piece.PositionFixture.B2;
import static domain.piece.PositionFixture.B4;
import static domain.piece.PositionFixture.D4;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessTest {

    private Chess chess;

    @BeforeEach
    void setUp() {
        chess = new Chess();
    }

    @Test
    @DisplayName("출발지와 도착지가 같은 경우 에러를 반환한다.")
    void tryMove_SamePosition() {
        assertThatThrownBy(() -> chess.tryMove(A1, A1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 제자리에 있을 수 없습니다.");
    }

    @Test
    @DisplayName("이동 경로에 다른 기물이 있는 경우 에러를 반환한다.")
    void tryMove_Blocked() {
        assertThatThrownBy(() -> chess.tryMove(A1, A3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 다른 기물에 막혀 이동하지 못했습니다.");
    }

    @Test
    @DisplayName("출발지에 기물이 없는 경우 에러를 반환한다.")
    void tryMove_EmptySource() {
        assertThatThrownBy(() -> chess.tryMove(A4, A3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 출발지에 기물이 없어 이동하지 못했습니다.");
    }

    @Test
    @DisplayName("차례가 아닌 경우 에러를 반환한다.")
    void tryMove_NotTurn() {
        assertThatThrownBy(() -> chess.tryMove(A7, A6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 차례가 아니므로 이동하지 못했습니다.");
    }

    @Test
    @DisplayName("도착지에 같은 편 기물이 있는 경우 에러를 반환한다.")
    void tryMove_SameTeamTarget() {
        assertThatThrownBy(() -> chess.tryMove(A1, A2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 도착지에 같은 편 기물이 있어 이동하지 못했습니다.");
    }

    /*
        ........
        ........
        ........
        ........
        .p.*....
        ........
        ........
        r.......
     */
    @Test
    @DisplayName("기물의 이동 전술에 해당하지 않는 경우 에러를 반환한다.")
    void tryMove_DifferentTactic() {
        chess.tryMove(B2, B4);
        chess.tryMove(A7, A6);

        assertThatThrownBy(() -> chess.tryMove(A1, D4))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 기물이 움직일 수 없는 방식입니다.");
    }

    /*
        ........
        ........
        ........
        P.......
        p.......
        ........
        ........
        ........
     */
    @Test
    @DisplayName("공격이 아닌 이동 시 도착지에 기물이 있는 경우 에러를 반환한다.")
    void tryMove_FilledTarget() {
        chess.tryMove(A2, A4);
        chess.tryMove(A7, A5);

        assertThatThrownBy(() -> chess.tryMove(A4, A5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 도착지에 기물이 있어 움직일 수 없습니다.");
    }
}
