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
                //White rank1 초기화
                Arguments.arguments(new Square(File.A, Rank.ONE), new Rook(Camp.NONE)),
                Arguments.arguments(new Square(File.B, Rank.ONE), new Knight(Camp.NONE)),
                Arguments.arguments(new Square(File.C, Rank.ONE), new Bishop(Camp.NONE)),
                Arguments.arguments(new Square(File.D, Rank.ONE), new Queen(Camp.NONE)),
                Arguments.arguments(new Square(File.E, Rank.ONE), new King(Camp.NONE)),
                Arguments.arguments(new Square(File.F, Rank.ONE), new Bishop(Camp.NONE)),
                Arguments.arguments(new Square(File.G, Rank.ONE), new Knight(Camp.NONE)),
                Arguments.arguments(new Square(File.H, Rank.ONE), new Rook(Camp.NONE)),
                //White rank2 초기화
                Arguments.arguments(new Square(File.A, Rank.TWO), new Pawn(Camp.NONE)),
                Arguments.arguments(new Square(File.B, Rank.TWO), new Pawn(Camp.NONE)),
                Arguments.arguments(new Square(File.C, Rank.TWO), new Pawn(Camp.NONE)),
                Arguments.arguments(new Square(File.D, Rank.TWO), new Pawn(Camp.NONE)),
                Arguments.arguments(new Square(File.E, Rank.TWO), new Pawn(Camp.NONE)),
                Arguments.arguments(new Square(File.F, Rank.TWO), new Pawn(Camp.NONE)),
                Arguments.arguments(new Square(File.G, Rank.TWO), new Pawn(Camp.NONE)),
                Arguments.arguments(new Square(File.H, Rank.TWO), new Pawn(Camp.NONE)),

                //Black rank7 초기화
                Arguments.arguments(new Square(File.A, Rank.SEVEN), new Pawn(Camp.NONE)),
                Arguments.arguments(new Square(File.B, Rank.SEVEN), new Pawn(Camp.NONE)),
                Arguments.arguments(new Square(File.C, Rank.SEVEN), new Pawn(Camp.NONE)),
                Arguments.arguments(new Square(File.D, Rank.SEVEN), new Pawn(Camp.NONE)),
                Arguments.arguments(new Square(File.E, Rank.SEVEN), new Pawn(Camp.NONE)),
                Arguments.arguments(new Square(File.F, Rank.SEVEN), new Pawn(Camp.NONE)),
                Arguments.arguments(new Square(File.G, Rank.SEVEN), new Pawn(Camp.NONE)),
                Arguments.arguments(new Square(File.H, Rank.SEVEN), new Pawn(Camp.NONE)),

                //Black rank8 초기화
                Arguments.arguments(new Square(File.A, Rank.EIGHT), new Rook(Camp.NONE)),
                Arguments.arguments(new Square(File.B, Rank.EIGHT), new Knight(Camp.NONE)),
                Arguments.arguments(new Square(File.C, Rank.EIGHT), new Bishop(Camp.NONE)),
                Arguments.arguments(new Square(File.D, Rank.EIGHT), new Queen(Camp.NONE)),
                Arguments.arguments(new Square(File.E, Rank.EIGHT), new King(Camp.NONE)),
                Arguments.arguments(new Square(File.F, Rank.EIGHT), new Bishop(Camp.NONE)),
                Arguments.arguments(new Square(File.G, Rank.EIGHT), new Knight(Camp.NONE)),
                Arguments.arguments(new Square(File.H, Rank.EIGHT), new Rook(Camp.NONE))
        );
    }
}
