package chess.view;

import chess.domain.piece.Camp;

import java.util.Arrays;

public enum ViewCamp {
    BLACK("흑", Camp.BLACK),
    WHITE("백", Camp.WHITE),
    NEUTRAL("무", Camp.NEUTRAL),
    ;

    private final String name;
    private final Camp camp;

    ViewCamp(final String name, final Camp camp) {
        this.name = name;
        this.camp = camp;
    }

    public static String getCampName(Camp camp) {
        return Arrays.stream(ViewCamp.values())
                .filter(viewCamp -> viewCamp.camp==camp)
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("올바르지 않은 명령어 입니다."))
                .name;
    }
}
