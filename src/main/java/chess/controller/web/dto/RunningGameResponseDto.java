package chess.controller.web.dto;

import java.util.Map;

public class RunningGameResponseDto {
    private final Map<Long, String> runningGames;

    public RunningGameResponseDto(Map<Long, String> runningGames) {
        this.runningGames = runningGames;
    }

    public Map<Long, String> getRunningGames() {
        return runningGames;
    }
}
