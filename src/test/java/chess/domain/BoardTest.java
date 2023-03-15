package chess.domain;

import chess.domain.piece.Empty;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BoardTest {
    
    @ParameterizedTest
    @ValueSource(strings = {"a1", "d2", "h8"})
    @DisplayName("빈 보드 생성 테스트")
    void create(String position) {
        Board board = Board.create();
        Assertions.assertThat(board).extracting("board").asInstanceOf(InstanceOfAssertFactories.MAP)
                .containsEntry(Position.from(position), Empty.create());
    }
}