package domain.board;

import domain.dto.PieceDto;
import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.nonpawn.Bishop;
import domain.piece.nonpawn.King;
import domain.piece.nonpawn.Knight;
import domain.piece.nonpawn.Queen;
import domain.piece.nonpawn.Rook;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.WhitePawn;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoardFactory {
    private static final int SPECIAL_PIECE_SIZE = 8;
    private static final List<Piece> whiteSpecialPieces = List.of(
            new Rook(Color.WHITE), new Knight(Color.WHITE), new Bishop(Color.WHITE), new Queen(Color.WHITE),
            new King(Color.WHITE), new Bishop(Color.WHITE), new Knight(Color.WHITE), new Rook(Color.WHITE)
    );
    private static final List<Piece> blackSpecialPieces = List.of(
            new Rook(Color.BLACK), new Knight(Color.BLACK), new Bishop(Color.BLACK), new Queen(Color.BLACK),
            new King(Color.BLACK), new Bishop(Color.BLACK), new Knight(Color.BLACK), new Rook(Color.BLACK)
    );

    private ChessBoardFactory() {
    }

    public static ChessBoard createInitialChessBoard() {
        final Map<Position, Piece> pieceMap = new HashMap<>();
        for (int order = 0; order < SPECIAL_PIECE_SIZE; order++) {
            pieceMap.put(new Position(File.fromOrder(order), Rank.EIGHT), blackSpecialPieces.get(order));
            pieceMap.put(new Position(File.fromOrder(order), Rank.SEVEN), new BlackPawn());
            pieceMap.put(new Position(File.fromOrder(order), Rank.TWO), new WhitePawn());
            pieceMap.put(new Position(File.fromOrder(order), Rank.ONE), whiteSpecialPieces.get(order));
        }
        return new ChessBoard(pieceMap);
    }

    public static ChessBoard loadPreviousChessBoard(List<PieceDto> pieceDtos, final Color color) {
        return pieceDtos.stream()
                .collect(Collectors.collectingAndThen(Collectors.toMap(
                        PieceDto::getPosition,
                        PieceDto::getPiece
                ), board -> new ChessBoard(board, color)));
    }
}
