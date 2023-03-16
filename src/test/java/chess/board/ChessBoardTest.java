package chess.board;

import chess.piece.Bishop;
import chess.piece.EmptyPiece;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.piece.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ChessBoardTest {

    private Map<Position, Piece> piecePosition;

    @BeforeEach
    void setUp() {
        piecePosition = new HashMap<>();
        for (final File file : File.values()) {
            for (final Rank rank : Rank.values()) {
                piecePosition.put(new Position(file, rank), new EmptyPiece());
            }
        }
    }

    @Nested
    class 룩은_이동경로에_ {

        @Test
        void 같은_팀의_말이면_예외() {
            //given
            Position A_ONE = new Position(File.A, Rank.ONE);
            Position F_ONE = new Position(File.F, Rank.ONE);

            piecePosition.put(A_ONE, new Rook(Team.WHITE));
            piecePosition.put(new Position(File.D, Rank.ONE), new Rook(Team.BLACK));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(A_ONE, F_ONE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }

        @Test
        void 끝_지점이_다른_팀의_말이면_갈_수_있다() {
            //given
            Position A_ONE = new Position(File.A, Rank.ONE);
            Position F_ONE = new Position(File.F, Rank.ONE);

            piecePosition.put(A_ONE, new Rook(Team.WHITE));
            piecePosition.put(F_ONE, new Rook(Team.BLACK));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertDoesNotThrow(() -> chessBoard.movePiece(A_ONE, F_ONE));
        }
    }

    @Nested
    class 비숍은_이동경로에 {

        @Test
        void 말이_있으면_예외() {
            //given
            Position A_ONE = new Position(File.A, Rank.ONE);
            Position H_EIGHT = new Position(File.H, Rank.EIGHT);

            piecePosition.put(A_ONE, new Bishop(Team.WHITE));
            piecePosition.put(new Position(File.D, Rank.FOUR), new Rook(Team.WHITE));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(A_ONE, H_EIGHT))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }

        @Test
        void 말이_있으면_예외2() {
            //given
            Position H_ONE = new Position(File.H, Rank.ONE);
            Position A_EIGHT = new Position(File.A, Rank.EIGHT);

            piecePosition.put(H_ONE, new Bishop(Team.WHITE));
            piecePosition.put(new Position(File.E, Rank.FOUR), new Rook(Team.WHITE));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(H_ONE, A_EIGHT))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }

        @Test
        void 말이_있으면_예외3() {
            //given
            Position D_FIVE = new Position(File.D, Rank.FIVE);
            Position A_TWO = new Position(File.A, Rank.TWO);

            piecePosition.put(D_FIVE, new Bishop(Team.WHITE));
            piecePosition.put(new Position(File.B, Rank.THREE), new Rook(Team.WHITE));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(D_FIVE, A_TWO))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }

        @Test
        void 말이_있으면_예외4() {
            //given
            Position D_FIVE = new Position(File.D, Rank.FIVE);
            Position H_ONE = new Position(File.H, Rank.ONE);

            piecePosition.put(D_FIVE, new Bishop(Team.WHITE));
            piecePosition.put(new Position(File.F, Rank.THREE), new Rook(Team.WHITE));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(D_FIVE, H_ONE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }

        @Test
        void 끝_지점이_다른_팀의_말이면_갈_수_있다() {
            //given
            Position A_ONE = new Position(File.A, Rank.ONE);
            Position F_ONE = new Position(File.F, Rank.ONE);

            piecePosition.put(A_ONE, new Rook(Team.WHITE));
            piecePosition.put(F_ONE, new Rook(Team.BLACK));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertDoesNotThrow(() -> chessBoard.movePiece(A_ONE, F_ONE));
        }
    }

    @Nested
    class 퀸은_이동경로에 {

        @Test
        void 말이_있으면_예외() {
            //given
            Position A_ONE = new Position(File.A, Rank.ONE);
            Position H_EIGHT = new Position(File.H, Rank.EIGHT);

            piecePosition.put(A_ONE, new Bishop(Team.WHITE));
            piecePosition.put(new Position(File.D, Rank.FOUR), new Rook(Team.WHITE));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(A_ONE, H_EIGHT))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }

        @Test
        void 말이_있으면_예외2() {
            //given
            Position H_ONE = new Position(File.H, Rank.ONE);
            Position A_EIGHT = new Position(File.A, Rank.EIGHT);

            piecePosition.put(H_ONE, new Bishop(Team.WHITE));
            piecePosition.put(new Position(File.E, Rank.FOUR), new Rook(Team.WHITE));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(H_ONE, A_EIGHT))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }

        @Test
        void 말이_있으면_예외3() {
            //given
            Position D_FIVE = new Position(File.D, Rank.FIVE);
            Position A_TWO = new Position(File.A, Rank.TWO);

            piecePosition.put(D_FIVE, new Bishop(Team.WHITE));
            piecePosition.put(new Position(File.B, Rank.THREE), new Rook(Team.WHITE));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(D_FIVE, A_TWO))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }

        @Test
        void 말이_있으면_예외4() {
            //given
            Position D_FIVE = new Position(File.D, Rank.FIVE);
            Position H_ONE = new Position(File.H, Rank.ONE);

            piecePosition.put(D_FIVE, new Bishop(Team.WHITE));
            piecePosition.put(new Position(File.F, Rank.THREE), new Rook(Team.WHITE));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(D_FIVE, H_ONE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }

        @Test
        void 말이_있으면_예외5() {
            //given
            Position A_ONE = new Position(File.A, Rank.ONE);
            Position F_ONE = new Position(File.F, Rank.ONE);

            piecePosition.put(A_ONE, new Rook(Team.WHITE));
            piecePosition.put(new Position(File.D, Rank.ONE), new Rook(Team.BLACK));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(A_ONE, F_ONE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }

        @Test
        void 끝_지점이_다른_팀의_말이면_갈_수_있다() {
            //given
            Position A_ONE = new Position(File.A, Rank.ONE);
            Position F_ONE = new Position(File.F, Rank.ONE);

            piecePosition.put(A_ONE, new Rook(Team.WHITE));
            piecePosition.put(F_ONE, new Rook(Team.BLACK));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertDoesNotThrow(() -> chessBoard.movePiece(A_ONE, F_ONE));
        }
    }

    @Nested
    class 킹은_이동경로에 {

        @Test
        void 같은_팀의_말이_있으면_예외() {
            //given
            Position A_ONE = new Position(File.A, Rank.ONE);
            Position A_TWO = new Position(File.A, Rank.TWO);

            piecePosition.put(A_ONE, new Bishop(Team.WHITE));
            piecePosition.put(new Position(File.A, Rank.TWO), new Rook(Team.WHITE));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(A_ONE, A_TWO))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }

        @Test
        void 끝_지점이_다른_팀의_말이면_갈_수_있다() {
            //given
            Position A_ONE = new Position(File.A, Rank.ONE);
            Position A_TWO = new Position(File.A, Rank.TWO);

            piecePosition.put(A_ONE, new Bishop(Team.WHITE));
            piecePosition.put(new Position(File.A, Rank.TWO), new Rook(Team.BLACK));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertDoesNotThrow(() -> chessBoard.movePiece(A_ONE, A_TWO));
        }
    }

    @Nested
    class 나이트는_이동경로에 {

        @Test
        void 같은_팀의_말이_있으면_예외() {
            //given
            Position A_ONE = new Position(File.A, Rank.ONE);
            Position C_TWO = new Position(File.C, Rank.TWO);

            piecePosition.put(A_ONE, new Bishop(Team.WHITE));
            piecePosition.put(new Position(File.C, Rank.TWO), new Rook(Team.WHITE));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(A_ONE, C_TWO))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }

        @Test
        void 끝_지점이_다른_팀의_말이면_갈_수_있다() {
            //given
            Position A_ONE = new Position(File.A, Rank.ONE);
            Position C_TWO = new Position(File.C, Rank.TWO);

            piecePosition.put(A_ONE, new Bishop(Team.WHITE));
            piecePosition.put(new Position(File.C, Rank.TWO), new Rook(Team.BLACK));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertDoesNotThrow(() -> chessBoard.movePiece(A_ONE, C_TWO));
        }
    }

    @Nested
    class 폰은_이동경로에 {

        @Test
        void 같은_팀의_말이_있으면_예외() {
            //given
            Position B_TWO = new Position(File.B, Rank.TWO);
            Position B_FOUR = new Position(File.B, Rank.FOUR);

            piecePosition.put(B_TWO, new Bishop(Team.WHITE));
            piecePosition.put(new Position(File.B, Rank.THREE), new Rook(Team.WHITE));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(B_TWO, B_FOUR))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }

        @Test
        void 끝_지점이_다른_팀의_말이면_갈_수_있다() {
            //given
            Position D_FOUR = new Position(File.D, Rank.FOUR);
            Position E_FIVE = new Position(File.E, Rank.FIVE);

            piecePosition.put(D_FOUR, new Bishop(Team.WHITE));
            piecePosition.put(new Position(File.E, Rank.FIVE), new Rook(Team.BLACK));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(piecePosition);

            //when & then
            assertDoesNotThrow(() -> chessBoard.movePiece(D_FOUR, E_FIVE));
        }
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
    class 흰색_팀의_ {

        @ParameterizedTest(name = "폰의 초기 위치는 {0}{1}이다.")
        @CsvSource(value = {"A:TWO", "B:TWO", "C:TWO", "D:TWO", "E:TWO", "F:TWO", "G:TWO", "H:TWO"}, delimiter = ':')
        void 폰의_초기_위치는_(File file, Rank rank) {
            //given
            Position position = new Position(file, rank);
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
            Position position = new Position(file, rank);
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
            Position position = new Position(file, rank);
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
            Position position = new Position(file, rank);
            ChessBoard chessBoard = ChessBoard.createBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getPiecePosition();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Bishop.class);
        }

        @Test
        void 퀸의_초기_위치는_D1_이다() {
            //given
            Position position = new Position(File.D, Rank.ONE);
            ChessBoard chessBoard = ChessBoard.createBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getPiecePosition();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Queen.class);
        }

        @Test
        void 킹의_초기_위치는_E1_이다() {
            //given
            Position position = new Position(File.E, Rank.ONE);
            ChessBoard chessBoard = ChessBoard.createBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getPiecePosition();
            assertThat(piecePosition.get(position))
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
            Position position = new Position(file, rank);
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
            Position position = new Position(file, rank);
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
            Position position = new Position(file, rank);
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
            Position position = new Position(file, rank);
            ChessBoard chessBoard = ChessBoard.createBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getPiecePosition();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Bishop.class);
        }

        @Test
        void 퀸의_초기_위치는_D1_이다() {
            //given
            Position position = new Position(File.D, Rank.EIGHT);
            ChessBoard chessBoard = ChessBoard.createBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getPiecePosition();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Queen.class);
        }

        @Test
        void 킹의_초기_위치는_E1_이다() {
            //given
            Position position = new Position(File.E, Rank.EIGHT);
            ChessBoard chessBoard = ChessBoard.createBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getPiecePosition();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(King.class);
        }
    }


}
