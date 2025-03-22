package chess.piece;

import chess.ChessBoard;
import chess.Color;
import chess.Column;
import chess.Fixtures;
import chess.Position;
import chess.Row;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KingTest {

    @ParameterizedTest
    @MethodSource("provideMovablePosition")
    void 킹은_상_하_좌_우_대각선_한칸_같은팀의_기물이_없다면_이동할_수_있다(Position from, Position to) {
        //given
        ChessBoard chessBoard = new ChessBoard(List.of());
        King king = new King(Color.BLACK, from);

        //when
        boolean result = king.isMovable(chessBoard, to);

        //then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    void 킹은_상_하_좌_우_한칸이_아닌_다른곳으로_이동할_수_없다() {
        //given
        ChessBoard chessBoard = new ChessBoard(List.of());
        King king = new King(Color.BLACK, new Position(Column.B, Row.FIVE));

        //when
        boolean result = king.isMovable(chessBoard, new Position(Column.A, Row.THREE));

        //then
        Assertions.assertThat(result).isFalse();
    }

    private static Stream<Arguments> provideMovablePosition() {
        return Stream.of(
                Arguments.of(Fixtures.A1, Fixtures.A2),
                Arguments.of(Fixtures.B2, Fixtures.B1),
                Arguments.of(Fixtures.B1, Fixtures.C1),
                Arguments.of(Fixtures.C2, Fixtures.B2),
                Arguments.of(Fixtures.C2, Fixtures.D3),
                Arguments.of(Fixtures.C2, Fixtures.B2),
                Arguments.of(Fixtures.C2, Fixtures.B1),
                Arguments.of(Fixtures.C2, Fixtures.D1)
        );
    }
}
