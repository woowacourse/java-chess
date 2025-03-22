package chess.board;

import chess.Position;
import chess.piece.Piece;
import chess.piece.Pieces;
import java.util.List;

public class Board {

    private final Pieces pieces;

    public Board(Pieces pieces) {
        this.pieces = pieces;
    }

    public void move(Position current, Position destination) {
        Piece pickedPiece = pieces.findByPosition(current);
        int differenceX = destination.column().ordinal() - current.column().ordinal();
        int differenceY = current.row().ordinal() - destination.row().ordinal();
        if (pieces.existsByPosition(destination)) {
            Piece targetPiece = pieces.findByPosition(destination);
            if (pickedPiece.isSameColor(targetPiece)) {
                throw new IllegalArgumentException("같은 팀이 있는 위치로는 이동할 수 없습니다.");
            }
        }
        pickedPiece.move(differenceX, differenceY);
    }

    public List<Piece> getPieces() {
        return pieces.getPieces();
    }
}
