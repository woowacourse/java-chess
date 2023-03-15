package chess.domain;

import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
    
    @Test
    @DisplayName("보드 피스 생성 테스트")
    void initialize() {
        Board board = Board.create();
        board.initialize();
        List<String> positions = List.of("a1", "b2", "g7", "h8");
        List<Piece> pieces = List.of(Rook.create(Color.WHITE), Pawn.create(Color.WHITE), Pawn.create(Color.BLACK),
                Rook.create(Color.BLACK));
        for (int i = 0; i < 4; i++) {
            Assertions.assertThat(board).extracting("board").asInstanceOf(InstanceOfAssertFactories.MAP)
                    .containsEntry(Position.from(positions.get(i)), pieces.get(i));
        }
    }
}