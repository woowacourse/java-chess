package chess.domain;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public enum ChessResult {
    BLACK_WIN, WHITE_WIN, KEEP;

    public static ChessResult judge(Board board) {
        Set<PieceType> livingPieces = board.getBoardState().entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toSet());

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

        throw new IllegalArgumentException("결과를 확인 할 수 없습니다." + livingPieces);
    }

}
