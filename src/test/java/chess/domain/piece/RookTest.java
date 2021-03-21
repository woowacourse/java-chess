package chess.domain.piece;

import chess.domain.board.Cell;
import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardGenerator;
import chess.domain.board.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    private final Piece rook = new Rook(TeamType.BLACK);
    private final Coordinate current = Coordinate.from("d5");
    private ChessBoard chessBoard;
    private Map<Coordinate, Cell> cells;

    @BeforeEach
    void setup() {
        cells = ChessBoardGenerator.generateEmptyBoard();
        chessBoard = new ChessBoard(cells);
        cells.get(current).put(rook);
    }

    @DisplayName("Rook의 isMovable 메서드는")
    @Nested
    class Describe_isMovable {

        @DisplayName("빈 체스판에서")
        @Nested
        class Context_EmptyChessBoard {

            @DisplayName("동서남북 방향으로 최소 1칸 이상 이동 가능하다.")
            @ParameterizedTest
            @ValueSource(strings = {"d4", "d7", "c5", "f5"})
            void moveSouthNorthEastWest(String destinationInput) {
                Coordinate destination = Coordinate.from(destinationInput);

                boolean isMovable = rook.isMovable(chessBoard, current, destination);

                assertThat(isMovable).isTrue();
            }

            @DisplayName("동서남북 방향을 제외한 방향으로는 이동할 수 없다.")
            @ParameterizedTest
            @ValueSource(strings = {"c4", "c6", "e4", "e6"})
            void cannotMoveDiagonal(String destinationInput) {
                Coordinate destination = Coordinate.from(destinationInput);

                boolean isMovable = rook.isMovable(chessBoard, current, destination);

                assertThat(isMovable).isFalse();
            }
        }

        @DisplayName("적이나 아군 기물이 졵재하는 체스판에서")
        @Nested
        class Context_InitializedChessBoard {

            private final Coordinate destination = Coordinate.from("g5");

            @DisplayName("현재 위치와 도착 위치 사이의 경로에 기물(팀에 상관없이)이 존재하는 경우 이동할 수 없다.")
            @ParameterizedTest
            @EnumSource(TeamType.class)
            void cannotMoveWhenPieceOnRouteExists(TeamType teamType) {
                Coordinate routeCoordinate = Coordinate.from("f5");
                cells.get(routeCoordinate).put(new King(teamType));

                boolean isMovable = rook.isMovable(chessBoard, current, destination);

                assertThat(isMovable).isFalse();
            }

            @DisplayName("도착 위치에 아군이 존재하는 경우 이동할 수 없다.")
            @Test
            void cannotMoveWhenSameTeamExistsOnDestination() {
                cells.get(destination).put(new King(TeamType.BLACK));

                boolean isMovable = rook.isMovable(chessBoard, current, destination);

                assertThat(isMovable).isFalse();
            }

            @DisplayName("도착 위치에 적군이 존재하는 경우 이동할 수 있다.")
            @Test
            void canMoveWhenEnemyExistsOnDestination() {
                cells.get(destination).put(new King(TeamType.WHITE));

                boolean isMovable = rook.isMovable(chessBoard, current, destination);

                assertThat(isMovable).isTrue();
            }
        }
    }
}
