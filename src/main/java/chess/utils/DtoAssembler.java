package chess.utils;

import chess.domain.board.Rank;
import chess.domain.dto.RankDto;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.stream.Collectors;

public class DtoAssembler {

    public static List<RankDto> board(final List<Rank> ranks) {
        return ranks.stream()
            .map(rank -> new RankDto(
                rank.pieces()
                    .stream()
                    .map(Piece::getSymbol)
                    .collect(Collectors.toList()))
            ).collect(Collectors.toList());

    }
}
