package domain.board;

import domain.piece.Piece;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;

import java.util.LinkedHashMap;
import java.util.Map;

public class SquaresGenerator {

    private final SettingRule settingRule = new SettingRule();

    public Map<Position, Piece> generate() {
        Map<Position, Piece> squares = new LinkedHashMap<>();
        for (Rank rank : Rank.valuesByOrder()) {
            generate(rank, squares);
        }
        return squares;
    }

    private void generate(Rank rank, Map<Position, Piece> squares) {
        for (File file : File.values()) {
            Piece piece = settingRule.findPieceByPosition(rank, file);
            squares.put(new Position(file, rank), piece);
        }
    }
}
