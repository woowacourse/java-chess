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
                Arguments.of("h", "1", new WhitePiece(PieceType.ROOK))
        );
    }
}
