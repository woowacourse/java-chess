package chess.domain.chess.game;

import chess.domain.chess.game.initializer.SettableChessBoardInitializer;
import chess.domain.chess.unit.Unit;
import chess.domain.chess.unit.UnitSymbolMapper;
import chess.domain.geometric.Position;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static chess.domain.geometric.Position.MAX_POSITION;

public class ChessBoardConverter {

    public static String convertsToString(ChessBoard chessBoard) {
        String chessBoardInfo = "";
        for (int y = MAX_POSITION; y >= Position.MIN_POSITION; y--) {
            chessBoardInfo += convertsSingleRowToString(chessBoard, y);
        }
        return chessBoardInfo;
    }

    private static String convertsSingleRowToString(ChessBoard chessBoard, int y) {
        String row = "";
        for (int x = Position.MIN_POSITION; x <= MAX_POSITION; x++) {
            Optional<Unit> optional = chessBoard.getUnit(Position.create(x, y));
            row += convertsUnitToString(optional);
        }
        return row;
    }

    private static String convertsUnitToString(Optional<Unit> optional) {
        if (optional.isPresent()) {
            return optional.get().toString();
        }
        return ".";
    }

    public static ChessBoard convertsStringToChessBoard(String units, Team team) {
        Map<Position, Unit> map = new HashMap<>();
        for (int y = MAX_POSITION; y >= Position.MIN_POSITION; y--) {
            addUnitsInSingleRow(map, units.split(""), y);
        }
        return new ChessBoard(new SettableChessBoardInitializer(map, team));
    }

    private static void addUnitsInSingleRow(Map<Position, Unit> map, String[] units, int y) {
        for (int x = Position.MIN_POSITION; x <= MAX_POSITION; x++) {
            addUnit(map, units[y * (MAX_POSITION + 1) + x], x, y);
        }
    }

    private static void addUnit(Map<Position, Unit> map, String unit, int x, int y) {
        Optional<Unit> optionalUnit = UnitSymbolMapper.getUnit(unit);
        optionalUnit.ifPresent(unit1 -> map.put(Position.create(x, MAX_POSITION - y), unit1));
    }
}
