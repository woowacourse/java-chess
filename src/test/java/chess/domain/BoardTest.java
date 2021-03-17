package chess.domain;

import chess.domain.piece.*;
import chess.domain.position.Position;
import chess.view.OutputView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("피스 이동 기능")
    void move() {
        board.move(new Position("a", "2"), new Position("a", "3")); // map의 요소가 바뀜
        assertThat(board.unwrap().get(new Position("a", "2"))).isEqualTo(new Blank());
        assertThat(board.unwrap().get(new Position("a", "3"))).isEqualTo(new Pawn(false));
        OutputView.printCurrentBoard(board.unwrap());
    }
}