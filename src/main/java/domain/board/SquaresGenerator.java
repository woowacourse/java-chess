package domain.board;

import domain.piece.Piece;
import java.util.ArrayList;
import java.util.List;

public class SquaresGenerator {

    private final SettingRule settingRule = new SettingRule();

    public List<Square> generate() {
        List<Square> squares = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            generateSquares(rank, squares);
        }
        return squares;
    }

    private void generateSquares(Rank rank, List<Square> squares) {
        for (File file : File.values()) {
            Piece piece = settingRule.findPieceByPosition(rank, file);
            Square square = new Square(new Position(rank, file), piece);
            squares.add(square);
        }
    }
}
