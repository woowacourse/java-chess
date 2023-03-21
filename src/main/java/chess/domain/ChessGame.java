package chess.domain;

import chess.domain.board.Chessboard;
import chess.domain.board.Square;
import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public class ChessGame {
    private final Chessboard chessboard;
    private Camp turn;

    public ChessGame() {
        turn = Camp.WHITE;
        chessboard = new Chessboard();
        BoardInitializer.initializeBoard(chessboard);
    }

    public void move(Square source, Square target) {
        validateTurn(source);

        if (canMove(source, target)) {
            chessboard.swapPiece(source, target);
            turn = turn.getOpposite();
            return;
        }

        throw new IllegalArgumentException("이동할 수 없는 위치입니다");
    }

    private void validateTurn(Square square) {
        Piece pieceAtSquare = chessboard.getPieceAt(square);

        if (pieceAtSquare.isNotMoveOrder(turn)) {
            throw new IllegalArgumentException("해당 위치에는 당신의 Piece가 없습니다.");
        }
    }

    private boolean canMove(Square source, Square target) {
        Piece sourcePiece = chessboard.getPieceAt(source);

        if (source.equals(target)) {
            return false;
        }

        if (sourcePiece.canMove(source, target)) {
            return canMoveByPiece(source, target);
        }

        return false;
    }

    private boolean canMoveByPiece(Square source, Square target) {
        Piece sourcePiece = chessboard.getPieceAt(source);
        Piece targetPiece = chessboard.getPieceAt(target);

        if (sourcePiece.getPieceType() == PieceType.KNIGHT) {
            return canMoveKnight(sourcePiece, targetPiece);
        }

        if (sourcePiece.getPieceType() == PieceType.PAWN) {
            return canMovePawn(source, target);
        }

        return canMoveOtherPiece(source, target);
    }

    private boolean canMoveKnight(Piece sourcePiece, Piece targetPiece) {
        return sourcePiece.isNotSameCamp(targetPiece);
    }

    private boolean canMovePawn(Square source, Square target) {
        Piece sourcePiece = chessboard.getPieceAt(source);
        Piece targetPiece = chessboard.getPieceAt(target);

        if (source.isSameFile(target)) {
            return chessboard.isEmptyInRoute(source, target) &&
                    targetPiece.getPieceType() == PieceType.EMPTY;
        }

        return sourcePiece.isOpposite(targetPiece);
    }

    private boolean canMoveOtherPiece(Square source, Square target) {
        Piece sourcePiece = chessboard.getPieceAt(source);
        Piece targetPiece = chessboard.getPieceAt(target);

        if (chessboard.isEmptyInRoute(source, target)) {
            return sourcePiece.isNotSameCamp(targetPiece);
        }

        return false;
    }

    public void promotePawn(Square currentSquare, PieceType pieceType) {
        Piece pawn = chessboard.getPieceAt(currentSquare);

        if (pawn.isWhite()) {
            chessboard.putPiece(currentSquare, pieceType.createPiece(Camp.WHITE));
            return;
        }

        chessboard.putPiece(currentSquare, pieceType.createPiece(Camp.BLACK));
    }

    public boolean canPromotion(Square currentSquare) {
        Piece piece = chessboard.getPieceAt(currentSquare);

        return piece.getPieceType() == PieceType.PAWN
                && currentSquare.reachedEndRank();
    }

    public Chessboard getChessboard() {
        return chessboard;
    }
}
