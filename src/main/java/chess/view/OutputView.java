package chess.view;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class OutputView {
    private static final int RANK = 8;
    private static final int FILE = 8;

    private OutputView() {
    }

    public static void showChessBoard(Map<Position, Piece> chessBoard) {
        StringBuilder sb = new StringBuilder();
        for (int rank = RANK; rank > 0; rank--) {
            final int finalRank = rank;
            IntStream.rangeClosed(1, FILE)
                    .mapToObj(file -> Position.valueOf(Rank.findByValue(finalRank), File.findByValue(file)))
                    .forEach(position -> {
                        sb.append(checkBoard(chessBoard, position));
                    });
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static String checkBoard(Map<Position, Piece> chessBoard, Position position) {
        if (Objects.isNull(chessBoard.get(position))) {
            return ".";
        }
        return chessBoard.get(position).getPiece();
    }
}
