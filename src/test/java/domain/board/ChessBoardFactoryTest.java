package domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.nonpawn.Bishop;
import domain.piece.nonpawn.King;
import domain.piece.nonpawn.Knight;
import domain.piece.nonpawn.Queen;
import domain.piece.nonpawn.Rook;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.WhitePawn;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ChessBoardFactoryTest {
    @ParameterizedTest(name = "Rank {0}은 폰이 아닌 기물이고 색은 {1}이다")
    @CsvSource(value = {"ONE,WHITE", "EIGHT,BLACK"})
    void 초기_체스_보드에서_폰이_아닌_기물의_Rank와_Color_테스트(Rank rank, Color color) {
        ChessBoard chessBoard = ChessBoardFactory.createInitialChessBoard();

        Map<Position, Piece> pieces = chessBoard.getPositionAndPieces();
        assertAll(
                () -> assertThat(pieces.get(new Position(File.A, rank))).isExactlyInstanceOf(Rook.class)
                        .extracting(Piece::color).isEqualTo(color),
                () -> assertThat(pieces.get(new Position(File.B, rank))).isExactlyInstanceOf(Knight.class)
                        .extracting(Piece::color).isEqualTo(color),
                () -> assertThat(pieces.get(new Position(File.C, rank))).isExactlyInstanceOf(Bishop.class)
                        .extracting(Piece::color).isEqualTo(color),
                () -> assertThat(pieces.get(new Position(File.D, rank))).isExactlyInstanceOf(Queen.class)
                        .extracting(Piece::color).isEqualTo(color),
                () -> assertThat(pieces.get(new Position(File.E, rank))).isExactlyInstanceOf(King.class)
                        .extracting(Piece::color).isEqualTo(color),
                () -> assertThat(pieces.get(new Position(File.F, rank))).isExactlyInstanceOf(Bishop.class)
                        .extracting(Piece::color).isEqualTo(color),
                () -> assertThat(pieces.get(new Position(File.G, rank))).isExactlyInstanceOf(Knight.class)
                        .extracting(Piece::color).isEqualTo(color),
                () -> assertThat(pieces.get(new Position(File.H, rank))).isExactlyInstanceOf(Rook.class)
                        .extracting(Piece::color).isEqualTo(color)
        );
    }

    @ParameterizedTest(name = "Rank {0}은 폰 기물이고 색은 {1}이다")
    @MethodSource("provideRankAndPiece")
    void 초기_체스_보드에서_폰_기물의_Rank와_Color_테스트(Rank rank, Class<? extends Piece> pawnClass) {
        ChessBoard chessBoard = ChessBoardFactory.createInitialChessBoard();

        Map<Position, Piece> pieces = chessBoard.getPositionAndPieces();
        assertAll(
                () -> assertThat(pieces.get(new Position(File.A, rank))).isExactlyInstanceOf(pawnClass),
                () -> assertThat(pieces.get(new Position(File.B, rank))).isExactlyInstanceOf(pawnClass),
                () -> assertThat(pieces.get(new Position(File.C, rank))).isExactlyInstanceOf(pawnClass),
                () -> assertThat(pieces.get(new Position(File.D, rank))).isExactlyInstanceOf(pawnClass),
                () -> assertThat(pieces.get(new Position(File.E, rank))).isExactlyInstanceOf(pawnClass),
                () -> assertThat(pieces.get(new Position(File.F, rank))).isExactlyInstanceOf(pawnClass),
                () -> assertThat(pieces.get(new Position(File.G, rank))).isExactlyInstanceOf(pawnClass),
                () -> assertThat(pieces.get(new Position(File.H, rank))).isExactlyInstanceOf(pawnClass)
        );
    }

    private Stream<Arguments> provideRankAndPiece() {
        return Stream.of(
                Arguments.of(Rank.TWO, WhitePawn.class),
                Arguments.of(Rank.SEVEN, BlackPawn.class)
        );
    }

    @ParameterizedTest
    @EnumSource(value = Rank.class, names = {"THREE", "FOUR", "FIVE", "SIX"})
    void 초기_체스_보드에서_Rank_3부터_6은_비어있다(Rank rank) {
        ChessBoard chessBoard = ChessBoardFactory.createInitialChessBoard();

        Map<Position, Piece> pieces = chessBoard.getPositionAndPieces();
        assertAll(
                () -> assertThat(pieces.get(new Position(File.A, rank))).isNull(),
                () -> assertThat(pieces.get(new Position(File.B, rank))).isNull(),
                () -> assertThat(pieces.get(new Position(File.C, rank))).isNull(),
                () -> assertThat(pieces.get(new Position(File.D, rank))).isNull(),
                () -> assertThat(pieces.get(new Position(File.E, rank))).isNull(),
                () -> assertThat(pieces.get(new Position(File.F, rank))).isNull(),
                () -> assertThat(pieces.get(new Position(File.G, rank))).isNull(),
                () -> assertThat(pieces.get(new Position(File.H, rank))).isNull()
        );
    }
}
