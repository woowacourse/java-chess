package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.File;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.type.Bishop;
import chess.domain.piece.type.King;
import chess.domain.piece.type.Knight;
import chess.domain.piece.type.Pawn;
import chess.domain.piece.type.Queen;
import chess.domain.piece.type.Rook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessBoardFactory {

    private static final List<Piece> pieces = new ArrayList<>();

    static {
        createPieceExcludingPawn(1, Color.WHITE);
        createPieceExcludingPawn(8, Color.BLACK);
        createPawns(2, Color.WHITE);
        createPawns(7, Color.BLACK);
    }

    private static void createPawns(final int rank, final Color color) {
        for (char file = File.MIN; file <= File.MAX; file++) {
            pieces.add(new Pawn(color, PiecePosition.of(file, rank)));
        }
    }

    private static void createPieceExcludingPawn(final int rank, final Color color) {
        pieces.add(new Rook(color, PiecePosition.of('a', rank)));
        pieces.add(new Knight(color, PiecePosition.of('b', rank)));
        pieces.add(new Bishop(color, PiecePosition.of('c', rank)));
        pieces.add(new Queen(color, PiecePosition.of('d', rank)));
        pieces.add(new King(color, PiecePosition.of('e', rank)));
        pieces.add(new Bishop(color, PiecePosition.of('f', rank)));
        pieces.add(new Knight(color, PiecePosition.of('g', rank)));
        pieces.add(new Rook(color, PiecePosition.of('h', rank)));
    }

    public static ChessBoard create() {
        return ChessBoard.from(pieces.stream()
                .map(Piece::clone)
                .collect(Collectors.toList()));
    }
}
