package chess.piece.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    @DisplayName("File과 Rank를 받아서 Position을 생성한다.")
    void generatePosition() {
        // when, then
        Assertions.assertDoesNotThrow(() -> new Position(File.A, Rank.ONE));
    }

    @Test
    @DisplayName("받은 File과 Rank로 이동한다.")
    void move() {
        // given
        final Position position = new Position(File.A, Rank.ONE);

        // when
        position.move(File.F, Rank.EIGHT);

        // then
        assertThat(position.getFile()).isEqualTo(File.F);
        assertThat(position.getRank()).isEqualTo(Rank.EIGHT);
    }
}
