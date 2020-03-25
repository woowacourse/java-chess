package chess.domain.piece;

import chess.domain.board.File;
import chess.domain.board.Rank;

import java.util.Optional;
import java.util.function.Function;

public enum Direction {

    NORTH(Rank::next, Optional::of),
    NORTH_EAST(Rank::next, File::next),
    EAST(Optional::of, File::next),
    SOUTH_EAST(Rank::previous, File::next),
    SOUTH(Rank::previous, Optional::of),
    SOUTH_WEST(Rank::previous, File::previous),
    WEST(Optional::of, File::previous),
    NORTH_WEST(Rank::next, File::previous);

    private Function<Rank, Optional<Rank>> rankDestination;
    private Function<File, Optional<File>> fileDestination;

    Direction(Function<Rank, Optional<Rank>> rankDestination, Function<File, Optional<File>> fileDestination) {
        this.rankDestination = rankDestination;
        this.fileDestination = fileDestination;
    }

    public Optional<Rank> findRankDestination(Rank rank) {
        return rankDestination.apply(rank);
    }

    public Optional<File> findFileDestination(File file) {
        return fileDestination.apply(file);
    }
}
