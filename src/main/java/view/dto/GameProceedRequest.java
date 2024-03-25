package view.dto;

import view.GameCommand;

import java.util.Optional;

public record GameProceedRequest(GameCommand gameCommand, Optional<String> sourcePosition, Optional<String> targetPosition) {
    public GameProceedRequest(GameCommand gameCommand){
        this(gameCommand, Optional.empty(), Optional.empty());
    }
}
