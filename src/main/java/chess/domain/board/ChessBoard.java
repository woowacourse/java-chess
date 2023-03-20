package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChessBoard {

    private final List<Piece> pieces;

    private ChessBoard(final List<Piece> pieces) {
        this.pieces = pieces;
    }

    static ChessBoard from(final List<Piece> pieces) {
        return new ChessBoard(pieces);
    }

    public void movePiece(final Turn turn, final PiecePosition source, final PiecePosition destination) {
        final Piece from = findByPosition(source);
        final Piece to = optGet(destination).orElse(null);
        validateMissMatchSelect(turn, from);
        validateNonBlock(from, destination, to);
        move(from, destination, to);
    }

    private void validateMissMatchSelect(final Turn turn, final Piece from) {
        if (turn.misMatch(from.color())) {
            throw new IllegalArgumentException("상대 말을 선택하셨습니다.");
        }
    }

    private void validateNonBlock(final Piece from, final PiecePosition destination, final Piece to) {
        final List<PiecePosition> waypoints = from.waypoints(destination, to);
        if (isBlocking(waypoints)) {
            throw new IllegalArgumentException("경로 상에 말이 있어서 이동할 수 없습니다.");
        }
    }

    private boolean isBlocking(final List<PiecePosition> waypoints) {
        return waypoints.stream()
                .anyMatch(this::existByPosition);
    }

    private void move(final Piece from, final PiecePosition destination, final Piece to) {
        final Piece move = from.move(destination, to);
        pieces.remove(from);
        if (to != null) {
            pieces.remove(to);
        }
        pieces.add(move);
    }

    private boolean existByPosition(final PiecePosition piecePosition) {
        return pieces.stream()
                .anyMatch(piece -> piece.existIn(piecePosition));
    }

    private Optional<Piece> optGet(final PiecePosition piecePosition) {
        return pieces.stream()
                .filter(piece -> piece.existIn(piecePosition))
                .findAny();
    }

    Piece findByPosition(final PiecePosition piecePosition) {
        return pieces.stream()
                .filter(piece -> piece.existIn(piecePosition))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 존재하는 피스가 없습니다."));
    }

    public List<Piece> pieces() {
        return new ArrayList<>(pieces);
    }
}
