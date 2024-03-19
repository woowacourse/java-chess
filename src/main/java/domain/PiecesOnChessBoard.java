package domain;

import java.util.List;
import java.util.Optional;

public class PiecesOnChessBoard {
    private final List<Piece> pieces;

    public PiecesOnChessBoard(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public Optional<Team> whichTeam(Position position) {
        Optional<Piece> pieceOnPosition = pieces.stream().filter(piece -> piece.isOn(position))
                .findFirst();
        return pieceOnPosition.map(Piece::getTeam);
    }
}
