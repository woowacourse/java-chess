package chess.domain.board;

import chess.domain.direction.Route;
import chess.domain.direction.core.Square;
import chess.domain.direction.core.TargetStatus;
import chess.domain.piece.*;
import chess.domain.piece.core.Piece;
import chess.domain.piece.core.Type;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static chess.domain.piece.core.Team.*;
import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {
    @Test
    void 보드_초기화_테스트() {
        assertThat(Board.drawBoard()).isEqualTo(testInitBoard());
    }

    @Test
    void 보드에서_말_선택하기() {
        Board board = Board.drawBoard();
        assertThat(board.getPiece(Square.of(0, 6))).isEqualTo(Type.PAWN.create(WHITE));
        assertThat(board.getPiece(Square.of(0, 1))).isEqualTo(Type.PAWN.create(BLACK));
    }

    @Test
    void 목적지_확인_테스트() {
        Board board = Board.drawBoard();
        assertThat(board.getTargetStatus(Square.of(0, 6), Square.of(0, 4))).isEqualTo(TargetStatus.EMPTY);
    }

    @Test
    void 보드_움직이기_테스트() {
        Board board = Board.drawBoard();
        Piece piece = board.getPiece(Square.of(6, 7));
        Route route = piece.getRoute(Square.of(6, 7), Square.of(5, 5));
        Board changedBoard = board.changeBoard(route);
        assertThat(board == changedBoard).isFalse();
        assertThat(changedBoard).isEqualTo(testChangedBoard());
    }

    private Board testInitBoard() {
        Map<Square, Piece> testBoard = Maps.newHashMap();
        testBoard.put(Square.of(0, 0), new Rook(BLACK));
        testBoard.put(Square.of(1, 0), new Knight(BLACK));
        testBoard.put(Square.of(2, 0), new Bishop(BLACK));
        testBoard.put(Square.of(3, 0), new Queen(BLACK));
        testBoard.put(Square.of(4, 0), new King(BLACK));
        testBoard.put(Square.of(5, 0), new Bishop(BLACK));
        testBoard.put(Square.of(6, 0), new Knight(BLACK));
        testBoard.put(Square.of(7, 0), new Rook(BLACK));

        testBoard.put(Square.of(0, 1), new Pawn(BLACK));
        testBoard.put(Square.of(1, 1), new Pawn(BLACK));
        testBoard.put(Square.of(2, 1), new Pawn(BLACK));
        testBoard.put(Square.of(3, 1), new Pawn(BLACK));
        testBoard.put(Square.of(4, 1), new Pawn(BLACK));
        testBoard.put(Square.of(5, 1), new Pawn(BLACK));
        testBoard.put(Square.of(6, 1), new Pawn(BLACK));
        testBoard.put(Square.of(7, 1), new Pawn(BLACK));

        testBoard.put(Square.of(0, 7), new Rook(WHITE));
        testBoard.put(Square.of(1, 7), new Knight(WHITE));
        testBoard.put(Square.of(2, 7), new Bishop(WHITE));
        testBoard.put(Square.of(3, 7), new Queen(WHITE));
        testBoard.put(Square.of(4, 7), new King(WHITE));
        testBoard.put(Square.of(5, 7), new Bishop(WHITE));
        testBoard.put(Square.of(6, 7), new Knight(WHITE));
        testBoard.put(Square.of(7, 7), new Rook(WHITE));

        testBoard.put(Square.of(0, 6), new Pawn(WHITE));
        testBoard.put(Square.of(1, 6), new Pawn(WHITE));
        testBoard.put(Square.of(2, 6), new Pawn(WHITE));
        testBoard.put(Square.of(3, 6), new Pawn(WHITE));
        testBoard.put(Square.of(4, 6), new Pawn(WHITE));
        testBoard.put(Square.of(5, 6), new Pawn(WHITE));
        testBoard.put(Square.of(6, 6), new Pawn(WHITE));
        testBoard.put(Square.of(7, 6), new Pawn(WHITE));

        return Board.drawBoard(testBoard, WHITE);
    }

    private Board testChangedBoard() {
        Map<Square, Piece> testBoard = Maps.newHashMap();
        testBoard.put(Square.of(0, 0), new Rook(BLACK));
        testBoard.put(Square.of(1, 0), new Knight(BLACK));
        testBoard.put(Square.of(2, 0), new Bishop(BLACK));
        testBoard.put(Square.of(3, 0), new Queen(BLACK));
        testBoard.put(Square.of(4, 0), new King(BLACK));
        testBoard.put(Square.of(5, 0), new Bishop(BLACK));
        testBoard.put(Square.of(6, 0), new Knight(BLACK));
        testBoard.put(Square.of(7, 0), new Rook(BLACK));

        testBoard.put(Square.of(0, 1), new Pawn(BLACK));
        testBoard.put(Square.of(1, 1), new Pawn(BLACK));
        testBoard.put(Square.of(2, 1), new Pawn(BLACK));
        testBoard.put(Square.of(3, 1), new Pawn(BLACK));
        testBoard.put(Square.of(4, 1), new Pawn(BLACK));
        testBoard.put(Square.of(5, 1), new Pawn(BLACK));
        testBoard.put(Square.of(6, 1), new Pawn(BLACK));
        testBoard.put(Square.of(7, 1), new Pawn(BLACK));

        testBoard.put(Square.of(0, 7), new Rook(WHITE));
        testBoard.put(Square.of(1, 7), new Knight(WHITE));
        testBoard.put(Square.of(2, 7), new Bishop(WHITE));
        testBoard.put(Square.of(3, 7), new Queen(WHITE));
        testBoard.put(Square.of(4, 7), new King(WHITE));
        testBoard.put(Square.of(5, 7), new Bishop(WHITE));
        testBoard.put(Square.of(5, 5), new Knight(WHITE));
        testBoard.put(Square.of(7, 7), new Rook(WHITE));

        testBoard.put(Square.of(0, 6), new Pawn(WHITE));
        testBoard.put(Square.of(1, 6), new Pawn(WHITE));
        testBoard.put(Square.of(2, 6), new Pawn(WHITE));
        testBoard.put(Square.of(3, 6), new Pawn(WHITE));
        testBoard.put(Square.of(4, 6), new Pawn(WHITE));
        testBoard.put(Square.of(5, 6), new Pawn(WHITE));
        testBoard.put(Square.of(6, 6), new Pawn(WHITE));
        testBoard.put(Square.of(7, 6), new Pawn(WHITE));

        return Board.drawBoard(testBoard, BLACK);
    }
}