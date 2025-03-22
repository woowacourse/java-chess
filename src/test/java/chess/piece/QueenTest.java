package chess.piece;

import static chess.Fixtures.A1;
import static chess.Fixtures.A8;
import static chess.Fixtures.B1;
import static chess.Fixtures.B2;
import static chess.Fixtures.B4;
import static chess.Fixtures.B8;
import static chess.Fixtures.C1;
import static chess.Fixtures.C2;
import static chess.Fixtures.C3;
import static chess.Fixtures.D3;
import static chess.Fixtures.H2;
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

class QueenTest {

    @ParameterizedTest
    @MethodSource("provideMovablePosition")
    void 퀸은_상하좌우_또는_대각선으로_보드판_끝까지_갈_수_있다(Position from, Position to) {
        //given
        ChessBoard chessBoard = new ChessBoard(List.of());
        Queen queen = new Queen(Color.BLACK, from);

        //when
        boolean movable = queen.isMovable(chessBoard, to);

        //then
        assertThat(movable).isTrue();
    }

    @Test
    void 퀸이_기는_방향에_기물이_있다면_갈_수_없다() {
        //given
        ChessBoard chessBoard = new ChessBoard(List.of(
                new BlackPawn(C2)
        ));
        Queen queen = new Queen(Color.BLACK, B1);

        //when
        boolean movable = queen.isMovable(chessBoard, D3);

        //then
        assertThat(movable).isFalse();
    }

    private static Stream<Arguments> provideMovablePosition() {
        return Stream.of(
                Arguments.of(Fixtures.A1, Fixtures.H8),
                Arguments.of(Fixtures.B2, Fixtures.A3),
                Arguments.of(Fixtures.D2, Fixtures.C1),
                Arguments.of(Fixtures.E7, Fixtures.F6),
                Arguments.of(A1, A8),
                Arguments.of(B2, H2),
                Arguments.of(C3, C1),
                Arguments.of(B8, B1),
                Arguments.of(B8, B4)
        );
    }
}
