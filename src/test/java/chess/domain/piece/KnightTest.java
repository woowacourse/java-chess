package chess.domain.piece;

import chess.domain.board.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class KnightTest {

    private final Piece knight = new Knight(TeamType.BLACK);
    private final Coordinate current = Coordinate.from("d4");
    private ChessBoard chessBoard;
    private Map<Coordinate, Cell> cells;

    @BeforeEach
    void setup() {
        cells = Arrays.stream(File.values())
                .flatMap(file -> Arrays.stream(Rank.values()).map(rank -> new Coordinate(file, rank)))
                .collect(Collectors.toMap(coordinate -> coordinate, value -> new Cell()));
        chessBoard = new ChessBoard(cells);
        cells.get(current).put(knight);
    }

    @DisplayName("Knight의 isMovable 메서드는")
    @Nested
    class Describe_isMovable {

        @DisplayName("빈 체스판에서")
        @Nested
        class Context_EmptyChessBoard {

            @DisplayName("현재 위치에서 현재 위치로 이동할 수 없다.")
            @Test
            void cannotMoveToCurrentDestination() {
                assertThatCode(() -> knight.isMovable(chessBoard, current, current))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("현재 위치와 도착 위치가 동일합니다.");
            }

            @DisplayName("지정된 8 방향으로만 이동할 수 있다.")
            @ParameterizedTest
            @ValueSource(strings = {"b3", "b5", "c2", "c6", "e2", "e6", "f3", "f5"})
            void moveKnightDirection(String destinationInput) {
                Coordinate destination = Coordinate.from(destinationInput);

                boolean isMovable = knight.isMovable(chessBoard, current, destination);

                assertThat(isMovable).isTrue();
            }

            @DisplayName("그 외의 방향으로는 이동할 수 없다.")
            @ParameterizedTest
            @ValueSource(strings = {"c3", "c4", "c5", "e3", "e4", "e5"})
            void cannotMoveWExceptValidDirection(String destinationInput) {
                Coordinate destination = Coordinate.from(destinationInput);

                boolean isMovable = knight.isMovable(chessBoard, current, destination);

                assertThat(isMovable).isFalse();
            }

            @DisplayName("정의되지 않은 방향으로는 이동할 수 없다.")
            @ParameterizedTest
            @ValueSource(strings = {"a3", "c1"})
            void cannotMoveUndefinedDirection(String destinationInput) {
                Coordinate destination = Coordinate.from(destinationInput);

                assertThatCode(() -> knight.isMovable(chessBoard, current, destination))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("주어진 위치로의 방향을 찾을 수 없습니다.");
            }
        }

        @DisplayName("적이나 아군 기물이 졵재하는 체스판에서")
        @Nested
        class Context_InitializedChessBoard {

            private final Coordinate destination = Coordinate.from("b5");

            @DisplayName("현재 위치와 도착 위치 사이의 경로에 기물(팀에 상관없이)이 존재하는 경우 이동할 수 있다")
            @ParameterizedTest
            @EnumSource(TeamType.class)
            void cannotMoveWhenPieceOnRouteExists(TeamType teamType) {
                Coordinate routeCoordinate = Coordinate.from("c5");
                Coordinate routeCoordinate2 = Coordinate.from("c4");
                cells.get(routeCoordinate).put(new King(teamType));
                cells.get(routeCoordinate2).put(new Queen(teamType));

                boolean isMovable = knight.isMovable(chessBoard, current, destination);

                assertThat(isMovable).isTrue();
            }

            @DisplayName("도착 위치에 아군이 존재하는 경우 이동할 수 없다.")
            @Test
            void cannotMoveWhenSameTeamExistsOnDestination() {
                cells.get(destination).put(new King(TeamType.BLACK));

                boolean isMovable = knight.isMovable(chessBoard, current, destination);

                assertThat(isMovable).isFalse();
            }

            @DisplayName("도착 위치에 적군이 존재하는 경우 이동할 수 있다.")
            @Test
            void canMoveWhenEnemyExistsOnDestination() {
                cells.get(destination).put(new King(TeamType.WHITE));

                boolean isMovable = knight.isMovable(chessBoard, current, destination);

                assertThat(isMovable).isTrue();
            }
        }
    }
}
