package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.BoardFactory;
import chess.domain.File;
import chess.domain.Rank;
import chess.domain.Square;
import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("기물 생성")
class BoardFactoryTest {

    @DisplayName("검은 기물")
    @Nested
    class BlackTest {
        @DisplayName("검은 기물들의 생성에 성공한다")
        @Test
        void createBlackPieces() {
            //given
            Map<Square, Piece> pieces = BoardFactory.createBoard().getPieces();

            //when & then
            assertAll(
                    () -> assertThat(pieces.get(new Square(File.A, Rank.EIGHT))).isEqualTo(new Rook(Color.BLACK)),
                    () -> assertThat(pieces.get(new Square(File.B, Rank.EIGHT))).isEqualTo(new Knight(Color.BLACK)),
                    () -> assertThat(pieces.get(new Square(File.C, Rank.EIGHT))).isEqualTo(new Bishop(Color.BLACK)),
                    () -> assertThat(pieces.get(new Square(File.D, Rank.EIGHT))).isEqualTo(new Queen(Color.BLACK)),
                    () -> assertThat(pieces.get(new Square(File.E, Rank.EIGHT))).isEqualTo(new King(Color.BLACK)),
                    () -> assertThat(pieces.get(new Square(File.F, Rank.EIGHT))).isEqualTo(new Bishop(Color.BLACK)),
                    () -> assertThat(pieces.get(new Square(File.G, Rank.EIGHT))).isEqualTo(new Knight(Color.BLACK)),
                    () -> assertThat(pieces.get(new Square(File.H, Rank.EIGHT))).isEqualTo(new Rook(Color.BLACK))
            );
        }

        @DisplayName("검은 폰 생성에 성공한다")
        @Test
        void createBlackPawns() {
            //given
            Map<Square, Piece> pieces = BoardFactory.createBoard().getPieces();

            //when & then
            assertAll(
                    () -> assertThat(pieces.get(new Square(File.A, Rank.SEVEN))).isEqualTo(new Pawn(Color.BLACK)),
                    () -> assertThat(pieces.get(new Square(File.B, Rank.SEVEN))).isEqualTo(new Pawn(Color.BLACK)),
                    () -> assertThat(pieces.get(new Square(File.C, Rank.SEVEN))).isEqualTo(new Pawn(Color.BLACK)),
                    () -> assertThat(pieces.get(new Square(File.D, Rank.SEVEN))).isEqualTo(new Pawn(Color.BLACK)),
                    () -> assertThat(pieces.get(new Square(File.E, Rank.SEVEN))).isEqualTo(new Pawn(Color.BLACK)),
                    () -> assertThat(pieces.get(new Square(File.F, Rank.SEVEN))).isEqualTo(new Pawn(Color.BLACK)),
                    () -> assertThat(pieces.get(new Square(File.G, Rank.SEVEN))).isEqualTo(new Pawn(Color.BLACK)),
                    () -> assertThat(pieces.get(new Square(File.H, Rank.SEVEN))).isEqualTo(new Pawn(Color.BLACK))
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
            Map<Square, Piece> pieces = BoardFactory.createBoard().getPieces();

            //when & then
            assertAll(
                    () -> assertThat(pieces.get(new Square(File.A, Rank.ONE))).isEqualTo(new Rook(Color.WHITE)),
                    () -> assertThat(pieces.get(new Square(File.B, Rank.ONE))).isEqualTo(new Knight(Color.WHITE)),
                    () -> assertThat(pieces.get(new Square(File.C, Rank.ONE))).isEqualTo(new Bishop(Color.WHITE)),
                    () -> assertThat(pieces.get(new Square(File.D, Rank.ONE))).isEqualTo(new Queen(Color.WHITE)),
                    () -> assertThat(pieces.get(new Square(File.E, Rank.ONE))).isEqualTo(new King(Color.WHITE)),
                    () -> assertThat(pieces.get(new Square(File.F, Rank.ONE))).isEqualTo(new Bishop(Color.WHITE)),
                    () -> assertThat(pieces.get(new Square(File.G, Rank.ONE))).isEqualTo(new Knight(Color.WHITE)),
                    () -> assertThat(pieces.get(new Square(File.H, Rank.ONE))).isEqualTo(new Rook(Color.WHITE))
            );
        }

        @DisplayName("흰 폰 생성에 성공한다")
        @Test
        void createWhitePawns() {
            //given
            Map<Square, Piece> pieces = BoardFactory.createBoard().getPieces();

            //when & then
            assertAll(
                    () -> assertThat(pieces.get(new Square(File.A, Rank.TWO))).isEqualTo(new Pawn(Color.WHITE)),
                    () -> assertThat(pieces.get(new Square(File.B, Rank.TWO))).isEqualTo(new Pawn(Color.WHITE)),
                    () -> assertThat(pieces.get(new Square(File.C, Rank.TWO))).isEqualTo(new Pawn(Color.WHITE)),
                    () -> assertThat(pieces.get(new Square(File.D, Rank.TWO))).isEqualTo(new Pawn(Color.WHITE)),
                    () -> assertThat(pieces.get(new Square(File.E, Rank.TWO))).isEqualTo(new Pawn(Color.WHITE)),
                    () -> assertThat(pieces.get(new Square(File.F, Rank.TWO))).isEqualTo(new Pawn(Color.WHITE)),
                    () -> assertThat(pieces.get(new Square(File.G, Rank.TWO))).isEqualTo(new Pawn(Color.WHITE)),
                    () -> assertThat(pieces.get(new Square(File.H, Rank.TWO))).isEqualTo(new Pawn(Color.WHITE))
            );
        }
    }
}
