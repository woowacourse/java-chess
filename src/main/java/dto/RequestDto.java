package dto;

import domain.File;
import domain.GameCommand;
import domain.Rank;

public record RequestDto(GameCommand gameCommand, Rank rank, File file) {
    public static RequestDto of(GameCommand gameCommand) {
        // TODO: null 값 해결
        return new RequestDto(gameCommand, null, null);
    }

    public static RequestDto of(GameCommand gameCommand, Rank rank, File file) {
        return new RequestDto(gameCommand, rank, file);
    }
}
