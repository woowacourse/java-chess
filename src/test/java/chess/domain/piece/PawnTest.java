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
import static org.assertj.core.api.Assertions.assertThatCode;

class PawnTest {

    private ChessBoard chessBoard;
    private Map<Coordinate, Cell> cells;

    @BeforeEach
    void setup() {
        cells = ChessBoardGenerator.generateEmptyBoard();
        chessBoard = new ChessBoard(cells);
    }

    @DisplayName("Pawn의 isMovable 메서드는")
    @Nested
    class Describe_isMovable {

        @DisplayName("흑팀일 때")
        @Nested
        class Context_BlackTeam {

            private final Coordinate current = Coordinate.from("d7");
            private final Piece pawn = new Pawn(TeamType.BLACK);

            @DisplayName("빈 체스판에서")
            @Nested
            class Context_EmptyChessBoard {

                @DisplayName("아래 방향으로 1칸 전진할 수 있다.")
                @Test
                void canMoveDownward() {
                    cells.get(current).put(pawn);
                    Coordinate destination = Coordinate.from("d6");

                    boolean isMovable = pawn.isMovable(chessBoard, current, destination);

                    assertThat(isMovable).isTrue();
                }

                @DisplayName("처음 위치(d7)인 경우 아래 방향으로 2칸 전진할 수 있다.")
                @Test
                void canMoveDownwardTwoStep() {
                    cells.get(current).put(pawn);
                    Coordinate destination = Coordinate.from("d5");

                    boolean isMovable = pawn.isMovable(chessBoard, current, destination);

                    assertThat(isMovable).isTrue();
                }

                @DisplayName("처음 위치(d7)가 아닌 경우 아래 2칸으로 전진할 수 없다.")
                @Test
                void cannotMoveDownwardTwoStep() {
                    cells.get(Coordinate.from("d6")).put(pawn);
                    Coordinate destination = Coordinate.from("d4");

                    boolean isMovable = pawn.isMovable(chessBoard, current, destination);

                    assertThat(isMovable).isFalse();
                }

                @DisplayName("대각선에 적이 없어서 이동할 수 없다.")
                @ParameterizedTest
                @ValueSource(strings = {"c6", "e6"})
                void cannotMoveDiagonal(String destinationInput) {
                    cells.get(current).put(pawn);
                    Coordinate destination = Coordinate.from(destinationInput);

                    boolean isMovable = pawn.isMovable(chessBoard, current, destination);

                    assertThat(isMovable).isFalse();
                }

                @DisplayName("앞 3가지 방향의 전진을 제외하면 이동할 수 없다.")
                @ParameterizedTest
                @ValueSource(strings = {"c7", "e7", "c8", "e8", "d8"})
                void cannotMoveBackward(String destinationInput) {
                    cells.get(current).put(pawn);
                    Coordinate destination = Coordinate.from(destinationInput);

                    boolean isMovable = pawn.isMovable(chessBoard, current, destination);

                    assertThat(isMovable).isFalse();
                }

                @DisplayName("정의되지 않은 방향으로는 이동할 수 없다.")
                @ParameterizedTest
                @ValueSource(strings = {"a5", "c1"})
                void cannotMoveUndefinedDirection(String destinationInput) {
                    Coordinate destination = Coordinate.from(destinationInput);

                    assertThatCode(() -> pawn.isMovable(chessBoard, current, destination))
                            .isInstanceOf(IllegalArgumentException.class)
                            .hasMessage("주어진 위치로의 방향을 찾을 수 없습니다.");
                }
            }

            @DisplayName("아군이나 적 기물이 존재하는 체스판에서")
            @Nested
            class Context_InitializedChessBoard {

                private final Piece enemy = new Knight(TeamType.WHITE);

                @DisplayName("바로 앞에 적이 있더라도 아래 방향으로는 공격할 수 없다.")
                @Test
                void cannotMoveDownward() {
                    cells.get(current).put(pawn);
                    Coordinate destination = Coordinate.from("d6");
                    cells.get(destination).put(enemy);

                    boolean isMovable = pawn.isMovable(chessBoard, current, destination);

                    assertThat(isMovable).isFalse();
                }

                @DisplayName("처음 위치(d7)에서 2칸 이동할 때 사이에 기물이 존재하면 팀에 상관없이 이동할 수 없다.")
                @ParameterizedTest
                @EnumSource(TeamType.class)
                void cannotMoveTwiceWhenPieceOnRouteExists(TeamType teamType) {
                    cells.get(current).put(pawn);
                    Coordinate route = Coordinate.from("d6");
                    cells.get(route).put(new Knight(teamType));

                    Coordinate destination = Coordinate.from("d5");
                    boolean isMovable = pawn.isMovable(chessBoard, current, destination);

                    assertThat(isMovable).isFalse();
                }

                @DisplayName("적이 대각선에 위치한 경우 대각선으로 이동(공격)할 수 있다.")
                @Test
                void canMoveDiagonal() {
                    cells.get(current).put(pawn);
                    Coordinate destination = Coordinate.from("c6");
                    cells.get(destination).put(enemy);

                    boolean isMovable = pawn.isMovable(chessBoard, current, destination);

                    assertThat(isMovable).isTrue();
                }

