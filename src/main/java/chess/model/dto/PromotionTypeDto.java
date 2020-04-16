package chess.model.dto;

public class PromotionTypeDto {

    private final String promotionType;
    private final int gameId;

    public PromotionTypeDto(String promotionType, int gameId) {
        this.promotionType = promotionType;
        this.gameId = gameId;
    }

    public String getPromotionType() {
        return promotionType;
    }

    public int getGameId() {
        return gameId;
    }
}
