package chess.domain.board;

import chess.domain.piece.Color;
import java.util.ArrayList;
import java.util.List;

public class BoardFactory {

    public static Board createBoard() {
        List<Rank> ranks = new ArrayList<>();
        for (RankCoordinate rankCoordinate : RankCoordinate.values()) {
            Color color = Color.of(rankCoordinate);
            RankType rankType = RankType.of(rankCoordinate);
            Rank rank = RankFactory.createRank(rankType, rankCoordinate, color);
            ranks.add(rank);
        }
        return new Board(ranks);
    }
}
