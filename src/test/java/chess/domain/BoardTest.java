package chess.domain;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BoardTest {

    @Nested
    @DisplayName("보드판을 생성하는 create 메서드 테스트")
    class createTest {

        @Test
        @DisplayName("보드가 정상적으로 생성된다.")
        void moveTest() {
            assertDoesNotThrow(BoardGenerator::createBoard);
        }
    }

    @Nested
    @DisplayName("말을 움직이는 movePiece 메서드 테스트")
    class movePieceTest {

        @Test
        @DisplayName("source 위치에 조작할 수 있는 말이 없으면 예외처리한다.")
        void moveTest1() {
            Board board = BoardGenerator.createBoard();
            Position source = new Position(3, 3);
            Position target = new Position(3, 4);

            assertThatThrownBy(() -> board.movePiece(source, target)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("source 위치에 조작할 수 있는 말이 없습니다.");
        }


        @Test
        @DisplayName("말이 knight 가 아닌데 다른 말을 뛰어넘으려는 경우 예외처리한다.")
        void moveTest4() {
            Board board = BoardGenerator.createBoard();
            Position source = new Position(3, 0);
            Position target = new Position(3, 4);

            assertThatThrownBy(() -> board.movePiece(source, target)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("말이 target 위치로 움직일 수 없습니다.");
        }

        @Test
        @DisplayName("knight 는 다른 말을 건너 뛸 수 있다.")
        void moveTest6() {
            Board board = BoardGenerator.createBoard();
            Position source = new Position(1, 0);
            Position target = new Position(2, 2);

            assertDoesNotThrow(() -> board.movePiece(source, target));
        }
    }
}
