package chess;

import chess.domain.BoardInitializer;
import chess.domain.board.Chessboard;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.piece.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardInitializerTest {
    @ParameterizedTest(name = "초기 기물을 위치에 맞게 생성한다.")
    @MethodSource("squareProvider")
    void initializeBoardTest(File file, Rank rank, Piece piece) {
        Chessboard chessboard = new Chessboard();
        BoardInitializer.initializeBoard(chessboard);

        assertThat(chessboard.getPieceAt(Square.getInstanceOf(file, rank)).getPieceType())
                .isEqualTo(piece.getPieceType());
    }

    static Stream<Arguments> squareProvider() {
        return Stream.of(
                Arguments.arguments(File.A, Rank.ONE, Rook.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.B, Rank.ONE, Knight.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.C, Rank.ONE, Bishop.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.D, Rank.ONE, Queen.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.E, Rank.ONE, King.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.F, Rank.ONE, Bishop.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.G, Rank.ONE, Knight.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.H, Rank.ONE, Rook.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.A, Rank.TWO, Pawn.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.B, Rank.TWO, Pawn.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.C, Rank.TWO, Pawn.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.D, Rank.TWO, Pawn.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.E, Rank.TWO, Pawn.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.F, Rank.TWO, Pawn.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.G, Rank.TWO, Pawn.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.H, Rank.TWO, Pawn.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.A, Rank.SEVEN, Pawn.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.B, Rank.SEVEN, Pawn.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.C, Rank.SEVEN, Pawn.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.D, Rank.SEVEN, Pawn.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.E, Rank.SEVEN, Pawn.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.F, Rank.SEVEN, Pawn.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.G, Rank.SEVEN, Pawn.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.H, Rank.SEVEN, Pawn.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.A, Rank.EIGHT, Rook.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.B, Rank.EIGHT, Knight.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.C, Rank.EIGHT, Bishop.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.D, Rank.EIGHT, Queen.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.E, Rank.EIGHT, King.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.F, Rank.EIGHT, Bishop.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.G, Rank.EIGHT, Knight.getInstanceOf(Camp.NONE)),
                Arguments.arguments(File.H, Rank.EIGHT, Rook.getInstanceOf(Camp.NONE))
        );
    }
}
