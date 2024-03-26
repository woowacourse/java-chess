package chess.domain.piece;

import static chess.domain.fixture.CoordinateFixture.A1;
import static chess.domain.fixture.CoordinateFixture.B1;
import static chess.domain.fixture.CoordinateFixture.C3;
import static chess.domain.fixture.CoordinateFixture.D4;
import static chess.domain.fixture.CoordinateFixture.F2;
import static chess.domain.fixture.CoordinateFixture.F3;
import static chess.domain.piece.directionmove.Queen.WHITE_QUEEN;
import static chess.domain.piece.fixedmove.Knight.BLACK_KNIGHT;
import static chess.domain.piece.fixedmove.Knight.WHITE_KNIGHT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Coordinate;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {


    @DisplayName("나이트는 사방중 한 방향으로 한 칸, 그리고 그 방향의 양 대각선 방향 중 한 방향으로 움직이는 이동 패턴을 가지고 있다.")
    @Test
    void findMovablePath() {
        List<Coordinate> result = WHITE_KNIGHT.legalNextCoordinates(D4, F3);

        List<Coordinate> expected = List.of(F3);
        assertThat(result).containsExactlyElementsOf(expected);
    }

    @DisplayName("나이트가 목적지로 갈 수 없는 경우, 예외를 발생한다.")
    @Test
    void noPath() {
        assertThatThrownBy(() -> WHITE_KNIGHT.legalNextCoordinates(D4, F2))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("나이트가  경로 정보를 토대로 목적지로 갈 수 있는지 판단한다.")
    @Test
    void canMove() {
        HashMap<Coordinate, Piece> boardInformation = new HashMap<>();
        boardInformation.put(C3, DummyPiece.getInstance());

        assertThat(WHITE_KNIGHT.canMove(B1, C3, boardInformation)).isTrue();
    }

    @DisplayName("나이트가  경로 정보를 토대로 목적지로 갈 수 있는지 판단한다(기물은 잡으며 이동하는 경우).")
    @Test
    void canMoveCaseTakeDown() {
        HashMap<Coordinate, Piece> boardInformation = new HashMap<>();
        boardInformation.put(A1, BLACK_KNIGHT);

        assertThat(WHITE_KNIGHT.canMove(B1, A1, boardInformation)).isTrue();
    }

    @DisplayName("나이트가  경로 정보를 토대로 목적지로 갈 수 있는지 판단한다(목적지에 같은 팀이 있는 경우).")
    @Test
    void canMoveCaseStuckCuzSameTeamPiece() {
        HashMap<Coordinate, Piece> boardInformation = new HashMap<>();
        boardInformation.put(C3, WHITE_QUEEN);

        assertThat(WHITE_KNIGHT.canMove(B1, C3, boardInformation)).isFalse();
    }
}
