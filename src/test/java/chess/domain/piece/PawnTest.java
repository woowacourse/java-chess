package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.RelativePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PawnTest {

    @Test
    @DisplayName("흑팀 폰은 처음 남쪽으로 2칸 전진할 수 있다.")
    void blackPawnCanMoveSouth2FirstTest() {
        Pawn pawn = Pawn.from(Team.BLACK);
        RelativePosition relativePosition = new RelativePosition(0, -2);

        assertTrue(pawn.isMobile(relativePosition, new EmptyPiece()));
    }

    @Test
    @DisplayName("흑팀 폰은 처음에 아래로 1칸만 전진할 수도 있다.")
    void blackPawnCanMoveSouth1FirstTest() {
        Pawn pawn = Pawn.from(Team.BLACK);
        RelativePosition relativePosition = new RelativePosition(0, -1);

        assertTrue(pawn.isMobile(relativePosition, new EmptyPiece()));
    }

    @Test
    @DisplayName("백팀 폰은 처음 위로로 2칸 전진할 수 있다.")
    void blackPawnCanMoveNorth2FirstTest() {
        Pawn pawn = Pawn.from(Team.WHITE);
        RelativePosition relativePosition = new RelativePosition(0, 2);

        assertTrue(pawn.isMobile(relativePosition, new EmptyPiece()));
    }

    @Test
    @DisplayName("흑팀 폰은 처음에 아래로 1칸만 전진할 수도 있다.")
    void blackPawnCanMoveNorth1FirstTest() {
        Pawn pawn = Pawn.from(Team.WHITE);
        RelativePosition relativePosition = new RelativePosition(0, 1);

        assertTrue(pawn.isMobile(relativePosition, new EmptyPiece()));
    }

    @Test
    @DisplayName("폰은 이미 움직인 적이 있다면 앞으로 2칸 갈 수 없다.")
    void whitePawnCannotMoveSouth2AgainTest() {
        Pawn pawn = Pawn.from(Team.BLACK);
        RelativePosition relativePosition = new RelativePosition(0, -2);

        pawn.isMobile(relativePosition, new EmptyPiece());
        assertThatThrownBy(() -> pawn.isMobile(relativePosition, new EmptyPiece()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 처음 이동할 때만 앞으로 두 칸 갈 수 있습니다.");
    }

    @Test
    @DisplayName("흑팀 폰은 남동쪽에 백팀이 있다면 남동쪽으로 1칸 이동할 수 있다.")
    void blackPawnCanMoveSouthEastTest() {
        Pawn pawn = Pawn.from(Team.BLACK);
        RelativePosition relativePosition = new RelativePosition(1, -1);

        assertTrue(pawn.isMobile(relativePosition, Pawn.from(Team.WHITE)));
    }

    @Test
    @DisplayName("흑팀 폰은 남서쪽에 백팀이 있다면 남서쪽으로 1칸 이동할 수 있다.")
    void blackPawnCanMoveSouthWestTest() {
        Pawn pawn = Pawn.from(Team.BLACK);
        RelativePosition relativePosition = new RelativePosition(-1, -1);

        assertTrue(pawn.isMobile(relativePosition, Pawn.from(Team.WHITE)));
    }

    @Test
    @DisplayName("백팀 폰은 북동쪽에 흑팀이 있다면 북동쪽으로 1칸 이동할 수 있다.")
    void whitePawnCanMoveNorthEastTest() {
        Pawn pawn = Pawn.from(Team.WHITE);
        RelativePosition relativePosition = new RelativePosition(1, 1);

        assertTrue(pawn.isMobile(relativePosition, Pawn.from(Team.BLACK)));
    }

    @Test
    @DisplayName("백팀 폰은 북서쪽에 흑팀이 있다면 북서쪽으로 1칸 이동할 수 있다.")
    void whitePawnCanMoveNorthWestTest() {
        Pawn pawn = Pawn.from(Team.WHITE);
        RelativePosition relativePosition = new RelativePosition(-1, 1);

        assertTrue(pawn.isMobile(relativePosition, Pawn.from(Team.BLACK)));
    }

    @Test
    @DisplayName("pawn 이 잡을 말이 없는데 대각선으로 이동하고자 할 경우 예외처리한다.")
    void PawnCannotMoveDiagonalWhenNoEnemy() {
        Pawn whitePawn = Pawn.from(Team.WHITE);

        assertThatThrownBy(() -> whitePawn.isMobile(new RelativePosition(1, 1), new EmptyPiece())).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 상대팀을 공격할 때만 대각선으로 이동 가능합니다.");
    }

    @Test
    @DisplayName("이동하려는 위치에 있는 말의 팀 색깔이 같으면 예외처리한다.")
    void sameTeamTest() {
        Pawn whitePawn = Pawn.from(Team.WHITE);

        assertThatThrownBy(() -> whitePawn.isMobile(new RelativePosition(0, 1), Pawn.from(Team.WHITE))).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동하고자 하는 자리에 같은 팀이 존재합니다.");
    }

}
