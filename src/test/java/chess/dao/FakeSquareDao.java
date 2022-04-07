package chess.dao;

import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.HashMap;
import java.util.Map;

public class FakeSquareDao implements SquareDaoInterface {

    private Map<String, String> values = new HashMap<>();

    @Override
    public void save(Position position, Piece piece) {
        values.put(position.getKey(), piece.getTeam() + "_" + piece.getSymbol());
    }

    @Override
    public Map<String, String> find() {
        return values;
    }

    @Override
    public void delete() {
        values = new HashMap<>();
    }

    @Override
    public void update(String position, Piece piece) {
        values.put(position, piece.getTeam() + "_" + piece.getSymbol());
    }
}
