package chess.domain.board;

import java.util.Map;
import chess.domain.piece.Blank;
import chess.domain.piece.MovePath;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;

public class ChessBoard {

    private static final int DEFAULT_ALIVE_KING_COUNT = 2;
    private final Map<Position, Piece> board;

    public ChessBoard(Map<Position, Piece> pieces) {
        this.board = pieces;
    }

    public static ChessBoard create() {
        return new ChessBoard(ChessBoardFactory.initChessBoard());
    }

    public void move(Position from, Position to) {
        final Piece sourcePiece = findByPiece(from);
        final Piece targetPiece = findByPiece(to);

        MovePath movePath = sourcePiece.findByMovePath(targetPiece);
        validatePath(movePath);

        sourcePiece.move(to);
        updateBoard(from, to);
    }

    public Piece findByPiece(Position position) {
        if (board.containsKey(position)) {
            return board.get(position);
        }

        throw new IllegalArgumentException("해당 위치에 아무 값도 없습니다.");
    }

    private void validatePath(MovePath movePath) {
        if (movePath.hasNext()) {
            validateBlank(movePath.next());
        }
    }

    private void validateBlank(Position position) {
        Piece piece = findByPiece(position);

        if (!piece.isBlank()) {
            throw new IllegalArgumentException("이동 경로에 장애물이 존재하여 이동할 수 없습니다.");
        }
    }

    private void updateBoard(Position from, Position to) {
        Piece piece = findByPiece(from);
        board.put(to, piece);
        board.put(from, new Blank(from));
    }

    public boolean isFinished() {
        return getAliveKingCount() != DEFAULT_ALIVE_KING_COUNT;
    }

    private long getAliveKingCount() {
        return board.values().stream()
                .filter(Piece::isKing)
                .count();
    }

    public boolean hasKing(PieceColor pieceColor) {
        return board.values().stream()
                .anyMatch(piece -> piece.isKing() && piece.isSameColor(pieceColor));
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }
}
