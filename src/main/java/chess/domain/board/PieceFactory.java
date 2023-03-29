package chess.domain.board;

import chess.domain.board.position.File;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class PieceFactory {

    private static final Map<Position, Piece> CACHE = new HashMap<>();

    static {
        CACHE.putAll(initializePiece(Team.WHITE, Rank.ONE));
        CACHE.putAll(initializePawn(Team.WHITE, Rank.TWO));
        CACHE.putAll(initializeEmptyPiece(List.of(Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX)));
        CACHE.putAll(initializePawn(Team.BLACK, Rank.SEVEN));
        CACHE.putAll(initializePiece(Team.BLACK, Rank.EIGHT));
    }

    public static Map<Position, Piece> createPiece() {
        return new HashMap<>(CACHE);
    }

    public static Map<Position, Piece> createEmptyPiece() {
        return initializeEmptyPiece(List.of(Rank.ONE, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX, Rank.SEVEN, Rank.EIGHT));
    }

    private static Map<Position, Piece> initializePiece(final Team team, final Rank rank) {
        final List<Piece> pieces = List.of(
                new Rook(team), new Knight(team), new Bishop(team), new Queen(team),
                new King(team), new Bishop(team), new Knight(team), new Rook(team)
        );
        final List<File> files = Arrays.stream(File.values()).collect(toList());

        return IntStream.range(0, pieces.size())
                .boxed()
                .collect(toMap(index -> Position.of(files.get(index), rank), pieces::get));
    }

    private static Map<Position, Piece> initializePawn(final Team team, final Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> Position.of(file, rank))
                .collect(toMap(Function.identity(), ignore -> new Pawn(team)));
    }

    private static Map<Position, Piece> initializeEmptyPiece(final List<Rank> ranks) {
        return ranks.stream()
                .flatMap(rank -> Arrays.stream(File.values()).map(file -> Position.of(file, rank)))
                .collect(toMap(Function.identity(), ignore -> new EmptyPiece()));
    }
}
