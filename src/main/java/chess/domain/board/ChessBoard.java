package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.strategy.KingMovementStrategy;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {

    private final List<Piece> pieces;

    private ChessBoard(final List<Piece> pieces) {
        this.pieces = pieces;
    }

    static ChessBoard from(final List<Piece> pieces) {
        return new ChessBoard(pieces);
    }

    public void movePiece(final Turn turn,
                          final PiecePosition source,
                          final PiecePosition destination) {
        final Piece fromPiece = findByPosition(source);
        final Piece destinationPiece = findNullableByPosition(destination);
        validateMissMatchSelect(turn, fromPiece);
        validateNonBlock(fromPiece, destination, destinationPiece);
        move(fromPiece, destination, destinationPiece);
    }

    private void validateMissMatchSelect(final Turn turn, final Piece from) {
        if (turn.misMatch(from.color())) {
            throw new IllegalArgumentException("상대 말을 선택하셨습니다.");
        }
    }

    private void validateNonBlock(final Piece fromPiece,
                                  final PiecePosition destination,
                                  final Piece destinationPiece) {
        final List<PiecePosition> waypoints = fromPiece.waypoints(destination, destinationPiece);
        if (isBlocking(waypoints)) {
            throw new IllegalArgumentException("경로 상에 말이 있어서 이동할 수 없습니다.");
        }
    }

    private boolean isBlocking(final List<PiecePosition> waypoints) {
        return waypoints.stream()
                .anyMatch(this::existByPosition);
    }

    private void move(final Piece fromPiece, final PiecePosition destination, final Piece destinationPiece) {
        final Piece movedPiece = fromPiece.move(destination, destinationPiece);
        pieces.remove(fromPiece);
        if (destinationPiece != null) {
            pieces.remove(destinationPiece);
        }
        pieces.add(movedPiece);
    }

    private boolean existByPosition(final PiecePosition piecePosition) {
        return pieces.stream()
                .anyMatch(piece -> piece.existIn(piecePosition));
    }

    private Piece findNullableByPosition(final PiecePosition piecePosition) {
        return pieces.stream()
                .filter(piece -> piece.existIn(piecePosition))
                .findAny()
                .orElse(null);
    }

    public Piece findByPosition(final PiecePosition piecePosition) {
        return pieces.stream()
                .filter(piece -> piece.existIn(piecePosition))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 존재하는 피스가 없습니다."));
    }

    public boolean existKingByColor(final Color color) {
        return pieces.stream()
                .filter(it -> it.isKing())
                .anyMatch(it -> it.color() == color);
    }

    public List<Piece> pieces() {
        return new ArrayList<>(pieces);
    }
}