                @DisplayName("대각선에 아군이 위치한 경우 이동할 수 없다.")
                @Test
                void cannotMoveDiagonalWhenSameTeamExists() {
                    cells.get(current).put(pawn);
                    Coordinate destination = Coordinate.from("e6");
                    cells.get(destination).put(new Knight(TeamType.BLACK));

                    boolean isMovable = pawn.isMovable(chessBoard, current, destination);

                    assertThat(isMovable).isFalse();
                }
            }
        }

        @DisplayName("백팀일 때")
        @Nested
        class Context_WhiteTeam {

            private final Coordinate current = Coordinate.from("d2");
            private final Piece pawn = new Pawn(TeamType.WHITE);

            @DisplayName("빈 체스판에서")
            @Nested
            class Context_EmptyChessBoard {

                @DisplayName("위 방향으로 1칸 전진할 수 있다.")
                @Test
                void canMoveUpward() {
                    cells.get(current).put(pawn);
                    Coordinate destination = Coordinate.from("d3");

                    boolean isMovable = pawn.isMovable(chessBoard, current, destination);

                    assertThat(isMovable).isTrue();
                }

                @DisplayName("처음 위치(d2)인 경우 위 방향으로 2칸 전진할 수 있다.")
                @Test
                void canMoveUpwardTwoStep() {
                    cells.get(current).put(pawn);
                    Coordinate destination = Coordinate.from("d4");

                    boolean isMovable = pawn.isMovable(chessBoard, current, destination);

                    assertThat(isMovable).isTrue();
                }

                @DisplayName("처음 위치(d2)가 아닌 경우 위 2칸으로 전진할 수 없다.")
                @Test
                void cannotMoveUpwardTwoStep() {
                    cells.get(Coordinate.from("d3")).put(pawn);
                    Coordinate destination = Coordinate.from("d5");

                    boolean isMovable = pawn.isMovable(chessBoard, current, destination);

                    assertThat(isMovable).isFalse();
                }

                @DisplayName("대각선에 적이 없어서 이동할 수 없다.")
                @ParameterizedTest
                @ValueSource(strings = {"c3", "e3"})
                void cannotMoveDiagonal(String destinationInput) {
                    cells.get(current).put(pawn);
                    Coordinate destination = Coordinate.from(destinationInput);

                    boolean isMovable = pawn.isMovable(chessBoard, current, destination);

                    assertThat(isMovable).isFalse();
                }

                @DisplayName("앞 3가지 방향의 전진을 제외하면 이동할 수 없다.")
                @ParameterizedTest
                @ValueSource(strings = {"c2", "e2", "c1", "e1", "d1"})
                void cannotMoveBackward(String destinationInput) {
                    cells.get(current).put(pawn);
                    Coordinate destination = Coordinate.from(destinationInput);

                    boolean isMovable = pawn.isMovable(chessBoard, current, destination);

                    assertThat(isMovable).isFalse();
                }

                @DisplayName("정의되지 않은 방향으로는 이동할 수 없다.")
                @ParameterizedTest
                @ValueSource(strings = {"a8", "h1"})
                void cannotMoveUndefinedDirection(String destinationInput) {
                    Coordinate destination = Coordinate.from(destinationInput);

                    assertThatCode(() -> pawn.isMovable(chessBoard, current, destination))
                            .isInstanceOf(IllegalArgumentException.class)
                            .hasMessage("주어진 위치로의 방향을 찾을 수 없습니다.");
                }
            }

            @DisplayName("아군이나 적 기물이 존재하는 체스판에서")
            @Nested
            class Context_InitializedChessBoard {

                private final Piece enemy = new Knight(TeamType.BLACK);

                @DisplayName("바로 앞에 적이 있더라도 위 방향으로는 공격할 수 없다.")
                @Test
                void cannotMoveUpward() {
                    cells.get(current).put(pawn);
                    Coordinate destination = Coordinate.from("d3");
                    cells.get(destination).put(enemy);

                    boolean isMovable = pawn.isMovable(chessBoard, current, destination);

                    assertThat(isMovable).isFalse();
                }

                @DisplayName("처음 위치(d2)에서 2칸 이동할 때 사이에 기물이 존재하면 팀에 상관없이 이동할 수 없다.")
                @ParameterizedTest
                @EnumSource(TeamType.class)
                void cannotMoveTwiceWhenPieceOnRouteExists(TeamType teamType) {
                    cells.get(current).put(pawn);
                    Coordinate route = Coordinate.from("d3");
                    cells.get(route).put(new Knight(teamType));

                    Coordinate destination = Coordinate.from("d4");
                    boolean isMovable = pawn.isMovable(chessBoard, current, destination);

                    assertThat(isMovable).isFalse();
                }

                @DisplayName("적이 대각선에 위치한 경우 대각선으로 이동(공격)할 수 있다.")
                @Test
                void canMoveDiagonal() {
                    cells.get(current).put(pawn);
                    Coordinate destination = Coordinate.from("c3");
                    cells.get(destination).put(enemy);

                    boolean isMovable = pawn.isMovable(chessBoard, current, destination);

                    assertThat(isMovable).isTrue();
                }

                @DisplayName("대각선에 아군이 위치한 경우 이동할 수 없다.")
                @Test
                void cannotMoveDiagonalWhenSameTeamExists() {
                    cells.get(current).put(pawn);
                    Coordinate destination = Coordinate.from("e3");
                    cells.get(destination).put(new Knight(TeamType.WHITE));

                    boolean isMovable = pawn.isMovable(chessBoard, current, destination);

                    assertThat(isMovable).isFalse();
                }
            }
        }
    }
}
