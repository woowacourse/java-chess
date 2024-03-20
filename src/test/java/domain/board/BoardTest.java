package domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("말이 존재하지 않는지 여부 반환 가능")
    void isNoPieceAt() {
        Board board = Board.generatedBy(new InitialBoardGenerator());
        Position exist = new Position(new File(1), new Rank(1));
        Position notExist = new Position(new File(4), new Rank(4));

        assertAll(
            () -> assertThat(board.isNoPieceAt(exist)).isFalse(),
            () -> assertThat(board.isNoPieceAt(notExist)).isTrue()
        );
    }
}
