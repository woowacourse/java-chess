package chess.domain.piece;

import static chess.domain.fixture.CoordinateFixture.A1;
import static chess.domain.fixture.CoordinateFixture.A3;
import static chess.domain.fixture.CoordinateFixture.D1;
import static chess.domain.fixture.CoordinateFixture.E1;
import static chess.domain.fixture.CoordinateFixture.E2;
import static chess.domain.fixture.CoordinateFixture.E4;
import static chess.domain.fixture.CoordinateFixture.E5;
import static chess.domain.piece.directionmove.Queen.WHITE_QUEEN;
import static chess.domain.piece.directionmove.Rook.BLACK_ROOK;
import static chess.domain.piece.fixedmove.King.WHITE_KING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Coordinate;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {
    @DisplayName("킹은 가로, 세로 및 대각선으로도 1칸씩 움직일 수 있다.")
    @Test
    void findMovablePath() {
        List<Coordinate> result = WHITE_KING.legalNextCoordinates(E4, E5);

        List<Coordinate> expected = List.of(E5);
        assertThat(result)
                .containsExactlyElementsOf(expected);
    }

    @DisplayName("킹이 목적지로 갈 수 없는 경우, 예외를 발생한다.")
    @Test
    void noPath() {
        assertThatThrownBy(() -> WHITE_KING.legalNextCoordinates(A1, A3))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("킹이 경로 정보를 토대로 목적지로 갈 수 있는지 판단한다.")
    @Test
    void canMove() {
        HashMap<Coordinate, Piece> boardInformation = new HashMap<>();
        boardInformation.put(E2, DummyPiece.getInstance());

        assertThat(WHITE_KING.canMove(E1, E2, boardInformation)).isTrue();
    }

    @DisplayName("킹이 경로 정보를 토대로 목적지로 갈 수 있는지 판단한다(기물은 잡으며 이동하는 경우).")
    @Test
    void canMoveCaseTakeDown() {
        HashMap<Coordinate, Piece> boardInformation = new HashMap<>();
        boardInformation.put(E2, BLACK_ROOK);

        assertThat(WHITE_KING.canMove(E1, E2, boardInformation)).isTrue();
    }

    @DisplayName("킹이 경로 정보를 토대로 목적지로 갈 수 있는지 판단한다(목적지에 같은 팀이 있는 경우).")
    @Test
    void canMoveCaseStuckCuzSameTeamPiece() {
        HashMap<Coordinate, Piece> boardInformation = new HashMap<>();
        boardInformation.put(D1, WHITE_QUEEN);

        assertThat(WHITE_KING.canMove(E1, D1, boardInformation)).isFalse();
    }

}
