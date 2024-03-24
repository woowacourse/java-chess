package chess.view;

import chess.domain.board.Coordinate;

public record MoveCommand(boolean isEnd, Coordinate source, Coordinate target) {
}
