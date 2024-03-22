package chess.view.dto;

import chess.domain.board.Position;
import chess.domain.piece.Color;

public record MovePositionDto(Position sourcePosition, Position targetPosition, Color color) {

}
