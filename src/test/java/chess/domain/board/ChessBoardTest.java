package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.exception.PieceCannotMoveException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static chess.fixture.PieceFixture.BISHOP_WHITE;
import static chess.fixture.PieceFixture.KING_WHITE;
import static chess.fixture.PieceFixture.KNIGHT_WHITE;
import static chess.fixture.PieceFixture.PAWN_WHITE;
import static chess.fixture.PieceFixture.QUEEN_WHITE;
import static chess.fixture.PieceFixture.ROOK_BLACK;
import static chess.fixture.PieceFixture.ROOK_WHITE;
import static chess.fixture.PositionFixture.A1;
import static chess.fixture.PositionFixture.A2;
import static chess.fixture.PositionFixture.A8;
import static chess.fixture.PositionFixture.B2;
import static chess.fixture.PositionFixture.B3;
import static chess.fixture.PositionFixture.B4;
import static chess.fixture.PositionFixture.C2;
import static chess.fixture.PositionFixture.D1;
import static chess.fixture.PositionFixture.D4;
import static chess.fixture.PositionFixture.D5;
import static chess.fixture.PositionFixture.D8;
import static chess.fixture.PositionFixture.E1;
import static chess.fixture.PositionFixture.E4;
import static chess.fixture.PositionFixture.E5;
import static chess.fixture.PositionFixture.E8;
import static chess.fixture.PositionFixture.F1;
import static chess.fixture.PositionFixture.F2;
import static chess.fixture.PositionFixture.F3;
import static chess.fixture.PositionFixture.F6;
import static chess.fixture.PositionFixture.H1;
import static chess.fixture.PositionFixture.H8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ChessBoardTest {

    private Map<Position, Piece> piecePosition = new HashMap<>();

    @BeforeEach
    void setUp() {
        this.piecePosition = PieceFactory.createEmptyPiece();
    }

    @Test
    void 체스판은_64개의_칸을_가진다() {
        //given
        ChessBoard chessBoard = ChessBoard.createBoard();
        //when & then
        assertThat(chessBoard.getPiecePosition().size())
                .isEqualTo(64);
    }

    @Nested
    class 룩은_ {

        @Test
        void 이동할_수_없는_경로로_움직이면_예외() {
            //given
            Position from = A1;
            Position to = F2;

            piecePosition.put(from, ROOK_WHITE);

            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(PieceCannotMoveException.class)
                    .hasMessage("ROOK이 움직일 수 없는 경로입니다.");
        }

        @Test
        void 이동경로에_같은_팀의_말이면_예외() {
            //given
            Position from = A1;
            Position to = F1;

            piecePosition.put(from, ROOK_WHITE);
            piecePosition.put(D1, ROOK_BLACK);
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동하려는 경로에 말이 존재합니다.");
        }

        @Test
        void 끝_지점이_다른_팀의_말이면_갈_수_있다() {
            //given
            Position from = A1;
            Position to = F1;

            piecePosition.put(from, ROOK_WHITE);
            piecePosition.put(to, ROOK_BLACK);
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertDoesNotThrow(() -> chessBoard.movePiece(from, to));
        }
    }

    @Nested
    class 비숍은_ {

        @Test
        void 이동할_수_없는_경로로_움직이면_예외() {
            //given
            Position from = A1;
            Position to = F2;

            piecePosition.put(from, BISHOP_WHITE);

            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(PieceCannotMoveException.class)
                    .hasMessage("BISHOP이 움직일 수 없는 경로입니다.");
        }

        @Test
        void 이동경로에_말이_있으면_예외() {
            //given
            Position from = A1;
            Position to = H8;

            piecePosition.put(from, BISHOP_WHITE);
            piecePosition.put(D4, ROOK_WHITE);
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동하려는 경로에 말이 존재합니다.");
        }

        @Test
        void 이동경로에_말이_있으면_예외2() {
            //given
            Position from = H1;
            Position to = A8;

            piecePosition.put(from, BISHOP_WHITE);
            piecePosition.put(E4, ROOK_WHITE);
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동하려는 경로에 말이 존재합니다.");
        }

        @Test
        void 이동경로에_말이_있으면_예외3() {
            //given
            Position from = D5;
            Position to = A2;

            piecePosition.put(from, BISHOP_WHITE);
            piecePosition.put(B3, ROOK_WHITE);
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동하려는 경로에 말이 존재합니다.");
        }

        @Test
        void 이동경로에_말이_있으면_예외4() {
            //given
            Position from = D5;
            Position to = H1;

            piecePosition.put(from, BISHOP_WHITE);
            piecePosition.put(F3, ROOK_WHITE);
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동하려는 경로에 말이 존재합니다.");
        }

        @Test
        void 끝_지점이_다른_팀의_말이면_갈_수_있다() {
            //given
            Position from = A1;
            Position to = F6;

            piecePosition.put(from, BISHOP_WHITE);
            piecePosition.put(to, ROOK_BLACK);
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertDoesNotThrow(() -> chessBoard.movePiece(from, to));
        }
    }

    @Nested
    class 퀸은_ {

        @Test
        void 이동할_수_없는_경로로_움직이면_예외() {
            //given
            Position from = A1;
            Position to = F2;

            piecePosition.put(from, QUEEN_WHITE);

            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(PieceCannotMoveException.class)
                    .hasMessage("QUEEN이 움직일 수 없는 경로입니다.");
        }

        @Test
        void 말이_있으면_예외() {
            //given
            Position from = A1;
            Position to = H8;

            piecePosition.put(from, QUEEN_WHITE);
            piecePosition.put(D4, ROOK_WHITE);
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동하려는 경로에 말이 존재합니다.");
        }

        @Test
        void 말이_있으면_예외2() {
            //given
            Position from = H1;
            Position to = A8;

            piecePosition.put(from, QUEEN_WHITE);
            piecePosition.put(E4, ROOK_WHITE);
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동하려는 경로에 말이 존재합니다.");
        }

        @Test
        void 말이_있으면_예외3() {
            //given
            Position from = D5;
            Position to = A2;

            piecePosition.put(from, QUEEN_WHITE);
            piecePosition.put(B3, ROOK_WHITE);
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동하려는 경로에 말이 존재합니다.");
        }

        @Test
        void 말이_있으면_예외4() {
            //given
            Position from = D5;
            Position to = H1;

            piecePosition.put(from, QUEEN_WHITE);
            piecePosition.put(F3, ROOK_WHITE);
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동하려는 경로에 말이 존재합니다.");
        }

        @Test
        void 말이_있으면_예외5() {
            //given
            Position from = A1;
            Position to = F1;

            piecePosition.put(from, QUEEN_WHITE);
            piecePosition.put(D1, ROOK_BLACK);
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동하려는 경로에 말이 존재합니다.");
        }

        @Test
        void 끝_지점이_다른_팀의_말이면_갈_수_있다() {
            //given
            Position from = A1;
            Position to = F1;

            piecePosition.put(from, QUEEN_WHITE);
            piecePosition.put(to, ROOK_BLACK);
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertDoesNotThrow(() -> chessBoard.movePiece(from, to));
        }
    }

    @Nested
    class 킹은_ {

        @Test
        void 이동할_수_없는_경로로_움직이면_예외() {
            //given
            Position from = A1;
            Position to = F2;

            piecePosition.put(from, KING_WHITE);

            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(PieceCannotMoveException.class)
                    .hasMessage("KING이 움직일 수 없는 경로입니다.");
        }

        @Test
        void 목적지에_같은_팀의_말이_있으면_예외() {
            //given
            Position from = A1;
            Position to = A2;

            piecePosition.put(from, KING_WHITE);
            piecePosition.put(to, ROOK_WHITE);
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("도착지에 동일한 팀의 말이 존재합니다");
        }

        @Test
        void 끝_지점이_다른_팀의_말이면_갈_수_있다() {
            //given
            Position from = A1;
            Position to = A2;

            piecePosition.put(from, KING_WHITE);
            piecePosition.put(to, ROOK_BLACK);
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertDoesNotThrow(() -> chessBoard.movePiece(from, to));
        }
    }

    @Nested
    class 나이트는_ {

        @Test
        void 이동할_수_없는_경로로_움직이면_예외() {
            //given
            Position from = A1;
            Position to = F2;

            piecePosition.put(from, KNIGHT_WHITE);

            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(PieceCannotMoveException.class)
                    .hasMessage("KNIGHT이 움직일 수 없는 경로입니다.");
        }

        @Test
        void 목적지에_같은_팀의_말이_있으면_예외() {
            //given
            Position from = A1;
            Position to = C2;

            piecePosition.put(from, KNIGHT_WHITE);
            piecePosition.put(to, ROOK_WHITE);
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("도착지에 동일한 팀의 말이 존재합니다");
        }

        @Test
        void 끝_지점이_다른_팀의_말이면_갈_수_있다() {
            //given
            Position from = A1;
            Position to = C2;

            piecePosition.put(from, KNIGHT_WHITE);
            piecePosition.put(to, ROOK_BLACK);
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertDoesNotThrow(() -> chessBoard.movePiece(from, to));
        }
    }

    @Nested
    class 폰은_이동경로에 {

        @Test
        void 이동할_수_없는_경로로_움직이면_예외() {
            //given
            Position from = A1;
            Position to = F2;

            piecePosition.put(from, PAWN_WHITE);

            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(PieceCannotMoveException.class)
                    .hasMessage("PAWN이 움직일 수 없는 경로입니다.");
        }

        @Test
        void 같은_팀의_말이_있으면_예외() {
            //given
            Position from = B2;
            Position to = B4;

            Position other = B3;

            piecePosition.put(from, PAWN_WHITE);
            piecePosition.put(other, ROOK_WHITE);
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동하려는 경로에 말이 존재합니다.");
        }

        @Test
        void 끝_지점이_다른_팀의_말이면_갈_수_있다() {
            //given
            Position from = D4;
            Position to = E5;

            piecePosition.put(from, PAWN_WHITE);
            piecePosition.put(to, ROOK_BLACK);
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertDoesNotThrow(() -> chessBoard.movePiece(from, to));
        }
    }

    @Nested
    class 흰색_팀의_ {

        @ParameterizedTest(name = "폰의 초기 위치는 {0}{1}이다.")
        @CsvSource(value = {"A:TWO", "B:TWO", "C:TWO", "D:TWO", "E:TWO", "F:TWO", "G:TWO", "H:TWO"}, delimiter = ':')
        void 폰의_초기_위치는_(File file, Rank rank) {
            //given
            Position position = Position.of(file, rank);
            ChessBoard chessBoard = ChessBoard.createBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getPiecePosition();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Pawn.class);
        }

        @ParameterizedTest(name = "룩의 초기 위치는 {0}{1}이다.")
        @CsvSource(value = {"A:ONE", "H:ONE"}, delimiter = ':')
        void 룩의_초기_위치는_(File file, Rank rank) {
            //given
            Position position = Position.of(file, rank);
            ChessBoard chessBoard = ChessBoard.createBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getPiecePosition();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Rook.class);
        }

        @ParameterizedTest(name = "나이트의 초기 위치는 {0}{1}이다.")
        @CsvSource(value = {"B:ONE", "G:ONE"}, delimiter = ':')
        void 나이트의_초기_위치는_(File file, Rank rank) {
            //given
            Position position = Position.of(file, rank);
            ChessBoard chessBoard = ChessBoard.createBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getPiecePosition();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Knight.class);
        }

        @ParameterizedTest(name = "비숍의 초기 위치는 {0}{1}이다.")
        @CsvSource(value = {"C:ONE", "F:ONE"}, delimiter = ':')
        void 비숍의_초기_위치는_(File file, Rank rank) {
            //given
            Position position = Position.of(file, rank);
            ChessBoard chessBoard = ChessBoard.createBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getPiecePosition();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Bishop.class);
        }

        @Test
        void 퀸의_초기_위치는_D1_이다() {
            //given
            ChessBoard chessBoard = ChessBoard.createBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getPiecePosition();
            assertThat(piecePosition.get(D1))
                    .isInstanceOf(Queen.class);
        }

        @Test
        void 킹의_초기_위치는_E1_이다() {
            //given
            ChessBoard chessBoard = ChessBoard.createBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getPiecePosition();
            assertThat(piecePosition.get(E1))
                    .isInstanceOf(King.class);
        }
    }

    @Nested
    class 검은색_팀의_ {

        @ParameterizedTest(name = "폰의 초기 위치는 {0}{1}이다.")
        @CsvSource(value = {"A:SEVEN", "B:SEVEN", "C:SEVEN", "D:SEVEN", "E:SEVEN", "F:SEVEN", "G:SEVEN", "H:SEVEN"},
                delimiter = ':')
        void 폰의_초기_위치는_(File file, Rank rank) {
            //given
            Position position = Position.of(file, rank);
            ChessBoard chessBoard = ChessBoard.createBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getPiecePosition();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Pawn.class);
        }

        @ParameterizedTest(name = "룩의 초기 위치는 {0}{1}이다.")
        @CsvSource(value = {"A:EIGHT", "H:EIGHT"}, delimiter = ':')
        void 룩의_초기_위치는_(File file, Rank rank) {
            //given
            Position position = Position.of(file, rank);
            ChessBoard chessBoard = ChessBoard.createBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getPiecePosition();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Rook.class);
        }

        @ParameterizedTest(name = "나이트의 초기 위치는 {0}{1}이다.")
        @CsvSource(value = {"B:EIGHT", "G:EIGHT"}, delimiter = ':')
        void 나이트의_초기_위치는_(File file, Rank rank) {
            //given
            Position position = Position.of(file, rank);
            ChessBoard chessBoard = ChessBoard.createBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getPiecePosition();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Knight.class);
        }

        @ParameterizedTest(name = "비숍의 초기 위치는 {0}{1}이다.")
        @CsvSource(value = {"C:EIGHT", "F:EIGHT"}, delimiter = ':')
        void 비숍의_초기_위치는_(File file, Rank rank) {
            //given
            Position position = Position.of(file, rank);
            ChessBoard chessBoard = ChessBoard.createBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getPiecePosition();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Bishop.class);
        }

        @Test
        void 퀸의_초기_위치는_D1_이다() {
            //given
            ChessBoard chessBoard = ChessBoard.createBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getPiecePosition();
            assertThat(piecePosition.get(D8))
                    .isInstanceOf(Queen.class);
        }

        @Test
        void 킹의_초기_위치는_E8_이다() {
            //given
            ChessBoard chessBoard = ChessBoard.createBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getPiecePosition();
            assertThat(piecePosition.get(E8))
                    .isInstanceOf(King.class);
        }
    }
}
