package chess.dto;

public enum GameStatusDto {

    READY("ready"),
    PLAYING("playing"),
    FINISHED("finished");

    private final String name;

    GameStatusDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
