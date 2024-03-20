package view.dto;

import domain.board.Position;

public record MovePositionDto(Position sourcePosition, Position targetPosition) {

}
