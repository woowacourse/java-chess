package domain.chessboard;

import domain.piece.Piece;
import domain.position.Position;
import java.util.Map;

public class ChessBoard {

    Map<Position, Piece> board;

    public ChessBoard(final BoardGenerator boardGenerator) {
        this.board = boardGenerator.generate();
    }

    public String getSymbol(final Position position) {
        Piece piece = board.get(position);
        if (piece == null) {
            return ".";
        }
        return piece.symbol();
    }

    public void move(final Position source, final Position target) {
        validateTargetPieceColor(source, target);
        Piece selectedPiece = board.get(source);
        if (selectedPiece.isAvailableMove(source, target)) {
            board.put(source, null);
            board.put(target, selectedPiece);
            return;
        }
        throw new IllegalArgumentException();
    }

    private void validateTargetPieceColor(Position source, Position target) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);
        if (targetPiece != null && targetPiece.isSamePlayer(sourcePiece)) {
            throw new IllegalArgumentException();
        }
    }
}
