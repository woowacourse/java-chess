package chess.domain.board;

import chess.domain.square.Bishop;
import chess.domain.square.Color;
import chess.domain.square.Empty;
import chess.domain.square.King;
import chess.domain.square.Knight;
import chess.domain.square.Pawn;
import chess.domain.square.Queen;
import chess.domain.square.Rook;
import chess.domain.square.Square;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Row {

    private static final int ROW_SIZE = 8;
    private final List<Square> squares;

    private Row(List<Square> squares) {
        this.squares = squares;
    }

    public static Row ofEmpty() {
        List<Square> squares = Stream.generate(Empty::new)
                .limit(ROW_SIZE)
                .collect(Collectors.toList());
        return new Row(squares);
    }

    public static Row ofPawn(Color color) {
        List<Square> squares = Stream.generate(() -> new Pawn(color))
                .limit(ROW_SIZE)
                .collect(Collectors.toList());
        return new Row(squares);
    }

    public static Row ofMainPieces(Color color) {
        List<Square> squares = List.of(
                new Rook(color),
                new Knight(color),
                new Bishop(color),
                new Queen(color),
                new King(color),
                new Bishop(color),
                new Knight(color),
                new Rook(color));
        return new Row(squares);
    }

    public List<Square> getSquares() {
        return squares;
    }

    @Override
    public String toString() {
        return "Row{" + "squares=" + squares + '}';
    }
}
