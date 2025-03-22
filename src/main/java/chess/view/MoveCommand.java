package chess.view;

import chess.board.Position;

public record MoveCommand(Position source, Position destination) {
}
