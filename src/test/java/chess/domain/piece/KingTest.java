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

class KingTest {
    private Board board;
    private Map<Coordinate, Piece> cells;
    private final Piece king = new King(TeamType.BLACK);
    private final Coordinate currentCoordinate = Coordinate.from("d5");

    @BeforeEach
    void setup() {
        board = Board.getInstance();
        cells = board.getCells();
        cells.put(currentCoordinate, king);
    }

    @DisplayName("생성 테스트")
    @Test
    void makeKing() {
        assertThatCode(() -> new King(TeamType.BLACK))
            .doesNotThrowAnyException();
    }

    @DisplayName("King은 모든 방향으로 한 칸 이동할 수 있다")
    @ParameterizedTest
    @ValueSource(strings = {"c6", "e6", "c4", "e4", "d4", "d6", "c5", "e5"})
    void moveDiagonal(String targetCoordinateInput) {
        Coordinate destination = Coordinate.from(targetCoordinateInput);
        boolean isMovable = king.isMovableTo(board, currentCoordinate, destination);
        assertThat(isMovable).isTrue();
    }

    @DisplayName("갈 수 없는 방향 이동")
    @Test
    void cannotMoveInvalidDirection1() {
        Coordinate destination = Coordinate.from("e7");

        boolean isMovable = king.isMovableTo(board, currentCoordinate, destination);
        assertThat(isMovable).isFalse();
    }

    @DisplayName("갈 수 없는 방향 이동")
    @Test
    void cannotMoveInvalidDirection2() {
        Coordinate destination = Coordinate.from("e8");

        boolean isMovable = king.isMovableTo(board, currentCoordinate, destination);
        assertThat(isMovable).isFalse();
    }

    @DisplayName("King은 두 칸 이상 이동할 수 없다.")
    @Test
    void cannotMoveOverTwoCells() {
        Coordinate destination = Coordinate.from("b3");

        boolean isMovable = king.isMovableTo(board, currentCoordinate, destination);
        assertThat(isMovable).isFalse();
    }

    @DisplayName("King의 도착위치에 같은 팀 기물이 있으면 이동 불가")
    @Test
    void cannotMoveWhenSameTeamPieceExistsOnDestination() {
        Coordinate destination = Coordinate.from("c4");

        board.put(new Rook(TeamType.BLACK), destination);

        boolean isMovable = king.isMovableTo(board, currentCoordinate, destination);
        assertThat(isMovable).isFalse();
    }

    @DisplayName("King은 체크 위치에 이동 불가")
    @Test
    void cannotMoveToCheckCoordinate1() {
        board.put(new King(TeamType.WHITE), Coordinate.from("e7"));

        boolean isMovable = king.isMovableTo(board, currentCoordinate, Coordinate.from("e6"));
        assertThat(isMovable).isFalse();
    }

    @DisplayName("King은 체크 위치에 이동 불가")
    @Test
    void cannotMoveToCheckCoordinate2() {
        board.put(new Rook(TeamType.WHITE), Coordinate.from("c5"));

        boolean isMovable = king.isMovableTo(board, currentCoordinate, Coordinate.from("e5"));
        assertThat(isMovable).isFalse();
    }

    @DisplayName("King의 도착위치에 적 팀 기물이 있으면 이동 가능")
    @Test
    void moveToDestinationWhenEnemyExistsOnDestination() {
        Coordinate destination = Coordinate.from("c4");
        Piece dummy = new Rook(TeamType.WHITE);
        board.put(dummy, destination);
        boolean isMovable = king.isMovableTo(board, currentCoordinate, destination);

        assertThat(isMovable).isTrue();
    }
}