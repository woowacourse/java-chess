package chess.model.piece;

import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BlankTest {

    @Test
    @DisplayName("빈칸으로부터 갈 수 있는 경로는 없다.")
    void findPath() {
        // given
        Piece blank = Blank.INSTANCE;
        Position blankPosition = Position.of(File.C, Rank.FOUR);
        Position targetPosition = Position.of(File.C, Rank.FIVE);
        Piece targetPiece = Blank.INSTANCE;

        // when & then
        assertThatThrownBy(() -> blank.findPath(blankPosition, targetPosition, targetPiece))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("빈칸은 기물 가치가 없다.")
    void value() {
        // given
        Piece blank = Blank.INSTANCE;

        // when & then
        assertThatThrownBy(blank::value)
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
