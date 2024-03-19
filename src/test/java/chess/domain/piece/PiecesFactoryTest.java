package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
                            new Rook(Color.BLACK),
                            new Knight(Color.BLACK),
                            new Bishop(Color.BLACK),
                            new Queen(Color.BLACK),
                            new King(Color.BLACK),
                            new Bishop(Color.BLACK),
                            new Knight(Color.BLACK),
                            new Rook(Color.BLACK)
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
                    () -> assertThat(blackPawns).containsOnly(new Pawn(Color.BLACK)),
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
                            new Rook(Color.WHITE),
                            new Knight(Color.WHITE),
                            new Bishop(Color.WHITE),
                            new Queen(Color.WHITE),
                            new King(Color.WHITE),
                            new Bishop(Color.WHITE),
                            new Knight(Color.WHITE),
                            new Rook(Color.WHITE)
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
                    () -> assertThat(whitePawns).containsOnly(new Pawn(Color.WHITE)),
                    () -> assertThat(whitePawns).hasSize(expectedSize)
            );
        }
    }

    @DisplayName("빈 기물 생성에 성공한다")
    @Test
    void emptyPieces() {
        //given
        List<Piece> emptyPieces = PiecesFactory.createEmptyPieces();

        int expectedSize = 8;

        //when & then
        assertAll(
                () -> assertThat(emptyPieces).containsOnly(new EmptyPiece()),
                () -> assertThat(emptyPieces).hasSize(expectedSize)
        );
    }
}
