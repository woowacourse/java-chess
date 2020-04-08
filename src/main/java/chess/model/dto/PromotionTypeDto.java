package chess.model.dto;

public class PromotionTypeDto {

    private final String promotionType;
    private final String gameId;

    public PromotionTypeDto(String promotionType, String gameId) {
        this.promotionType = promotionType;
        this.gameId = gameId;
    }

    public String getPromotionType() {
        return promotionType;
    }

    public String getGameId() {
        return gameId;
    }
}
