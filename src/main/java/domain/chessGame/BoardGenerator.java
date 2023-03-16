package domain.chessGame;

import domain.piece.Piece;
import domain.position.Position;

import java.util.Map;

@FunctionalInterface
public interface BoardGenerator {

    public Map<Position, Piece> generate();
}
