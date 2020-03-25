package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BoardFactoryTest {
    @Test
    @DisplayName("보드 생성")
    void construct() {
        Board board = BoardFactory.createBoard();
        assertThat(board).isNotNull();
        assertThat(board.size()).isEqualTo(64);
    }

    @ParameterizedTest
    @DisplayName("초기화된 보드 생성")
    @MethodSource({"getCasesForInitializeBoard"})
    void initializeBoard(String x, String y, Piece expectedPiece) {
        //given
        Position position = new Position(x, y);
        //when
        Board board = BoardFactory.createBoard();
        //then
        assertThat(board).isNotNull();
        assertThat(board.size()).isEqualTo(64);

        //when
        Piece piece = board.get(position);
        //then
        assertThat(piece).isEqualTo(expectedPiece);
    }

    private static Stream<Arguments> getCasesForInitializeBoard() {
        return Stream.of(
                Arguments.of("a", "1", new WhitePiece(PieceType.ROOK)),
                Arguments.of("b", "1", new WhitePiece(PieceType.KNIGHT)),
                Arguments.of("c", "1", new WhitePiece(PieceType.BISHOP)),
                Arguments.of("d", "1", new WhitePiece(PieceType.QUEEN)),
                Arguments.of("e", "1", new WhitePiece(PieceType.KING)),
                Arguments.of("f", "1", new WhitePiece(PieceType.BISHOP)),
                Arguments.of("g", "1", new WhitePiece(PieceType.KNIGHT)),
                Arguments.of("h", "1", new WhitePiece(PieceType.ROOK)),

                Arguments.of("a", "2", new WhitePiece(PieceType.PAWN)),
                Arguments.of("b", "2", new WhitePiece(PieceType.PAWN)),
                Arguments.of("c", "2", new WhitePiece(PieceType.PAWN)),
                Arguments.of("d", "2", new WhitePiece(PieceType.PAWN)),
                Arguments.of("e", "2", new WhitePiece(PieceType.PAWN)),
                Arguments.of("f", "2", new WhitePiece(PieceType.PAWN)),
                Arguments.of("g", "2", new WhitePiece(PieceType.PAWN)),
                Arguments.of("h", "2", new WhitePiece(PieceType.PAWN)),

                Arguments.of("a", "3", new Blank(PieceType.BLANK)),
                Arguments.of("b", "3", new Blank(PieceType.BLANK)),
                Arguments.of("c", "3", new Blank(PieceType.BLANK)),
                Arguments.of("d", "3", new Blank(PieceType.BLANK)),
                Arguments.of("e", "3", new Blank(PieceType.BLANK)),
                Arguments.of("f", "3", new Blank(PieceType.BLANK)),
                Arguments.of("g", "3", new Blank(PieceType.BLANK)),
                Arguments.of("h", "3", new Blank(PieceType.BLANK)),

                Arguments.of("a", "4", new Blank(PieceType.BLANK)),
                Arguments.of("b", "4", new Blank(PieceType.BLANK)),
                Arguments.of("c", "4", new Blank(PieceType.BLANK)),
                Arguments.of("d", "4", new Blank(PieceType.BLANK)),
                Arguments.of("e", "4", new Blank(PieceType.BLANK)),
                Arguments.of("f", "4", new Blank(PieceType.BLANK)),
                Arguments.of("g", "4", new Blank(PieceType.BLANK)),
                Arguments.of("h", "4", new Blank(PieceType.BLANK)),

                Arguments.of("a", "5", new Blank(PieceType.BLANK)),
                Arguments.of("b", "5", new Blank(PieceType.BLANK)),
                Arguments.of("c", "5", new Blank(PieceType.BLANK)),
                Arguments.of("d", "5", new Blank(PieceType.BLANK)),
                Arguments.of("e", "5", new Blank(PieceType.BLANK)),
                Arguments.of("f", "5", new Blank(PieceType.BLANK)),
                Arguments.of("g", "5", new Blank(PieceType.BLANK)),
                Arguments.of("h", "5", new Blank(PieceType.BLANK)),

                Arguments.of("a", "6", new Blank(PieceType.BLANK)),
                Arguments.of("b", "6", new Blank(PieceType.BLANK)),
                Arguments.of("c", "6", new Blank(PieceType.BLANK)),
                Arguments.of("d", "6", new Blank(PieceType.BLANK)),
                Arguments.of("e", "6", new Blank(PieceType.BLANK)),
                Arguments.of("f", "6", new Blank(PieceType.BLANK)),
                Arguments.of("g", "6", new Blank(PieceType.BLANK)),
                Arguments.of("h", "6", new Blank(PieceType.BLANK)),

                Arguments.of("a", "7", new BlackPiece(PieceType.PAWN)),
                Arguments.of("b", "7", new BlackPiece(PieceType.PAWN)),
                Arguments.of("c", "7", new BlackPiece(PieceType.PAWN)),
                Arguments.of("d", "7", new BlackPiece(PieceType.PAWN)),
                Arguments.of("e", "7", new BlackPiece(PieceType.PAWN)),
                Arguments.of("f", "7", new BlackPiece(PieceType.PAWN)),
                Arguments.of("g", "7", new BlackPiece(PieceType.PAWN)),
                Arguments.of("h", "7", new BlackPiece(PieceType.PAWN)),

                Arguments.of("a", "8", new BlackPiece(PieceType.ROOK)),
                Arguments.of("b", "8", new BlackPiece(PieceType.KNIGHT)),
                Arguments.of("c", "8", new BlackPiece(PieceType.BISHOP)),
                Arguments.of("d", "8", new BlackPiece(PieceType.QUEEN)),
                Arguments.of("e", "8", new BlackPiece(PieceType.KING)),
                Arguments.of("f", "8", new BlackPiece(PieceType.BISHOP)),
                Arguments.of("g", "8", new BlackPiece(PieceType.KNIGHT)),
                Arguments.of("h", "8", new BlackPiece(PieceType.ROOK))
        );
    }
}
