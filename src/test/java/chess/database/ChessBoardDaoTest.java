package chess.database;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.ChessBoard;
import chess.ChessGame;
import chess.piece.Shape;
import chess.piece.Side;
import org.junit.jupiter.api.Test;

class ChessBoardDaoTest {
    @Test
    void addPieceInBoardTable() {
        ChessBoardDao chessBoardDao = new ChessBoardDao();
        assertDoesNotThrow(() -> chessBoardDao.addBoard(1, 2, 2, Shape.PAWN.name(), Side.WHITE.name()));
    }

    @Test
    void findPieceInBoardTable() {
        ChessBoard chessBoard = ChessBoard.generateChessBoard(0);
        ChessGame chessGame = new ChessGame(chessBoard, 0);
        ChessBoardDao chessBoardDao = new ChessBoardDao();
        assertAll(
                () -> assertThat(chessBoardDao.findPieceType(chessGame.getGameIdx(), 1, 1)).isEqualTo("ROOK"),
                () -> assertThat(chessBoardDao.findPieceType(chessGame.getGameIdx(), 2, 1)).isEqualTo("KNIGHT"),
                () -> assertThat(chessBoardDao.findPieceType(chessGame.getGameIdx(), 3, 1)).isEqualTo("BISHOP"),
                () -> assertThat(chessBoardDao.findPieceType(chessGame.getGameIdx(), 4, 1)).isEqualTo("QUEEN"),
                () -> assertThat(chessBoardDao.findPieceType(chessGame.getGameIdx(), 5, 1)).isEqualTo("KING"),
                () -> assertThat(chessBoardDao.findPieceType(chessGame.getGameIdx(), 6, 1)).isEqualTo("BISHOP"),
                () -> assertThat(chessBoardDao.findPieceType(chessGame.getGameIdx(), 7, 1)).isEqualTo("KNIGHT"),
                () -> assertThat(chessBoardDao.findPieceType(chessGame.getGameIdx(), 8, 1)).isEqualTo("ROOK"),
                () -> assertThat(chessBoardDao.findPieceType(chessGame.getGameIdx(), 1, 2)).isEqualTo("PAWN"),

                () -> assertThat(chessBoardDao.findPieceType(chessGame.getGameIdx(), 1, 8)).isEqualTo("ROOK"),
                () -> assertThat(chessBoardDao.findPieceType(chessGame.getGameIdx(), 2, 8)).isEqualTo("KNIGHT"),
                () -> assertThat(chessBoardDao.findPieceType(chessGame.getGameIdx(), 3, 8)).isEqualTo("BISHOP"),
                () -> assertThat(chessBoardDao.findPieceType(chessGame.getGameIdx(), 4, 8)).isEqualTo("QUEEN"),
                () -> assertThat(chessBoardDao.findPieceType(chessGame.getGameIdx(), 5, 8)).isEqualTo("KING"),
                () -> assertThat(chessBoardDao.findPieceType(chessGame.getGameIdx(), 6, 8)).isEqualTo("BISHOP"),
                () -> assertThat(chessBoardDao.findPieceType(chessGame.getGameIdx(), 7, 8)).isEqualTo("KNIGHT"),
                () -> assertThat(chessBoardDao.findPieceType(chessGame.getGameIdx(), 8, 1)).isEqualTo("ROOK"),
                () -> assertThat(chessBoardDao.findPieceType(chessGame.getGameIdx(), 1, 2)).isEqualTo("PAWN")
        );
    }

    @Test
    void movePieceInBoardTable() {
        ChessBoard chessBoard = ChessBoard.generateChessBoard(0);
        ChessGame chessGame = new ChessGame(chessBoard, 0);
        ChessBoardDao chessBoardDao = new ChessBoardDao();
        assertDoesNotThrow(() -> chessBoardDao.movePiece(chessGame.getGameIdx(), 2, 4, Shape.PAWN.name(), Side.WHITE.name()));
        assertThat(chessBoardDao.findPieceType(chessGame.getGameIdx(), 2, 4)).isEqualTo(Shape.PAWN.name());
        assertDoesNotThrow(() -> chessBoardDao.movePiece(chessGame.getGameIdx(), 2, 4, Shape.EMPTY.name(), Side.EMPTY.name()));
    }
}
