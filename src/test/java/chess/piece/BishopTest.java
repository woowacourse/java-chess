package chess.piece;

import static chess.Fixtures.B1;
import static chess.Fixtures.C2;
import static chess.Fixtures.D3;
import static org.assertj.core.api.Assertions.assertThat;

import chess.ChessBoard;
import chess.Color;
import chess.Fixtures;
import chess.Position;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BishopTest {

    @ParameterizedTest
    @MethodSource("provideMovablePosition")
    void 비숍은_대각선으로_보드판_끝까지_갈_수_있다(Position from, Position to) {
        //given
        ChessBoard chessBoard = new ChessBoard(List.of());
        Bishop bishop = new Bishop(Color.BLACK, from);

        //when
        boolean movable = bishop.isMovable(chessBoard, to);

        //then
        assertThat(movable).isTrue();
    }

    @Test
    void 비숍이_기는_방향에_기물이_있다면_갈_수_없다() {
        //given
        ChessBoard chessBoard = new ChessBoard(List.of(
                new BlackPawn(C2)
        ));
        Bishop bishop = new Bishop(Color.BLACK, B1);

        //when
        boolean movable = bishop.isMovable(chessBoard, D3);

        //then
        assertThat(movable).isFalse();
    }

    private static Stream<Arguments> provideMovablePosition() {
        return Stream.of(
                Arguments.of(Fixtures.A1, Fixtures.H8),
                Arguments.of(Fixtures.B2, Fixtures.A3),
                Arguments.of(Fixtures.D2, Fixtures.C1),
                Arguments.of(Fixtures.E7, Fixtures.F6)
        );
    }
}
