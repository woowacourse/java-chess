package chess.controller.dto;

import chess.domain.player.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserDTO {

    private final String name;
    private final int winCount;
    private final int loseCount;

    private UserDTO(String name, int winCount, int loseCount) {
        this.name = name;
        this.winCount = winCount;
        this.loseCount = loseCount;
    }

    public static List<UserDTO> createList(List<User> users) {
        return users.stream()
                .map(user -> new UserDTO(user.getName(), user.getWinCount(), user.getLoseCount()))
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }
}
