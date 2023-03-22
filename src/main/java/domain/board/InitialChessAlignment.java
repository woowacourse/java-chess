package domain.board;

import domain.piece.*;
import domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public final class InitialChessAlignment implements ChessAlignment {
    @Override
    public Map<Position, Piece> makeInitialPieces() {
        final HashMap<Position, Piece> board = new HashMap<>();

        addPieces(new Bishop(Team.BLACK), new Bishop(Team.WHITE), board);
        addPieces(new Pawn(Team.BLACK), new Pawn(Team.WHITE), board);
        addPieces(new Rook(Team.BLACK), new Rook(Team.WHITE), board);
        addPieces(new King(Team.BLACK), new King(Team.WHITE), board);
        addPieces(new Knight(Team.BLACK), new Knight(Team.WHITE), board);
        addPieces(new Queen(Team.BLACK), new Queen(Team.WHITE), board);

        return board;
    }

    private void addPieces(final Piece black, final Piece white, final Map<Position, Piece> board) {
        black.getInitialBlackPositions().forEach(position -> board.put(position, black));
        white.getInitialWhitePositions().forEach(position -> board.put(position, white));
    }
}
