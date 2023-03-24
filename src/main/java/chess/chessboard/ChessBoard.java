package chess.chessboard;

import chess.Turn;
import chess.piece.EmptyPiece;
import chess.piece.Piece;

import java.util.Map;

public class ChessBoard {
    private final Map<Square, Piece> pieces;
    private Turn turn;

    public ChessBoard(Map<Square, Piece> pieces) {
        this.pieces = pieces;
        this.turn = Turn.initialTurn();
    }

    public boolean executeTurnMove(final Square source, final Square destination) {
        validateNotEmpty(pieces.get(source));

        if (isMovePieceSuccess(source, destination)) {
            turn = turn.nextTurn();
            return true;
        }
        return false;
    }

    private boolean isMovePieceSuccess(final Square source, final Square destination) {
        final Piece pieceToMove = pieces.get(source);

        return turn.isTurnOf(pieceToMove) && movePiece(source, destination);
    }

    boolean movePiece(final Square source, final Square destination) {
        final Piece pieceToMove = pieces.get(source);
        validateNotEmpty(pieceToMove);

        if (isPieceMovable(source, destination)) {
            movePiece(source, destination, pieceToMove);
            return true;
        }
        return false;
    }

    private boolean isPieceMovable(final Square source, final Square destination) {
        final Piece pieceToMove = pieces.get(source);
        final Piece pieceOfDestination = pieces.get(destination);

        return pieceToMove.isMovable(source, destination, pieceOfDestination) && !hasObstacleAlongPath(source, destination);
    }

    private void movePiece(final Square source, final Square destination, final Piece pieceToMove) {
        pieces.put(destination, pieceToMove);
        pieces.put(source, EmptyPiece.getInstance());
    }

    private void validateNotEmpty(final Piece target) {
        if (target == EmptyPiece.getInstance()) {
            throw new IllegalArgumentException("기물이 존재하지 않습니다");
        }
    }

    private boolean hasObstacleAlongPath(final Square source, final Square destination) {
        return source.squaresOfPath(destination)
                     .stream()
                     .map(pieces::get)
                     .anyMatch(piece -> piece != EmptyPiece.getInstance());
    }

    public Map<Square, Piece> getPieces() {
        return Map.copyOf(pieces);
    }
}
