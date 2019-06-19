package chess.domain;

import java.util.List;

public enum  ChessResult {
    BLACK_WIN,WHITE_WIN,KEEP;

    public static ChessResult judge(List<PieceType> livingPieces) {
        boolean isBlackKingLiving = livingPieces.contains(PieceType.KING_BLACK);
        boolean isWhiteKingLiving = livingPieces.contains(PieceType.KING_WHITE);

        if ( isBlackKingLiving && isWhiteKingLiving) {
            return KEEP;
        }
        if (isBlackKingLiving) {
            return BLACK_WIN;
        }
        if (isWhiteKingLiving) {
            return WHITE_WIN;
        }

        throw new IllegalArgumentException("결과를 확인 할 수 없습니다." + livingPieces);
    }

}
