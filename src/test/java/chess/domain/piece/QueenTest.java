package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.player.TeamType;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class QueenTest {
    private Board board;
    private Map<Coordinate, Piece> cells;
    private final Piece queen = new Queen(TeamType.BLACK);
    private final Coordinate currentCoordinate = Coordinate.from("d5");

    @BeforeEach
    void setup() {
        board = Board.getInstance();
        cells = board.getCells();
        cells.put(currentCoordinate, queen);
    }

    @DisplayName("생성 테스트")
    @Test
    void makeQueen() {
        assertThatCode(() -> new Queen(TeamType.BLACK))
            .doesNotThrowAnyException();
    }

    @DisplayName("Queen의 모든 방향으로 이동할 수 있다")
    @ParameterizedTest
    @ValueSource(strings = {"c6", "e6", "c4", "e4", "d4", "d6", "c5", "e5"})
    void moveDiagonal(String targetCoordinateInput) {
        Coordinate destination = Coordinate.from(targetCoordinateInput);
        boolean isMovable = queen.isMovableTo(board, currentCoordinate, destination);

        assertThat(isMovable).isTrue();
    }

    @DisplayName("Queen의 도착위치까지 이동하는 경로 중간에 기물이 있으면 이동 불가")
    @Test
    void cannotMoveWhenPieceExistsOnRoute() {
        Coordinate destination = Coordinate.from("b3");

        Piece dummy = new Rook(TeamType.BLACK);
        board.put(dummy, Coordinate.from("c4"));

        boolean isMovable = queen.isMovableTo(board, currentCoordinate, destination);

        assertThat(isMovable).isFalse();
    }

    @DisplayName("Queen의 도착위치에 같은 팀 기물이 있으면 이동 불가")
    @Test
    void cannotMoveWhenSameTeamPieceExistsOnDestination() {
        Coordinate destination = Coordinate.from("b3");

        Piece dummy = new Rook(TeamType.BLACK);
        board.put(dummy, destination);
        boolean isMovable = queen.isMovableTo(board, currentCoordinate, destination);

        assertThat(isMovable).isFalse();
    }

    @DisplayName("Queen의 도착위치에 적 팀 기물이 있으면 이동 가능")
    @Test
    void moveToDestinationWhenEnemyExistsOnDestination() {
        Coordinate destination = Coordinate.from("b3");
        Piece dummy = new Rook(TeamType.WHITE);
        board.put(dummy, destination);
        boolean isMovable = queen.isMovableTo(board, currentCoordinate, destination);

        assertThat(isMovable).isTrue();
    }
}