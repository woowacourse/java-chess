package chess.model.boardinitializer;

import static chess.vo.PieceColor.*;
import static chess.vo.Rank.*;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.function.Function;

import chess.model.piece.Bishop;
import chess.model.piece.EmptyPiece;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;
import chess.model.piece.Queen;
import chess.model.piece.Rook;
import chess.vo.File;
import chess.vo.PieceColor;
import chess.vo.Position;
import chess.vo.Rank;

public class defaultInitializer implements BoardInitializer {

    private static final EmptyPiece EMPTY_PIECE = new EmptyPiece(EMPTY);
    private static final Function<PieceColor, List<Piece>> INIT_PIECE_FUNCTION = (PieceColor pieceColor) -> List.of(
        new Rook(pieceColor), new Knight(pieceColor), new Bishop(pieceColor),
        new Queen(pieceColor), new King(pieceColor), new Bishop(pieceColor),
        new Knight(pieceColor), new Rook(pieceColor));

    @Override
    public Map<Position, Piece> apply() {
        Map<Position, Piece> result = new HashMap<>();

        putAllEmptyPieces(result);
        putBlackPieces(result);
        putWhitePieces(result);

        return result;
    }

    private void putAllEmptyPieces(Map<Position, Piece> result) {
        for (Rank rank : Rank.reverseValues()) {
            putEmptyPiecesInOneRank(result, rank);
        }
    }

    private void putEmptyPiecesInOneRank(Map<Position, Piece> result, Rank rank) {
        for (File file : File.values()) {
            result.put(new Position(rank, file), EMPTY_PIECE);
        }
    }

    private void putBlackPieces(Map<Position, Piece> result) {
        putPawns(result, BLACK, SEVEN);
        putRemainPiecesExceptPawn(result, BLACK, EIGHT);
    }

    private void putWhitePieces(Map<Position, Piece> result) {
        putPawns(result, WHITE, TWO);
        putRemainPiecesExceptPawn(result, WHITE, ONE);
    }

    private void putPawns(Map<Position, Piece> result, PieceColor color, Rank rank) {
        for (File file : File.values()) {
            result.put(new Position(rank, file), new Pawn(color));
        }
    }

    private void putRemainPiecesExceptPawn(Map<Position, Piece> result, PieceColor color, Rank rank) {
        ListIterator<Piece> piecesIterator = INIT_PIECE_FUNCTION.apply(color).listIterator();

        for (File file : File.values()) {
            result.put(new Position(rank, file), piecesIterator.next());
        }
    }
}
