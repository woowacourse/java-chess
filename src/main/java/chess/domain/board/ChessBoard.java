package chess.domain.board;

import chess.domain.board.position.PiecePosition;
import chess.domain.piece.Piece;

import java.util.Map;

public class ChessBoard {

    private final Map<PiecePosition, Piece> pieces;

    private ChessBoard(final Map<PiecePosition, Piece> pieces) {
        this.pieces = pieces;
    }

    public static ChessBoard create() {
        return new ChessBoard(ChessBoardFactory.create());
    }

    public Piece get(final PiecePosition piecePosition) {
        return pieces.keySet().stream()
                .filter(piecePosition::equals)
                .map(pieces::get)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 존재하는 피스가 없습니다."));
    }

    public Map<PiecePosition, Piece> pieces() {
        return pieces;
    }
}
