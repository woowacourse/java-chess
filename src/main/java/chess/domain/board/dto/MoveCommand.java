package chess.domain.board.dto;

import chess.domain.square.Square;

public record MoveCommand(Square source, Square destination) {
}
