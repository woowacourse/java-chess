package chess.domain.piece;

import static chess.domain.fixture.CoordinateFixture.A1;
import static chess.domain.fixture.CoordinateFixture.A2;
import static chess.domain.fixture.CoordinateFixture.A3;
import static chess.domain.fixture.CoordinateFixture.A4;
import static chess.domain.fixture.CoordinateFixture.A7;
import static chess.domain.fixture.CoordinateFixture.D4;
import static chess.domain.fixture.CoordinateFixture.E4;
import static chess.domain.fixture.CoordinateFixture.F4;
import static chess.domain.fixture.CoordinateFixture.G4;
import static chess.domain.fixture.CoordinateFixture.H1;
import static chess.domain.fixture.CoordinateFixture.H4;
import static chess.domain.fixture.CoordinateFixture.H8;
import static chess.domain.piece.directionmove.Rook.BLACK_ROOK;
import static chess.domain.piece.directionmove.Rook.WHITE_ROOK;
import static chess.domain.piece.fixedmove.Knight.BLACK_KNIGHT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Coordinate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {
    @DisplayName("룩은 가로, 세로로 제한없이 움직일 수 있다.")
    @Test
    void findMovablePath() {
        List<Coordinate> result = WHITE_ROOK.legalNextCoordinates(D4, H4);

        List<Coordinate> expected = List.of(E4, F4, G4, H4);
        assertThat(result).containsExactlyElementsOf(expected);
    }

    @DisplayName("룩이 목적지로 갈 수 없는 경우, 예외를 발생한다.")
    @Test
    void noPath() {
        assertThatThrownBy(() -> WHITE_ROOK.legalNextCoordinates(A1, H8))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("룩이 경로 정보를 토대로 목적지로 갈 수 있는지 판단한다.")
    @Test
    void canMove() {
        HashMap<Coordinate, Piece> boardInformation = new LinkedHashMap<>();
        boardInformation.put(H1, DummyPiece.getInstance());

        assertThat(WHITE_ROOK.canMove(A1, H1, boardInformation)).isTrue();
    }

    @DisplayName("룩이 경로 정보를 토대로 목적지로 갈 수 있는지 판단한다(기물은 잡으며 이동하는 경우).")
    @Test
    void canMoveCaseTakeDown() {
        HashMap<Coordinate, Piece> boardInformation = new LinkedHashMap<>();
        boardInformation.put(A7, BLACK_ROOK);

        assertThat(WHITE_ROOK.canMove(A1, A7, boardInformation)).isTrue();
    }

    @DisplayName("룩이 경로 정보를 토대로 목적지로 갈 수 있는지 판단한다(목적지에 같은 팀이 있는 경우).")
    @Test
    void canMoveCaseStuckCuzSameTeamPiece() {
        HashMap<Coordinate, Piece> boardInformation = new LinkedHashMap<>();
        boardInformation.put(H1, WHITE_ROOK);

        assertThat(WHITE_ROOK.canMove(A1, H1, boardInformation)).isFalse();
    }

    @DisplayName("룩이 경로 정보를 토대로 목적지로 갈 수 있는지 판단한다(중간에 기물이 있는 경우).")
    @Test
    void canMoveCaseStuck() {
        HashMap<Coordinate, Piece> boardInformation = new LinkedHashMap<>();
        boardInformation.put(A2, DummyPiece.getInstance());
        boardInformation.put(A3, BLACK_KNIGHT);
        boardInformation.put(A4, DummyPiece.getInstance());

        assertThat(WHITE_ROOK.canMove(A1, A4, boardInformation)).isFalse();
    }
}
