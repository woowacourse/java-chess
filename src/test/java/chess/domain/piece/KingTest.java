package chess.domain.piece;

import chess.domain.board.Cell;
import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardGenerator;
import chess.domain.board.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class KingTest {
    private final Piece king = new King(TeamType.BLACK);
    private final Coordinate currentCoordinate = Coordinate.from("d5");
    private ChessBoard chessBoard;
    private Map<Coordinate, Cell> cells;

    @BeforeEach
    void setup() {
        chessBoard = new ChessBoard(ChessBoardGenerator.generateEmptyBoard());
        cells = chessBoard.getCells();
        cells.get(currentCoordinate).put(king);
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
        boolean isMovable = king.isMovable(chessBoard, currentCoordinate, destination);
        assertThat(isMovable).isTrue();
    }

    @DisplayName("갈 수 없는 방향 이동")
    @Test
    void cannotMoveInvalidDirection1() {
        Coordinate destination = Coordinate.from("e7");

        boolean isMovable = king.isMovable(chessBoard, currentCoordinate, destination);
        assertThat(isMovable).isFalse();
    }

    @DisplayName("갈 수 없는 방향 이동")
    @Test
    void cannotMoveInvalidDirection2() {
        Coordinate destination = Coordinate.from("e8");

        assertThatThrownBy(() -> king.isMovable(chessBoard, currentCoordinate, destination))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("King은 두 칸 이상 이동할 수 없다.")
    @Test
    void cannotMoveOverTwoCells() {
        Coordinate destination = Coordinate.from("b3");

        boolean isMovable = king.isMovable(chessBoard, currentCoordinate, destination);
        assertThat(isMovable).isFalse();
    }

    @DisplayName("King의 도착위치에 같은 팀 기물이 있으면 이동 불가")
    @Test
    void cannotMoveWhenSameTeamPieceExistsOnDestination() {
        Coordinate destination = Coordinate.from("c4");

        cells.get(destination).put(new Rook(TeamType.BLACK));

        boolean isMovable = king.isMovable(chessBoard, currentCoordinate, destination);
        assertThat(isMovable).isFalse();
    }

    @DisplayName("King의 도착위치에 적 팀 기물이 있으면 이동 가능")
    @Test
    void moveToDestinationWhenEnemyExistsOnDestination() {
        Coordinate destination = Coordinate.from("c4");
        Piece dummy = new Rook(TeamType.WHITE);
        cells.get(destination).put(dummy);
        boolean isMovable = king.isMovable(chessBoard, currentCoordinate, destination);

        assertThat(isMovable).isTrue();
    }
}
