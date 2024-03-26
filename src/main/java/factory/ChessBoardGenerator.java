package factory;

import domain.*;
import domain.chess.piece.Piece;
import domain.chess.piece.Pieces;
import domain.chess.Color;
import domain.chess.File;
import domain.chess.Point;
import domain.chess.Rank;
import domain.chess.piece.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessBoardGenerator {
    private ChessBoardGenerator() {
        throw new UnsupportedOperationException("생성할 수 없습니다");
    }

    public static ChessBoard createDefaultBoard() {
        final ArrayList<Piece> list = new ArrayList<>();
        list.addAll(selectPiece(Rank.EIGHT, Color.BLACK));
        list.addAll(selectPawn(Rank.SEVEN, Color.BLACK));

        list.addAll(selectPawn(Rank.TWO, Color.WHITE));
        list.addAll(selectPiece(Rank.ONE, Color.WHITE));
        return new ChessBoard(new Pieces(list), Color.WHITE);
    }

    private static List<Piece> selectPawn(final Rank rank, final Color color) {
        return Arrays.stream(File.values())
                     .map(file -> new Pawn(new Point(file, rank), color))
                     .map(Piece.class::cast)
                     .toList();
    }

    private static List<Piece> selectPiece(final Rank rank, final Color color) {
        return Arrays.stream(File.values())
                     .map(file -> switch (file) {
                         case A, H -> new Rook(new Point(file, rank), color);
                         case B, G -> new Knight(new Point(file, rank), color);
                         case C, F -> new Bishop(new Point(file, rank), color);
                         case D -> new Queen(new Point(file, rank), color);
                         case E -> new King(new Point(file, rank), color);
                     })
                     .toList();
    }
}
