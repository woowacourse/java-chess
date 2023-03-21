package chess;

import chess.domain.BoardInitializer;
import chess.domain.board.Chessboard;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.piece.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class BoardInitializerTest {
    @ParameterizedTest(name = "{0} 기물을 제 위치에 생성한다.")
    @MethodSource("squareProvider")
    void initializeBoardTest(String name, File file, Rank rank, Piece piece) {
        Chessboard chessboard = new Chessboard();
        BoardInitializer.initializeBoard(chessboard);

        Assertions.assertThat(chessboard.getPieceAt(new Square(file, rank)).getPieceType())
                .isEqualTo(piece.getPieceType());
    }

    static Stream<Arguments> squareProvider() {
        return Stream.of(
                Arguments.arguments("a1 룩", File.A, Rank.ONE, new Rook(Camp.NONE)),
                Arguments.arguments("b1 나이트", File.B, Rank.ONE, new Knight(Camp.NONE)),
                Arguments.arguments("c1 비숍", File.C, Rank.ONE, new Bishop(Camp.NONE)),
                Arguments.arguments("d1 퀸", File.D, Rank.ONE, new Queen(Camp.NONE)),
                Arguments.arguments("e1 폰", File.E, Rank.ONE, new King(Camp.NONE)),
                Arguments.arguments("f1 폰", File.F, Rank.ONE, new Bishop(Camp.NONE)),
                Arguments.arguments("g1 폰", File.G, Rank.ONE, new Knight(Camp.NONE)),
                Arguments.arguments("h1 폰", File.H, Rank.ONE, new Rook(Camp.NONE)),
                Arguments.arguments("a2 폰", File.A, Rank.TWO, new Pawn(Camp.NONE)),
                Arguments.arguments("b2 폰", File.B, Rank.TWO, new Pawn(Camp.NONE)),
                Arguments.arguments("c2 폰", File.C, Rank.TWO, new Pawn(Camp.NONE)),
                Arguments.arguments("d2 폰", File.D, Rank.TWO, new Pawn(Camp.NONE)),
                Arguments.arguments("e2 폰", File.E, Rank.TWO, new Pawn(Camp.NONE)),
                Arguments.arguments("f2 폰", File.F, Rank.TWO, new Pawn(Camp.NONE)),
                Arguments.arguments("g2 폰", File.G, Rank.TWO, new Pawn(Camp.NONE)),
                Arguments.arguments("h2 폰", File.H, Rank.TWO, new Pawn(Camp.NONE)),
                Arguments.arguments("a7 폰", File.A, Rank.SEVEN, new Pawn(Camp.NONE)),
                Arguments.arguments("b7 폰", File.B, Rank.SEVEN, new Pawn(Camp.NONE)),
                Arguments.arguments("c7 폰", File.C, Rank.SEVEN, new Pawn(Camp.NONE)),
                Arguments.arguments("d7 폰", File.D, Rank.SEVEN, new Pawn(Camp.NONE)),
                Arguments.arguments("e7 폰", File.E, Rank.SEVEN, new Pawn(Camp.NONE)),
                Arguments.arguments("f7 폰", File.F, Rank.SEVEN, new Pawn(Camp.NONE)),
                Arguments.arguments("g7 폰", File.G, Rank.SEVEN, new Pawn(Camp.NONE)),
                Arguments.arguments("h7 폰", File.H, Rank.SEVEN, new Pawn(Camp.NONE)),
                Arguments.arguments("a8 폰", File.A, Rank.EIGHT, new Rook(Camp.NONE)),
                Arguments.arguments("b8 나이트", File.B, Rank.EIGHT, new Knight(Camp.NONE)),
                Arguments.arguments("c8 비숍", File.C, Rank.EIGHT, new Bishop(Camp.NONE)),
                Arguments.arguments("d8 퀸", File.D, Rank.EIGHT, new Queen(Camp.NONE)),
                Arguments.arguments("e8 킹", File.E, Rank.EIGHT, new King(Camp.NONE)),
                Arguments.arguments("f8 비숍", File.F, Rank.EIGHT, new Bishop(Camp.NONE)),
                Arguments.arguments("g8 나이트", File.G, Rank.EIGHT, new Knight(Camp.NONE)),
                Arguments.arguments("h8 룩", File.H, Rank.EIGHT, new Rook(Camp.NONE))
        );
    }
}
