package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import domain.board.Board;
import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopTest {

    @DisplayName("비숍은 모든 대각 방향으로 칸 수 제한 없이 이동할 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"d5,a8", "d5,f7", "d5,b3", "d5,h1"}, delimiter = ',')
    void testMoveEmptyPlace(String source, String target) {
        Board board = new Board();
        Position sourcePosition = new Position(source);
        Position targetPosition = new Position(target);
        Bishop blackBishop = new Bishop(Color.BLACK);

        board.put(sourcePosition, blackBishop);

        assertThat(blackBishop.canMove(board.getBoard(), sourcePosition, targetPosition)).isTrue();
    }

    @DisplayName("비숍은 이동 가능 범위가 아닌 위치로 이동 할 수 없다.")
    @ParameterizedTest
    @CsvSource(value = {"d5,a5", "d5,h5", "d5,d7", "d5,d2"}, delimiter = ',')
    void testNotMoveEmptyPlace(String source, String target) {
        Board board = new Board();
        Position sourcePosition = new Position(source);
        Position targetPosition = new Position(target);
        Bishop blackBishop = new Bishop(Color.BLACK);

        board.put(sourcePosition, blackBishop);

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[Error] 해당 기물은 target 위치로 이동할 수 없습니다.");
    }

    @DisplayName("비숍은 다른 편 기물이 있는 위치로 이동할 수 있다.")
    @Test
    void testMoveEnemyPiecePlace() {
        Board board = new Board();
        Position sourcePosition = new Position("d5");
        Position targetPosition = new Position("a8");
        Bishop blackBishop = new Bishop(Color.BLACK);
        Rook blackRook = new Rook(Color.WHITE);

        board.put(sourcePosition, blackBishop);
        board.put(targetPosition, blackRook);

        assertThat(blackBishop.canMove(board.getBoard(), sourcePosition, targetPosition)).isTrue();
    }

    @DisplayName("비숍은 이동 경로상에 다른 기물이 있으면 이동할 수 없다.")
    @ParameterizedTest
    @CsvSource(value = {"d5,a8,b7", "d5,g8,e6", "d5,a2,b3", "d5,h1,g2"}, delimiter = ',')
    void testObstacleMove(String source, String target, String obstacle) {
        Board board = new Board();
        Position sourcePosition = new Position(source);
        Position targetPosition = new Position(target);
        Position obstaclePosition = new Position(obstacle);
        Bishop blackBishop = new Bishop(Color.BLACK);
        Rook whiteRook = new Rook(Color.WHITE);

        board.put(sourcePosition, blackBishop);
        board.put(obstaclePosition, whiteRook);

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[Error] 해당 기물은 target 위치로 이동할 수 없습니다.");
    }

}