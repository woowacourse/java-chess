package chess.controller;

import chess.domain.Board;
import chess.domain.position.Rank;
import java.util.stream.Collectors;

public class RankMapper {
    
    public static String map(final Board board, final Rank rank) {
        return board.getPiecesAt(rank).stream().map(PieceMapper::map).collect(Collectors.joining());
    }
}
