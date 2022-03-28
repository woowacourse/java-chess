package console.view;

import chess.piece.*;
import chess.position.*;
import java.math.BigDecimal;
import java.util.*;

public class OutputView {

    private static final Map<Class<? extends Piece>, String> PIECE_SYMBOL = Map.of(King.class, "K",
            Queen.class, "Q",
            Rook.class, "R",
            Bishop.class, "B",
            Pawn.class, "P",
            Knight.class, "N");

    public static void printInitChessGameMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printChessBoard(List<Piece> pieces) {
        for (Rank rank : Rank.orderedValues()) {
            printEachColumn(pieces, rank);
        }
        System.out.println();
    }

    private static void printEachColumn(List<Piece> pieces, Rank rank) {
        for (File file : File.orderedValues()) {
            printEachRow(pieces, rank, file);
        }
        System.out.println();
    }

    private static void printEachRow(List<Piece> pieces, Rank rank, File file) {
        Position position = new Position(file, rank);
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
            return PIECE_SYMBOL.get(piece.getClass()).toLowerCase(Locale.ROOT);
        }
        return PIECE_SYMBOL.get(piece.getClass());
    }

    public static void printScores(BigDecimal whiteScore, BigDecimal blackScore) {
        System.out.printf("WHITE 점수: %s%n", whiteScore.toPlainString());
        System.out.printf("BLACK 점수: %s%n", blackScore.toPlainString());
    }

    public static void printWinner(Color winnerColor) {
        System.out.printf("%s 승리!", winnerColor);
    }
}
