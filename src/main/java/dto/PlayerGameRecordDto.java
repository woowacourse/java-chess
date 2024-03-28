package dto;

public record PlayerGameRecordDto(
        int winCount,
        int loseCount,
        int drawCount
) {
}
