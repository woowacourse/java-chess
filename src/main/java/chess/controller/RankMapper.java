package chess.controller;

import chess.domain.board.PieceProvider;
import chess.domain.position.Rank;
import java.util.stream.Collectors;

public final class RankMapper {
    
    private RankMapper() {
    }
    
    public static String map(final PieceProvider board, final Rank rank) {
        return board.getRankPieces(rank).stream().map(PieceMapper::map).collect(Collectors.joining());
    }
}
