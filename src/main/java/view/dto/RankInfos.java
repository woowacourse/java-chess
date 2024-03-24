package view.dto;

import static constants.Bound.BOARD_LOWER_BOUND;
import static constants.Bound.BOARD_UPPER_BOUND;

import domain.board.Board;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RankInfos {
    private final List<RankInfo> rankInfos;

    private RankInfos(List<RankInfo> rankInfos) {
        this.rankInfos = rankInfos;
    }

    public static RankInfos of(final Board board) {
        final List<RankInfo> rankInfos = new ArrayList<>();
        for (int rank = BOARD_UPPER_BOUND.value(); rank >= BOARD_LOWER_BOUND.value(); rank--) {
            final RankInfo pieceShapeOfRank = DtoMapper.getPieceShapeOn(board, rank);
            rankInfos.add(pieceShapeOfRank);
        }
        return new RankInfos(rankInfos);
    }


    public List<RankInfo> getRankInfos() {
        return Collections.unmodifiableList(rankInfos);
    }
}
