package chess.domain.game;

import chess.domain.location.Position;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.Map;

public class EmptyBoardMap {
    public static Map<Position, Piece> create() {
        Piece empty = new Empty();
        Map<Position, Piece> maps = new HashMap<>();
        for (Position position : Position.all()) {
            maps.put(position, empty);
        }
        return maps;
    }
}
