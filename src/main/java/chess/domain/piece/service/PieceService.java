package chess.domain.piece.service;

import chess.domain.piece.Piece;
import chess.domain.piece.PiecesState;
import chess.domain.piece.factory.PieceType;
import chess.domain.piece.position.Position;

public class PieceService {
    public static Piece movePiece(Position from, Position to, PiecesState piecesState) {
        Piece fromPiece = piecesState.getPiece(from);
        Piece toPiece = piecesState.getPiece(to);

        if (PieceType.canNotMove(fromPiece, to, piecesState)) {
            throw new IllegalArgumentException(String.format("%s 위치의 말을 %s 위치로 옮길 수 없습니다.", from, to));
        }

        return fromPiece.move(to, toPiece);
    }
}
