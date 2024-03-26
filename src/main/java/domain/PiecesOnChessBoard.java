package domain;

import domain.piece.Piece;
import domain.piece.PieceWrapper;
import java.util.List;

public class PiecesOnChessBoard {
    private final List<PieceWrapper> pieces;

    PiecesOnChessBoard(List<Piece> pieces) {
        this.pieces = pieces.stream().map(PieceWrapper::new).toList();
    }

    public Team whichTeam(Position position) {
        return pieces.stream().filter(piece -> piece.isOn(position))
                .findFirst()
                .map(PieceWrapper::getTeam)
                .orElse(Team.NONE);
    }
}
