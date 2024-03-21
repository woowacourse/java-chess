package domain.board;

import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.None;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.info.File;
import domain.piece.info.Rank;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public enum BoardInitiator {
    BLACK_PAWN(File.pawnPosition(), Rank.blackPawnRank(), Pawn::black),
    BLACK_ROOK(File.rookPosition(), Rank.blackOtherRank(), Rook::black),
    BLACK_KNIGHT(File.knightPosition(), Rank.blackOtherRank(), Knight::black),
    BLACK_BISHOP(File.bishopPosition(), Rank.blackOtherRank(), Bishop::black),
    BLACK_QUEEN(File.queenPosition(), Rank.blackOtherRank(), Queen::black),
    BLACK_KING(File.kingPosition(), Rank.blackOtherRank(), King::black),

    WHITE_PAWN(File.pawnPosition(), Rank.whitePawnRank(), Pawn::white),
    WHITE_ROOK(File.rookPosition(), Rank.whiteOtherRank(), Rook::white),
    WHITE_KNIGHT(File.knightPosition(), Rank.whiteOtherRank(), Knight::white),
    WHITE_BISHOP(File.bishopPosition(), Rank.whiteOtherRank(), Bishop::white),
    WHITE_QUEEN(File.queenPosition(), Rank.whiteOtherRank(), Queen::white),
    WHITE_KING(File.kingPosition(), Rank.whiteOtherRank(), King::white),

    NONE(File.nonePosition(), Rank.nonePosition(), None::none);

    private final List<File> files;
    private final List<Rank> rank;
    private final Supplier<Piece> piece;

    BoardInitiator(final List<File> files, final List<Rank> rank, final Supplier<Piece> piece) {
        this.files = files;
        this.rank = rank;
        this.piece = piece;
    }

    public static Map<Position, Piece> init() {
        final Map<Position, Piece> squares = new LinkedHashMap<>();
        for (final BoardInitiator value : values()) {
            setPositionOnSquare(value, squares);
        }

        return squares;
    }

    private static void setPositionOnSquare(final BoardInitiator value, final Map<Position, Piece> squares) {
        final List<Position> positions = makePosition(value.files, value.rank);

        for (final Position position : positions) {
            squares.put(position, value.piece.get());
        }
    }

    private static List<Position> makePosition(final List<File> files, final List<Rank> ranks) {
        final List<Position> positions = new ArrayList<>();
        for (final File file : files) {
            setRankOfPosition(ranks, file, positions);
        }
        return positions;
    }

    private static void setRankOfPosition(final List<Rank> ranks, final File file, final List<Position> positions) {
        for (final Rank rank : ranks) {
            positions.add(new Position(file, rank));
        }
    }
}
