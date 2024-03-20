package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessTableTest {


    @DisplayName("제자리 이동은 못한다.")
    @Test
    void moveSamePlace() {
        // given
        final ChessTable chessTable = ChessTable.create();

        final Square source = new Square(Rank.TWO, File.A);

        // when & then
        Assertions.assertThatThrownBy(() -> chessTable.move(source, source))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("갈 수 없는 경로입니다.");
    }

    @DisplayName("갈 수 없는 경로는 못간다.")
    @Test
    void emptyPath() {
        // given
        final ChessTable chessTable = ChessTable.create();

        final Square source = new Square(Rank.TWO, File.A);
        final Square target = new Square(Rank.FIVE, File.A);

        // when & then
        Assertions.assertThatThrownBy(() -> chessTable.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("갈 수 없는 경로입니다.");
    }

    @DisplayName("Source 위치에 말이 없으면 안된다.")
    @Test
    void noPieceInSource() {
        // given
        final ChessTable chessTable = ChessTable.create();

        final Square source = new Square(Rank.THREE, File.A);
        final Square target = new Square(Rank.FOUR, File.A);

        // when & then
        Assertions.assertThatThrownBy(() -> chessTable.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 기물이 없습니다.");
    }
}
