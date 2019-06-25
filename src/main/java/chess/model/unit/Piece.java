package chess.model.unit;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class Piece {
    private static final Map<UnitType, Function<Side, Piece>> createMap = new HashMap<>();

    static{
        createMap.put(UnitType.KING, King::new);
        createMap.put(UnitType.QUEEN, Queen::new);
        createMap.put(UnitType.BISHOP, Bishop::new);
        createMap.put(UnitType.KNIGHT, Knight::new);
        createMap.put(UnitType.PAWN, Pawn::new);
        createMap.put(UnitType.ROOK, Rook::new);
    }

    private UnitType type;
    private Side side;

    Piece(UnitType type, Side side) {
        this.type = type;
        this.side = side;
    }
}
