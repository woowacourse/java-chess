package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.player.TeamType;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BishopTest {

    private Board board;
    private Map<Coordinate, Piece> cells;
    private final Piece bishop = new Bishop(TeamType.BLACK);
    private final Coordinate currentCoordinate = Coordinate.from("d5");

    @BeforeEach
    void setup() {
        board = Board.getInstance();
        cells = board.getCells();
        cells.put(currentCoordinate, bishop);
    }

    @DisplayName("생성 테스트")
    @Test
    void makeBishop() {
        assertThatCode(() -> new Bishop(TeamType.BLACK))
            .doesNotThrowAnyException();
    }

    @DisplayName("Bishop은 대각선으로 이동할 수 있다")
    @ParameterizedTest
    @ValueSource(strings = {"c6", "e6", "c4", "e4"})
    void moveDiagonal(String targetCoordinateInput) {
        Coordinate destination = Coordinate.from(targetCoordinateInput);
        bishop.move(board, currentCoordinate, destination);

        Piece pieceOnStartCoordinate = cells.get(currentCoordinate);
        Piece pieceOnDestination = cells.get(destination);

        assertThat(pieceOnStartCoordinate).isNull();
        assertThat(pieceOnDestination).isSameAs(bishop);
    }

    @DisplayName("Bishop은 상하좌우 방향으로 이동할 수 없다")
    @ParameterizedTest
    @ValueSource(strings = {"d4", "d6", "c5", "e5"})
    void cannotMoveDirectionNotDiagonal(String targetCoordinateInput) {
        Coordinate destination = Coordinate.from(targetCoordinateInput);

        assertThatCode(() -> bishop.move(board, currentCoordinate, destination))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이동할 수 없는 방향입니다.");
    }

    @DisplayName("Bishop이 도착위치까지 이동하는 경로 중간에 기물이 있으면 이동 불가")
    @Test
    void cannotMoveWhenPieceExistsOnRoute() {
        Coordinate destination = Coordinate.from("b3");

        Piece dummy = new Rook(TeamType.BLACK);
        board.put(dummy, Coordinate.from("c4"));

        assertThatCode(() -> bishop.move(board, currentCoordinate, destination))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이동할 수 없는 도착 위치 입니다.");
    }

    @DisplayName("Bishop의 도착위치에 같은 팀 기물이 있으면 이동 불가")
    @Test
    void cannotMoveWhenSameTeamPieceExistsOnDestination() {
        Coordinate destination = Coordinate.from("b3");

        Piece dummy = new Rook(TeamType.BLACK);
        board.put(dummy, destination);

        assertThatCode(() -> bishop.move(board, currentCoordinate, destination))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이동할 수 없는 도착 위치 입니다.");
    }

    @DisplayName("Bishop의 도착위치에 적 팀 기물이 있으면 이동 가능")
    @Test
    void moveToDestinationWhenEnemyExistsOnDestination() {
        Coordinate destination = Coordinate.from("b3");
        Piece dummy = new Rook(TeamType.WHITE);
        board.put(dummy, destination);
        bishop.move(board, currentCoordinate, destination);

        Piece pieceOnStartCoordinate = cells.get(currentCoordinate);
        Piece pieceOnDestination = cells.get(destination);

        assertThat(pieceOnStartCoordinate).isNull();
        assertThat(pieceOnDestination).isSameAs(bishop);
    }
}