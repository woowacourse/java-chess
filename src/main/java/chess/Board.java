package chess;

import chess.piece.Piece;
import java.util.List;
import java.util.Optional;

public class Board {
    private final List<Piece> pieces;

    public Board(List<Piece> pieces) {
        this.pieces = pieces;
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
