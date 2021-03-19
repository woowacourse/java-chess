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

class KnightTest {

    private Board board;
    private Map<Coordinate, Piece> cells;
    private final Piece knight = new Knight(TeamType.BLACK);
    private final Coordinate currentCoordinate = Coordinate.from("d4");

    @BeforeEach
    void setup() {
        board = Board.getInstance();
        cells = board.getCells();
        cells.put(currentCoordinate, knight);
    }

    @DisplayName("생성 테스트")
    @Test
    void makeKnight() {
        assertThatCode(() -> new Knight(TeamType.BLACK))
            .doesNotThrowAnyException();
    }

    @DisplayName("Bishop은 현재 위치를 기준으로 지정된 8자리만 이동할 수 있다")
    @ParameterizedTest
    @ValueSource(strings = {"b3", "b5", "c2", "c6", "e2", "e6", "f3", "f5"})
    void moveDiagonal(String targetCoordinateInput) {
        Coordinate destination = Coordinate.from(targetCoordinateInput);

        boolean isMovable = knight.isMovableTo(board, currentCoordinate, destination);

        assertThat(isMovable).isTrue();
    }

    @DisplayName("Bishop의 도착위치에 같은 팀 기물이 있으면 이동 불가")
    @Test
    void cannotMoveWhenSameTeamPieceExistsOnDestination() {
        Coordinate destination = Coordinate.from("b3");
        Piece dummy = new Rook(TeamType.BLACK);
        board.put(dummy, destination);

        boolean isMovable = knight.isMovableTo(board, currentCoordinate, destination);

        assertThat(isMovable).isFalse();
    }

    @DisplayName("Bishop의 도착위치에 적 팀 기물이 있으면 이동 가능")
    @Test
    void moveToDestinationWhenEnemyExistsOnDestination() {
        Coordinate destination = Coordinate.from("b3");
        Piece dummy = new Rook(TeamType.WHITE);
        board.put(dummy, destination);

        boolean isMovable = knight.isMovableTo(board, currentCoordinate, destination);

        assertThat(isMovable).isTrue();
    }
}