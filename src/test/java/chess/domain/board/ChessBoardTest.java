package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import domain.board.ChessBoard;
import domain.board.File;
import domain.board.Rank;
import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Piece;
import domain.piece.score.Score;
import domain.piece.type.Empty;
import domain.piece.type.Pawn;
import domain.piece.type.restricted.King;
import domain.piece.type.restricted.Knight;
import domain.piece.type.unrestricted.Bishop;
import domain.piece.type.unrestricted.Queen;
import domain.piece.type.unrestricted.Rook;
import dto.BoardDto;

class ChessBoardTest {

    @Test
    @DisplayName("체스판을 생성한다.")
    void createChessBoard() {
        assertDoesNotThrow(() -> new ChessBoard(new HashMap<>()));
    }


    @Test
    @DisplayName("좌표를 입력받아 해당 square를 반환한다.")
    void toSquare() {
        ChessBoard chessBoard = new ChessBoard(new HashMap<>());

        Square square = chessBoard.toSquare(1, 1);

        assertThat(square).isEqualTo(Square.of(File.B, Rank.TWO));
    }

    @Nested
    @DisplayName("기물이 정상적으로 놓여지는지 확인한다.")
    class InitializeTest {
        ChessBoard chessBoard;

        @BeforeEach
        void setUp() {
            chessBoard = new ChessBoard(new HashMap<>());
            chessBoard.initialize();
        }

        @Test
        @DisplayName("폰이 제대로 놓여있는지 확인한다.")
        void pawnTest() {
            Piece piece = chessBoard.getBoard().get(Square.of(File.H, Rank.TWO));

            assertThat(piece).isEqualTo(new Pawn(Camp.WHITE));
        }

        @Test
        @DisplayName("나이트가 제대로 놓여있는지 확인한다.")
        void knightTest() {
            Piece piece = chessBoard.getBoard().get(Square.of(File.B, Rank.ONE));

            assertThat(piece).isEqualTo(new Knight(Camp.WHITE));
        }

        @Test
        @DisplayName("룩이 제대로 놓여있는지 확인한다.")
        void rookTest() {
            Piece piece = chessBoard.getBoard().get(Square.of(File.A, Rank.ONE));

            assertThat(piece).isEqualTo(new Rook(Camp.WHITE));
        }

        @Test
        @DisplayName("비숍이 제대로 놓여있는지 확인한다.")
        void bishopTest() {
            Piece piece = chessBoard.getBoard().get(Square.of(File.C, Rank.ONE));

            assertThat(piece).isEqualTo(new Bishop(Camp.WHITE));
        }

        @Test
        @DisplayName("퀸이 제대로 놓여있는지 확인한다.")
        void queenTest() {
            Piece piece = chessBoard.getBoard().get(Square.of(File.D, Rank.ONE));

            assertThat(piece).isEqualTo(new Queen(Camp.WHITE));
        }

        @Test
        @DisplayName("킹이 제대로 놓여있는지 확인한다.")
        void kingTest() {
            Piece piece = chessBoard.getBoard().get(Square.of(File.E, Rank.ONE));

            assertThat(piece).isEqualTo(new King(Camp.WHITE));
        }
    }

    @Test
    @DisplayName("기물을 음직인다.")
    void moveTest() {
        ChessBoard chessBoard = new ChessBoard(new HashMap<>());
        chessBoard.initialize();
        Square currentSquare = Square.of(File.A, Rank.TWO);
        Square targetSquare = Square.of(File.A, Rank.FOUR);
        Piece piece = chessBoard.getBoard().get(currentSquare);
        chessBoard.move(currentSquare, targetSquare);
        assertThat(chessBoard.getBoard().get(targetSquare)).isEqualTo(piece);
    }

    @Test
    @DisplayName("board에 인자로 받은 캠프의 킹이 없으면 true를 반환한다.")
    void isCapturedKing() {
        ChessBoard chessBoard = new ChessBoard(new HashMap<>());

        boolean result = chessBoard.isCapturedKing(Camp.WHITE);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("board에 인자로 받은 캠프의 킹이 없으면 true를 반환한다.")
    void isCapturedKingFalse() {
        ChessBoard chessBoard = new ChessBoard(new HashMap<>());
        chessBoard.initialize();

        boolean result = chessBoard.isCapturedKing(Camp.WHITE);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("같은 file에 있는 같은 Camp의 Pawn의 갯수를 반환한다.")
    void countPawnSameRank() {
        HashMap<Square, Piece> map = new HashMap<>();
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                map.put(Square.of(file, rank), Empty.getInstance());
            }
        }
        map.put(Square.of(1, 2), new Pawn(Camp.WHITE));
        map.put(Square.of(1, 3), new Pawn(Camp.WHITE));
        map.put(Square.of(1, 4), new Pawn(Camp.WHITE));
        map.put(Square.of(1, 5), new Pawn(Camp.BLACK));
        map.put(Square.of(1, 1), new Pawn(Camp.BLACK));

        ChessBoard chessBoard = new ChessBoard(map);
        int result1 = chessBoard.countPawnInAllColumns(Camp.WHITE);
        int result2 = chessBoard.countPawnInAllColumns(Camp.BLACK);

        assertAll(() -> assertThat(result1).isEqualTo(3),
                () -> assertThat(result2).isEqualTo(2));
    }

    @Test
    @DisplayName("Camp를 인자로 전달받아 같은 Camp의 기물들의 점수 총합을 반환한다.")
    void calculateFinalScore() {
        HashMap<Square, Piece> map = new HashMap<>();
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                map.put(Square.of(file, rank), Empty.getInstance());
            }
        }

        map.put(Square.of(0, 0), new Pawn(Camp.WHITE));
        map.put(Square.of(0, 1), new Pawn(Camp.WHITE));
        map.put(Square.of(0, 2), new Pawn(Camp.WHITE));
        map.put(Square.of(1, 1), new Knight(Camp.WHITE));
        map.put(Square.of(2, 0), new Rook(Camp.WHITE));
        map.put(Square.of(2, 1), new Rook(Camp.WHITE));
        map.put(Square.of(3, 0), new Bishop(Camp.WHITE));
        map.put(Square.of(4, 0), new Queen(Camp.WHITE));
        ChessBoard chessBoard = new ChessBoard(map);

        Score result = chessBoard.calculateFinalScore(Camp.WHITE);

        assertThat(result.getScore()).isEqualTo(26);
    }

    @Test
    @DisplayName("boardDto를 통해 기물을 배치한다.")
    void setUpPieces() {
        HashMap<Square, Piece> map = new HashMap<>();
        ChessBoard chessBoard = new ChessBoard(map);
        Square square = Square.of(1, 1);
        Piece piece = new Pawn(Camp.WHITE);
        BoardDto boardDto = BoardDto.of(square, piece);

        chessBoard.setUpPieces(boardDto);

        Piece result = map.get(square);
        assertThat(result).isEqualTo(piece);
    }
}
