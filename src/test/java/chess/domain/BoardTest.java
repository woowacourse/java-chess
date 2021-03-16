package chess.domain;

import chess.domain.piece.Pawn;
import chess.domain.position.Position;
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
    @DisplayName("폰 초기화 기능")
    void initiatePawn() {
        board.initializePawn();
        assertThat(board.unwrap().get(new Position("a", "7")))
                .isInstanceOf(Pawn.class);
    }

    @ParameterizedTest
    @DisplayName("룩 초기화 기능")
    @ValueSource(strings = {"a,1", "h,1", "a,8", "h,8"})
    void initiateRook(final String input) {
        final String[] inputs = input.split(",");
        board.initializeRook();
        assertThat(board.unwrap().get(new Position(inputs[0], inputs[1])))
                .isInstanceOf(Pawn.class);
    }
}