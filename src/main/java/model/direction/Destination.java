package model.direction;

import model.piece.Piece;
import model.position.Position;

public record Destination(Position position, Piece target) {
}
