package chess.domain.board;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.chess.Chess;

class SymbolBoardDTOTest {

    @ParameterizedTest(name = "폰이 아닌 기물의 심볼 보드 테스트")
    @MethodSource("generateNonPawnSource")
    void initializedNonPawnSymbolTest(int file, String symbol) {

        // given
        Chess chess = Chess.createWithEmptyBoard()
                           .start();
        final int blackRow = 7;
        final int whiteRow = 0;

        // when
        final String[][] board = SymbolBoardDTO.from(chess)
                                               .getBoard();

        // then
        assertThat(board[file][blackRow]).isEqualTo(symbol.toUpperCase());
        assertThat(board[file][whiteRow]).isEqualTo(symbol);
    }

    private static Stream<Arguments> generateNonPawnSource() {
        return Stream.of(
                Arguments.of(0, "r"),
                Arguments.of(1, "n"),
                Arguments.of(2, "b"),
                Arguments.of(3, "q"),
                Arguments.of(4, "k"),
                Arguments.of(5, "b"),
                Arguments.of(6, "n"),
                Arguments.of(7, "r")
        );
    }

    @Test
    @DisplayName("폰 심볼 보드 테스트")
    void initializedPawnSymbolTest() {

        // given
        Chess chess = Chess.createWithEmptyBoard()
                           .start();
        final int blackRow = 6;
        final int whiteRow = 1;

        // when
        final String[][] board = SymbolBoardDTO.from(chess)
                                               .getBoard();

        // then
        for (int i = 0; i < 8; i++) {
            assertThat(board[i][blackRow]).isEqualTo("P");
        }

        for (int i = 0; i < 8; i++) {
            assertThat(board[i][whiteRow]).isEqualTo("p");
        }
    }

    @Test
    @DisplayName("블랭크 심볼 보드 테스트")
    void initializedBlankSymbolTest() {

        // given
        Chess chess = Chess.createWithEmptyBoard();

        // when
        final String[][] board = SymbolBoardDTO.from(chess)
                                               .getBoard();

        // then
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                assertThat(board[i][j]).isEqualTo(".");
            }
        }
    }
}
