package chess.domain.piece;

import static chess.domain.fixture.CoordinateFixture.A1;
import static chess.domain.fixture.CoordinateFixture.A7;
import static chess.domain.fixture.CoordinateFixture.B2;
import static chess.domain.fixture.CoordinateFixture.C3;
import static chess.domain.fixture.CoordinateFixture.D4;
import static chess.domain.fixture.CoordinateFixture.E5;
import static chess.domain.fixture.CoordinateFixture.F6;
import static chess.domain.fixture.CoordinateFixture.G7;
import static chess.domain.fixture.CoordinateFixture.H8;
import static chess.domain.piece.directionmove.Bishop.WHITE_BISHOP;
import static chess.domain.piece.fixedmove.Knight.BLACK_KNIGHT;
import static chess.domain.piece.pawn.InitialBlackPawn.INITIAL_BLACK_PAWN;
import static chess.domain.piece.pawn.NormalWhitePawn.NORMAL_WHITE_PAWN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Coordinate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {


    @DisplayName("비숍은 대각으로 제한없이 움직일 수 있다.")
    @Test
    void findMovablePath() {
        List<Coordinate> result = WHITE_BISHOP.legalNextCoordinates(D4, G7);

        List<Coordinate> expected = List.of(E5, F6, G7, H8);
        assertThat(result)
                .containsExactlyElementsOf(expected);
    }

    @DisplayName("비숍이 목적지로 갈 수 없는 경우, 예외가 발생한다.")
    @Test
    void noPath() {
        assertThatThrownBy(() -> WHITE_BISHOP.legalNextCoordinates(A1, A7))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("비숍이 경로 정보를 토대로 목적지로 갈 수 있는지 판단한다.")
    @Test
    void canMove() {
        HashMap<Coordinate, Piece> boardInformation = new LinkedHashMap<>();
        boardInformation.put(G7, DummyPiece.getInstance());

        assertThat(WHITE_BISHOP.canMove(A1, G7, boardInformation)).isTrue();
    }

    @DisplayName("비숍이 경로 정보를 토대로 목적지로 갈 수 있는지 판단한다(기물은 잡으며 이동하는 경우).")
    @Test
    void canMoveCaseTakeDown() {
        HashMap<Coordinate, Piece> boardInformation = new LinkedHashMap<>();
        boardInformation.put(G7, INITIAL_BLACK_PAWN);

        assertThat(WHITE_BISHOP.canMove(A1, G7, boardInformation)).isTrue();
    }

    @DisplayName("비숍이 경로 정보를 토대로 목적지로 갈 수 있는지 판단한다(목적지에 같은 팀이 있는 경우).")
    @Test
    void canMoveCaseStuckCuzSameTeamPiece() {
        HashMap<Coordinate, Piece> boardInformation = new LinkedHashMap<>();
        boardInformation.put(D4, NORMAL_WHITE_PAWN);

        assertThat(WHITE_BISHOP.canMove(A1, D4, boardInformation)).isFalse();
    }

    @DisplayName("비숍이 경로 정보를 토대로 목적지로 갈 수 있는지 판단한다(중간에 기물이 있는 경우).")
    @Test
    void canMoveCaseStuck() {
        HashMap<Coordinate, Piece> boardInformation = new LinkedHashMap<>();
        boardInformation.put(B2, DummyPiece.getInstance());
        boardInformation.put(C3, BLACK_KNIGHT);
        boardInformation.put(D4, DummyPiece.getInstance());

        assertThat(WHITE_BISHOP.canMove(A1, D4, boardInformation)).isFalse();
    }
}
