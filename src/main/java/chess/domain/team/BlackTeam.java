package chess.domain.team;

import chess.domain.Position;
import chess.domain.piece.Piece;

public class BlackTeam extends Team {
    private static final int BLACK_PAWN_COLUMN = 6;
    private static final int BLACK_PIECE_COLUMN = 7;
    private static final int BLACK_PAWN_DIRECTION = -1;

    public BlackTeam() {
        super();
        initializePawn(BLACK_PAWN_COLUMN, BLACK_PAWN_DIRECTION);
        initializePiece(BLACK_PIECE_COLUMN);
    }

    @Override
    public void move(final Position current, final Position destination) {
        final Piece chosenPiece = piecePosition.get(current);
        piecePosition.remove(current);
        piecePosition.put(destination, chosenPiece);
    }
}
