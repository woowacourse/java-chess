package chess;

import chess.piece.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static chess.piece.PieceType.*;

public class BoardInitialization {
    private static final List<PieceType> ARRANGEMENT_WITHOUT_PAWN;

    static {
        ARRANGEMENT_WITHOUT_PAWN = List.of(
              ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK
        );
    }

    public void initializeBoard(Chessboard chessboard) {
        setWhitePieces(chessboard);
        setBlackPieces(chessboard);
    }

    private void setBlackPieces(Chessboard chessboard) {
        chessboard.putPiece(Rank.EIGHT, initializeWithoutPawn(Camp.BLACK));
        chessboard.putPiece(Rank.SEVEN, initializePawn(Camp.BLACK));
    }

    private void setWhitePieces(Chessboard chessboard) {
        chessboard.putPiece(Rank.TWO, initializePawn(Camp.WHITE));
        chessboard.putPiece(Rank.ONE, initializeWithoutPawn(Camp.WHITE));
    }

    private List<Piece> initializeWithoutPawn(Camp camp) {
        return ARRANGEMENT_WITHOUT_PAWN.stream()
                .map(pieceType -> pieceType.createPiece(camp))
                .collect(Collectors.toList());
    }

    private List<Piece> initializePawn(Camp camp) {
        List<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Piece pawn = PieceType.PAWN.createPiece(camp);
            pieces.add(pawn);
        }
        return pieces;
    }
}
