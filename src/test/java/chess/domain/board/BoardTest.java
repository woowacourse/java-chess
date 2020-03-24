package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("board는 boardInitializer 타입의 객체를 받아서 생성")
    void initialize() {
        Board board = Board.of(new EnumRepositoryBoardInitializer());
    }
}