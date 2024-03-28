package view.format;

import domain.WinStatus;

import java.util.Arrays;

public enum WinStatusFormat {
    BLACK_WIN("블랙 승", WinStatus.BLACK_WIN),
    WHITE_WIN("화이트 승", WinStatus.WHITE_WIN),
    DRAW("무승부", WinStatus.DRAW);

    private final String format;
    private final WinStatus winStatus;

    WinStatusFormat(final String format, final WinStatus winStatus) {
        this.format = format;
        this.winStatus = winStatus;
    }

    public static String formatOf(final WinStatus winStatus) {
        return Arrays.stream(values())
                .filter(status -> status.winStatus == winStatus)
                .map(status -> status.format)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("잘못된 우승 결과 입니다."));
    }
}
