package view.dto;

import view.GameCommand;

public record GameProceedRequest(GameCommand gameCommand, String sourcePosition, String targetPosition) {
}
