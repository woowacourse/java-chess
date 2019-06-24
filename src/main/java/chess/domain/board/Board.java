package chess.domain.board;

import chess.domain.piece.piecefigure.Blank;
import chess.domain.piece.piecefigure.Piece;
import chess.domain.piece.pieceinfo.PieceType;
import chess.exception.NotMovablePositionException;

import java.util.Map;
import java.util.Set;

public class Board {
    private final Map<Position, Piece> pieces;

    public Board(final Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public boolean movePiece(Position source, Position destination) {
        if (!getPossiblePositions(source).contains(destination)) {
            throw new NotMovablePositionException();
        }

        boolean result = (pieces.get(destination).getPieceType() == PieceType.KING);

        pieces.put(destination, pieces.get(source));
        pieces.put(source, Blank.of());
        return result;
    }

    public Set<Position> getPossiblePositions(Position source) {
        return pieces.get(source).makePossiblePositions(source, this::getCurrentPiece);
    }

    public Piece getCurrentPiece(Position current) {
        return pieces.get(current);
    }
}
