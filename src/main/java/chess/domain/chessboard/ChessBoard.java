package chess.domain.chessboard;

import chess.domain.Score;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.position.Position;
import java.util.Map;
import java.util.Optional;

public class ChessBoard {

    private final Map<Position, ChessPiece> pieceByPosition;

    public ChessBoard(final Map<Position, ChessPiece> pieceByPosition) {
        this.pieceByPosition = pieceByPosition;
    }

    public Optional<ChessPiece> findPiece(final Position position) {
        final ChessPiece piece = pieceByPosition.get(position);
        return Optional.ofNullable(piece);
    }

    public void move(final Position from, final Position to) {
        final ChessPiece movablePiece = findPiece(from)
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다."));

        checkCanMove(from, to, movablePiece);
        movePiece(from, to);
    }

    private void checkCanMove(final Position from, final Position to, final ChessPiece movablePiece) {
        movablePiece.checkMovablePosition(from, to, findPiece(to));
        checkHurdle(from, to, movablePiece);
    }

    private void checkHurdle(final Position from, final Position to, final ChessPiece movablePiece) {
        final boolean hurdleExist = movablePiece.findRoute(from, to).stream()
                .map(this::findPiece)
                .anyMatch(Optional::isPresent);

        if (hurdleExist) {
            throw new IllegalArgumentException("이동 경로 사이에 다른 기물이 있습니다.");
        }
    }

    private void movePiece(final Position from, final Position to) {
        final ChessPiece movablePiece = pieceByPosition.remove(from);
        pieceByPosition.put(to, movablePiece);
    }

    public boolean isKingDie() {
        final long kingCount = pieceByPosition.values()
                .stream()
                .filter(ChessPiece::isKing)
                .count();
        return kingCount != 2;
    }

    public Map<Position, ChessPiece> findAllPiece() {
        return pieceByPosition;
    }

    public Score calculateScore() {
        return new Score(pieceByPosition);
    }
}
