package chess.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.fixture.BoardFixture;
import chess.fixture.PositionFixture;
import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.piece.Queen;
import chess.piece.Rook;
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
            Board board = BoardFixture.createBoardWithTwoOppositePiece(PositionFixture.C2, PositionFixture.C3,
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
            Board board = BoardFixture.createBoardWithTwoOppositePiece(start, end,
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


    @Nested
    @DisplayName("룩의 움직임")
    class RookTest {
        private final Position start = PositionFixture.A1;
        private final Position end = PositionFixture.A8;
        private final Piece rook = Rook.create();

        @DisplayName("A1에서 A8로 움직일 수 있다.")
        @Test
        void test1() {
            // given
            Board board = BoardFixture.createBoardWithOneWhitePiece(start, rook);
            // when
            board.move(start, end);
            // then
            assertThat(board.pieceTypeAt(end)).isEqualTo(PieceType.ROOK);
            assertThat(board.colorAt(end)).isEqualTo(Color.WHITE);
        }

        @DisplayName("시작 위치에 기물이 없을 경우 에외가 발생한다.")
        @Test
        void test11() {
            // given
            Board board = BoardFixture.createBoardWithOneWhitePiece(PositionFixture.C1, rook);
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
                    rook, rook);
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
            Board board = BoardFixture.createBoardWithOneWhitePiece(start, rook);
            // when
            // then
            assertThatThrownBy(() -> board.move(start, PositionFixture.B2))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 기물의 이동 규칙에 어긋나는 움직임입니다.");
        }

        @DisplayName("도착할 위치에 다른 팀의 기물이 있는 경우 해당 기물을 잡을 수 있다.")
        @Test
        void test4() {
            // given
            Board board = BoardFixture.createBoardWithTwoOppositePiece(start, end,
                    rook, Pawn.black());
            // when
            assertThat(board.pieceTypeAt(end)).isEqualTo(PieceType.PAWN);
            assertThat(board.colorAt(end)).isEqualTo(Color.BLACK);
            board.move(start, end);
            // then
            assertThat(board.pieceTypeAt(end)).isEqualTo(PieceType.ROOK);
            assertThat(board.colorAt(end)).isEqualTo(Color.WHITE);
        }

        @DisplayName("중간 경로에 기물이 존재할 경우 예외가 발생한다.")
        @Test
        void test5() {
            // given
            Board board = BoardFixture.createBoardWithTwoOppositePiece(start, PositionFixture.A3,
                    rook, Pawn.black());
            // when
            // then
            assertThatThrownBy(() -> board.move(start, end))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 다른 기물에 의해 막혀서 이동할 수 없는 경로입니다.");
        }
    }

    @Nested
    @DisplayName("비숍의 움직임")
    class BishopTest {
        private final Position start = PositionFixture.A1;
        private final Position end = PositionFixture.H8;
        private final Piece bishop = Bishop.create();

        @DisplayName("A1에서 H8로 움직일 수 있다.")
        @Test
        void test1() {
            // given
            Board board = BoardFixture.createBoardWithOneWhitePiece(start, bishop);
            // when
            board.move(start, end);
            // then
            assertThat(board.pieceTypeAt(end)).isEqualTo(PieceType.BISHOP);
            assertThat(board.colorAt(end)).isEqualTo(Color.WHITE);
        }

        @DisplayName("시작 위치에 기물이 없을 경우 에외가 발생한다.")
        @Test
        void test11() {
            // given
            Board board = BoardFixture.createBoardWithOneWhitePiece(PositionFixture.C1, bishop);
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
                    bishop, bishop);
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
            Board board = BoardFixture.createBoardWithOneWhitePiece(start, bishop);
            // when
            // then
            assertThatThrownBy(() -> board.move(start, PositionFixture.B1))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 기물의 이동 규칙에 어긋나는 움직임입니다.");
        }

        @DisplayName("도착할 위치에 다른 팀의 기물이 있는 경우 해당 기물을 잡을 수 있다.")
        @Test
        void test4() {
            // given
            Board board = BoardFixture.createBoardWithTwoOppositePiece(start, end,
                    bishop, Pawn.black());
            // when
            assertThat(board.pieceTypeAt(end)).isEqualTo(PieceType.PAWN);
            assertThat(board.colorAt(end)).isEqualTo(Color.BLACK);
            board.move(start, end);
            // then
            assertThat(board.pieceTypeAt(end)).isEqualTo(PieceType.BISHOP);
            assertThat(board.colorAt(end)).isEqualTo(Color.WHITE);
        }

        @DisplayName("중간 경로에 기물이 존재할 경우 예외가 발생한다.")
        @Test
        void test5() {
            // given
            Board board = BoardFixture.createBoardWithTwoOppositePiece(start, PositionFixture.C3,
                    bishop, Pawn.black());
            // when
            // then
            assertThatThrownBy(() -> board.move(start, end))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 다른 기물에 의해 막혀서 이동할 수 없는 경로입니다.");
        }
    }

    @Nested
    @DisplayName("퀸의 움직임")
    class QueenTest {
        private final Position start = PositionFixture.A1;
        private final Position end1 = PositionFixture.H8;
        private final Position end2 = PositionFixture.A8;
        private final Position end3 = PositionFixture.H1;
        private final Piece queen = Queen.create();

        @DisplayName("A1에서 H8로 움직일 수 있다.")
        @Test
        void test1() {
            // given
            Board board = BoardFixture.createBoardWithOneWhitePiece(start, queen);
            // when
            board.move(start, end1);
            // then
            assertThat(board.pieceTypeAt(end1)).isEqualTo(PieceType.QUEEN);
            assertThat(board.colorAt(end1)).isEqualTo(Color.WHITE);
        }

        @DisplayName("A1에서 A8로 움직일 수 있다.")
        @Test
        void test111() {
            // given
            Board board = BoardFixture.createBoardWithOneWhitePiece(start, queen);
            // when
            board.move(start, end2);
            // then
            assertThat(board.pieceTypeAt(end2)).isEqualTo(PieceType.QUEEN);
            assertThat(board.colorAt(end2)).isEqualTo(Color.WHITE);
        }

        @DisplayName("A1에서 H1으로 움직일 수 있다.")
        @Test
        void test1111() {
            // given
            Board board = BoardFixture.createBoardWithOneWhitePiece(start, queen);
            // when
            board.move(start, end3);
            // then
            assertThat(board.pieceTypeAt(end3)).isEqualTo(PieceType.QUEEN);
            assertThat(board.colorAt(end3)).isEqualTo(Color.WHITE);
        }

        @DisplayName("시작 위치에 기물이 없을 경우 에외가 발생한다.")
        @Test
        void test11() {
            // given
            Board board = BoardFixture.createBoardWithOneWhitePiece(PositionFixture.C1, queen);
            // when
            // then
            assertThatThrownBy(() -> board.move(start, end2))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 해당 위치에 기물이 없습니다.");
        }

        @DisplayName("도착할 위치에 같은 팀의 기물이 있는 경우 예외가 발생한다.")
        @Test
        void test3() {
            // given
            Board board = BoardFixture.createBoardWithTWoWhitePiece(start, end2,
                    queen, queen);
            // when
            // then
            assertThatThrownBy(() -> board.move(start, end2))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 같은 팀의 기물은 잡을 수 없습니다.");
        }

        @DisplayName("움직일 수 없는 위치를 입력한 경우 에외가 발생한다.")
        @Test
        void test2() {
            // given
            Board board = BoardFixture.createBoardWithOneWhitePiece(start, queen);
            // when
            // then
            assertThatThrownBy(() -> board.move(start, PositionFixture.C2))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 기물의 이동 규칙에 어긋나는 움직임입니다.");
        }

        @DisplayName("도착할 위치에 다른 팀의 기물이 있는 경우 해당 기물을 잡을 수 있다.")
        @Test
        void test4() {
            // given
            Board board = BoardFixture.createBoardWithTwoOppositePiece(start, end2,
                    queen, Pawn.black());
            // when
            assertThat(board.pieceTypeAt(end2)).isEqualTo(PieceType.PAWN);
            assertThat(board.colorAt(end2)).isEqualTo(Color.BLACK);
            board.move(start, end2);
            // then
            assertThat(board.pieceTypeAt(end2)).isEqualTo(PieceType.QUEEN);
            assertThat(board.colorAt(end2)).isEqualTo(Color.WHITE);
        }

        @DisplayName("중간 경로에 기물이 존재할 경우 예외가 발생한다.")
        @Test
        void test5() {
            // given
            Board board = BoardFixture.createBoardWithTwoOppositePiece(start, PositionFixture.A4,
                    queen, Pawn.black());
            // when
            // then
            assertThatThrownBy(() -> board.move(start, end2))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 다른 기물에 의해 막혀서 이동할 수 없는 경로입니다.");
        }
    }

    @Nested
    @DisplayName("흰색 폰의 움직임")
    class WhitePawnTest {
        private final Position start = PositionFixture.C2;
        private final Position upEnd = PositionFixture.C3;
        private final Position upUpEnd = PositionFixture.C4;
        private final Position rightUpEnd = PositionFixture.D3;
        private final Position leftUpEnd = PositionFixture.B3;
        private final Piece whitePawn = Pawn.white();

        @DisplayName("C2에서 C3로 이동할 수 있다.")
        @Test
        void test1() {
            // given
            Board board = BoardFixture.createBoardWithOneWhitePiece(start, whitePawn);
            // when
            board.move(start, upEnd);
            // then
            assertThat(board.pieceTypeAt(upEnd)).isEqualTo(PieceType.PAWN);
            assertThat(board.colorAt(upEnd)).isEqualTo(Color.WHITE);
        }

        @DisplayName("C2에서 C4로 이동할 수 있다.")
        @Test
        void test2() {
            // given
            Board board = BoardFixture.createBoardWithOneWhitePiece(start, whitePawn);
            // when
            board.move(start, upUpEnd);
            // then
            assertThat(board.pieceTypeAt(upUpEnd)).isEqualTo(PieceType.PAWN);
            assertThat(board.colorAt(upUpEnd)).isEqualTo(Color.WHITE);
        }

        @DisplayName("중간에 기물이 있을 경우 두 칸 이동할 수 없다.")
        @Test
        void test22() {
            // given
            Board board = BoardFixture.createBoardWithTwoOppositePiece(start, upEnd, whitePawn, Pawn.black());
            // when
            // then
            assertThatThrownBy(() -> board.move(start, upUpEnd))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 다른 기물에 의해 막혀서 이동할 수 없는 경로입니다.");
        }

        @DisplayName("첫 이동이 아닌 경우 앞으로 2칸 이동할 수 없다.")
        @Test
        void test3() {
            // given
            Board board = BoardFixture.createBoardWithTwoOppositePiece(start, PositionFixture.A8, Pawn.white(),
                    Pawn.black());
            // when
            board.move(start, PositionFixture.C3);
            board.move(PositionFixture.A8, PositionFixture.A6);
            // then
            assertThatThrownBy(() -> board.move(PositionFixture.C3, PositionFixture.C5))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 폰은 첫 움직임에만 두 칸 이동할 수 있습니다.");
        }

        @DisplayName("대각선 위에 아무 것도 없으면 이동할 수 없다.")
        @Test
        void test44() {
            // given
            Board board = BoardFixture.createBoardWithOneWhitePiece(start, whitePawn);
            // when
            // then
            assertThatThrownBy(() -> board.move(start, rightUpEnd))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 해당 위치에 잡을 수 있는 기물이 없습니다.");
        }

        @DisplayName("대각선 위에 상대 기물이 있으면 잡을 수 있다.")
        @Test
        void test4() {
            // given
            Board board = BoardFixture.createBoardWithTwoOppositePiece(start, leftUpEnd, whitePawn, Pawn.black());
            // when
            assertThat(board.pieceTypeAt(leftUpEnd)).isEqualTo(PieceType.PAWN);
            assertThat(board.colorAt(leftUpEnd)).isEqualTo(Color.BLACK);
            board.move(start, leftUpEnd);
            // then
            assertThat(board.pieceTypeAt(leftUpEnd)).isEqualTo(PieceType.PAWN);
            assertThat(board.colorAt(leftUpEnd)).isEqualTo(Color.WHITE);
        }
    }

    @Nested
    @DisplayName("검은색 폰의 움직임")
    class BlackPawnTest {
        private final Position start = PositionFixture.C3;
        private final Position downEnd = PositionFixture.C2;
        private final Position downDownEnd = PositionFixture.C1;
        private final Position rightDownEnd = PositionFixture.D2;
        private final Position leftDownEnd = PositionFixture.B2;
        private final Piece blackPawn = Pawn.black();
    }
}
