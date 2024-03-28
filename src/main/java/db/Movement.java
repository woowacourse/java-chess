package db;

import domain.board.position.Position;

public record Movement(Position source, Position target, String shape, String color) {

}
