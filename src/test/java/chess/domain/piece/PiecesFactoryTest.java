package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.File;
import chess.domain.Rank;
import chess.domain.Square;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("기물 생성")
class PiecesFactoryTest {

    @DisplayName("검은 기물")
    @Nested
    class BlackTest {
        @DisplayName("검은 기물들의 생성에 성공한다")
        @Test
        void createBlackPieces() {
            //given
            List<Piece> blackPieces = PiecesFactory.createBlackPieces();

            //when & then
            assertThat(blackPieces)
                    .containsExactly(
                            new Rook(Color.BLACK, new Square(File.A, Rank.EIGHT)),
                            new Knight(Color.BLACK, new Square(File.B, Rank.EIGHT)),
                            new Bishop(Color.BLACK, new Square(File.C, Rank.EIGHT)),
                            new Queen(Color.BLACK, new Square(File.D, Rank.EIGHT)),
                            new King(Color.BLACK, new Square(File.E, Rank.EIGHT)),
                            new Bishop(Color.BLACK, new Square(File.F, Rank.EIGHT)),
                            new Knight(Color.BLACK, new Square(File.G, Rank.EIGHT)),
                            new Rook(Color.BLACK, new Square(File.H, Rank.EIGHT))
                    );
        }

        @DisplayName("검은 폰 생성에 성공한다")
        @Test
        void createBlackPawns() {
            //given
            List<Piece> blackPawns = PiecesFactory.createBlackPawns();

            int expectedSize = 8;

            //when & then
            assertAll(
                    () -> assertThat(blackPawns).containsExactly(
                            new Pawn(Color.BLACK, new Square(File.A, Rank.SEVEN)),
                            new Pawn(Color.BLACK, new Square(File.B, Rank.SEVEN)),
                            new Pawn(Color.BLACK, new Square(File.C, Rank.SEVEN)),
                            new Pawn(Color.BLACK, new Square(File.D, Rank.SEVEN)),
                            new Pawn(Color.BLACK, new Square(File.E, Rank.SEVEN)),
                            new Pawn(Color.BLACK, new Square(File.F, Rank.SEVEN)),
                            new Pawn(Color.BLACK, new Square(File.G, Rank.SEVEN)),
                            new Pawn(Color.BLACK, new Square(File.H, Rank.SEVEN))
                    ),
                    () -> assertThat(blackPawns).hasSize(expectedSize)
            );
        }
    }

    @DisplayName("흰 기물")
    @Nested
    class WhiteTest {
        @DisplayName("흰 기물들의 생성에 성공한다")
        @Test
        void createWhitePieces() {
            //given
            List<Piece> whitePieces = PiecesFactory.createWhitePieces();

            //when & then
            assertThat(whitePieces)
                    .containsExactly(
                            new Rook(Color.WHITE, new Square(File.A, Rank.ONE)),
                            new Knight(Color.WHITE, new Square(File.B, Rank.ONE)),
                            new Bishop(Color.WHITE, new Square(File.C, Rank.ONE)),
                            new Queen(Color.WHITE, new Square(File.D, Rank.ONE)),
                            new King(Color.WHITE, new Square(File.E, Rank.ONE)),
                            new Bishop(Color.WHITE, new Square(File.F, Rank.ONE)),
                            new Knight(Color.WHITE, new Square(File.G, Rank.ONE)),
                            new Rook(Color.WHITE, new Square(File.H, Rank.ONE))
                    );
        }

        @DisplayName("흰 폰 생성에 성공한다")
        @Test
        void createWhitePawns() {
            //given
            List<Piece> whitePawns = PiecesFactory.createWhitePawns();

            int expectedSize = 8;

            //when & then
            assertAll(
                    () -> assertThat(whitePawns).containsExactly(
                            new Pawn(Color.WHITE, new Square(File.A, Rank.TWO)),
                            new Pawn(Color.WHITE, new Square(File.B, Rank.TWO)),
                            new Pawn(Color.WHITE, new Square(File.C, Rank.TWO)),
                            new Pawn(Color.WHITE, new Square(File.D, Rank.TWO)),
                            new Pawn(Color.WHITE, new Square(File.E, Rank.TWO)),
                            new Pawn(Color.WHITE, new Square(File.F, Rank.TWO)),
                            new Pawn(Color.WHITE, new Square(File.G, Rank.TWO)),
                            new Pawn(Color.WHITE, new Square(File.H, Rank.TWO))
                    ),
                    () -> assertThat(whitePawns).hasSize(expectedSize)
            );
        }
    }

    @DisplayName("빈 기물 생성에 성공한다")
    @Test
    void emptyPieces() {
        //given
        List<Piece> emptyPieces = PiecesFactory.createEmptyPieces();

        int expectedSize = 32;

        //when & then
        assertThat(emptyPieces).hasSize(expectedSize);
    }
}
