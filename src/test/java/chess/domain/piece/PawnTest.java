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
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class PawnTest {
    private ChessBoard chessBoard;
    private Map<Coordinate, Cell> cells;

    @DisplayName("생성 테스트")
    @Test
    void makePawn() {
        assertThatCode(() -> new Pawn(TeamType.BLACK))
                .doesNotThrowAnyException();
    }

    @DisplayName("폰 이동 테스트")
    @Nested
    class Context_Pawn_Move {
        @DisplayName("흑팀일 경우")
        @Nested
        class BlackTeam {

            private final Piece pawn = new Pawn(TeamType.BLACK);
            private Coordinate currentCoordinate = Coordinate.from("d7");

            @BeforeEach
            void setup() {
                chessBoard = new ChessBoard(ChessBoardGenerator.generateEmptyBoard());
                cells = chessBoard.getCells();
                cells.get(currentCoordinate).put(pawn);
            }

            @DisplayName("폰의 DOWN방향 한 칸 앞에 아무것도 없으면, 갈 수 있다.")
            @Test
            void pawnCanMoveForward() {
                Coordinate destination = Coordinate.from("d6");

                boolean isMovable = pawn.isMovable(chessBoard, currentCoordinate, destination);

                assertThat(isMovable).isTrue();
            }

            @DisplayName("폰의 DOWN방향 한 칸 앞에 기물이 존재하면, 갈 수 없다.")
            @Test
            void pawnCannotMoveForward() {
                Coordinate destination = Coordinate.from("d6");

                Piece dummy = new Rook(TeamType.BLACK);
                cells.get(Coordinate.from("d6")).put(dummy);

                boolean isMovable = pawn.isMovable(chessBoard, currentCoordinate, destination);

                assertThat(isMovable).isFalse();
            }

            @DisplayName("폰이 시작 위치이고, DOWN방향으로 두 칸 모두 아무것도 없으면, 갈 수 있다.")
            @Test
            void pawnCanMoveTwoCellsForwardFirstTime() {
                Coordinate destination = Coordinate.from("d5");

                boolean isMovable = pawn.isMovable(chessBoard, currentCoordinate, destination);

                assertThat(isMovable).isTrue();
            }

            @DisplayName("폰이 DOWN방향으로 두 칸 전진하려고 할 때, 한 칸 앞에 기물이 존재하면, 갈 수 없다.")
            @Test
            void pawnCannotMoveForwardTwoCells() {
                Coordinate destination = Coordinate.from("d5");

                Piece dummy = new Rook(TeamType.BLACK);
                cells.get(Coordinate.from("d6")).put(dummy);

                boolean isMovable = pawn.isMovable(chessBoard, currentCoordinate, destination);

                assertThat(isMovable).isFalse();
            }

            @DisplayName("폰이 DOWN방향으로 두 칸 전진하려고 할 때, 두 칸 앞에 기물이 존재하면, 갈 수 없다.")
            @Test
            void pawnCannotMoveForwardTwoCells2() {
                Coordinate destination = Coordinate.from("d5");

                Piece dummy = new Rook(TeamType.BLACK);
                cells.get(Coordinate.from("d5")).put(dummy);

                boolean isMovable = pawn.isMovable(chessBoard, currentCoordinate, destination);

                assertThat(isMovable).isFalse();
            }

            @DisplayName("폰이 시작위치가 아닌 곳에서 DOWN방향으로 두 칸 전진할 수 없다.")
            @Test
            void pawnCannotMoveForwardTwoCellsOnNotFirstPlace() {
                currentCoordinate = Coordinate.from("d6");
                Coordinate destination = Coordinate.from("d4");

                boolean isMovable = pawn.isMovable(chessBoard, currentCoordinate, destination);

                assertThat(isMovable).isFalse();
            }

            @DisplayName("폰의 DOWN방향 대각선 한 칸 앞에 적 기물이 존재하면, 갈 수 있다.")
            @ParameterizedTest
            @ValueSource(strings = {"c6", "e6"})
            void pawnCanMoveDiagonal(String destinationInput) {
                Coordinate destination = Coordinate.from(destinationInput);

                Piece dummy = new Rook(TeamType.WHITE);
                cells.get(destination).put(dummy);

                boolean isMovable = pawn.isMovable(chessBoard, currentCoordinate, destination);

                assertThat(isMovable).isTrue();
            }

            @DisplayName("폰의 DOWN방향 대각선 한 칸 앞에 적 기물이 없으면, 갈 수 없다.")
            @ParameterizedTest
            @ValueSource(strings = {"c6", "e6"})
            void pawnCannotMoveDiagonal(String destinationInput) {
                Coordinate destination = Coordinate.from(destinationInput);

                boolean isMovable = pawn.isMovable(chessBoard, currentCoordinate, destination);

                assertThat(isMovable).isFalse();
            }
        }

        @DisplayName("백팀일 경우")
        @Nested
        class WhiteTeam {

            private final Piece pawn = new Pawn(TeamType.WHITE);
            private Coordinate currentCoordinate = Coordinate.from("d2");

            @BeforeEach
            void setup() {
                chessBoard = new ChessBoard(ChessBoardGenerator.generateEmptyBoard());
                cells = chessBoard.getCells();
                cells.get(currentCoordinate).put(pawn);
            }

            @DisplayName("폰의 UP방향 한 칸 앞에 아무것도 없으면, 갈 수 있다.")
            @Test
            void pawnCanMoveForward() {
                Coordinate destination = Coordinate.from("d3");

                boolean isMovable = pawn.isMovable(chessBoard, currentCoordinate, destination);

                assertThat(isMovable).isTrue();
            }

            @DisplayName("폰의 UP방향 한 칸 앞에 기물이 존재하면, 갈 수 없다.")
            @Test
            void pawnCannotMoveForward() {
                Coordinate destination = Coordinate.from("d3");

                Piece dummy = new Rook(TeamType.WHITE);
                cells.get(Coordinate.from("d3")).put(dummy);

                boolean isMovable = pawn.isMovable(chessBoard, currentCoordinate, destination);

                assertThat(isMovable).isFalse();
            }

            @DisplayName("폰이 시작 위치이고, UP방향 두 칸 모두 아무것도 없으면, 갈 수 있다.")
            @Test
            void pawnCanMoveTwoCellsForwardFirstTime() {
                Coordinate destination = Coordinate.from("d4");

                boolean isMovable = pawn.isMovable(chessBoard, currentCoordinate, destination);

                assertThat(isMovable).isTrue();
            }

            @DisplayName("폰이 UP방향 두 칸 전진하려고 할 때, 한 칸 앞에 기물이 존재하면, 갈 수 없다.")
            @Test
            void pawnCannotMoveForwardTwoCells() {
                Coordinate destination = Coordinate.from("d4");

                Piece dummy = new Rook(TeamType.BLACK);
                cells.get(Coordinate.from("d3")).put(dummy);

                boolean isMovable = pawn.isMovable(chessBoard, currentCoordinate, destination);

                assertThat(isMovable).isFalse();
            }

            @DisplayName("폰이 UP방향 두 칸 전진하려고 할 때, 두 칸 앞에 기물이 존재하면, 갈 수 없다.")
            @Test
            void pawnCannotMoveForwardTwoCells2() {
                Coordinate destination = Coordinate.from("d4");

                Piece dummy = new Rook(TeamType.WHITE);
                cells.get(Coordinate.from("d4")).put(dummy);

                boolean isMovable = pawn.isMovable(chessBoard, currentCoordinate, destination);

                assertThat(isMovable).isFalse();
            }

            @DisplayName("폰이 시작위치가 아닌 곳에서 UP방향 두 칸 전진할 수 없다.")
            @Test
            void pawnCannotMoveForwardTwoCellsOnNotFirstPlace() {
                currentCoordinate = Coordinate.from("d3");
                Coordinate destination = Coordinate.from("d5");

                boolean isMovable = pawn.isMovable(chessBoard, currentCoordinate, destination);

                assertThat(isMovable).isFalse();
            }

            @DisplayName("폰의 UP방향 대각선 한 칸 앞에 적 기물이 존재하면, 갈 수 있다.")
            @ParameterizedTest
            @ValueSource(strings = {"c3", "e3"})
            void pawnCanMoveDiagonal(String destinationInput) {
                Coordinate destination = Coordinate.from(destinationInput);

                Piece dummy = new Rook(TeamType.BLACK);
                cells.get(destination).put(dummy);

                boolean isMovable = pawn.isMovable(chessBoard, currentCoordinate, destination);

                assertThat(isMovable).isTrue();
            }

            @DisplayName("폰의 UP방향 대각선 한 칸 앞에 적 기물이 없으면, 갈 수 없다.")
            @ParameterizedTest
            @ValueSource(strings = {"c3", "e3"})
            void pawnCannotMoveDiagonal(String destinationInput) {
                Coordinate destination = Coordinate.from(destinationInput);

                boolean isMovable = pawn.isMovable(chessBoard, currentCoordinate, destination);

                assertThat(isMovable).isFalse();
            }
        }
    }
}
