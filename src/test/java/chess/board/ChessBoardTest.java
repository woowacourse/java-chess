package chess.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.fixture.FixturePosition;
import chess.piece.Bishop;
import chess.piece.BlackPawn;
import chess.piece.EmptyPiece;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.piece.Team;
import chess.piece.WhitePawn;

class ChessBoardTest {

    private Map<Position, Piece> board;

    @BeforeEach
    void setUp() {
        board = new HashMap<>();
        for (final File file : File.values()) {
            for (final Rank rank : Rank.values()) {
                board.put(new Position(file, rank), new EmptyPiece());
            }
        }
    }

    @Nested
    class 룩은_이동경로에_ {

        @Test
        void 같은_팀의_말이면_예외() {
            //given
            Position from = FixturePosition.A1;
            Position to = FixturePosition.F1;

            Position other = FixturePosition.D1;

            board.put(from, new Rook(Team.WHITE));
            board.put(other, new Rook(Team.BLACK));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(board);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }

        @Test
        void 끝_지점이_다른_팀의_말이면_갈_수_있다() {
            //given
            Position from = FixturePosition.A1;
            Position to = FixturePosition.F1;

            board.put(from, new Rook(Team.WHITE));
            board.put(to, new Rook(Team.BLACK));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(board);

            //when & then
            assertDoesNotThrow(() -> chessBoard.movePiece(from, to));
        }
    }

    @Nested
    class 비숍은_이동경로에 {

        @Test
        void 말이_있으면_예외() {
            //given
            Position from = FixturePosition.A1;
            Position to = FixturePosition.H8;

            Position other = FixturePosition.D4;

            board.put(from, new Bishop(Team.WHITE));
            board.put(other, new Rook(Team.WHITE));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(board);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }

        @Test
        void 말이_있으면_예외2() {
            //given
            Position from = FixturePosition.H1;
            Position to = FixturePosition.A8;

            Position other = FixturePosition.E4;

            board.put(from, new Bishop(Team.WHITE));
            board.put(other, new Rook(Team.WHITE));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(board);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }

        @Test
        void 말이_있으면_예외3() {
            //given
            Position from = FixturePosition.D5;
            Position to = FixturePosition.A2;

            Position other = FixturePosition.B3;

            board.put(from, new Bishop(Team.WHITE));
            board.put(other, new Rook(Team.WHITE));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(board);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }

        @Test
        void 말이_있으면_예외4() {
            //given
            Position from = FixturePosition.D5;
            Position to = FixturePosition.H1;

            Position other = FixturePosition.F3;

            board.put(from, new Bishop(Team.WHITE));
            board.put(other, new Rook(Team.WHITE));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(board);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }

        @Test
        void 끝_지점이_다른_팀의_말이면_갈_수_있다() {
            //given
            Position from = FixturePosition.A1;
            Position to = FixturePosition.F6;

            board.put(from, new Bishop(Team.WHITE));
            board.put(to, new Rook(Team.BLACK));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(board);

            //when & then
            assertDoesNotThrow(() -> chessBoard.movePiece(from, to));
        }
    }

    @Nested
    class 퀸은_이동경로에 {

        @Test
        void 말이_있으면_예외() {
            //given
            Position from = FixturePosition.A1;
            Position to = FixturePosition.H8;

            Position other = FixturePosition.D4;

            board.put(from, new Queen(Team.WHITE));
            board.put(other, new Rook(Team.WHITE));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(board);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }

        @Test
        void 말이_있으면_예외2() {
            //given
            Position from = FixturePosition.H1;
            Position to = FixturePosition.A8;

            Position other = FixturePosition.E4;

            board.put(from, new Queen(Team.WHITE));
            board.put(other, new Rook(Team.WHITE));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(board);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }

        @Test
        void 말이_있으면_예외3() {
            //given
            Position from = FixturePosition.D5;
            Position to = FixturePosition.A2;

            Position other = FixturePosition.B3;

            board.put(from, new Queen(Team.WHITE));
            board.put(other, new Rook(Team.WHITE));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(board);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }

        @Test
        void 말이_있으면_예외4() {
            //given
            Position from = FixturePosition.D5;
            Position to = FixturePosition.H1;

            Position other = FixturePosition.F3;

            board.put(from, new Queen(Team.WHITE));
            board.put(other, new Rook(Team.WHITE));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(board);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }

        @Test
        void 말이_있으면_예외5() {
            //given
            Position from = FixturePosition.A1;
            Position to = FixturePosition.F1;

            Position other = FixturePosition.D1;

            board.put(from, new Queen(Team.WHITE));
            board.put(other, new Rook(Team.BLACK));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(board);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }

