package chess.view;

import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class OutputView {
    private static final int RANK = 8;
    private static final int FILE = 8;

    private OutputView() {
    }

    public static void showChessBoard(final Map<Position, Piece> chessBoard) {
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

    public static void showScore(final String name, final double calculateScore) {
        System.out.println(String.format("%s의 점수: %.1f", name , calculateScore));
        System.out.println();
    }

    private static String checkBoard(final Map<Position, Piece> chessBoard, final Position position) {
        if (Objects.isNull(chessBoard.get(position))) {
            return ".";
        }
        return chessBoard.get(position).getPiece();
    }

    public static void showCurrentPlayer(final String name) {
        System.out.println(String.format("%s의 턴 입니다.",name));
    }
}
