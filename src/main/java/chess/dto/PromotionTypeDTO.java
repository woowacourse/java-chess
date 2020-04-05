package chess.dto;

public class PromotionTypeDTO {

    private final String promotionType;

    public PromotionTypeDTO(String promotionType) {
        this.promotionType = promotionType;
    }

    public String getPromotionType() {
        return promotionType;
    }
}