        @Test
        void 끝_지점이_다른_팀의_말이면_갈_수_있다() {
            //given
            Position from = FixturePosition.A1;
            Position to = FixturePosition.F1;

            board.put(from, new Queen(Team.WHITE));
            board.put(to, new Rook(Team.BLACK));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(board);

            //when & then
            assertDoesNotThrow(() -> chessBoard.movePiece(from, to));
        }
    }

    @Nested
    class 킹은_ {

        @Test
        void 목적지에_같은_팀의_말이_있으면_예외() {
            //given
            Position from = FixturePosition.A1;
            Position to = FixturePosition.A2;

            board.put(from, new King(Team.WHITE));
            board.put(to, new Rook(Team.WHITE));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(board);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("목적지에 같은 색의 말이 존재하여 이동할 수 없습니다.");
        }

        @Test
        void 끝_지점이_다른_팀의_말이면_갈_수_있다() {
            //given
            Position from = FixturePosition.A1;
            Position to = FixturePosition.A2;

            board.put(from, new King(Team.WHITE));
            board.put(to, new Rook(Team.BLACK));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(board);

            //when & then
            assertDoesNotThrow(() -> chessBoard.movePiece(from, to));
        }
    }

    @Nested
    class 나이트는_ {

        @Test
        void 목적지에_같은_팀의_말이_있으면_예외() {
            //given
            Position from = FixturePosition.A1;
            Position to = FixturePosition.C2;

            board.put(from, new Knight(Team.WHITE));
            board.put(to, new Rook(Team.WHITE));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(board);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("목적지에 같은 색의 말이 존재하여 이동할 수 없습니다.");
        }

        @Test
        void 끝_지점이_다른_팀의_말이면_갈_수_있다() {
            //given
            Position from = FixturePosition.A1;
            Position to = FixturePosition.C2;

            board.put(from, new Knight(Team.WHITE));
            board.put(to, new Rook(Team.BLACK));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(board);

            //when & then
            assertDoesNotThrow(() -> chessBoard.movePiece(from, to));
        }
    }

    @Nested
    class 폰은_이동경로에 {

        @Test
        void 같은_팀의_말이_있으면_예외() {
            //given
            Position from = FixturePosition.B2;
            Position to = FixturePosition.B4;

            Position other = FixturePosition.B3;

            board.put(from, new WhitePawn());
            board.put(other, new Rook(Team.WHITE));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(board);

            //when & then
            assertThatThrownBy(() -> chessBoard.movePiece(from, to))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }

        @Test
        void 끝_지점이_다른_팀의_말이면_갈_수_있다() {
            //given
            Position from = FixturePosition.D4;
            Position to = FixturePosition.E5;

            board.put(from, new WhitePawn());
            board.put(to, new Rook(Team.BLACK));
            ChessBoard chessBoard = ChessBoard.createBoardByRule(board);

            //when & then
            assertDoesNotThrow(() -> chessBoard.movePiece(from, to));
        }
    }

    @Test
    void 체스판은_64개의_칸을_가진다() {
        //given
        ChessBoard chessBoard = new ChessBoard();
        //when & then
        assertThat(chessBoard.getBoard().size())
                .isEqualTo(64);
    }

    @Nested
    class 흰색_팀의_ {

        @ParameterizedTest(name = "폰의 초기 위치는 {0}{1}이다.")
        @CsvSource(value = {"A:TWO", "B:TWO", "C:TWO", "D:TWO", "E:TWO", "F:TWO", "G:TWO", "H:TWO"}, delimiter = ':')
        void 폰의_초기_위치는_(File file, Rank rank) {
            //given
            Position position = new Position(file, rank);
            ChessBoard chessBoard = new ChessBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getBoard();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Pawn.class);
        }

