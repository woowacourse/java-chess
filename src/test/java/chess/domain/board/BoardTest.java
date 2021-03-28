package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceKind;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {

    @DisplayName("보드 초기화 상태 테스트 : 비숍 화이트")
    @Test
    void checkPieceAtPosition_BishopWhite() {
        InitializedBoard initializedBoard = new InitializedBoard();
        Piece piece = new Piece(PieceKind.BISHOP, PieceColor.WHITE);
        Position position = Position.of('c', 1);
        Board board = new Board(initializedBoard.board());

        Piece extractedPiece = board.pieceAtPosition(position);

        assertEquals(piece, extractedPiece);
    }

    @DisplayName("보드 초기화 상태 테스트 : 나이트 블랙")
    @Test
    void checkPieceAtPosition_KnightBlack() {
        Piece piece = new Piece(PieceKind.KNIGHT, PieceColor.BLACK);
        Position positionL = Position.of('b', 8);
        Position positionR = Position.of('g', 8);

        InitializedBoard initializedBoard = new InitializedBoard();
        Board board = new Board(initializedBoard.board());

        Piece extractedPieceL = board.pieceAtPosition(positionL);
        Piece extractedPieceR = board.pieceAtPosition(positionR);

        assertEquals(piece, extractedPieceL);
        assertEquals(piece, extractedPieceR);
    }

    @DisplayName("보드 초기화 상태 테스트 : VOID")
    @Test
    void checkPieceAtPosition_void() {
        Piece piece = new Piece(PieceKind.VOID, PieceColor.VOID);
        Position position = Position.of('f', 3);

        InitializedBoard initializedBoard = new InitializedBoard();
        Board board = new Board(initializedBoard.board());

        Piece extractedPiece = board.pieceAtPosition(position);

        assertEquals(piece, extractedPiece);
    }
}