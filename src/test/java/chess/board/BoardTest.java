package chess.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.fixture.BoardFixture;
import chess.fixture.PositionFixture;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Nested
    @DisplayName("킹의 움직임")
    class KingTest {

        // 킹의 움직임
        @DisplayName("킹은 C2에서 C1으로 이동할 수 있다.")
        @Test
        void test1() {
            // given
            Position start = PositionFixture.C2;
            Position end = PositionFixture.C1;
            Board board = BoardFixture.createBoardWithOneWhitePiece(start, King.create());
            // when
            board.move(start, end);
            // then
            assertThat(board.pieceTypeAt(end)).isEqualTo(PieceType.KING);
            assertThat(board.colorAt(end)).isEqualTo(Color.WHITE);
        }

        @DisplayName("시작 위치에 기물이 없을 경우 에외가 발생한다.")
        @Test
        void test11() {
            // given
            Position start = PositionFixture.C2;
            Position end = PositionFixture.C3;
            Board board = BoardFixture.createBoardWithOneWhitePiece(PositionFixture.C1, King.create());
            // when
            // then
            assertThatThrownBy(() -> board.move(start, end))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 해당 위치에 기물이 없습니다.");
        }

        @DisplayName("킹이 도착할 위치에 같은 팀의 기물이 있는 경우 예외가 발생한다.")
        @Test
        void test3() {
            // given
            Position start = PositionFixture.C2;
            Position end = PositionFixture.C3;
            Board board = BoardFixture.createBoardWithTWoWhitePiece(PositionFixture.C2, PositionFixture.C3,
                    King.create(), Pawn.white());
            // when
            // then
            assertThatThrownBy(() -> board.move(start, end))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 같은 팀의 기물은 잡을 수 없습니다.");
        }

        @DisplayName("킹이 움직일 수 없는 위치를 입력한 경우 에외가 발생한다.")
        @Test
        void test2() {
            // given
            Position start = PositionFixture.C2;
            Position end = PositionFixture.C4;
            Board board = BoardFixture.createBoardWithOneWhitePiece(start, King.create());
            // when
            // then
            assertThatThrownBy(() -> board.move(start, end))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 기물의 이동 규칙에 어긋나는 움직임입니다.");
        }

        @DisplayName("킹이 도착할 위치에 다른 팀의 기물이 있는 경우 해당 기물을 잡을 수 있다.")
        @Test
        void test4() {
            // given
            Position start = PositionFixture.C2;
            Position end = PositionFixture.C3;
            Board board = BoardFixture.createBoardWithTWoOppositePiece(PositionFixture.C2, PositionFixture.C3,
                    King.create(), Pawn.black());
            // when
            assertThat(board.pieceTypeAt(end)).isEqualTo(PieceType.PAWN);
            assertThat(board.colorAt(end)).isEqualTo(Color.BLACK);
            board.move(start, end);
            // then
            assertThat(board.pieceTypeAt(end)).isEqualTo(PieceType.KING);
            assertThat(board.colorAt(end)).isEqualTo(Color.WHITE);
        }
    }

    @Nested
    @DisplayName("나이트의 움직임")
    class KnightTest {
        private final Position start = PositionFixture.C2;
        private final Position end = PositionFixture.E3;
        private final Piece knight = Knight.create();

        // 나이트의 움직임
        @DisplayName("나이트는 C2에서 E3로 움직일 수 있다.")
        @Test
        void test1() {
            // given
            Board board = BoardFixture.createBoardWithOneWhitePiece(start, knight);
            // when
            board.move(start, end);
            // then
            assertThat(board.pieceTypeAt(end)).isEqualTo(PieceType.KNIGHT);
            assertThat(board.colorAt(end)).isEqualTo(Color.WHITE);
        }

        @DisplayName("시작 위치에 기물이 없을 경우 에외가 발생한다.")
        @Test
        void test11() {
            // given
            Board board = BoardFixture.createBoardWithOneWhitePiece(PositionFixture.C1, knight);
            // when
            // then
            assertThatThrownBy(() -> board.move(start, end))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 해당 위치에 기물이 없습니다.");
        }

        @DisplayName("도착할 위치에 같은 팀의 기물이 있는 경우 예외가 발생한다.")
        @Test
        void test3() {
            // given
            Board board = BoardFixture.createBoardWithTWoWhitePiece(start, end,
                    knight, knight);
            // when
            // then
            assertThatThrownBy(() -> board.move(start, end))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 같은 팀의 기물은 잡을 수 없습니다.");
        }

        @DisplayName("움직일 수 없는 위치를 입력한 경우 에외가 발생한다.")
        @Test
        void test2() {
            // given
            Board board = BoardFixture.createBoardWithOneWhitePiece(start, knight);
            // when
            // then
            assertThatThrownBy(() -> board.move(start, PositionFixture.A2))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 기물의 이동 규칙에 어긋나는 움직임입니다.");
        }

        @DisplayName("도착할 위치에 다른 팀의 기물이 있는 경우 해당 기물을 잡을 수 있다.")
        @Test
        void test4() {
            // given
            Board board = BoardFixture.createBoardWithTWoOppositePiece(start, end,
                    knight, Pawn.black());
            // when
            assertThat(board.pieceTypeAt(end)).isEqualTo(PieceType.PAWN);
            assertThat(board.colorAt(end)).isEqualTo(Color.BLACK);
            board.move(start, end);
            // then
            assertThat(board.pieceTypeAt(end)).isEqualTo(PieceType.KNIGHT);
            assertThat(board.colorAt(end)).isEqualTo(Color.WHITE);
        }
    }
}
