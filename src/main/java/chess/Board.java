package chess;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Board {
    private final List<Piece> pieces;

    public Board(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public static Board initialize() {
        List<Piece> pieces = new ArrayList<>();

        for (Color color : Color.validColors()) {
            pieces.addAll(Bishop.initialize(color));
            pieces.addAll(King.initialize(color));
            pieces.addAll(Knight.initialize(color));
            pieces.addAll(Pawn.initialize(color));
            pieces.addAll(Queen.initialize(color));
            pieces.addAll(Rook.initialize(color));
        }
        return new Board(pieces);
    }

    //TODO 존재하는 포지션만 주기

    public Hurdles findHurdlePositions() {
        return new Hurdles(pieces.stream()
                .map(Piece::getPosition)
                .toList());
    }

    public Optional<Piece> findByPosition(Position newPosition) {
        return pieces.stream()
                .filter(piece -> piece.getPosition().equals(newPosition)) //TODO 수정
                .findFirst();
    }
}
