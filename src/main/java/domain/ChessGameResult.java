package domain;

public record ChessGameResult(
        Score whiteScore,
        Score blackScore,
        WinStatus winStatus
) {
}
