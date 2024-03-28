package chess.model.board;

import chess.model.piece.*;
import chess.model.position.Position;
import chess.model.position.File;
import chess.model.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


class ChessBoardInitializerTest {
    @Test
    @DisplayName("체스판을 초기화한다")
    void initChessBoard() {
        // given
        ChessBoardInitializer chessBoardInitializer = new ChessBoardInitializer();

        // when
        Map<Position, Piece> chessBoard = chessBoardInitializer.create();

        // then
        long blankCount = calculateCountBy(piece -> piece.equals(Blank.INSTANCE), chessBoard);
        long blackPawnCount = calculateCountBy(piece -> piece.equals(Pawn.from(Side.BLACK)), chessBoard);
        long whitePawnCount = calculateCountBy(piece -> piece.equals(Pawn.from(Side.WHITE)), chessBoard);

        assertAll(
                () -> assertThat(blankCount).isEqualTo(32),
                () -> assertThat(blackPawnCount).isEqualTo(8),
                () -> assertThat(whitePawnCount).isEqualTo(8),
                () -> assertThat(chessBoard).hasSize(64)
                        .containsAllEntriesOf(Map.of(
                                Position.of(File.A, Rank.EIGHT), Rook.from(Side.BLACK),
                                Position.of(File.B, Rank.EIGHT), Knight.from(Side.BLACK),
                                Position.of(File.C, Rank.EIGHT), Bishop.from(Side.BLACK),
                                Position.of(File.D, Rank.EIGHT), Queen.from(Side.BLACK),
                                Position.of(File.E, Rank.EIGHT), King.from(Side.BLACK),
                                Position.of(File.F, Rank.EIGHT), Bishop.from(Side.BLACK),
                                Position.of(File.G, Rank.EIGHT), Knight.from(Side.BLACK),
                                Position.of(File.H, Rank.EIGHT), Rook.from(Side.BLACK)))
                        .containsAllEntriesOf(Map.of(
                                Position.of(File.A, Rank.ONE), Rook.from(Side.WHITE),
                                Position.of(File.B, Rank.ONE), Knight.from(Side.WHITE),
                                Position.of(File.C, Rank.ONE), Bishop.from(Side.WHITE),
                                Position.of(File.D, Rank.ONE), Queen.from(Side.WHITE),
                                Position.of(File.E, Rank.ONE), King.from(Side.WHITE),
                                Position.of(File.F, Rank.ONE), Bishop.from(Side.WHITE),
                                Position.of(File.G, Rank.ONE), Knight.from(Side.WHITE),
                                Position.of(File.H, Rank.ONE), Rook.from(Side.WHITE))));
    }

    private long calculateCountBy(Predicate<Piece> condition, Map<Position, Piece> chessBoard) {
        return chessBoard.values()
                .stream()
                .filter(condition)
                .count();
    }

    @Test
    void calculateTotalScore() {
        ChessBoardInitializer chessBoardInitializer = new ChessBoardInitializer();
        Map<Position, Piece> chessBoard = chessBoardInitializer.create();
        Map<Piece, List<Position>> positionsByPiece = chessBoard.entrySet().stream()
                .collect(
                        groupingBy(Map.Entry::getValue, mapping(Map.Entry::getKey, toList()))
                );
        for(Piece piece: positionsByPiece.keySet()) System.out.println(piece.getClass());
    }
}
