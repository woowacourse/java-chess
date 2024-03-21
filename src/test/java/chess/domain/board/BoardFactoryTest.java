package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.pieces.Bishop;
import chess.domain.pieces.King;
import chess.domain.pieces.Knight;
import chess.domain.pieces.Pawn;
import chess.domain.pieces.Queen;
import chess.domain.pieces.Rook;
import chess.domain.pieces.piece.Color;
import chess.domain.pieces.piece.Piece;
import chess.domain.pieces.piece.PieceResponse;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("기물 생성")
class BoardFactoryTest {

    Map<Square, Piece> pieces;

    @BeforeEach
    void setUp() {
        pieces = BoardFactory.createBoard().getPieces();
    }

    @DisplayName("검은 기물")
    @Nested
    class BlackTest {
        @DisplayName("검은 기물들의 생성에 성공한다")
        @Test
        void createBlackPieces() {
            //given & when & then
            assertAll(
                    () -> assertThat(pieces.get(Square.of(Rank.EIGHT, File.A))).isEqualTo(new Rook(Color.BLACK)),
                    () -> assertThat(pieces.get(Square.of(Rank.EIGHT, File.B))).isEqualTo(new Knight(Color.BLACK)),
                    () -> assertThat(pieces.get(Square.of(Rank.EIGHT, File.C))).isEqualTo(new Bishop(Color.BLACK)),
                    () -> assertThat(pieces.get(Square.of(Rank.EIGHT, File.D))).isEqualTo(new Queen(Color.BLACK)),
                    () -> assertThat(pieces.get(Square.of(Rank.EIGHT, File.E))).isEqualTo(new King(Color.BLACK)),
                    () -> assertThat(pieces.get(Square.of(Rank.EIGHT, File.F))).isEqualTo(new Bishop(Color.BLACK)),
                    () -> assertThat(pieces.get(Square.of(Rank.EIGHT, File.G))).isEqualTo(new Knight(Color.BLACK)),
                    () -> assertThat(pieces.get(Square.of(Rank.EIGHT, File.H))).isEqualTo(new Rook(Color.BLACK))
            );
        }

        @DisplayName("검은 폰 생성에 성공한다")
        @Test
        void createBlackPawns() {
            //given & when & then
            assertAll(
                    () -> assertThat(pieces.get(Square.of(Rank.SEVEN, File.A))).isEqualTo(new Pawn(Color.BLACK)),
                    () -> assertThat(pieces.get(Square.of(Rank.SEVEN, File.B))).isEqualTo(new Pawn(Color.BLACK)),
                    () -> assertThat(pieces.get(Square.of(Rank.SEVEN, File.C))).isEqualTo(new Pawn(Color.BLACK)),
                    () -> assertThat(pieces.get(Square.of(Rank.SEVEN, File.D))).isEqualTo(new Pawn(Color.BLACK)),
                    () -> assertThat(pieces.get(Square.of(Rank.SEVEN, File.E))).isEqualTo(new Pawn(Color.BLACK)),
                    () -> assertThat(pieces.get(Square.of(Rank.SEVEN, File.F))).isEqualTo(new Pawn(Color.BLACK)),
                    () -> assertThat(pieces.get(Square.of(Rank.SEVEN, File.G))).isEqualTo(new Pawn(Color.BLACK)),
                    () -> assertThat(pieces.get(Square.of(Rank.SEVEN, File.H))).isEqualTo(new Pawn(Color.BLACK))
            );
        }
    }

    @DisplayName("흰 기물")
    @Nested
    class WhiteTest {
        @DisplayName("흰 기물들의 생성에 성공한다")
        @Test
        void createWhitePieces() {
            //given & when & then
            assertAll(
                    () -> assertThat(pieces.get(Square.of(Rank.ONE, File.A))).isEqualTo(new Rook(Color.WHITE)),
                    () -> assertThat(pieces.get(Square.of(Rank.ONE, File.B))).isEqualTo(new Knight(Color.WHITE)),
                    () -> assertThat(pieces.get(Square.of(Rank.ONE, File.C))).isEqualTo(new Bishop(Color.WHITE)),
                    () -> assertThat(pieces.get(Square.of(Rank.ONE, File.D))).isEqualTo(new Queen(Color.WHITE)),
                    () -> assertThat(pieces.get(Square.of(Rank.ONE, File.E))).isEqualTo(new King(Color.WHITE)),
                    () -> assertThat(pieces.get(Square.of(Rank.ONE, File.F))).isEqualTo(new Bishop(Color.WHITE)),
                    () -> assertThat(pieces.get(Square.of(Rank.ONE, File.G))).isEqualTo(new Knight(Color.WHITE)),
                    () -> assertThat(pieces.get(Square.of(Rank.ONE, File.H))).isEqualTo(new Rook(Color.WHITE))
            );
        }

        @DisplayName("흰 폰 생성에 성공한다")
        @Test
        void createWhitePawns() {
            //given & when & then
            assertAll(
                    () -> assertThat(pieces.get(Square.of(Rank.TWO, File.A))).isEqualTo(new Pawn(Color.WHITE)),
                    () -> assertThat(pieces.get(Square.of(Rank.TWO, File.B))).isEqualTo(new Pawn(Color.WHITE)),
                    () -> assertThat(pieces.get(Square.of(Rank.TWO, File.C))).isEqualTo(new Pawn(Color.WHITE)),
                    () -> assertThat(pieces.get(Square.of(Rank.TWO, File.D))).isEqualTo(new Pawn(Color.WHITE)),
                    () -> assertThat(pieces.get(Square.of(Rank.TWO, File.E))).isEqualTo(new Pawn(Color.WHITE)),
                    () -> assertThat(pieces.get(Square.of(Rank.TWO, File.F))).isEqualTo(new Pawn(Color.WHITE)),
                    () -> assertThat(pieces.get(Square.of(Rank.TWO, File.G))).isEqualTo(new Pawn(Color.WHITE)),
                    () -> assertThat(pieces.get(Square.of(Rank.TWO, File.H))).isEqualTo(new Pawn(Color.WHITE))
            );
        }
    }
}
