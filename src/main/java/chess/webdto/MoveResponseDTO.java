package chess.webdto;

public class MoveResponseDTO {
    private final String movement;

    private MoveResponseDTO(String movement) {
        this.movement = movement;
    }

    public static MoveResponseDTO success() {
        return new MoveResponseDTO("success");
    }

    public static MoveResponseDTO fail() {
        return new MoveResponseDTO("fail");
    }
}
