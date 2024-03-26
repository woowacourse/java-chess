package chess.controller;

import chess.domain.piece.Color;
import chess.domain.position.Position;

public record MovePositionDto(Position sourcePosition, Position targetPosition, Color color) {

}
