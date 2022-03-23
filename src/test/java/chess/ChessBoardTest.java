package chess;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ChessBoardTest {

    @DisplayName("체스판은 32개의 체스 기물을 생성한다.")
    @Test
    void chess_board_create_pieces() {
        //given
        //when
        ChessBoard chessBoard = new ChessBoard();

        //then
        assertThat(chessBoard.getValue()).hasSize(32);
    }

    @MethodSource("provideRookPosition")
    @ParameterizedTest(name = "{0}에 룩이 있다")
    void check_rook_positions(ChessBoardPosition expectedPosition) {
        //given
        ChessBoard chessBoard = new ChessBoard();
        //when
        Map<ChessBoardPosition, Piece> boardValue = chessBoard.getValue();
        //then
        assertThat(boardValue.get(expectedPosition))
                .isInstanceOf(Rook.class);
    }

    @MethodSource("provideBishopPosition")
    @ParameterizedTest(name = "{0}에 비숍이 있다")
    void check_bishop_positions(ChessBoardPosition expectedPosition) {
        //given
        ChessBoard chessBoard = new ChessBoard();
        //when
        Map<ChessBoardPosition, Piece> boardValue = chessBoard.getValue();
        //then
        assertThat(boardValue.get(expectedPosition))
                .isInstanceOf(Bishop.class);
    }

    private static Stream<Arguments> provideRookPosition() {
        return Stream.of(
                Arguments.of(new ChessBoardPosition(ChessBoardColumn.A, ChessBoardRow.ONE)),
                Arguments.of(new ChessBoardPosition(ChessBoardColumn.H, ChessBoardRow.ONE)),
                Arguments.of(new ChessBoardPosition(ChessBoardColumn.A, ChessBoardRow.EIGHT)),
                Arguments.of(new ChessBoardPosition(ChessBoardColumn.H, ChessBoardRow.EIGHT))
        );
    }

    private static Stream<Arguments> provideBishopPosition() {
        return Stream.of(
                Arguments.of(new ChessBoardPosition(ChessBoardColumn.C, ChessBoardRow.ONE)),
                Arguments.of(new ChessBoardPosition(ChessBoardColumn.F, ChessBoardRow.ONE)),
                Arguments.of(new ChessBoardPosition(ChessBoardColumn.C, ChessBoardRow.EIGHT)),
                Arguments.of(new ChessBoardPosition(ChessBoardColumn.F, ChessBoardRow.EIGHT))
        );
    }
}
