package chess.board;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChessBoardTest {

    @Nested
    class 흰색_팀의_ {

        @ParameterizedTest(name = "폰의 초기 위치는 {0}{1}이다.")
        @CsvSource(value = {"A:2", "B:2", "C:2", "D:2", "E:2", "F:2", "G:2", "H:2"}, delimiter = ':')
        void 폰의_초기_위치는_(File file, String rank) {
            //given
            Position position = new Position(file, rank);
            ChessBoard chessBoard = ChessBoard.createBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getPiecePosition();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Pawn.class);
        }

        @ParameterizedTest(name = "룩의 초기 위치는 {0}{1}이다.")
        @CsvSource(value = {"A:1", "H:1"}, delimiter = ':')
        void 룩의_초기_위치는_(File file, String rank) {
            //given
            Position position = new Position(file, rank);
            ChessBoard chessBoard = ChessBoard.createBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getPiecePosition();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Rook.class);
        }

        @ParameterizedTest(name = "나이트의 초기 위치는 {0}{1}이다.")
        @CsvSource(value = {"B:1", "G:1"}, delimiter = ':')
        void 나이트의_초기_위치는_(File file, String rank) {
            //given
            Position position = new Position(file, rank);
            ChessBoard chessBoard = ChessBoard.createBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getPiecePosition();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Knight.class);
        }

        @ParameterizedTest(name = "비숍의 초기 위치는 {0}{1}이다.")
        @CsvSource(value = {"C:1", "F:1"}, delimiter = ':')
        void 비숍의_초기_위치는_(File file, String rank) {
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
            Position position = new Position(File.D, "1");
            ChessBoard chessBoard = ChessBoard.createBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getPiecePosition();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Queen.class);
        }

        @Test
        void 킹의_초기_위치는_E1_이다() {
            //given
            Position position = new Position(File.E, "1");
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
        @CsvSource(value = {"A:7", "B:7", "C:7", "D:7", "E:7", "F:7", "G:7", "H:7"}, delimiter = ':')
        void 폰의_초기_위치는_(File file, String rank) {
            //given
            Position position = new Position(file, rank);
            ChessBoard chessBoard = ChessBoard.createBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getPiecePosition();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Pawn.class);
        }

        @ParameterizedTest(name = "룩의 초기 위치는 {0}{1}이다.")
        @CsvSource(value = {"A:8", "H:8"}, delimiter = ':')
        void 룩의_초기_위치는_(File file, String rank) {
            //given
            Position position = new Position(file, rank);
            ChessBoard chessBoard = ChessBoard.createBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getPiecePosition();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Rook.class);
        }

        @ParameterizedTest(name = "나이트의 초기 위치는 {0}{1}이다.")
        @CsvSource(value = {"B:8", "G:8"}, delimiter = ':')
        void 나이트의_초기_위치는_(File file, String rank) {
            //given
            Position position = new Position(file, rank);
            ChessBoard chessBoard = ChessBoard.createBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getPiecePosition();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Knight.class);
        }

        @ParameterizedTest(name = "비숍의 초기 위치는 {0}{1}이다.")
        @CsvSource(value = {"C:8", "F:8"}, delimiter = ':')
        void 비숍의_초기_위치는_(File file, String rank) {
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
            Position position = new Position(File.D, "8");
            ChessBoard chessBoard = ChessBoard.createBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getPiecePosition();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(Queen.class);
        }

        @Test
        void 킹의_초기_위치는_E1_이다() {
            //given
            Position position = new Position(File.E, "8");
            ChessBoard chessBoard = ChessBoard.createBoard();

            //when & then
            Map<Position, Piece> piecePosition = chessBoard.getPiecePosition();
            assertThat(piecePosition.get(position))
                    .isInstanceOf(King.class);
        }
    }
}
