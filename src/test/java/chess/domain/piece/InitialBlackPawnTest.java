package chess.domain.piece;

import static chess.domain.fixture.CoordinateFixture.B6;
import static chess.domain.fixture.CoordinateFixture.C3;
import static chess.domain.fixture.CoordinateFixture.C4;
import static chess.domain.fixture.CoordinateFixture.C5;
import static chess.domain.fixture.CoordinateFixture.C6;
import static chess.domain.fixture.CoordinateFixture.C7;
import static chess.domain.fixture.CoordinateFixture.D6;
import static chess.domain.fixture.PieceFixture.BLACK_KNIGHT;
import static chess.domain.fixture.PieceFixture.EMPTY_PIECE;
import static chess.domain.fixture.PieceFixture.INITIAL_BLACK_PAWN;
import static chess.domain.fixture.PieceFixture.NORMAL_BLACK_PAWN;
import static chess.domain.fixture.PieceFixture.WHITE_BISHOP;
import static chess.domain.fixture.PieceFixture.WHITE_QUEEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Coordinate;
import chess.domain.piece.pawn.InitialBlackPawn;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitialBlackPawnTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(InitialBlackPawn::new)
                .doesNotThrowAnyException();
    }

    @DisplayName("초기 검은색 폰의 이동 가능한 모든 좌표를 계산한다.")
    @Test
    void legalNextCoordinatesBlackPawn() {
        List<Coordinate> result = INITIAL_BLACK_PAWN.legalNextCoordinates(C7, C5);

        List<Coordinate> expected = List.of(C6, C5, B6, D6);
        assertThat(result).containsAll(expected);
    }

    @DisplayName("초기 검은색 폰이 목적지로 갈 수 없는 경우, 예외를 발생한다.")
    @Test
    void noPath() {
        assertThatThrownBy(() -> NORMAL_BLACK_PAWN.legalNextCoordinates(C7, C4))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("초기 검은색 폰이 경로 정보를 토대로 목적지로 갈 수 있는지 판단한다.")
    @Test
    void canMove() {
        HashMap<Coordinate, Piece> boardInformation = new HashMap<>();
        boardInformation.put(C6, EMPTY_PIECE);
        boardInformation.put(C5, EMPTY_PIECE);

        assertThat(INITIAL_BLACK_PAWN.canMove(C7, C5, boardInformation)).isTrue();
    }

    @DisplayName("초기 검은색 폰이 경로 정보를 토대로 목적지로 갈 수 있는지 판단한다(기물은 잡으며 이동하는 경우).")
    @Test
    void canMoveCaseTakeDown() {
        HashMap<Coordinate, Piece> boardInformation = new HashMap<>();
        boardInformation.put(D6, WHITE_BISHOP);

        assertThat(INITIAL_BLACK_PAWN.canMove(C7, D6, boardInformation)).isTrue();
    }

    @DisplayName("초기 검은색 폰이 경로 정보를 토대로 목적지로 갈 수 있는지 판단한다(목적지에 같은 팀이 있는 경우).")
    @Test
    void canMoveCaseCuzSameTeamPiece() {
        HashMap<Coordinate, Piece> boardInformation = new HashMap<>();
        boardInformation.put(C3, EMPTY_PIECE);
        boardInformation.put(C5, BLACK_KNIGHT);

        assertThat(INITIAL_BLACK_PAWN.canMove(C7, C5, boardInformation)).isFalse();
    }

    @DisplayName("초기 검은색 폰이 경로 정보를 토대로 목적지로 갈 수 있는지 판단한다(직진으로 이동하는 방향에 기물이 하나라도 있을 경우).")
    @Test
    void canMoveCaseStuck() {
        HashMap<Coordinate, Piece> boardInformation = new HashMap<>();
        boardInformation.put(C6, WHITE_QUEEN);
        boardInformation.put(C5, EMPTY_PIECE);

        assertThat(INITIAL_BLACK_PAWN.canMove(C7, C5, boardInformation)).isFalse();
    }

    @DisplayName("초기 검은색 폰이 한번 이동하면, 평범한 검은색 폰이 된다.")
    @Test
    void updateAfterMove() {
        assertThat(INITIAL_BLACK_PAWN.updateAfterMove()).isInstanceOf(NORMAL_BLACK_PAWN.getClass());
    }
}