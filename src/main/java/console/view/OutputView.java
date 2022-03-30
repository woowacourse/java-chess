package console.view;

import chess.piece.Bishop;
import chess.piece.Color;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.position.File;
import chess.position.Position;
import chess.position.Rank;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class OutputView {

    private static final Map<Class<? extends Piece>, String> PIECE_SYMBOL = Map.of(King.class, "K",
            Queen.class, "Q",
            Rook.class, "R",
            Bishop.class, "B",
            Pawn.class, "P",
            Knight.class, "N");
    private static final String EMPTY_SPACE = ".";

    public static void printInitChessGameMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printChessBoard(Map<Position, Piece> board) {
        for (Rank rank : Rank.orderedValues()) {
            printEachColumn(board, rank);
        }
        System.out.println();
    }

    private static void printEachColumn(Map<Position, Piece> board, Rank rank) {
        for (File file : File.orderedValues()) {
            printEachRow(board, rank, file);
        }
        System.out.println();
    }

    private static void printEachRow(Map<Position, Piece> board, Rank rank, File file) {
        Position position = new Position(file, rank);
        Optional<Piece> pieceOptional = findByPosition(board, position);
        pieceOptional.ifPresentOrElse(
                piece -> System.out.print(pieceSymbol(piece)),
                () -> System.out.print(EMPTY_SPACE));
    }

    private static Optional<Piece> findByPosition(Map<Position, Piece> board, Position position) {
        if (board.containsKey(position)) {
            return Optional.of(board.get(position));
        }
        return Optional.empty();
    }

    private static String pieceSymbol(Piece piece) {
        if (piece.getColor() == Color.WHITE) {
            return PIECE_SYMBOL.get(piece.getClass()).toLowerCase(Locale.ENGLISH);
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
