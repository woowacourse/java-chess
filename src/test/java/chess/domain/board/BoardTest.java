package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.Position;
import chess.domain.strategy.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BoardTest {
    @DisplayName("보드 생성 실패")
    @Test
    void constructFail() {
        List<Piece> initializedPieces = new ArrayList<>();
        for (int row = 8; row >= 1; row--) {
            for (int col = 1; col <= 7; col++) {
                initializedPieces.add(new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(col, row)));
            }
        }
        assertThatThrownBy(() -> {
            Board board = new Board(initializedPieces);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보드가 제대로 생성되지 못했습니다.");
    }

    @DisplayName("문자열 좌표로 해당 좌표인 piece 색인")
    @ParameterizedTest
    @MethodSource("getCasesForFindingPieceByStringPosition")
    void findPiece(String position, Piece expectedPiece) {
        Board board = BoardFactory.createBoard();
        int index = board.getBoardIndexByStringPosition(position);
        assertThat(board.findPieceBy(index)).isEqualTo(expectedPiece);
    }

    private static Stream<Arguments> getCasesForFindingPieceByStringPosition() {
        return Stream.of(
                Arguments.of("a1", new Rook(new RookMoveStrategy(), 'r', Team.WHITE, new Position(1, 1))),
                Arguments.of("b1", new Knight(new KnightMoveStrategy(), 'n', Team.WHITE, new Position(2, 1))),
                Arguments.of("c1", new Bishop(new BishopMoveStrategy(), 'b', Team.WHITE, new Position(3, 1))),
                Arguments.of("d1", new Queen(new QueenMoveStrategy(), 'q', Team.WHITE, new Position(4, 1))),
                Arguments.of("e1", new King(new KingMoveStrategy(), 'k', Team.WHITE, new Position(5, 1))),
                Arguments.of("f1", new Bishop(new BishopMoveStrategy(), 'b', Team.WHITE, new Position(6, 1))),
                Arguments.of("g1", new Knight(new KnightMoveStrategy(), 'n', Team.WHITE, new Position(7, 1))),
                Arguments.of("h1", new Rook(new RookMoveStrategy(), 'r', Team.WHITE, new Position(8, 1))),

                Arguments.of("a2", new WhitePawn(new WhitePawnMoveStrategy(), 'p', Team.WHITE, new Position(1, 2))),
                Arguments.of("b2", new WhitePawn(new WhitePawnMoveStrategy(), 'p', Team.WHITE, new Position(2, 2))),
                Arguments.of("c2", new WhitePawn(new WhitePawnMoveStrategy(), 'p', Team.WHITE, new Position(3, 2))),
                Arguments.of("d2", new WhitePawn(new WhitePawnMoveStrategy(), 'p', Team.WHITE, new Position(4, 2))),
                Arguments.of("e2", new WhitePawn(new WhitePawnMoveStrategy(), 'p', Team.WHITE, new Position(5, 2))),
                Arguments.of("f2", new WhitePawn(new WhitePawnMoveStrategy(), 'p', Team.WHITE, new Position(6, 2))),
                Arguments.of("g2", new WhitePawn(new WhitePawnMoveStrategy(), 'p', Team.WHITE, new Position(7, 2))),
                Arguments.of("h2", new WhitePawn(new WhitePawnMoveStrategy(), 'p', Team.WHITE, new Position(8, 2))),

                Arguments.of("a3", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(1, 3))),
                Arguments.of("b3", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(2, 3))),
                Arguments.of("c3", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(3, 3))),
                Arguments.of("d3", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(4, 3))),
                Arguments.of("e3", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(5, 3))),
                Arguments.of("f3", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(6, 3))),
                Arguments.of("g3", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(7, 3))),
                Arguments.of("h3", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(8, 3))),

                Arguments.of("a4", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(1, 4))),
                Arguments.of("b4", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(2, 4))),
                Arguments.of("c4", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(3, 4))),
                Arguments.of("d4", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(4, 4))),
                Arguments.of("e4", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(5, 4))),
                Arguments.of("f4", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(6, 4))),
                Arguments.of("g4", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(7, 4))),
                Arguments.of("h4", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(8, 4))),

                Arguments.of("a5", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(1, 5))),
                Arguments.of("b5", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(2, 5))),
                Arguments.of("c5", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(3, 5))),
                Arguments.of("d5", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(4, 5))),
                Arguments.of("e5", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(5, 5))),
                Arguments.of("f5", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(6, 5))),
                Arguments.of("g5", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(7, 5))),
                Arguments.of("h5", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(8, 5))),

                Arguments.of("a6", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(1, 6))),
                Arguments.of("b6", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(2, 6))),
                Arguments.of("c6", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(3, 6))),
                Arguments.of("d6", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(4, 6))),
                Arguments.of("e6", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(5, 6))),
                Arguments.of("f6", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(6, 6))),
                Arguments.of("g6", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(7, 6))),
                Arguments.of("h6", new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(8, 6))),

                Arguments.of("a7", new BlackPawn(new BlackPawnMoveStrategy(), 'P', Team.BLACK, new Position(1, 7))),
                Arguments.of("b7", new BlackPawn(new BlackPawnMoveStrategy(), 'P', Team.BLACK, new Position(2, 7))),
                Arguments.of("c7", new BlackPawn(new BlackPawnMoveStrategy(), 'P', Team.BLACK, new Position(3, 7))),
                Arguments.of("d7", new BlackPawn(new BlackPawnMoveStrategy(), 'P', Team.BLACK, new Position(4, 7))),
                Arguments.of("e7", new BlackPawn(new BlackPawnMoveStrategy(), 'P', Team.BLACK, new Position(5, 7))),
                Arguments.of("f7", new BlackPawn(new BlackPawnMoveStrategy(), 'P', Team.BLACK, new Position(6, 7))),
                Arguments.of("g7", new BlackPawn(new BlackPawnMoveStrategy(), 'P', Team.BLACK, new Position(7, 7))),
                Arguments.of("h7", new BlackPawn(new BlackPawnMoveStrategy(), 'P', Team.BLACK, new Position(8, 7))),

                Arguments.of("a8", new Rook(new RookMoveStrategy(), 'R', Team.BLACK, new Position(1, 8))),
                Arguments.of("b8", new Knight(new KnightMoveStrategy(), 'N', Team.BLACK, new Position(2, 8))),
                Arguments.of("c8", new Bishop(new BishopMoveStrategy(), 'B', Team.BLACK, new Position(3, 8))),
                Arguments.of("d8", new Queen(new QueenMoveStrategy(), 'Q', Team.BLACK, new Position(4, 8))),
                Arguments.of("e8", new King(new KingMoveStrategy(), 'K', Team.BLACK, new Position(5, 8))),
                Arguments.of("f8", new Bishop(new BishopMoveStrategy(), 'B', Team.BLACK, new Position(6, 8))),
                Arguments.of("g8", new Knight(new KnightMoveStrategy(), 'N', Team.BLACK, new Position(7, 8))),
                Arguments.of("h8", new Rook(new RookMoveStrategy(), 'R', Team.BLACK, new Position(8, 8)))
        );
    }

    @DisplayName("피스 위치 변경")
    @ParameterizedTest
    @MethodSource("getCasesForPieceMove")
    void movePiece(String from, String to, Object fromPieceType, Object toPieceType) {
        Board board = BoardFactory.createBoard();

        Piece fromPiece = board.findPieceBy(new Position(from));
        Piece toPiece = board.findPieceBy(new Position(to));
        Board movedBoard = fromPiece.move(board, toPiece, Team.WHITE);

        assertThat(movedBoard.findPieceBy(movedBoard.getBoardIndexByStringPosition(from)).getClass()).isEqualTo(toPieceType);
        assertThat(movedBoard.findPieceBy(movedBoard.getBoardIndexByStringPosition(to)).getClass()).isEqualTo(fromPieceType);
    }

    private static Stream<Arguments> getCasesForPieceMove() {
        return Stream.of(
                Arguments.of("a2", "a4", WhitePawn.class, Blank.class),
                Arguments.of("b1", "c3", Knight.class, Blank.class)
        );
    }
}
