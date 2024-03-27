package chess.domain.piece;

import static chess.domain.fixture.CoordinateFixture.B4;
import static chess.domain.fixture.CoordinateFixture.B5;
import static chess.domain.fixture.CoordinateFixture.C4;
import static chess.domain.fixture.CoordinateFixture.C5;
import static chess.domain.fixture.CoordinateFixture.C6;
import static chess.domain.fixture.CoordinateFixture.D4;
import static chess.domain.fixture.CoordinateFixture.D5;
import static chess.domain.piece.directionmove.Bishop.WHITE_BISHOP;
import static chess.domain.piece.fixedmove.Knight.BLACK_KNIGHT;
import static chess.domain.piece.pawn.NormalBlackPawn.NORMAL_BLACK_PAWN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Coordinate;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalBlackPawnTest {
    @DisplayName("일반 검은색 폰의 이동 가능한 모든 좌표를 계산한다.")
    @Test
    void legalNextCoordinatesBlackPawn() {
        List<Coordinate> result = NORMAL_BLACK_PAWN.legalNextCoordinates(C6, C5);

        List<Coordinate> expected = List.of(C5, D5, B5);
        assertThat(result).containsAll(expected);
    }

    @DisplayName("일반 검은색 폰이 목적지로 갈 수 없는 경우, 예외를 발생한다.")
    @Test
    void noPath() {
        assertThatThrownBy(() -> NORMAL_BLACK_PAWN.legalNextCoordinates(C6, C4))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("일반 검은색 폰이 경로 정보를 토대로 목적지로 갈 수 있는지 판단한다.")
    @Test
    void canMove() {
        HashMap<Coordinate, Piece> boardInformation = new HashMap<>();
        boardInformation.put(B4, DummyPiece.getInstance());
        boardInformation.put(D4, DummyPiece.getInstance());
        boardInformation.put(C4, DummyPiece.getInstance());

        assertThat(NORMAL_BLACK_PAWN.canMove(C5, C4, boardInformation)).isTrue();
    }

    @DisplayName("일반 검은색 폰이 경로 정보를 토대로 목적지로 갈 수 있는지 판단한다(기물은 잡으며 이동하는 경우).")
    @Test
    void canMoveCaseTakeDown() {
        HashMap<Coordinate, Piece> boardInformation = new HashMap<>();
        boardInformation.put(B4, WHITE_BISHOP);
        boardInformation.put(D4, DummyPiece.getInstance());
        boardInformation.put(C4, DummyPiece.getInstance());

        assertThat(NORMAL_BLACK_PAWN.canMove(C5, B4, boardInformation)).isTrue();
    }

    @DisplayName("일반 검은색 폰이 경로 정보를 토대로 목적지로 갈 수 있는지 판단한다(목적지에 같은 팀이 있는 경우).")
    @Test
    void canMoveCaseStuckCuzSameTeamPiece() {
        HashMap<Coordinate, Piece> boardInformation = new HashMap<>();
        boardInformation.put(B4, DummyPiece.getInstance());
        boardInformation.put(D4, DummyPiece.getInstance());
        boardInformation.put(C4, BLACK_KNIGHT);

        assertThat(NORMAL_BLACK_PAWN.canMove(C5, C4, boardInformation)).isFalse();
    }
}
