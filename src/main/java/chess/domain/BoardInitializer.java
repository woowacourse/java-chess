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
    private static final int FILE_LENGTH = 8;

    private static final List<PieceType> ARRANGEMENT_WITHOUT_PAWN;

    static {
        ARRANGEMENT_WITHOUT_PAWN = List.of(
                ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK
        );
    }

    private BoardInitializer() {
    }

    public static void initializeBoard(Chessboard chessboard) {
        setWhitePieces(chessboard);
        setBlackPieces(chessboard);
    }

    private static void setWhitePieces(Chessboard chessboard) {
        chessboard.putPiece(Rank.TWO, initializePawn(Camp.WHITE));
        chessboard.putPiece(Rank.ONE, initializeWithoutPawn(Camp.WHITE));
    }

    private static void setBlackPieces(Chessboard chessboard) {
        chessboard.putPiece(Rank.EIGHT, initializeWithoutPawn(Camp.BLACK));
        chessboard.putPiece(Rank.SEVEN, initializePawn(Camp.BLACK));
    }

    private static List<Piece> initializeWithoutPawn(Camp camp) {
        return ARRANGEMENT_WITHOUT_PAWN.stream()
                .map(pieceType -> pieceType.createPiece(camp))
                .collect(Collectors.toList());
    }

    private static List<Piece> initializePawn(Camp camp) {
        List<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < FILE_LENGTH; i++) {
            Piece pawn = PieceType.PAWN.createPiece(camp);
            pieces.add(pawn);
        }

        return pieces;
    }
}
