package chess.domain.position;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Piece;

import java.util.Objects;

public class Source {
    private final Piece piece;

    private Source(final Piece piece) {
        this.piece = piece;
    }

    public static Source valueOf(final Position position, final ChessBoard chessBoard) {
        if (Objects.isNull(chessBoard.findPiece(position))) {
            throw new IllegalArgumentException(String.format("해당 위치에 기물없음! 입력 값: %s", position));
        }
        return new Source(chessBoard.findPiece(position));
    }

    public boolean isSameColor(Piece piece) {
        return this.piece.isSameColor(piece);
    }

    public boolean isSamePosition(Position piece) {
        return this.piece.isSamePosition(piece);
    }

    public void move(Target target, ChessBoard chessBoard) {
        piece.move(target, chessBoard);
    }
}
