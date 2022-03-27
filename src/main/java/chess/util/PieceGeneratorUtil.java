package chess.util;

import static chess.util.PositionUtil.FILES_TOTAL_SIZE;

import chess.domain.piece.strongmen.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.strongmen.King;
import chess.domain.piece.strongmen.Knight;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.strongmen.Queen;
import chess.domain.piece.strongmen.Rook;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PieceGeneratorUtil {

    public static List<Piece> initAllChessmen() {
        List<Piece> pieces = new ArrayList<>(32);
        for (Color color : Color.values()) {
            pieces.addAll(initStrongmen(color));
            pieces.addAll(initPawnsOf(color));
        }
        return pieces;
    }

    private static List<Piece> initPawnsOf(Color color) {
        return IntStream.range(0, FILES_TOTAL_SIZE)
                .mapToObj(fileIdx -> Pawn.of(color, fileIdx))
                .collect(Collectors.toList());
    }

    private static List<Piece> initStrongmen(Color color) {
        return List.of(new King(color), new Queen(color),
                Rook.ofLeft(color), Rook.ofRight(color),
                Bishop.ofLeft(color), Bishop.ofRight(color),
                Knight.ofLeft(color), Knight.ofRight(color));
    }
}