        @ParameterizedTest(name = "룩의 초기 위치는 {0}{1}이다.")
        @CsvSource(value = {"A:ONE", "H:ONE"}, delimiter = ':')
        void 룩의_초기_위치는_(File file, Rank rank) {
            //given
            Position position = new Position(file, rank);
            ChessBoard chessBoard = new ChessBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getBoard();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Rook.class);
        }

        @ParameterizedTest(name = "나이트의 초기 위치는 {0}{1}이다.")
        @CsvSource(value = {"B:ONE", "G:ONE"}, delimiter = ':')
        void 나이트의_초기_위치는_(File file, Rank rank) {
            //given
            Position position = new Position(file, rank);
            ChessBoard chessBoard = new ChessBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getBoard();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Knight.class);
        }

        @ParameterizedTest(name = "비숍의 초기 위치는 {0}{1}이다.")
        @CsvSource(value = {"C:ONE", "F:ONE"}, delimiter = ':')
        void 비숍의_초기_위치는_(File file, Rank rank) {
            //given
            Position position = new Position(file, rank);
            ChessBoard chessBoard = new ChessBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getBoard();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Bishop.class);
        }

        @Test
        void 퀸의_초기_위치는_D1_이다() {
            //given
            Position position = FixturePosition.D1;
            ChessBoard chessBoard = new ChessBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getBoard();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Queen.class);
        }

        @Test
        void 킹의_초기_위치는_E1_이다() {
            //given
            Position position = FixturePosition.E1;
            ChessBoard chessBoard = new ChessBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getBoard();
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
            ChessBoard chessBoard = new ChessBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getBoard();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Pawn.class);
        }

        @ParameterizedTest(name = "룩의 초기 위치는 {0}{1}이다.")
        @CsvSource(value = {"A:EIGHT", "H:EIGHT"}, delimiter = ':')
        void 룩의_초기_위치는_(File file, Rank rank) {
            //given
            Position position = new Position(file, rank);
            ChessBoard chessBoard = new ChessBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getBoard();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Rook.class);
        }

        @ParameterizedTest(name = "나이트의 초기 위치는 {0}{1}이다.")
        @CsvSource(value = {"B:EIGHT", "G:EIGHT"}, delimiter = ':')
        void 나이트의_초기_위치는_(File file, Rank rank) {
            //given
            Position position = new Position(file, rank);
            ChessBoard chessBoard = new ChessBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getBoard();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Knight.class);
        }

        @ParameterizedTest(name = "비숍의 초기 위치는 {0}{1}이다.")
        @CsvSource(value = {"C:EIGHT", "F:EIGHT"}, delimiter = ':')
        void 비숍의_초기_위치는_(File file, Rank rank) {
            //given
            Position position = new Position(file, rank);
            ChessBoard chessBoard = new ChessBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getBoard();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Bishop.class);
        }

        @Test
        void 퀸의_초기_위치는_D1_이다() {
            //given
            Position position = FixturePosition.D8;
            ChessBoard chessBoard = new ChessBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getBoard();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Queen.class);
        }

        @Test
        void 킹의_초기_위치는_E8_이다() {
            //given
            Position position = FixturePosition.E8;
            ChessBoard chessBoard = new ChessBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getBoard();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(King.class);
        }
    }

    // TODO: Nested로 묶기
    @Test
    void isGameOver메서드는_해당되는_팀에_왕이_없으면_true를_반환한다() {
        // given
        board.put(new Position(File.B, Rank.FOUR), new King(Team.BLACK));
        ChessBoard chessBoard = ChessBoard.createBoardByRule(board);

        // when & then
        assertThat(chessBoard.isKingKilled(Team.WHITE)).isTrue();
    }

    @Test
    void isGameOver메서드는_해당되는_팀에_왕이_있으면_true를_반환한다() {
        // given
        board.put(new Position(File.B, Rank.FOUR), new King(Team.BLACK));
        board.put(new Position(File.C, Rank.FOUR), new King(Team.WHITE));
        ChessBoard chessBoard = ChessBoard.createBoardByRule(board);

        // when & then
        assertThat(chessBoard.isKingKilled(Team.WHITE)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"BLACK:18", "WHITE:17"}, delimiter = ':')
    void calculateScore메서드는_각_팀에_해당하는_점수를_반환한다(Team team, double score) {
        // given
        board.put(new Position(File.A, Rank.FOUR), new Queen(Team.BLACK));
        board.put(new Position(File.B, Rank.FOUR), new Queen(Team.WHITE));
        board.put(new Position(File.A, Rank.FIVE), new Bishop(Team.BLACK));
        board.put(new Position(File.B, Rank.FIVE), new Bishop(Team.WHITE));
        board.put(new Position(File.A, Rank.SIX), new Rook(Team.BLACK));
        board.put(new Position(File.B, Rank.SIX), new Rook(Team.WHITE));
        board.put(new Position(File.A, Rank.SEVEN), new BlackPawn());

        ChessBoard chessBoard = ChessBoard.createBoardByRule(board);

        // when & then
        assertThat(chessBoard.calculateScore(team)).isEqualTo(score);
    }

    @Test
    void calculateScore메서드는_세로_줄에_폰이_두개_이상이면_점수의_절반으로_계산한다() {
        // given
        board.put(new Position(File.A, Rank.FOUR), new Queen(Team.BLACK));
        board.put(new Position(File.B, Rank.FOUR), new Queen(Team.WHITE));
        board.put(new Position(File.A, Rank.FIVE), new Bishop(Team.BLACK));
        board.put(new Position(File.B, Rank.FIVE), new Bishop(Team.WHITE));
        board.put(new Position(File.A, Rank.SIX), new Rook(Team.BLACK));
        board.put(new Position(File.B, Rank.SIX), new Rook(Team.WHITE));
        board.put(new Position(File.A, Rank.SEVEN), new BlackPawn());
        board.put(new Position(File.A, Rank.THREE), new BlackPawn());

        ChessBoard chessBoard = ChessBoard.createBoardByRule(board);

        // when & then
        assertThat(chessBoard.calculateScore(Team.BLACK)).isEqualTo(18);
    }
}
