package dto;

public record GameResultResponse(Double whiteScore, Double blackScore, Boolean isWhiteKingDead,
                                 Boolean isBlackKingDead) {
}
