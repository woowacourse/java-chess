package chess.view;

import chess.model.ChessBoard;
import chess.model.piece.Piece;
import chess.model.piece.types.*;
import chess.model.position.Column;
import chess.model.position.Position;
import chess.model.position.Row;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class OutputView {

    private static final Map<Row, String> ROW_FORMAT = Map.of(
            Row.ONE, "1",
            Row.TWO, "2",
            Row.THREE, "3",
            Row.FOUR, "4",
            Row.FIVE, "5",
            Row.SIX, "6",
            Row.SEVEN, "7",
            Row.EIGHT, "8"
    );

    private static final Map<Class<? extends Piece>, String> PIECE_FORMAT = Map.of(
            Bishop.class, "B",
            King.class, "K",
            Knight.class, "N",
            Pawn.class, "P",
            Queen.class, "Q",
            Rook.class, "R"
    );

    public static void printChessBoard(ChessBoard board) {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("----------------------");
        System.out.println("아래는 현재 체스판 입니다.");

        StringBuffer sb = new StringBuffer();
        sb.append("  ");
        Arrays.stream(Column.values()).forEach(column -> sb.append(column).append(" "));
        sb.append("\n");

        for (Row row : Row.values()) {
            sb.append(ROW_FORMAT.get(row) + " ");
            for (Column column : Column.values()) {
                sb.append(findPieceFormat(board, row, column) + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static String findPieceFormat(ChessBoard board, Row row, Column column) {
        Optional<Piece> optionalPiece = board.findPieceByPosition(new Position(column, row));
        if (optionalPiece.isEmpty()) {
            return "-";
        }
        Piece piece = optionalPiece.get();
        String pieceText = PIECE_FORMAT.get(piece.getClass());
        if (piece.getColor().isBlack()) {
            return pieceText;
        }
        return pieceText.toLowerCase(Locale.ROOT);
    }
}
