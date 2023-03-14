package techcourse.fp.chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static techcourse.fp.chess.domain.PositionFixtures.A1;
import static techcourse.fp.chess.domain.PositionFixtures.A2;
import static techcourse.fp.chess.domain.PositionFixtures.A3;
import static techcourse.fp.chess.domain.PositionFixtures.A4;
import static techcourse.fp.chess.domain.PositionFixtures.A5;
import static techcourse.fp.chess.domain.PositionFixtures.A6;
import static techcourse.fp.chess.domain.PositionFixtures.A7;
import static techcourse.fp.chess.domain.PositionFixtures.A8;
import static techcourse.fp.chess.domain.PositionFixtures.B1;
import static techcourse.fp.chess.domain.PositionFixtures.B4;
import static techcourse.fp.chess.domain.PositionFixtures.C1;
import static techcourse.fp.chess.domain.PositionFixtures.C4;
import static techcourse.fp.chess.domain.PositionFixtures.D1;
import static techcourse.fp.chess.domain.PositionFixtures.D2;
import static techcourse.fp.chess.domain.PositionFixtures.D3;
import static techcourse.fp.chess.domain.PositionFixtures.D4;
import static techcourse.fp.chess.domain.PositionFixtures.D5;
import static techcourse.fp.chess.domain.PositionFixtures.D6;
import static techcourse.fp.chess.domain.PositionFixtures.D7;
import static techcourse.fp.chess.domain.PositionFixtures.D8;
import static techcourse.fp.chess.domain.PositionFixtures.E1;
import static techcourse.fp.chess.domain.PositionFixtures.E4;
import static techcourse.fp.chess.domain.PositionFixtures.F1;
import static techcourse.fp.chess.domain.PositionFixtures.F4;
import static techcourse.fp.chess.domain.PositionFixtures.G1;
import static techcourse.fp.chess.domain.PositionFixtures.G4;
import static techcourse.fp.chess.domain.PositionFixtures.H1;
import static techcourse.fp.chess.domain.PositionFixtures.H4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {


    @DisplayName("ROOK의 이동 가능한 좌표들을 가져온다. - A1에 위치한 경우")
    @Test
    void getMovablePositionsTest1() {
        Rook rook = new Rook(Side.BLACK);
        assertThat(rook.findMovablePositions(A1)).containsOnly(
                A2, A3, A4, A5, A6, A7, A8,
                B1, C1, D1, E1, F1, G1, H1
        );
    }

    @DisplayName("ROOK의 이동 가능한 좌표들을 가져온다. - D4에 위치한 경우")
    @Test
    void getMovablePositionsTest2() {
        Rook rook = new Rook(Side.BLACK);
        assertThat(rook.findMovablePositions(D4)).containsOnly(
                D1, D2, D3, D5, D6, D7, D8,
                A4, B4, C4, E4,F4, G4, H4
        );
    }
}
