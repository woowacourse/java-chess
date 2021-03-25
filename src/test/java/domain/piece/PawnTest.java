package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import domain.board.Board;
import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {

    @DisplayName("폰은 전방으로 1칸 이동 가능하다.")
    @ParameterizedTest
    @CsvSource(value = {"d7,d6,true", "d2,d3,false"}, delimiter = ',')
    void testMoveEmptyPlace(String source, String target, boolean isBlack) {
        Board board = new Board();
        Position sourcePosition = new Position(source);
        Position targetPosition = new Position(target);
        Pawn pawn = new Pawn(isBlack);
        board.put(sourcePosition, pawn);

        assertThat(pawn.canMove(board.getBoard(), sourcePosition, targetPosition)).isTrue();
    }

    @DisplayName("폰은 전방으로 1칸 위치에 기물이 있으면 이동할 수 없다.")
    @ParameterizedTest
    @CsvSource(value = {"d7,d6,true", "d2,d3,false"}, delimiter = ',')
    void testNotMoveEmptyPlace(String source, String target, boolean isBlack) {
        Board board = new Board();
        Position sourcePosition = new Position(source);
        Position targetPosition = new Position(target);
        Pawn pawn = new Pawn(isBlack);
        Knight knight = new Knight(!isBlack);

        board.put(sourcePosition, pawn);
        board.put(targetPosition, knight);

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[Error] 해당 기물은 target 위치로 이동할 수 없습니다.");
    }

    @DisplayName("폰은 첫 수에 전방으로 2칸 이동 가능하다.")
    @ParameterizedTest
    @CsvSource(value = {"d7,d5,true", "f2,f4,false"}, delimiter = ',')
    void testFirstMoveEmptyPlace(String source, String target, boolean isBlack) {
        Board board = new Board();
        Position sourcePosition = new Position(source);
        Position targetPosition = new Position(target);
        Pawn pawn = new Pawn(isBlack);

        board.put(sourcePosition, pawn);

        assertThat(pawn.canMove(board.getBoard(), sourcePosition, targetPosition)).isTrue();
    }

    @DisplayName("폰은 전방으로 2칸 위치에 기물이 있으면, 첫 수에 전방으로 2칸 이동할 수 없다.")
    @ParameterizedTest
    @CsvSource(value = {"d7,d5,true", "f2,f4,false"}, delimiter = ',')
    void testFirstNotMove(String source, String target, boolean isBlack) {
        Board board = new Board();
        Position sourcePosition = new Position(source);
        Position targetPosition = new Position(target);
        Pawn pawn = new Pawn(isBlack);
        Knight knight = new Knight(!isBlack);

        board.put(sourcePosition, pawn);
        board.put(targetPosition, knight);

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[Error] 해당 기물은 target 위치로 이동할 수 없습니다.");
    }

    @DisplayName("폰은 첫 수가 아니라면 전방으로 2칸 이동 불가능하다.")
    @ParameterizedTest
    @CsvSource(value = {"d6,d4,true", "d3,d5,false"}, delimiter = ',')
    void testFirstNotMoveEmptyPlace(String source, String target, boolean isBlack) {
        Board board = new Board();
        Position sourcePosition = new Position(source);
        Position targetPosition = new Position(target);
        Pawn pawn = new Pawn(isBlack);

        board.put(sourcePosition, pawn);

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[Error] 해당 기물은 target 위치로 이동할 수 없습니다.");
    }

    @DisplayName("폰은 전방으로 2칸 이동하려는 이동 경로에 장애물이 있을 경우, 이동 불가능하다.")
    @ParameterizedTest
    @CsvSource(value = {"d7,d5,d6,true", "d2,d4,d3,false"}, delimiter = ',')
    void testFirstNotMoveEmptyPlace(String source, String target, String obstacle,
        boolean isBlack) {
        Board board = new Board();
        Position sourcePosition = new Position(source);
        Position targetPosition = new Position(target);
        Position obstaclePosition = new Position(obstacle);
        Pawn pawn = new Pawn(isBlack);
        Knight knight = new Knight(isBlack);

        board.put(sourcePosition, pawn);
        board.put(obstaclePosition, knight);

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[Error] 해당 기물은 target 위치로 이동할 수 없습니다.");
    }

    @DisplayName("폰은 전방 양쪽 대각 방향에 적 기물이 있을 경우에 이동 가능하다.")
    @ParameterizedTest
    @CsvSource(value = {"d7,c6,true", "d7,e6,true", "d2,c3,false", "d2,c3,false"}, delimiter = ',')
    void testDiagonalMoveEmptyPlace(String source, String target, boolean isBlack) {
        Board board = new Board();
        Position sourcePosition = new Position(source);
        Position targetPosition = new Position(target);
        Pawn pawn = new Pawn(isBlack);
        Knight knight = new Knight(!isBlack);

        board.put(sourcePosition, pawn);
        board.put(targetPosition, knight);

        assertThat(pawn.canMove(board.getBoard(), sourcePosition, targetPosition)).isTrue();
    }

    @DisplayName("폰은 전방 양쪽 대각 방향에 적 기물이 없을 경우에 이동 불가능하다.")
    @ParameterizedTest
    @CsvSource(value = {"d7,c6,true", "d7,e6,true", "d2,c3,false", "d2,e3,false"}, delimiter = ',')
    void testDiagonalNotMoveEmptyPlace(String source, String target, boolean isBlack) {
        Board board = new Board();
        Position sourcePosition = new Position(source);
        Position targetPosition = new Position(target);
        Pawn pawn = new Pawn(isBlack);

        board.put(sourcePosition, pawn);

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[Error] 해당 기물은 target 위치로 이동할 수 없습니다.");
    }

}