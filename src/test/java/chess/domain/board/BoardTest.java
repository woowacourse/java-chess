package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BoardTest {

    @DisplayName("체스판은 64칸이다.")
    @Test
    void chess_board_size_64() {
        //given & when
        Board board = new Board();

        //then
        assertThat(board.getValue()).hasSize(64);
    }

    @MethodSource("provideRookPosition")
    @ParameterizedTest(name = "{0}에 룩이 있다")
    void check_rook_positions(Position expectedPosition) {
        //given
        Board board = new Board();
        //when
        Map<Position, Piece> boardValue = board.getValue();
        //then
        assertThat(boardValue.get(expectedPosition))
            .isInstanceOf(Rook.class);
    }

    @MethodSource("provideBishopPosition")
    @ParameterizedTest(name = "{0}에 비숍이 있다")
    void check_bishop_positions(Position expectedPosition) {
        //given
        Board board = new Board();
        //when
        Map<Position, Piece> boardValue = board.getValue();
        //then
        assertThat(boardValue.get(expectedPosition))
            .isInstanceOf(Bishop.class);
    }

    @MethodSource("provideKnightPosition")
    @ParameterizedTest(name = "{0}에 나이트가 있다")
    void check_knight_positions(Position expectedPosition) {
        //given
        Board board = new Board();
        //when
        Map<Position, Piece> boardValue = board.getValue();
        //then
        assertThat(boardValue.get(expectedPosition))
            .isInstanceOf(Knight.class);
    }

    @MethodSource("provideQueenPosition")
    @ParameterizedTest(name = "{0}에 퀸이 있다")
    void check_queen_positions(Position expectedPosition) {
        //given
        Board board = new Board();
        //when
        Map<Position, Piece> boardValue = board.getValue();
        //then
        assertThat(boardValue.get(expectedPosition))
            .isInstanceOf(Queen.class);
    }

    @MethodSource("provideKingPosition")
    @ParameterizedTest(name = "{0}에 킹이 있다")
    void check_king_positions(Position expectedPosition) {
        //given
        Board board = new Board();
        //when
        Map<Position, Piece> boardValue = board.getValue();
        //then
        assertThat(boardValue.get(expectedPosition))
            .isInstanceOf(King.class);
    }

    @MethodSource("providePawnPosition")
    @ParameterizedTest(name = "{0}에 폰이 있다")
    void check_pawn_positions(Position expectedPosition) {
        //given
        Board board = new Board();
        //when
        Map<Position, Piece> boardValue = board.getValue();
        //then
        assertThat(boardValue.get(expectedPosition))
            .isInstanceOf(Pawn.class);
    }

    @MethodSource("provideWhiteRow")
    @ParameterizedTest(name = "{0}행의 모든 말은 white이다.")
    void check_camp_white(Row row) {
        Board board = new Board();

        Map<Position, Piece> boardValue = board.getValue();
        for (Column column : Column.values()) {
            Piece piece = boardValue.get(Position.of(column, row));
            assertThat(piece.isBlack()).isFalse();
        }
    }

    @MethodSource("provideBlackRow")
    @ParameterizedTest(name = "{0}행의 모든 말은 black이다.")
    void check_camp_black(Row row) {
        Board board = new Board();

        Map<Position, Piece> boardValue = board.getValue();
        for (Column column : Column.values()) {
            Piece piece = boardValue.get(Position.of(column, row));
            assertThat(piece.isBlack()).isTrue();
        }
    }

    @DisplayName("이동하려는 위치에 같은 팀 기물이 있으면 갈 수 없다")
    @Test
    void move_h1_h2() {
        Board board = new Board();

        Position h1 = Position.of(Column.H, Row.ONE);
        Position h2 = Position.of(Column.H, Row.TWO);

        assertThatThrownBy(() -> board.move(h1, h2))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("같은 팀 기물이 있는 위치로는 이동할 수 없습니다.");
    }

    @DisplayName("이동하려는 위치가 빈 칸이면 이동할 수 있다.")
    @Test
    void move_a2_a4() {
        Board board = new Board();

        Position a2 = Position.of(Column.A, Row.TWO);
        Position a4 = Position.of(Column.A, Row.FOUR);

        assertThatNoException().isThrownBy(() -> board.move(a2, a4));
    }

    @DisplayName("빈칸의 위치를 출발지로 둘 수 없다.")
    @Test
    void move_blank_exception() {
        Board board = new Board();
        Position a3 = Position.of(Column.A, Row.THREE);
        Position a4 = Position.of(Column.A, Row.FOUR);
        assertThatThrownBy(() -> board.move(a3, a4))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("빈 기물을 움직일 수 없습니다.");
    }

    @DisplayName("초기 상태의 체스판에서 흑색 진영의 점수는 38점이다.")
    @Test
    void calculateScoreOfBlack_38() {
        Board board = new Board();

        assertThat(new BoardStatusCalculator(board).calculate(Piece::isBlack)).isEqualTo(38);
    }

    @DisplayName("체스판에서 두 pawn이 한 열에 있을 떄 흑색 진영의 점수는 37점이다.")
    @Test
    void calculateScoreOfBlack_37() {
        Board board = new Board();
        Position b2 = Position.of(Column.B, Row.TWO);
        Position b4 = Position.of(Column.B, Row.FOUR);
        Position c7 = Position.of(Column.C, Row.SEVEN);
        Position c5 = Position.of(Column.C, Row.FIVE);
        Position d2 = Position.of(Column.D, Row.TWO);
        Position d4 = Position.of(Column.D, Row.FOUR);

        board.move(b2, b4);
        board.move(c7, c5);
        board.move(d2, d4);
        board.move(c5, b4);
        assertThat(new BoardStatusCalculator(board).calculate(Piece::isBlack)).isEqualTo(37);
    }

    @DisplayName("초기 상태의 체스판에서 백색 진영의 점수는 38점이다.")
    @Test
    void calculateScoreOfWhite_38() {
        Board board = new Board();

        assertThat(new BoardStatusCalculator(board).calculate(piece -> !piece.isBlack())).isEqualTo(38);
    }

    @DisplayName("체스판에서 두 pawn이 한 열에 있을 떄 백색 진영의 점수는 37점이다.")
    @Test
    void calculateScoreOfWhite_37() {
        Board board = new Board();
        Position b2 = Position.of(Column.B, Row.TWO);
        Position b4 = Position.of(Column.B, Row.FOUR);
        Position c7 = Position.of(Column.C, Row.SEVEN);
        Position c5 = Position.of(Column.C, Row.FIVE);
        board.move(b2, b4);
        board.move(c7, c5);
        board.move(b4, c5);
        assertThat(new BoardStatusCalculator(board).calculate(piece -> !piece.isBlack())).isEqualTo(37);
    }

    private static Stream<Arguments> provideRookPosition() {
        return Stream.of(
            Arguments.of(Position.of(Column.A, Row.ONE)),
            Arguments.of(Position.of(Column.H, Row.ONE)),
            Arguments.of(Position.of(Column.A, Row.EIGHT)),
            Arguments.of(Position.of(Column.H, Row.EIGHT))
        );
    }

    private static Stream<Arguments> provideBishopPosition() {
        return Stream.of(
            Arguments.of(Position.of(Column.C, Row.ONE)),
            Arguments.of(Position.of(Column.F, Row.ONE)),
            Arguments.of(Position.of(Column.C, Row.EIGHT)),
            Arguments.of(Position.of(Column.F, Row.EIGHT))
        );
    }

    private static Stream<Arguments> provideKnightPosition() {
        return Stream.of(
            Arguments.of(Position.of(Column.B, Row.ONE)),
            Arguments.of(Position.of(Column.G, Row.ONE)),
            Arguments.of(Position.of(Column.B, Row.EIGHT)),
            Arguments.of(Position.of(Column.G, Row.EIGHT))
        );
    }

    private static Stream<Arguments> provideQueenPosition() {
        return Stream.of(
            Arguments.of(Position.of(Column.D, Row.ONE)),
            Arguments.of(Position.of(Column.D, Row.EIGHT))
        );
    }

    private static Stream<Arguments> provideKingPosition() {
        return Stream.of(
            Arguments.of(Position.of(Column.E, Row.ONE)),
            Arguments.of(Position.of(Column.E, Row.EIGHT))
        );
    }

    private static Stream<Arguments> providePawnPosition() {
        return Stream.of(
            Arguments.of(Position.of(Column.A, Row.TWO)),
            Arguments.of(Position.of(Column.B, Row.TWO)),
            Arguments.of(Position.of(Column.C, Row.TWO)),
            Arguments.of(Position.of(Column.D, Row.TWO)),
            Arguments.of(Position.of(Column.E, Row.TWO)),
            Arguments.of(Position.of(Column.F, Row.TWO)),
            Arguments.of(Position.of(Column.G, Row.TWO)),
            Arguments.of(Position.of(Column.H, Row.TWO)),
            Arguments.of(Position.of(Column.A, Row.SEVEN)),
            Arguments.of(Position.of(Column.B, Row.SEVEN)),
            Arguments.of(Position.of(Column.C, Row.SEVEN)),
            Arguments.of(Position.of(Column.D, Row.SEVEN)),
            Arguments.of(Position.of(Column.E, Row.SEVEN)),
            Arguments.of(Position.of(Column.F, Row.SEVEN)),
            Arguments.of(Position.of(Column.G, Row.SEVEN)),
            Arguments.of(Position.of(Column.H, Row.SEVEN))
        );
    }

    private static Stream<Arguments> provideWhiteRow() {
        return Stream.of(
            Arguments.of(Row.ONE),
            Arguments.of(Row.TWO)
        );
    }

    private static Stream<Arguments> provideBlackRow() {
        return Stream.of(
            Arguments.of(Row.SEVEN),
            Arguments.of(Row.EIGHT)
        );
    }
}
