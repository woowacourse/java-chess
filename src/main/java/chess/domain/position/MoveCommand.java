package chess.domain.position;

import chess.domain.position.Square;

public record MoveCommand(Square source, Square destination) {
}
