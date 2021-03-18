package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.Map;

public class ChessBoard {
    private final Map<Position, Piece> board;

    public ChessBoard(Map<Position, Piece> board) {
        this.board = board;
    }

    public void initializeBoard() {
        PieceFactory.initializeWhitePiece()
                .forEach(piece -> board.putIfAbsent(piece.getPosition(), piece));
        PieceFactory.initializeBlackPiece()
                .forEach(piece -> board.putIfAbsent(piece.getPosition(), piece));
    }

    public Piece findPiece(final Position position) {
        return board.get(position);
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public void changePiecePosition(Piece source, Position target) {
        board.put(source.getPosition(), null);
        board.put(target, source);
    }

    public void put(Piece piece) {
        board.put(piece.getPosition(), piece);
    }
}
