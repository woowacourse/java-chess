package chess.domain;

import chess.domain.board.Chessboard;
import chess.domain.board.Rank;
import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static chess.domain.piece.PieceType.*;

public class BoardInitializer {

    private BoardInitializer() {
    }

    private static final List<PieceType> ARRANGEMENT_WITHOUT_PAWN;

    static {
        ARRANGEMENT_WITHOUT_PAWN = List.of(
                ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK
        );
    }

    public static void initializeBoard(final Chessboard chessboard) {
        setWhitePieces(chessboard);
        setBlackPieces(chessboard);
    }

    private static void setBlackPieces(final Chessboard chessboard) {
        chessboard.putPiece(Rank.EIGHT, initializeWithoutPawn(Camp.BLACK));
        chessboard.putPiece(Rank.SEVEN, initializePawn(Camp.BLACK));
    }

    private static void setWhitePieces(final Chessboard chessboard) {
        chessboard.putPiece(Rank.TWO, initializePawn(Camp.WHITE));
        chessboard.putPiece(Rank.ONE, initializeWithoutPawn(Camp.WHITE));
    }

    private static List<Piece> initializeWithoutPawn(final Camp camp) {
        return ARRANGEMENT_WITHOUT_PAWN.stream()
                .map(pieceType -> pieceType.createPiece(camp))
                .collect(Collectors.toList());
    }

    private static List<Piece> initializePawn(final Camp camp) {
        List<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Piece pawn = PieceType.PAWN.createPiece(camp);
            pieces.add(pawn);
        }
        return pieces;
    }
}
