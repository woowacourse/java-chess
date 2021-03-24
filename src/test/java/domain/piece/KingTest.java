package domain.piece;


import static org.assertj.core.api.Assertions.assertThat;

import domain.board.Board;
import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {

    @DisplayName("킹은 상하좌우와 모든 대각선 방향으로 1칸 이동할 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"d5,e5", "d5,e4", "d5,d4", "d5,c4", "d5,c5", "d5,c6", "d5,d6",
        "d5,e6"}, delimiter = ',')
    void testMoveEmptyPlace(String source, String target) {
        Board board = new Board();
        Position sourcePosition = new Position(source);
        Position targetPosition = new Position(target);
        King blackKing = new King(true);
        board.put(sourcePosition, blackKing);

        assertThat(blackKing.canMove(board, sourcePosition, targetPosition)).isTrue();
    }

    @DisplayName("킹은 이동 가능 범위가 아닌 위치로 이동 할 수 없다.")
    @ParameterizedTest
    @CsvSource(value = {"d5,h1", "d5,h2", "d5,h3", "d5,h4"}, delimiter = ',')
    void testNotMoveEmptyPlace(String source, String target) {
        Board board = new Board();
        Position sourcePosition = new Position(source);
        Position targetPosition = new Position(target);
        King blackKing = new King(true);
        board.put(sourcePosition, blackKing);

        assertThat(blackKing.canMove(board, sourcePosition, targetPosition)).isFalse();
    }

    @DisplayName("킹은 다른 편 기물이 있는 위치로 이동할 수 있다.")
    @Test
    void testMoveEnemyPiecePlace() {
        Board board = new Board();
        Position sourcePosition = new Position("d5");
        Position targetPosition = new Position("e5");
        King blackKing = new King(true);
        Rook blackRook = new Rook(false);
        board.put(sourcePosition, blackKing);
        board.put(targetPosition, blackRook);

        assertThat(blackKing.canMove(board, sourcePosition, targetPosition)).isTrue();
    }

}