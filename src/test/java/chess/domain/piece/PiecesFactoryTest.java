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
                            new Rook(Color.BLACK, new Square(Rank.A, File.EIGHT)),
                            new Knight(Color.BLACK, new Square(Rank.B, File.EIGHT)),
                            new Bishop(Color.BLACK, new Square(Rank.C, File.EIGHT)),
                            new Queen(Color.BLACK, new Square(Rank.D, File.EIGHT)),
                            new King(Color.BLACK, new Square(Rank.E, File.EIGHT)),
                            new Bishop(Color.BLACK, new Square(Rank.F, File.EIGHT)),
                            new Knight(Color.BLACK, new Square(Rank.G, File.EIGHT)),
                            new Rook(Color.BLACK, new Square(Rank.H, File.EIGHT))
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
                            new Pawn(Color.BLACK, new Square(Rank.A, File.SEVEN)),
                            new Pawn(Color.BLACK, new Square(Rank.B, File.SEVEN)),
                            new Pawn(Color.BLACK, new Square(Rank.C, File.SEVEN)),
                            new Pawn(Color.BLACK, new Square(Rank.D, File.SEVEN)),
                            new Pawn(Color.BLACK, new Square(Rank.E, File.SEVEN)),
                            new Pawn(Color.BLACK, new Square(Rank.F, File.SEVEN)),
                            new Pawn(Color.BLACK, new Square(Rank.G, File.SEVEN)),
                            new Pawn(Color.BLACK, new Square(Rank.H, File.SEVEN))
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
                            new Rook(Color.WHITE, new Square(Rank.A, File.ONE)),
                            new Knight(Color.WHITE, new Square(Rank.B, File.ONE)),
                            new Bishop(Color.WHITE, new Square(Rank.C, File.ONE)),
                            new Queen(Color.WHITE, new Square(Rank.D, File.ONE)),
                            new King(Color.WHITE, new Square(Rank.E, File.ONE)),
                            new Bishop(Color.WHITE, new Square(Rank.F, File.ONE)),
                            new Knight(Color.WHITE, new Square(Rank.G, File.ONE)),
                            new Rook(Color.WHITE, new Square(Rank.H, File.ONE))
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
                            new Pawn(Color.WHITE, new Square(Rank.A, File.TWO)),
                            new Pawn(Color.WHITE, new Square(Rank.B, File.TWO)),
                            new Pawn(Color.WHITE, new Square(Rank.C, File.TWO)),
                            new Pawn(Color.WHITE, new Square(Rank.D, File.TWO)),
                            new Pawn(Color.WHITE, new Square(Rank.E, File.TWO)),
                            new Pawn(Color.WHITE, new Square(Rank.F, File.TWO)),
                            new Pawn(Color.WHITE, new Square(Rank.G, File.TWO)),
                            new Pawn(Color.WHITE, new Square(Rank.H, File.TWO))
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
