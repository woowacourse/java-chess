package console.view;

import chess.*;
import java.util.*;

public class OutputView {

    private static final Map<PieceType, String> PIECE_SYMBOL = Map.of(PieceType.KING, "K",
            PieceType.QUEEN, "Q",
            PieceType.ROOK, "R",
            PieceType.BISHOP, "B",
            PieceType.PAWN, "P",
            PieceType.KNIGHT, "N");

    public static void printInitChessGameMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printChessBoard(List<Piece> pieces) {
        for (Col col : Col.orderedValues()) {
            printEachColumn(pieces, col);
        }
        System.out.println();
    }

    private static void printEachColumn(List<Piece> pieces, Col col) {
        for (Row row : Row.orderedValues()) {
            printEachRow(pieces, col, row);
        }
        System.out.println();
    }

    private static void printEachRow(List<Piece> pieces, Col col, Row row) {
        Position position = new Position(row, col);
        Optional<Piece> pieceOptional = findByPosition(pieces, position);
        pieceOptional.ifPresentOrElse(
            piece -> System.out.print(pieceSymbol(piece)),
            () -> System.out.print("."));
    }

    private static Optional<Piece> findByPosition(List<Piece> pieces, Position position) {
        return pieces.stream()
            .filter(piece -> piece.isSamePosition(position))
            .findFirst();
    }

    private static String pieceSymbol(Piece piece) {
        if (piece.getColor() == Color.WHITE) {
            return PIECE_SYMBOL.get(piece.getType()).toLowerCase(Locale.ROOT);
        }
        return PIECE_SYMBOL.get(piece.getType());
    }
}
