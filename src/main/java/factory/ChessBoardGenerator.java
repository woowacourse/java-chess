package factory;

import domain.*;
import domain.piece.Piece;
import domain.piece.Pieces;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.File;
import domain.piece.attribute.point.Point;
import domain.piece.attribute.point.Rank;
import domain.piece.kind.*;

import domain.piece.kind.sliding.Bishop;
import domain.piece.kind.sliding.Rook;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessBoardGenerator {
    private ChessBoardGenerator() {
        throw new UnsupportedOperationException("생성할 수 없습니다");
    }

    public static ChessBoard createDefaultBoard() {
        final var list = new ArrayList<Piece>();
        list.addAll(selectPiece(Rank.EIGHT, Color.BLACK));
        list.addAll(selectPawn(Rank.SEVEN, Color.BLACK));

        list.addAll(selectPawn(Rank.TWO, Color.WHITE));
        list.addAll(selectPiece(Rank.ONE, Color.WHITE));
        return new ChessBoard(new Pieces(list));
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
