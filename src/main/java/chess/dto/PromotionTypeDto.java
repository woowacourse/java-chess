package chess.dto;

public class PromotionTypeDto {

    private final String promotionType;

    public PromotionTypeDto(String promotionType) {
        this.promotionType = promotionType;
    }

    public String getPromotionType() {
        return promotionType;
    }
}
