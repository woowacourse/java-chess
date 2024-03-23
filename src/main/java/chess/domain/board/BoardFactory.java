package chess.domain.board;

import chess.domain.square.File;
import chess.domain.square.Square;
import chess.domain.square.Rank;
import chess.domain.piece.CampType;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

import java.util.*;

public class BoardFactory {

    private static final List<PieceType> PIECE_TYPE_ORDER = List.of(PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP,
            PieceType.QUEEN, PieceType.KING, PieceType.BISHOP, PieceType.KNIGHT, PieceType.ROOK);
    private static final List<Rank> BLACK_PIECE_ZONE = List.of(Rank.SEVEN, Rank.EIGHT);
    private static final List<Rank> PAWN_ZONE = List.of(Rank.TWO, Rank.SEVEN);
    private static final List<Rank> NORMAL_PIECE_ZONE = List.of(Rank.ONE, Rank.EIGHT);

    public BoardFactory() {
    }

    public Map<Square, Piece> create() {
        Map<Square, Piece> board = new HashMap<>();

        for (Rank rank : Rank.values()) {
            createPieceByRank(rank, board);
        }

        return board;
    }

    private void createPieceByRank(Rank rank, Map<Square, Piece> board) {
        if (NORMAL_PIECE_ZONE.contains(rank)) {
            createPieceByOrder(rank, board);
            return;
        }

        if (PAWN_ZONE.contains(rank)) {
            createPawnPiece(rank, board);
            return;
        }

        createEmptyPiece(rank, board);
    }

    private void createPawnPiece(Rank rank, Map<Square, Piece> board) {
        CampType campType = decideColorType(rank);

        for (File file : File.values()) {
            board.put(Square.of(file, rank), new Piece(PieceType.PAWN, campType));
        }
    }

    private void createPieceByOrder(Rank rank, Map<Square, Piece> board) {
        Iterator<File> fileIterator = Arrays.stream(File.values()).iterator();
        Iterator<PieceType> pieceTypeIterator = PIECE_TYPE_ORDER.iterator();

        CampType campType = decideColorType(rank);

        while (fileIterator.hasNext() && pieceTypeIterator.hasNext()) {
            File file = fileIterator.next();
            PieceType pieceType = pieceTypeIterator.next();

            board.put(Square.of(file, rank), new Piece(pieceType, campType));
        }
    }

    private CampType decideColorType(Rank rank) {
        if (BLACK_PIECE_ZONE.contains(rank)) {
            return CampType.BLACK;
        }

        return CampType.WHITE;
    }

    private void createEmptyPiece(Rank rank, Map<Square, Piece> board) {
        for (File file : File.values()) {
            board.put(Square.of(file, rank), new Piece(PieceType.EMPTY, CampType.EMPTY));
        }
    }
}
