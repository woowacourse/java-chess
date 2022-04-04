package chess.controller.dto;

public class PromotionRequest {

    private final String promotionValue;

    public PromotionRequest(String promotionValue) {
        this.promotionValue = promotionValue;
    }

    public String getPromotionValue() {
        return promotionValue;
    }
}
