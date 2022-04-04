package chess.dto;

public class StatusDto {
    private final String white;
    private final String black;

    private StatusDto(String statusOfWhite, String statusOfBlack) {
        this.white = statusOfWhite;
        this.black = statusOfBlack;
    }

    public static StatusDto of(double statusOfWhite, double statusOfBlack) {
        return new StatusDto(String.format("%.1f", statusOfWhite), String.format("%.1f", statusOfBlack));
    }

    public String getWhite() {
        return white;
    }

    public String getBlack() {
        return black;
    }
}
