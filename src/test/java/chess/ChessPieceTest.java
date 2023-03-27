package chess;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.piece.Shape;
import chess.piece.Side;
import chess.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ChessPieceTest {

    private static final String OUT_OF_CHESS_BOUND_ERROR = "[ERROR] 해당 위치로 움직일 수 없습니다.";

    @Test
    @DisplayName("각 기물들이 올바르게 생성된다.")
    void shouldSuccessGeneratePieces() {
        assertSoftly(softly -> {
            assertDoesNotThrow(() -> new King(Side.BLACK));
            assertDoesNotThrow(() -> new King(Side.WHITE));
            assertDoesNotThrow(() -> new Queen(Side.BLACK));
            assertDoesNotThrow(() -> new Queen(Side.WHITE));
            assertDoesNotThrow(() -> new Knight(Side.BLACK));
            assertDoesNotThrow(() -> new Knight(Side.WHITE));
            assertDoesNotThrow(() -> new Bishop(Side.BLACK));
            assertDoesNotThrow(() -> new Bishop(Side.WHITE));
            assertDoesNotThrow(() -> new Rook(Side.BLACK));
            assertDoesNotThrow(() -> new Rook(Side.WHITE));
            assertDoesNotThrow(() -> new Pawn(Side.BLACK));
            assertDoesNotThrow(() -> new Pawn(Side.WHITE));
        });
    }

    @ParameterizedTest
    @DisplayName("폰 이동 성공 케이스")
    @CsvSource(value = {"a2,a3,WHITE", "b2,b4,WHITE", "h2,h4,WHITE", "a7,a5,BLACK", "h7,h6,BLACK"})
    void shouldSuccessMovePawn(String source, String target, Side side) {
        ChessBoard chessBoard = ChessBoard.generateChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        ChessController chessController = new ChessController(chessBoard, chessGame);
        assertDoesNotThrow(
                () -> chessController.moveChessPieceByCondition(List.of("move", source, target), side));
    }

    @ParameterizedTest
    @DisplayName("폰 이동 실패 케이스")
    @CsvSource(value = {"a2,a5,WHITE", "a7,a4,BLACK"})
    void shouldFailMovePawn(String source, String target, Side side) {
        ChessBoard chessBoard = ChessBoard.generateChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        ChessController chessController = new ChessController(chessBoard, chessGame);
        assertThatThrownBy(
                        () -> chessController.moveChessPieceByCondition(List.of("move", source, target), side))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(OUT_OF_CHESS_BOUND_ERROR);
    }


    @ParameterizedTest
    @DisplayName("룩 이동 성공 케이스")
    @CsvSource(value = {"a1,a2,WHITE", "a1,a3,WHITE", "a1,a4,WHITE", "a1,a5,WHITE", "a1,a6,WHITE"})
    void shouldSuccessMoveRook(String source, String target, Side side) {
        ChessBoard chessBoard = ChessBoard.generateChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        chessGame.removeChessPiece(Position.of("a2"));
        ChessController chessController = new ChessController(chessBoard, chessGame);
        assertDoesNotThrow(
                () -> chessController.moveChessPieceByCondition(List.of("move", source, target), side));
    }

    @ParameterizedTest
    @DisplayName("룩 이동 실패 케이스")
    @CsvSource(value = {"a1,a7,WHITE", "a1,a8,WHITE", "a8,a7,BLACK", "a8,a6,BLACK"})
    void shouldFailMoveRook(String source, String target, Side side) {
        ChessBoard chessBoard = ChessBoard.generateChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        ChessController chessController = new ChessController(chessBoard, chessGame);
        assertThatThrownBy(
                        () -> chessController.moveChessPieceByCondition(List.of("move", source, target), side))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(OUT_OF_CHESS_BOUND_ERROR);
    }

    @ParameterizedTest
    @DisplayName("비숍 이동 성공 케이스")
    @CsvSource(value = {"c1,d2,WHITE", "c1,e3,WHITE", "c1,f4,WHITE", "c1,g5,WHITE"})
    void shouldSuccessMoveBishop(String source, String target, Side side) {
        ChessBoard chessBoard = ChessBoard.generateChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        chessGame.removeChessPiece(Position.of("d2"));
        ChessController chessController = new ChessController(chessBoard, chessGame);
        assertDoesNotThrow(
                () -> chessController.moveChessPieceByCondition(List.of("move", source, target), side));
    }

    @ParameterizedTest
    @DisplayName("비숍 이동 실패 케이스")
    @CsvSource(value = {"c1,e3,WHITE", "c1,f4,WHITE", "c1,g5,WHITE", "e8,d7,BLACK"})
    void shouldFailMoveBishop(String source, String target, Side side) {
        ChessBoard chessBoard = ChessBoard.generateChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        ChessController chessController = new ChessController(chessBoard, chessGame);
        assertThatThrownBy(
                        () -> chessController.moveChessPieceByCondition(List.of("move", source, target), side))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(OUT_OF_CHESS_BOUND_ERROR);
    }

    @ParameterizedTest
    @DisplayName("나이트 이동 성공 케이스")
    @CsvSource(value = {"b1,a3,WHITE", "b1,c3,WHITE", "g1,f3,WHITE", "g1,h3,WHITE",
            "b8,a6,BLACK", "b8,c6,BLACK", "g8,f6,BLACK", "g8,h6,BLACK"})
    void shouldSuccessMoveKnight(String source, String target, Side side) {
        ChessBoard chessBoard = ChessBoard.generateChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        ChessController chessController = new ChessController(chessBoard, chessGame);
        chessController.moveChessPieceByCondition(List.of("move", "b2", "b3"), Side.WHITE);
        chessController.moveChessPieceByCondition(List.of("move", "b7", "b6"), Side.BLACK);
        assertDoesNotThrow(
                () -> chessController.moveChessPieceByCondition(List.of("move", source, target), side));
    }

    @ParameterizedTest
    @DisplayName("나이트 이동 실패 케이스")
    @CsvSource(value = {"b1,d2,WHITE", "g1,e2,WHITE", "b8,d7,BLACK", "g8,e7,BLACK"})
    void shouldFailMoveKnight(String source, String target, Side side) {
        ChessBoard chessBoard = ChessBoard.generateChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        ChessController chessController = new ChessController(chessBoard, chessGame);
        assertThatThrownBy(
                        () -> chessController.moveChessPieceByCondition(List.of("move", source, target), side))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(OUT_OF_CHESS_BOUND_ERROR);
    }

    @ParameterizedTest
    @DisplayName("퀸 이동 성공 케이스")
    @CsvSource(value = {"d1,c2,WHITE", "d1,b3,WHITE", "d1,a4,WHITE", "d1,d2,WHITE", "d1,d3,WHITE", "d1,d4,WHITE"})
    void shouldSuccessMoveQueen(String source, String target, Side side) {
        ChessBoard chessBoard = ChessBoard.generateChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        chessGame.removeChessPiece(Position.of("d2"));
        chessGame.removeChessPiece(Position.of("c2"));
        ChessController chessController = new ChessController(chessBoard, chessGame);
        assertDoesNotThrow(
                () -> chessController.moveChessPieceByCondition(List.of("move", source, target), side));
    }

    @ParameterizedTest
    @DisplayName("퀸 이동 실패 케이스")
    @CsvSource(value = {"d1,c2,WHITE", "d1,b3,WHITE", "d1,a4,WHITE", "d1,d2,WHITE", "d1,d3,WHITE", "d1,d4,WHITE"})
    void shouldFailMoveQueen(String source, String target, Side side) {
        ChessBoard chessBoard = ChessBoard.generateChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        ChessController chessController = new ChessController(chessBoard, chessGame);
        assertThatThrownBy(
                        () -> chessController.moveChessPieceByCondition(List.of("move", source, target), side))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(OUT_OF_CHESS_BOUND_ERROR);
    }

    @ParameterizedTest
    @DisplayName("킹 이동 성공 케이스")
    @CsvSource(value = {"e1,d2,WHITE", "e1,e2,WHITE", "e1,f2,WHITE"})
    void shouldSuccessMoveKing(String source, String target, Side side) {
        ChessBoard chessBoard = ChessBoard.generateChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        chessGame.removeChessPiece(Position.of("d2"));
        chessGame.removeChessPiece(Position.of("e2"));
        chessGame.removeChessPiece(Position.of("f2"));
        ChessController chessController = new ChessController(chessBoard, chessGame);
        assertDoesNotThrow(
                () -> chessController.moveChessPieceByCondition(List.of("move", source, target), side));
    }

    @ParameterizedTest
    @DisplayName("킹 이동 실패 케이스")
    @CsvSource(value = {"e1,d2,WHITE", "e1,e2,WHITE", "e1,f2,WHITE"})
    void shouldFailMoveKing(String source, String target, Side side) {
        ChessBoard chessBoard = ChessBoard.generateChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        ChessController chessController = new ChessController(chessBoard, chessGame);
        assertThatThrownBy(
                        () -> chessController.moveChessPieceByCondition(List.of("move", source, target), side))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(OUT_OF_CHESS_BOUND_ERROR);
    }
}
