package chess.piece;

import static chess.Fixtures.A1;
import static chess.Fixtures.A8;
import static chess.Fixtures.B1;
import static chess.Fixtures.B2;
import static chess.Fixtures.B4;
import static chess.Fixtures.B8;
import static chess.Fixtures.C1;
import static chess.Fixtures.C3;
import static chess.Fixtures.H2;
import static org.assertj.core.api.Assertions.assertThat;

import chess.ChessBoard;
import chess.Color;
import chess.Position;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RookTest {

    @ParameterizedTest
    @MethodSource("provideMovablePosition")
    void 룩은_상하좌우_보드_끝까지_갈_수_있다(Position from, Position to) {
        //given
        ChessBoard chessBoard = new ChessBoard(List.of());
        Rook rook = new Rook(Color.BLACK, from);

        //when
        boolean movable = rook.isMovable(chessBoard, to);

        //then
        assertThat(movable).isTrue();
    }

    @Test
    void 룩이_기는_방향에_기물이_있다면_갈_수_없다() {
        //given
        ChessBoard chessBoard = new ChessBoard(List.of(
                new BlackPawn(B2)
        ));
        Rook rook = new Rook(Color.BLACK, B1);

        //when
        boolean movable = rook.isMovable(chessBoard, B4);

        //then
        assertThat(movable).isFalse();
    }

    private static Stream<Arguments> provideMovablePosition() {
        return Stream.of(
                Arguments.of(A1, A8),
                Arguments.of(B2, H2),
                Arguments.of(C3, C1),
                Arguments.of(B8, B1),
                Arguments.of(B8, B4)
        );
    }
}
