package chess.domain.board;

import static chess.domain.fixture.CoordinateFixture.A1;
import static chess.domain.fixture.CoordinateFixture.A2;
import static chess.domain.fixture.CoordinateFixture.A4;
import static chess.domain.fixture.CoordinateFixture.A8;
import static chess.domain.fixture.CoordinateFixture.B2;
import static chess.domain.fixture.CoordinateFixture.D1;
import static chess.domain.fixture.CoordinateFixture.D2;
import static chess.domain.fixture.CoordinateFixture.D4;
import static chess.domain.fixture.CoordinateFixture.D8;
import static chess.domain.fixture.CoordinateFixture.G4;
import static chess.domain.fixture.CoordinateFixture.H1;
import static chess.domain.fixture.CoordinateFixture.H4;
import static chess.domain.fixture.CoordinateFixture.H7;
import static chess.domain.fixture.CoordinateFixture.H8;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoordinateTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> Coordinate.of(File.A, Rank.ONE))
                .doesNotThrowAnyException();
    }

    @DisplayName("가중치만큼 움직일 수 있다.")
    @Test
    void move() {
        assertAll(
                () -> assertEquals(G4, D2.move(3, 2)),
                () -> assertEquals(A1, A2.move(0, -1)),
                () -> assertEquals(B2, A1.move(1, 1)),
                () -> assertEquals(H8, H1.move(0, 7)),
                () -> assertEquals(H1, H1.move(0, 0))
        );
    }

    @DisplayName("가중치만큼 이동할 수 있는지를 판단한다.")
    @Test
    void canMove() {
        assertAll(
                () -> assertTrue(H7.canMove(0, 1)),
                () -> assertFalse(H7.canMove(1, 0))
        );
    }

    @DisplayName("다른 좌표와 비교할 수 있다.")
    @Test
    void compare() {
        assertAll(
                () -> assertEquals(Map.entry(-1, -1), D4.compare(H8)),
                () -> assertEquals(Map.entry(-1, 0), D4.compare(H4)),
                () -> assertEquals(Map.entry(-1, 1), D4.compare(H1)),
                () -> assertEquals(Map.entry(0, -1), D4.compare(D8)),
                () -> assertEquals(Map.entry(0, 0), D4.compare(D4)),
                () -> assertEquals(Map.entry(0, 1), D4.compare(D1)),
                () -> assertEquals(Map.entry(1, -1), D4.compare(A8)),
                () -> assertEquals(Map.entry(1, 0), D4.compare(A4)),
                () -> assertEquals(Map.entry(1, 1), D4.compare(A1))
        );
    }
}
