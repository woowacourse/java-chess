package chess.domain.team;

import chess.domain.Position;
import chess.domain.piece.Piece;

public class WhiteTeam extends Team {
    private static final int WHITE_PAWN_COLUMN = 1;
    private static final int WHITE_PIECE_COLUMN = 0;

    public WhiteTeam() {
        super();
        initializePiece(WHITE_PAWN_COLUMN, WHITE_PIECE_COLUMN);
    }

    @Override
    public void move(final Position current, final Position destination) {
        final Piece chosenPiece = piecePosition.get(current);
        piecePosition.remove(current);
        piecePosition.put(destination, chosenPiece);
    }
}
