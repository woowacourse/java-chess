package chess.domain.game;

import static chess.domain.position.util.PositionUtil.FILES_TOTAL_SIZE;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ActivePieces {

    private final List<Piece> pieces = new ArrayList<>(32);

    public ActivePieces() {
        for (Color color : Color.values()) {
            pieces.addAll(initStrongmen(color));
            pieces.addAll(initPawnsOf(color));
        }
    }

    private List<Piece> initPawnsOf(Color color) {
        return IntStream.range(0, FILES_TOTAL_SIZE)
                .mapToObj(fileIdx -> Pawn.of(color, fileIdx))
                .collect(Collectors.toList());
    }

    private List<Piece> initStrongmen(Color color) {
        return List.of(new King(color), new Queen(color),
                Rook.ofLeft(color), Rook.ofRight(color),
                Bishop.ofLeft(color), Bishop.ofRight(color),
                Knight.ofLeft(color), Knight.ofRight(color));
    }

    public List<Piece> getAll() {
        return Collections.unmodifiableList(pieces);
    }
}
