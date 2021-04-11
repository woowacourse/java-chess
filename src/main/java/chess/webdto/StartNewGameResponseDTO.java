package chess.webdto;

public class StartNewGameResponseDTO {
    private final String availability;

    private StartNewGameResponseDTO(String availability) {
        this.availability = availability;
    }

    public static StartNewGameResponseDTO available() {
        return new StartNewGameResponseDTO("available");
    }

    public static StartNewGameResponseDTO unavailable() {
        return new StartNewGameResponseDTO("unavailable");
    }
}
