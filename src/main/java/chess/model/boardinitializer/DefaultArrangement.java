package chess.model.boardinitializer;

import static chess.model.PieceColor.*;
import static chess.model.Rank.*;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.function.Function;

import chess.model.File;
import chess.model.PieceColor;
import chess.model.Position;
import chess.model.Rank;
import chess.model.piece.Bishop;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;
import chess.model.piece.Queen;
import chess.model.piece.Rook;

public class DefaultArrangement implements PieceArrangement {

    public static final Rank PAWN_WHITE_INIT_RANK = TWO;
    public static final Rank PAWN_BLACK_INIT_RANK = SEVEN;
    private static final Function<PieceColor, List<Piece>> INIT_PIECE_FUNCTION = (PieceColor pieceColor) -> List.of(
        Rook.colorOf(pieceColor), Knight.colorOf(pieceColor), Bishop.colorOf(pieceColor),
        Queen.colorOf(pieceColor), King.colorOf(pieceColor), Bishop.colorOf(pieceColor),
        Knight.colorOf(pieceColor), Rook.colorOf(pieceColor));

    @Override
    public Map<Position, Piece> apply() {
        Map<Position, Piece> result = new HashMap<>();

        putBlackPieces(result);
        putWhitePieces(result);

        return result;
    }

    private void putBlackPieces(Map<Position, Piece> result) {
        putPawns(result, BLACK, PAWN_BLACK_INIT_RANK);
        putRemainPiecesExceptPawn(result, BLACK, EIGHT);
    }

    private void putWhitePieces(Map<Position, Piece> result) {
        putPawns(result, WHITE, PAWN_WHITE_INIT_RANK);
        putRemainPiecesExceptPawn(result, WHITE, ONE);
    }

    private void putPawns(Map<Position, Piece> result, PieceColor color, Rank rank) {
        for (File file : File.values()) {
            result.put(Position.of(file, rank), Pawn.colorOf(color));
        }
    }

    private void putRemainPiecesExceptPawn(Map<Position, Piece> result, PieceColor color, Rank rank) {
        ListIterator<Piece> piecesIterator = INIT_PIECE_FUNCTION.apply(color).listIterator();

        for (File file : File.values()) {
            result.put(Position.of(file, rank), piecesIterator.next());
        }
    }
}
