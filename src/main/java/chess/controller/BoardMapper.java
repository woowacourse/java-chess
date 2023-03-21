package chess.controller;

import chess.domain.Board;
import chess.domain.position.Rank;
import java.util.Arrays;
import java.util.stream.Collectors;

public class BoardMapper {
    
    public static String map(Board board) {
        return Arrays.stream(Rank.values())
                .map(rank -> RankMapper.map(board, rank))
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
