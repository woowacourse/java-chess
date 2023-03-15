package chess;

import chess.piece.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class BoardInitializerTest {
    @ParameterizedTest(name = "초기 기물을 위치에 맞게 생성한다.")
    @MethodSource("squareProvider")
    void initializeBoardTest(File file, Rank rank, Piece piece) {
        Chessboard chessboard = new Chessboard();
        BoardInitializer.initializeBoard(chessboard);

        Assertions.assertThat(chessboard.getPieceAt(file, rank))
                .isInstanceOf(piece.getClass());
    }

    static Stream<Arguments> squareProvider() {
        return Stream.of(
                Arguments.arguments(File.A, Rank.ONE, new Rook(Camp.NONE)),
                Arguments.arguments(File.B, Rank.ONE, new Knight(Camp.NONE)),
                Arguments.arguments(File.C, Rank.ONE, new Bishop(Camp.NONE)),
                Arguments.arguments(File.D, Rank.ONE, new Queen(Camp.NONE)),
                Arguments.arguments(File.E, Rank.ONE, new King(Camp.NONE)),
                Arguments.arguments(File.F, Rank.ONE, new Bishop(Camp.NONE)),
                Arguments.arguments(File.G, Rank.ONE, new Knight(Camp.NONE)),
                Arguments.arguments(File.H, Rank.ONE, new Rook(Camp.NONE)),
                Arguments.arguments(File.A, Rank.TWO, new Pawn(Camp.NONE)),
                Arguments.arguments(File.B, Rank.TWO, new Pawn(Camp.NONE)),
                Arguments.arguments(File.C, Rank.TWO, new Pawn(Camp.NONE)),
                Arguments.arguments(File.D, Rank.TWO, new Pawn(Camp.NONE)),
                Arguments.arguments(File.E, Rank.TWO, new Pawn(Camp.NONE)),
                Arguments.arguments(File.F, Rank.TWO, new Pawn(Camp.NONE)),
                Arguments.arguments(File.G, Rank.TWO, new Pawn(Camp.NONE)),
                Arguments.arguments(File.H, Rank.TWO, new Pawn(Camp.NONE)),
                Arguments.arguments(File.A, Rank.SEVEN, new Pawn(Camp.NONE)),
                Arguments.arguments(File.B, Rank.SEVEN, new Pawn(Camp.NONE)),
                Arguments.arguments(File.C, Rank.SEVEN, new Pawn(Camp.NONE)),
                Arguments.arguments(File.D, Rank.SEVEN, new Pawn(Camp.NONE)),
                Arguments.arguments(File.E, Rank.SEVEN, new Pawn(Camp.NONE)),
                Arguments.arguments(File.F, Rank.SEVEN, new Pawn(Camp.NONE)),
                Arguments.arguments(File.G, Rank.SEVEN, new Pawn(Camp.NONE)),
                Arguments.arguments(File.H, Rank.SEVEN, new Pawn(Camp.NONE)),
                Arguments.arguments(File.A, Rank.EIGHT, new Rook(Camp.NONE)),
                Arguments.arguments(File.B, Rank.EIGHT, new Knight(Camp.NONE)),
                Arguments.arguments(File.C, Rank.EIGHT, new Bishop(Camp.NONE)),
                Arguments.arguments(File.D, Rank.EIGHT, new Queen(Camp.NONE)),
                Arguments.arguments(File.E, Rank.EIGHT, new King(Camp.NONE)),
                Arguments.arguments(File.F, Rank.EIGHT, new Bishop(Camp.NONE)),
                Arguments.arguments(File.G, Rank.EIGHT, new Knight(Camp.NONE)),
                Arguments.arguments(File.H, Rank.EIGHT, new Rook(Camp.NONE))
        );
    }
}
