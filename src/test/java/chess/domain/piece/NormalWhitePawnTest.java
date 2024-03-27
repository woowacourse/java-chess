package chess.domain.piece;

import static chess.domain.fixture.CoordinateFixture.A3;
import static chess.domain.fixture.CoordinateFixture.A5;
import static chess.domain.fixture.CoordinateFixture.B3;
import static chess.domain.fixture.CoordinateFixture.B4;
import static chess.domain.fixture.CoordinateFixture.C2;
import static chess.domain.fixture.CoordinateFixture.C3;
import static chess.domain.fixture.CoordinateFixture.C4;
import static chess.domain.fixture.CoordinateFixture.D3;
import static chess.domain.fixture.CoordinateFixture.D4;
import static chess.domain.piece.directionmove.Bishop.BLACK_BISHOP;
import static chess.domain.piece.fixedmove.Knight.WHITE_KNIGHT;
import static chess.domain.piece.pawn.NormalWhitePawn.NORMAL_WHITE_PAWN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Coordinate;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalWhitePawnTest {

    @DisplayName("일반 흰색 폰의 이동 가능한 모든 좌표를 계산한다.")
    @Test
    void legalNextCoordinatesWhitePawn() {
        List<Coordinate> result = NORMAL_WHITE_PAWN.legalNextCoordinates(C2, C3);

        List<Coordinate> expected = List.of(C3, B3, D3);
        assertThat(result).containsAll(expected);
    }

    @DisplayName("일반 흰색 폰이 목적지로 갈 수 없는 경우, 예외를 발생한다.")
    @Test
    void noPath() {
        assertThatThrownBy(() -> NORMAL_WHITE_PAWN.legalNextCoordinates(A3, A5))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("일반 흰색 폰이 경로 정보를 토대로 목적지로 갈 수 있는지 판단한다.")
    @Test
    void canMove() {
        HashMap<Coordinate, Piece> boardInformation = new HashMap<>();
        boardInformation.put(B4, DummyPiece.getInstance());
        boardInformation.put(D4, DummyPiece.getInstance());
        boardInformation.put(C4, DummyPiece.getInstance());

        assertThat(NORMAL_WHITE_PAWN.canMove(C3, C4, boardInformation)).isTrue();
    }

    @DisplayName("일반 흰색 폰이 경로 정보를 토대로 목적지로 갈 수 있는지 판단한다(기물은 잡으며 이동하는 경우).")
    @Test
    void canMoveCaseTakeDown() {
        HashMap<Coordinate, Piece> boardInformation = new HashMap<>();
        boardInformation.put(D3, DummyPiece.getInstance());
        boardInformation.put(B3, BLACK_BISHOP);
        boardInformation.put(C3, DummyPiece.getInstance());

        assertThat(NORMAL_WHITE_PAWN.canMove(C2, B3, boardInformation)).isTrue();
    }

    @DisplayName("일반 흰색 폰이 경로 정보를 토대로 목적지로 갈 수 있는지 판단한다(목적지에 같은 팀이 있는 경우).")
    @Test
    void canMoveCaseStuckCuzSameTeamPiece() {
        HashMap<Coordinate, Piece> boardInformation = new HashMap<>();
        boardInformation.put(D3, DummyPiece.getInstance());
        boardInformation.put(B3, DummyPiece.getInstance());
        boardInformation.put(C3, WHITE_KNIGHT);

        assertThat(NORMAL_WHITE_PAWN.canMove(C2, C3, boardInformation)).isFalse();
    }
}
