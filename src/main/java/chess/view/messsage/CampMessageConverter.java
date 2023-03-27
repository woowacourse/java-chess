package chess.view.messsage;

import chess.model.piece.Camp;
import java.util.Arrays;

public enum CampMessageConverter {

    BLACK(Camp.BLACK, "검은색"),
    WHITE(Camp.WHITE, "흰색");

    private final Camp camp;
    private final String message;

    CampMessageConverter(final Camp camp, final String message) {
        this.camp = camp;
        this.message = message;
    }

    public static String convert(final Camp camp) {
        return Arrays.stream(values())
                .filter(converter -> converter.camp.isSameCamp(camp))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 진영입니다."))
                .message;
    }
}
