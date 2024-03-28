package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.square.Empty;
import chess.domain.square.Square;
import chess.domain.square.piece.Color;
import chess.domain.square.piece.Pawn;
import chess.domain.square.piece.unified.Bishop;
import chess.domain.square.piece.unified.King;
import chess.domain.square.piece.unified.Knight;
import chess.domain.square.piece.unified.Queen;
import chess.domain.square.piece.unified.Rook;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessBoardMakerTest {

    private static final int FILE_SIZE = 8;
    private static final int EMPTY_SQUARE_RANK_COUNT = 4;

    @DisplayName("체스판의 1번째 랭크는 흰색 룩, 나이트, 비숍, 퀸, 킹, 비숍, 나이트, 룩 순서로 이루어져 있다.")
    @Test
    void checkFirstRank() {
        final ChessBoardMaker chessBoardMaker = new ChessBoardMaker();
        final List<Square> expected = List.of(
                Rook.from(Color.WHITE), Knight.from(Color.WHITE), Bishop.from(Color.WHITE), Queen.from(Color.WHITE),
                King.from(Color.WHITE), Bishop.from(Color.WHITE), Knight.from(Color.WHITE), Rook.from(Color.WHITE));
        final List<Position> firstRankPositions = makeRankPositions(Rank.FIRST);

        final ChessBoard board = chessBoardMaker.make();
        final Map<Position, Square> squares = board.getSquares();
        final List<Square> actual = new ArrayList<>();
        firstRankPositions.forEach(position -> actual.add(squares.get(position)));

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @DisplayName("체스판의 2번째 랭크는 흰색 폰으로 순서로 이루어져 있다.")
    @Test
    void checkSecondRank() {
        final ChessBoardMaker chessBoardMaker = new ChessBoardMaker();
        final List<Square> expected = List.of(
                Pawn.from(Color.WHITE), Pawn.from(Color.WHITE), Pawn.from(Color.WHITE), Pawn.from(Color.WHITE),
                Pawn.from(Color.WHITE), Pawn.from(Color.WHITE), Pawn.from(Color.WHITE), Pawn.from(Color.WHITE));
        final List<Position> secondRankPositions = makeRankPositions(Rank.SECOND);

        final ChessBoard board = chessBoardMaker.make();
        final Map<Position, Square> squares = board.getSquares();
        final List<Square> actual = new ArrayList<>();
        secondRankPositions.forEach(position -> actual.add(squares.get(position)));

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @DisplayName("체스판의 3번째 링크~6번째 링크까지는 빈 칸으로 이루어져 있다.")
    @Test
    void checkThirdToSixthRank() {
        final ChessBoardMaker chessBoardMaker = new ChessBoardMaker();
        final List<Square> expected = new ArrayList<>();
        for (int i = 0; i < FILE_SIZE * EMPTY_SQUARE_RANK_COUNT; i++) {
            expected.add(Empty.getInstance());
        }
        final List<Position> rankPositions = new ArrayList<>(makeRankPositions(Rank.THIRD));
        rankPositions.addAll(makeRankPositions(Rank.FOURTH));
        rankPositions.addAll(makeRankPositions(Rank.FIFTH));
        rankPositions.addAll(makeRankPositions(Rank.SIXTH));

        final ChessBoard board = chessBoardMaker.make();
        final Map<Position, Square> squares = board.getSquares();
        final List<Square> actual = new ArrayList<>();
        rankPositions.forEach(position -> actual.add(squares.get(position)));

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @DisplayName("체스판의 7번째 랭크는 검정색 폰으로 순서로 이루어져 있다.")
    @Test
    void checkSeventhRank() {
        final ChessBoardMaker chessBoardMaker = new ChessBoardMaker();
        final List<Square> expected = List.of(
                Pawn.from(Color.BLACK), Pawn.from(Color.BLACK), Pawn.from(Color.BLACK), Pawn.from(Color.BLACK),
                Pawn.from(Color.BLACK), Pawn.from(Color.BLACK), Pawn.from(Color.BLACK), Pawn.from(Color.BLACK));
        final List<Position> secondRankPositions = makeRankPositions(Rank.SEVENTH);

        final ChessBoard board = chessBoardMaker.make();
        final Map<Position, Square> squares = board.getSquares();
        final List<Square> actual = new ArrayList<>();
        secondRankPositions.forEach(position -> actual.add(squares.get(position)));

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @DisplayName("체스판의 8번째 랭크는 검정색 룩, 나이트, 비숍, 퀸, 킹, 비숍, 나이트, 룩 순서로 이루어져 있다.")
    @Test
    void checkEighthRank() {
        final ChessBoardMaker chessBoardMaker = new ChessBoardMaker();
        final List<Square> expected = List.of(
                Rook.from(Color.BLACK), Knight.from(Color.BLACK), Bishop.from(Color.BLACK), Queen.from(Color.BLACK),
                King.from(Color.BLACK), Bishop.from(Color.BLACK), Knight.from(Color.BLACK), Rook.from(Color.BLACK));
        final List<Position> firstRankPositions = makeRankPositions(Rank.EIGHTH);

        final ChessBoard board = chessBoardMaker.make();
        final Map<Position, Square> squares = board.getSquares();
        final List<Square> actual = new ArrayList<>();
        firstRankPositions.forEach(position -> actual.add(squares.get(position)));

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    static List<Position> makeRankPositions(Rank rank) {
        return List.of(
                new Position(rank, File.A), new Position(rank, File.B),
                new Position(rank, File.C), new Position(rank, File.D),
                new Position(rank, File.E), new Position(rank, File.F),
                new Position(rank, File.G), new Position(rank, File.H));
    }
}
