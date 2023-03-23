package chess.domain.board;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.exception.PieceCannotMoveException;

import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> piecePosition;

    private ChessBoard(final Map<Position, Piece> piecePosition) {
        this.piecePosition = piecePosition;
    }

    public static ChessBoard createBoard() {
        final Map<Position, Piece> piecePosition = PieceFactory.createPiece();
        return new ChessBoard(piecePosition);
    }

    static ChessBoard createBoardByRule(final Map<Position, Piece> piecePosition) {
        return new ChessBoard(piecePosition);
    }

    public void movePiece(final Position from, final Position to) {
        Piece fromPiece = piecePosition.get(from);
        Piece toPiece = piecePosition.get(to);

        validateMovable(from, to);
        validateAlly(fromPiece, toPiece);
        validateRoute(from, to);

        move(from, to);
    }

    private void validateMovable(final Position from, final Position to) {
        final Piece fromPiece = piecePosition.get(from);
        if (!fromPiece.isMovable(from, to)) {
            throw new PieceCannotMoveException(fromPiece.getType());
        }
    }

    private void validateAlly(final Piece fromPiece, final Piece toPiece) {
        if (fromPiece.getTeam() == toPiece.getTeam()) {
            throw new IllegalArgumentException("도착지에 동일한 팀의 말이 존재합니다");
        }
    }

    private void validateRoute(final Position from, final Position to) {
        RouteFinder.findRoute(from, to).stream()
                .filter(position -> !piecePosition.get(position).isEmpty())
                .forEach(position -> {
                    throw new IllegalArgumentException("이동하려는 경로에 말이 존재합니다.");
                });
    }

    private void move(final Position from, final Position to) {
        final Piece piece = piecePosition.get(from);
        piecePosition.put(from, new EmptyPiece());
        piecePosition.put(to, piece);
    }

    public Map<Position, Piece> getPiecePosition() {
        return Map.copyOf(piecePosition);
    }
}
