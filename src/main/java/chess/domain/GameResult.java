package chess.domain;

import java.util.Map;
import java.util.Set;

public enum GameResult {
    BLACK_WIN, WHITE_WIN, KEEP, DRAW;

    /**
     * 양 팀의 킹이 살아있는지 확인하여 게임을 계속할 지 결정한다.
     *
     * @param livingPieces
     * @return
     */
    public static GameResult judge(Set<PieceType> livingPieces) {
        boolean isBlackKingLiving = livingPieces.contains(PieceType.KING_BLACK);
        boolean isWhiteKingLiving = livingPieces.contains(PieceType.KING_WHITE);

        if (isBlackKingLiving && isWhiteKingLiving) {
            return KEEP;
        }
        if (isBlackKingLiving) {
            return BLACK_WIN;
        }
        if (isWhiteKingLiving) {
            return WHITE_WIN;
        }

        throw new IllegalArgumentException("결과를 확인할 수 없습니다." + livingPieces);
    }

    /**
     * 양 팀의 승점을 계산하여 승패를 결정한다.
     *
     * @param boardState
     * @return
     */
    public static GameResult judgeScore(Map<CoordinatePair, PieceType> boardState) {
        ChessScoreCounter counter = new ChessScoreCounter(boardState);
        double blackScore = counter.getScore(Team.BLACK);
        double whiteScore = counter.getScore(Team.WHITE);
        if (blackScore > whiteScore) {
            return BLACK_WIN;
        }
        if (blackScore < whiteScore) {
            return WHITE_WIN;
        }
        return DRAW;
    }

}
