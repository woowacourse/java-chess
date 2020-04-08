package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.Position;
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
                initializedPieces.add(Blank.create(new Position(col, row)));
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
        Board board = BoardFactory.createEmptyBoard();
        int index = Board.getBoardIndexByStringPosition(position);
        assertThat(board.findPieceBy(index)).isEqualTo(expectedPiece);
    }

    private static Stream<Arguments> getCasesForFindingPieceByStringPosition() {
        return Stream.of(
                Arguments.of("a1", Rook.createWhite(new Position(1, 1))),
                Arguments.of("b1", Knight.createWhite(new Position(2, 1))),
                Arguments.of("c1", Bishop.createWhite(new Position(3, 1))),
                Arguments.of("d1", Queen.createWhite(new Position(4, 1))),
                Arguments.of("e1", King.createWhite(new Position(5, 1))),
                Arguments.of("f1", Bishop.createWhite(new Position(6, 1))),
                Arguments.of("g1", Knight.createWhite(new Position(7, 1))),
                Arguments.of("h1", Rook.createWhite(new Position(8, 1))),

                Arguments.of("a2", Pawn.createWhite(new Position(1, 2))),
                Arguments.of("b2", Pawn.createWhite(new Position(2, 2))),
                Arguments.of("c2", Pawn.createWhite(new Position(3, 2))),
                Arguments.of("d2", Pawn.createWhite(new Position(4, 2))),
                Arguments.of("e2", Pawn.createWhite(new Position(5, 2))),
                Arguments.of("f2", Pawn.createWhite(new Position(6, 2))),
                Arguments.of("g2", Pawn.createWhite(new Position(7, 2))),
                Arguments.of("h2", Pawn.createWhite(new Position(8, 2))),

                Arguments.of("a3", Blank.create(new Position(1, 3))),
                Arguments.of("b3", Blank.create(new Position(2, 3))),
                Arguments.of("c3", Blank.create(new Position(3, 3))),
                Arguments.of("d3", Blank.create(new Position(4, 3))),
                Arguments.of("e3", Blank.create(new Position(5, 3))),
                Arguments.of("f3", Blank.create(new Position(6, 3))),
                Arguments.of("g3", Blank.create(new Position(7, 3))),
                Arguments.of("h3", Blank.create(new Position(8, 3))),

                Arguments.of("a4", Blank.create(new Position(1, 4))),
                Arguments.of("b4", Blank.create(new Position(2, 4))),
                Arguments.of("c4", Blank.create(new Position(3, 4))),
                Arguments.of("d4", Blank.create(new Position(4, 4))),
                Arguments.of("e4", Blank.create(new Position(5, 4))),
                Arguments.of("f4", Blank.create(new Position(6, 4))),
                Arguments.of("g4", Blank.create(new Position(7, 4))),
                Arguments.of("h4", Blank.create(new Position(8, 4))),

                Arguments.of("a5", Blank.create(new Position(1, 5))),
                Arguments.of("b5", Blank.create(new Position(2, 5))),
                Arguments.of("c5", Blank.create(new Position(3, 5))),
                Arguments.of("d5", Blank.create(new Position(4, 5))),
                Arguments.of("e5", Blank.create(new Position(5, 5))),
                Arguments.of("f5", Blank.create(new Position(6, 5))),
                Arguments.of("g5", Blank.create(new Position(7, 5))),
                Arguments.of("h5", Blank.create(new Position(8, 5))),

                Arguments.of("a6", Blank.create(new Position(1, 6))),
                Arguments.of("b6", Blank.create(new Position(2, 6))),
                Arguments.of("c6", Blank.create(new Position(3, 6))),
                Arguments.of("d6", Blank.create(new Position(4, 6))),
                Arguments.of("e6", Blank.create(new Position(5, 6))),
                Arguments.of("f6", Blank.create(new Position(6, 6))),
                Arguments.of("g6", Blank.create(new Position(7, 6))),
                Arguments.of("h6", Blank.create(new Position(8, 6))),

                Arguments.of("a7", Pawn.createBlack(new Position(1, 7))),
                Arguments.of("b7", Pawn.createBlack(new Position(2, 7))),
                Arguments.of("c7", Pawn.createBlack(new Position(3, 7))),
                Arguments.of("d7", Pawn.createBlack(new Position(4, 7))),
                Arguments.of("e7", Pawn.createBlack(new Position(5, 7))),
                Arguments.of("f7", Pawn.createBlack(new Position(6, 7))),
                Arguments.of("g7", Pawn.createBlack(new Position(7, 7))),
                Arguments.of("h7", Pawn.createBlack(new Position(8, 7))),

                Arguments.of("a8", Rook.createBlack(new Position(1, 8))),
                Arguments.of("b8", Knight.createBlack(new Position(2, 8))),
                Arguments.of("c8", Bishop.createBlack(new Position(3, 8))),
                Arguments.of("d8", Queen.createBlack(new Position(4, 8))),
                Arguments.of("e8", King.createBlack(new Position(5, 8))),
                Arguments.of("f8", Bishop.createBlack(new Position(6, 8))),
                Arguments.of("g8", Knight.createBlack(new Position(7, 8))),
                Arguments.of("h8", Rook.createBlack(new Position(8, 8)))
        );
    }

    @DisplayName("피스 위치 변경")
    @ParameterizedTest
    @MethodSource("getCasesForPieceMove")
    void movePiece(String from, String to, PieceType fromPieceType, PieceType toPieceType) {
        Board board = BoardFactory.createEmptyBoard();

        board.move(from, to);
        assertThat(board.findPieceBy(Position.of(from)).getPieceType()).isEqualTo(toPieceType);
        assertThat(board.findPieceBy(Position.of(to)).getPieceType()).isEqualTo(fromPieceType);
    }

    private static Stream<Arguments> getCasesForPieceMove() {
        return Stream.of(
                Arguments.of("a2", "a4", PieceType.WHITE_PAWN, PieceType.BLANK),
                Arguments.of("b1", "c3", PieceType.KNIGHT, PieceType.BLANK)
        );
    }
}
