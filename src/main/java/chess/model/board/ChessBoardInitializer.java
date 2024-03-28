package chess.model.board;

import chess.model.piece.*;
import chess.model.position.Position;
import chess.model.position.File;
import chess.model.position.Rank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Collections.unmodifiableMap;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class ChessBoardInitializer {
    private static final int FIRST_BLANK_RANK_COORDINATE = 3;
    private static final int LAST_BLANK_RANK_COORDINATE = 6;

    public Map<Position, Piece> create() {
        Map<Position, Piece> board = new HashMap<>();
        board.putAll(createSpecialPieces(Side.BLACK));
        board.putAll(createPawns(Side.BLACK));
        board.putAll(createSpecialPieces(Side.WHITE));
        board.putAll(createPawns(Side.WHITE));
        board.putAll(createBlanks());
        return unmodifiableMap(board);
    }

    private Map<Position, Piece> createSpecialPieces(Side side) {
        Rank rank = convertSpecialPieceRankWithSide(side);
        return Map.of(
                Position.of(File.A, rank), Rook.from(side),
                Position.of(File.B, rank), Knight.from(side),
                Position.of(File.C, rank), Bishop.from(side),
                Position.of(File.D, rank), Queen.from(side),
                Position.of(File.E, rank), King.from(side),
                Position.of(File.F, rank), Bishop.from(side),
                Position.of(File.G, rank), Knight.from(side),
                Position.of(File.H, rank), Rook.from(side)
        );
    }

    private Rank convertSpecialPieceRankWithSide(Side side) {
        if (side.isBlack()) {
            return Rank.EIGHT;
        }
        return Rank.ONE;
    }

    private Map<Position, Piece> createPawns(Side side) {
        Rank rank = convertPawnRanksWithSide(side);
        return Arrays.stream(File.values())
                .collect(toMap(
                        file -> Position.of(file, rank),
                        file -> Pawn.from(side))
                );
    }

    private Rank convertPawnRanksWithSide(Side side) {
        if (side.isBlack()) {
            return Rank.SEVEN;
        }
        return Rank.TWO;
    }

    private Map<Position, Piece> createBlanks() {
        return IntStream.range(FIRST_BLANK_RANK_COORDINATE, LAST_BLANK_RANK_COORDINATE + 1)
                .boxed()
                .flatMap(this::createBlankPositionStream)
                .collect(toMap(identity(), position -> Blank.INSTANCE));
    }

    private Stream<Position> createBlankPositionStream(int rankCoordinate) {
        return Arrays.stream(File.values())
                .map(file -> Position.of(file, Rank.from(rankCoordinate)));
    }
}
