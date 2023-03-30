package chess.controller;

import chess.domain.board.PieceProvider;
import chess.domain.position.Rank;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public final class BoardMapper {
    
    private BoardMapper() {
    }
    
    public static String map(PieceProvider board) {
        return Arrays.stream(Rank.values())
                .sorted(Comparator.reverseOrder())
                .map(rank -> RankMapper.map(board, rank))
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
