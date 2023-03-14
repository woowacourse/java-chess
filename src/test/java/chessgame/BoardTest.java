package chessgame;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chessgame.domain.Board;
import chessgame.util.ChessBoardFactory;

class BoardTest {
    @Test
    @DisplayName("모든 좌표값을 가진 보드를 생성한다")
    void Should_NotThrowException_When_ConstructBord() {
        Assertions.assertDoesNotThrow(() -> new Board(ChessBoardFactory.create()));
    }
}
