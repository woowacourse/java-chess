package chess.domain.chess;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

import java.util.Map;

public final class ChessGame {
    private final ChessBoard chessBoard;

    public ChessGame() {
        this.chessBoard = new ChessBoard();
    }

    public void playFirstTurn(final Position source, final Position target) {
        final Piece piece = chessBoard.checkPiece(source);
        validateMovablePiece(piece);
        if (piece.isPawn()) {
            movePawnFirst(source, target, piece);
            return;
        }
        moveKnightFirst(source, target, piece);
    }

    private void validateMovablePiece(final Piece piece) {
        if (piece.isBlackCamp() || !piece.isPawn() && !piece.isKnight()) {
            throw new IllegalArgumentException("처음은 백진영의 폰과 나이트만 움직일 수 있습니다.");
        }
    }

    private void movePawnFirst(final Position source, final Position target, final Piece piece) {
        final int rankGap = target.getRank() - source.getRank();
        if (source.getFile() != target.getFile() || rankGap < 0 || rankGap > 2) {
            throw new IllegalArgumentException("폰은 처음에 2칸 이내만 전진할 수 있습니다.");
        }
        movePiece(source, target, piece);
    }

    private void moveKnightFirst(final Position source, final Position target, final Piece piece) {
        final int rankGap = target.getRank() - source.getRank();
        final int fileGap = target.getFile() - source.getFile();
        if (Math.abs(rankGap * fileGap) != 2) {
            throw new IllegalArgumentException("잘못된 나이트의 이동 경로입니다.");
        }
        movePiece(source, target, piece);
    }

    private void movePiece(final Position source, final Position target, final Piece piece) {
        chessBoard.removePiece(source);
        chessBoard.putPiece(target, piece);
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard.getBoard();
    }
}
