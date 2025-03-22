package chess.piece;

import static chess.Fixtures.A1;
import static chess.Fixtures.A4;
import static chess.Fixtures.B1;
import static chess.Fixtures.B2;
import static chess.Fixtures.C2;
import static chess.Fixtures.C3;
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

class KnightTest {

    @ParameterizedTest
    @MethodSource("provideMovablePosition")
    void 나이트는_상하좌우_한칸_후_대각선_모든_방향으로_갈_수_있다(Position from, Position to) {
        //given
        ChessBoard chessBoard = new ChessBoard(List.of());
        Knight knight = new Knight(Color.BLACK, from);

        //when
        boolean movable = knight.isMovable(chessBoard, to);

        //then
        assertThat(movable).isTrue();
    }

    @Test
    void 나이트가_가는방향에_기물이_있더라도_갈_수_있다() {
        //given
        ChessBoard chessBoard = new ChessBoard(List.of(
                new BlackPawn(C2)
        ));
        Knight knight = new Knight(Color.BLACK, A1);

        //when
        boolean movable = knight.isMovable(chessBoard, C2);

        //then
        assertThat(movable).isTrue();
    }

    private static Stream<Arguments> provideMovablePosition() {
        return Stream.of(
                Arguments.of(A1, C2),
                Arguments.of(B2, A4),
                Arguments.of(C3, B1),
                Arguments.of(B1, C3)
        );
    }
}
