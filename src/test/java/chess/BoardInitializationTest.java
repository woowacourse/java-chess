package chess;

import chess.piece.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class BoardInitializationTest {
    @ParameterizedTest(name = "초기 기물을 위치에 맞게 생성한다.")
    @MethodSource("squareProvider")
    void initializeBoardTest(Square square,Piece piece){
        Chessboard chessboard = new Chessboard();
        BoardInitialization boardInitialization = new BoardInitialization();
        boardInitialization.initializeBoard(chessboard);

        Assertions.assertThat(chessboard.getPieceAt(square))
                .isInstanceOf(piece.getClass());
    }

    static Stream<Arguments> squareProvider() {
        return Stream.of(
                Arguments.arguments(new Square(File.A, Rank.ONE), new Rook(Camp.NONE)),
                Arguments.arguments(new Square(File.B, Rank.ONE), new Knight(Camp.NONE)),
                Arguments.arguments(new Square(File.C, Rank.ONE), new Bishop(Camp.NONE)),
                Arguments.arguments(new Square(File.D, Rank.ONE), new Queen(Camp.NONE)),
                Arguments.arguments(new Square(File.E, Rank.ONE), new King(Camp.NONE)),
                Arguments.arguments(new Square(File.F, Rank.ONE), new Bishop(Camp.NONE)),
                Arguments.arguments(new Square(File.G, Rank.ONE), new Knight(Camp.NONE)),
                Arguments.arguments(new Square(File.H, Rank.ONE), new Rook(Camp.NONE))
        );
    }
}
