package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("나이트")
class KnightTest {

    @ParameterizedTest
    @ValueSource(strings = {"a5", "a7", "e5", "e7", "b4", "d4", "b8", "d8"})
    @DisplayName("앞으로 두칸 후 옆으로 한칸 이동한다.")
    void move(String targetInput) {
        // given
        Square source = Square.from("c6");
        Square target = Square.from(targetInput);
        Knight knight = new Knight(PieceColor.BLACK, source);
        Board board = new Board(Set.of(knight));

        // when
        knight.move(board, target);

        // then
        assertThat(knight.getSquare()).isEqualTo(target);
    }
}
