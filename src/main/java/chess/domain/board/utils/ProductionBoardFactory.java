package chess.domain.board.utils;

import static chess.domain.board.Rank.EIGHT;
import static chess.domain.board.Rank.ONE;
import static chess.domain.board.Rank.SEVEN;
import static chess.domain.board.Rank.TWO;
import static chess.domain.piece.constant.PieceColor.BLACK;
import static chess.domain.piece.constant.PieceColor.EMPTY;
import static chess.domain.piece.constant.PieceColor.WHITE;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import chess.domain.board.Rank;
import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.constant.PieceColor;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.function.Function;

public class ProductionBoardFactory extends BoardFactory {

    private static final EmptyPiece EMPTY_PIECE = new EmptyPiece(EMPTY);

    private static final Function<PieceColor, List<Piece>> PIECES_CREATOR_BY_COLOR =
            (PieceColor color) -> List.of(
                    new Rook(color), new Knight(color), new Bishop(color), new Queen(color),
                    new King(color), new Bishop(color), new Knight(color), new Rook(color)
            );

    private final static BoardFactory CACHE = new ProductionBoardFactory();

    private ProductionBoardFactory() {
    }

    public static BoardFactory getInstance() {
        return CACHE;
    }

    public Map<Position, Piece> create() {
        final Map<Position, Piece> result = new HashMap<>();

        putAllEmptyPieces(result);
        putBlackPieces(result);
        putWhitePieces(result);

        return result;
    }

    private void putAllEmptyPieces(Map<Position, Piece> result) {
        for (Rank rank : Rank.reverseValues()) {
            for (File file : File.values()) {
                Position findPosition = Positions.findPosition(file, rank);
                result.put(findPosition, EMPTY_PIECE);
            }
        }
    }

    private void putWhitePieces(Map<Position, Piece> result) {
        putPawns(result, WHITE, TWO);
        putRemainPiecesExceptPawn(result, WHITE, ONE);
    }

    private void putBlackPieces(Map<Position, Piece> result) {
        putPawns(result, BLACK, SEVEN);
        putRemainPiecesExceptPawn(result, BLACK, EIGHT);
    }

    private void putPawns(Map<Position, Piece> result, PieceColor color, Rank rank) {
        for (File file : File.values()) {
            Position findPosition = Positions.findPosition(file, rank);
            result.put(findPosition, new Pawn(color));
        }
    }

    private void putRemainPiecesExceptPawn(Map<Position, Piece> result, PieceColor color, Rank rank) {

        ListIterator<Piece> piecesIterator = PIECES_CREATOR_BY_COLOR.apply(color).listIterator();
        for (File file : File.values()) {
            Position findPosition = Positions.findPosition(file, rank);
            result.put(findPosition, piecesIterator.next());
        }
    }
}
