package chess.domain.piece;

import java.util.Optional;
import java.util.function.Function;

import chess.domain.board.File;
import chess.domain.board.Rank;

public enum Direction {

    // TODO: 2020/03/25 Rank와 File 위치 변경
    NORTH(Rank::next, Optional::of),
    NORTH_EAST(Rank::next, File::next),
    EAST(Optional::of, File::next),
    SOUTH_EAST(Rank::previous, File::next),
    SOUTH(Rank::previous, Optional::of),
    SOUTH_WEST(Rank::previous, File::previous),
    WEST(Optional::of, File::previous),
    NORTH_WEST(Rank::next, File::previous),
    NNE(rank -> rank.jump(2), File::next),
    NEE(Rank::next, file -> file.jump(2)),
    SEE(Rank::previous, file -> file.jump(2)),
    SSE(rank -> rank.jump(-2), File::next),
    SSW(rank -> rank.jump(-2), File::previous),
    SWW(Rank::previous, file -> file.jump(-2)),
    NWW(Rank::next, file -> file.jump(-2)),
    NNW(rank -> rank.jump(2), File::previous);

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
