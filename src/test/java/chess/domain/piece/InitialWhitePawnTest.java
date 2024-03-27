package chess.domain.piece;

import static chess.domain.fixture.CoordinateFixture.A2;
import static chess.domain.fixture.CoordinateFixture.A5;
import static chess.domain.fixture.CoordinateFixture.B3;
import static chess.domain.fixture.CoordinateFixture.C2;
import static chess.domain.fixture.CoordinateFixture.C3;
import static chess.domain.fixture.CoordinateFixture.C4;
import static chess.domain.fixture.CoordinateFixture.D3;
import static chess.domain.piece.directionmove.Bishop.BLACK_BISHOP;
import static chess.domain.piece.fixedmove.Knight.BLACK_KNIGHT;
import static chess.domain.piece.fixedmove.Knight.WHITE_KNIGHT;
import static chess.domain.piece.pawn.InitialWhitePawn.INITIAL_WHITE_PAWN;
import static chess.domain.piece.pawn.NormalWhitePawn.NORMAL_WHITE_PAWN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Coordinate;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitialWhitePawnTest {

    @DisplayName("초기 흰색 폰의 이동 가능한 모든 좌표를 계산한다.")
    @Test
    void legalNextCoordinatesBlackPawn() {
        List<Coordinate> result = INITIAL_WHITE_PAWN.legalNextCoordinates(C2, C4);

        List<Coordinate> expected = List.of(C3, C4, D3, B3);
        assertThat(result).containsAll(expected);
    }

    @DisplayName("초기 흰색 폰이 목적지로 갈 수 없는 경우, 예외를 발생한다.")
    @Test
    void noPath() {
        assertThatThrownBy(() -> INITIAL_WHITE_PAWN.legalNextCoordinates(A2, A5))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("초기 흰색 폰이 경로 정보를 토대로 목적지로 갈 수 있는지 판단한다.")
    @Test
    void canMove() {
        HashMap<Coordinate, Piece> boardInformation = new HashMap<>();
        boardInformation.put(B3, DummyPiece.getInstance());
        boardInformation.put(D3, DummyPiece.getInstance());
        boardInformation.put(C3, DummyPiece.getInstance());
        boardInformation.put(C4, DummyPiece.getInstance());

        assertThat(INITIAL_WHITE_PAWN.canMove(C2, C4, boardInformation)).isTrue();
    }

    @DisplayName("초기 흰색 폰이 경로 정보를 토대로 목적지로 갈 수 있는지 판단한다(기물은 잡으며 이동하는 경우).")
    @Test
    void canMoveCaseTakeDown() {
        HashMap<Coordinate, Piece> boardInformation = new HashMap<>();
        boardInformation.put(B3, BLACK_BISHOP);
        boardInformation.put(D3, DummyPiece.getInstance());
        boardInformation.put(C3, DummyPiece.getInstance());
        boardInformation.put(C4, DummyPiece.getInstance());

        assertThat(INITIAL_WHITE_PAWN.canMove(C2, B3, boardInformation)).isTrue();
    }

    @DisplayName("초기 흰색 폰이 경로 정보를 토대로 목적지로 갈 수 있는지 판단한다(목적지에 같은 팀이 있는 경우).")
    @Test
    void canMoveCaseStuckCuzSameTeamPiece() {
        HashMap<Coordinate, Piece> boardInformation = new HashMap<>();
        boardInformation.put(B3, DummyPiece.getInstance());
        boardInformation.put(D3, DummyPiece.getInstance());
        boardInformation.put(C3, DummyPiece.getInstance());
        boardInformation.put(C4, WHITE_KNIGHT);

        assertThat(INITIAL_WHITE_PAWN.canMove(C2, C4, boardInformation)).isFalse();
    }

    @DisplayName("초기 흰색 폰이 경로 정보를 토대로 목적지로 갈 수 있는지 판단한다(직진으로 이동하는 방향에 기물이 하나라도 있을 경우).")
    @Test
    void canMoveCaseStuck() {
        HashMap<Coordinate, Piece> boardInformation = new HashMap<>();
        boardInformation.put(B3, DummyPiece.getInstance());
        boardInformation.put(D3, DummyPiece.getInstance());
        boardInformation.put(C3, BLACK_KNIGHT);
        boardInformation.put(C4, DummyPiece.getInstance());

        assertThat(INITIAL_WHITE_PAWN.canMove(C2, C4, boardInformation)).isFalse();
    }

    @DisplayName("초기 흰색 폰이 한번 이동하면, 평범한 흰색 폰이 된다.")
    @Test
    void updateAfterMove() {
        assertThat(INITIAL_WHITE_PAWN.updateAfterMove()).isInstanceOf(NORMAL_WHITE_PAWN.getClass());
    }
}