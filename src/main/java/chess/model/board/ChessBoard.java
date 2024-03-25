package chess.model.board;

import chess.model.piece.Piece;
import chess.model.position.ChessPosition;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ChessBoard {
    private final Map<ChessPosition, Piece> board;

    public ChessBoard(final Map<ChessPosition, Piece> board) {
        this.board = new HashMap<>(board);
    }

    public void move(final ChessPosition sourcePosition, final ChessPosition targetPosition) {
        final Piece sourcePiece = board.get(sourcePosition);
        final Piece targetPiece = board.get(targetPosition);
        validateSourcePiece(sourcePiece);

        final List<ChessPosition> path = sourcePiece.findPath(sourcePosition, targetPosition, targetPiece);
        validatePathContainsPiece(path);
        changePositions(sourcePosition, targetPosition, sourcePiece, targetPiece);
    }

    private void changePositions(
            final ChessPosition sourcePosition, final ChessPosition targetPosition,
            final Piece sourcePiece, final Piece targetPiece
    ) {
        board.put(targetPosition, sourcePiece);
        board.put(sourcePosition, sourcePiece.catchTargetPiece(targetPiece));
    }

    private void validateSourcePiece(final Piece sourcePiece) {
        if (sourcePiece.isEmpty()) {
            throw new IllegalArgumentException("Source에 기물이 존재하지 않습니다.");
        }
    }

    private void validatePathContainsPiece(final List<ChessPosition> path) {
        int repeatCount = path.size() - 1;
        IntStream.range(0, repeatCount)
                .mapToObj(path::get)
                .map(board::get)
                .forEach(this::validatePathContainsPiece);
    }

    private void validatePathContainsPiece(final Piece found) {
        if (!found.isEmpty()) {
            throw new IllegalArgumentException("이동 경로에 기물이 존재하여 움직일 수 없습니다.");
        }
    }

    public Map<ChessPosition, Piece> getBoard() {
        return board;
    }
}
