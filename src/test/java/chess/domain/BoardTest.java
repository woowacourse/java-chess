package chess.domain;

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
            assertDoesNotThrow(Board::create);
        }
    }

    @Nested
    @DisplayName("말을 움직이는 movePiece 메서드 테스트")
    class movePieceTest {

        @Test
        @DisplayName("source 위치에 조작할 수 있는 말이 없으면 예외처리한다.")
        void moveTest1() {
            Board board = Board.create();
            Position source = new Position(3, 3);
            Position target = new Position(3, 4);

            assertThatThrownBy(() -> board.movePiece(source, target)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("source 위치에 조작할 수 있는 말이 없습니다.");
        }

        @Test
        @DisplayName("source 위치에 있는 말과 target 위치에 있는 말의 팀 색깔이 같으면 예외처리한다.")
        void moveTest2() {
            Board board = Board.create();
            Position source = new Position(0, 0);
            Position target = new Position(0, 1);

            assertThatThrownBy(() -> board.movePiece(source, target)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("target 위치에 같은 팀의 말이 존재합니다.");
        }

        @Test
        @DisplayName("말의 이동방향이 올바르지 않을 경우 예외처리한다.")
        void moveTest3() {
            Board board = Board.create();
            Position initial = new Position(0, 1);
            Position source = new Position(0, 2);
            Position target = new Position(0, 1);
            board.movePiece(initial, source);

            assertThatThrownBy(() -> board.movePiece(source, target)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("말이 target 위치로 움직일 수 없습니다.");
        }

        @Test
        @DisplayName("말이 knight 가 아닌데 다른 말을 뛰어넘으려는 경우 예외처리한다.")
        void moveTest4() {
            Board board = Board.create();
            Position source = new Position(3, 0);
            Position target = new Position(3, 4);

            assertThatThrownBy(() -> board.movePiece(source, target)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("말이 target 위치로 움직일 수 없습니다.");
        }

        @Test
        @DisplayName("pawn 이 잡을 말이 없는데 대각선으로 이동하고자 할 경우 예외처리한다.")
        void moveTest5() {
            Board board = Board.create();
            Position source = new Position(1, 6);
            Position target = new Position(2, 5);

            assertThatThrownBy(() -> board.movePiece(source, target)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("말이 target 위치로 움직일 수 없습니다.");
        }

        @Test
        @DisplayName("knight 는 다른 말을 건너 뛸 수 있다.")
        void moveTest6() {
            Board board = Board.create();
            Position source = new Position(1, 0);
            Position target = new Position(2, 2);

            assertDoesNotThrow(() -> board.movePiece(source, target));
        }
    }
}
