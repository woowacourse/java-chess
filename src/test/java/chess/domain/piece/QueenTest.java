package chess.domain.piece;

import static chess.domain.fixture.CoordinateFixture.A1;
import static chess.domain.fixture.CoordinateFixture.A7;
import static chess.domain.fixture.CoordinateFixture.B6;
import static chess.domain.fixture.CoordinateFixture.C5;
import static chess.domain.fixture.CoordinateFixture.D4;
import static chess.domain.fixture.CoordinateFixture.E6;
import static chess.domain.fixture.CoordinateFixture.H8;
import static chess.domain.fixture.PieceFixture.BLACK_ROOK;
import static chess.domain.fixture.PieceFixture.EMPTY_PIECE;
import static chess.domain.fixture.PieceFixture.NORMAL_WHITE_PAWN;
import static chess.domain.fixture.PieceFixture.WHITE_QUEEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Coordinate;
import chess.domain.piece.directionmove.Queen;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Queen(Team.WHITE))
                .doesNotThrowAnyException();
    }

    @DisplayName("퀸은 대각과 가로, 세로로 제한없이 움직일 수 있다.")
    @Test
    void findMovablePath() {
        List<Coordinate> result = WHITE_QUEEN.legalNextCoordinates(D4, A7);

        List<Coordinate> expected = List.of(C5, B6, A7);
        assertThat(result).containsExactlyElementsOf(expected);
    }

    @DisplayName("퀸이 목적지로 갈 수 없는 경우, 예외가 발생한다.")
    @Test
    void noPath() {
        assertThatThrownBy(() -> WHITE_QUEEN.legalNextCoordinates(D4, E6))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("퀸이 경로 정보를 토대로 목적지로 갈 수 있는지 판단한다.")
    @Test
    void canMove() {
        HashMap<Coordinate, Piece> boardInformation = new HashMap<>();
        boardInformation.put(H8, EMPTY_PIECE);

        assertThat(WHITE_QUEEN.canMove(A1, H8, boardInformation)).isTrue();
    }

    @DisplayName("퀸이 경로 정보를 토대로 목적지로 갈 수 있는지 판단한다(기물은 잡으며 이동하는 경우).")
    @Test
    void canMoveCaseTakeDown() {
        HashMap<Coordinate, Piece> boardInformation = new HashMap<>();
        boardInformation.put(A7, BLACK_ROOK);

        assertThat(WHITE_QUEEN.canMove(A1, A7, boardInformation)).isTrue();
    }

    @DisplayName("퀸이 경로 정보를 토대로 목적지로 갈 수 있는지 판단한다(목적지에 같은 팀이 있는 경우).")
    @Test
    void canMoveCaseStuckCuzSameTeamPiece() {
        HashMap<Coordinate, Piece> boardInformation = new HashMap<>();
        boardInformation.put(D4, NORMAL_WHITE_PAWN);

        assertThat(WHITE_QUEEN.canMove(A1, D4, boardInformation)).isFalse();
    }

}
