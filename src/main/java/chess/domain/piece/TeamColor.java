package chess.domain.piece;

import static chess.domain.board.position.Rank.EIGHT;
import static chess.domain.board.position.Rank.ONE;

import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;

public enum TeamColor {
    WHITE(EIGHT),
    BLACK(ONE),
    ;

    private final Rank promotionRank;

    TeamColor(final Rank promotionRank) {
        this.promotionRank = promotionRank;
    }

    public static TeamColor findByRank(final Rank rank) {
        if (isWhiteTeamRank(rank)) {
            return WHITE;
        }
        if (isBlackTeam(rank)) {
            return BLACK;
        }
        throw new IllegalArgumentException("팀 컬러를 식별할 수 있는 Rank는 1,2,7,8 입니다.");
    }

    private static boolean isWhiteTeamRank(final Rank rank) {
        return rank == Rank.ONE || rank == Rank.TWO;
    }

    private static boolean isBlackTeam(final Rank rank) {
        return rank == Rank.SEVEN || rank == EIGHT;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public TeamColor turnToNext() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    public boolean isPromotablePosition(final Position position) {
        return position.isInRank(promotionRank);
    }

}
