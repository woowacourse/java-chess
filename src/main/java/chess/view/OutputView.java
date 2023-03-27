package chess.view;

import chess.domain.board.position.Position;
import chess.domain.board.score.Score;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.jumper.King;
import chess.domain.piece.jumper.Knight;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.slider.Bishop;
import chess.domain.piece.slider.Queen;
import chess.domain.piece.slider.Rook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {

    private static final Map<Class<?>, String> PIECE_VALUE_MAP = new HashMap<>();
    private static final String EMPTY_PIECE = ".";

    static {
        PIECE_VALUE_MAP.put(King.class, "k");
        PIECE_VALUE_MAP.put(Queen.class, "q");
        PIECE_VALUE_MAP.put(Bishop.class, "b");
        PIECE_VALUE_MAP.put(Rook.class, "r");
        PIECE_VALUE_MAP.put(Knight.class, "n");
        PIECE_VALUE_MAP.put(Pawn.class, "p");
    }

    public static void printBoard(final Map<Position, Piece> boardMap) {
        List<List<String>> chessBoard = initializeBoard();

        mappingChessBoard(boardMap, chessBoard);

        chessBoard.stream()
                  .map(board -> String.join("", board))
                  .forEach(System.out::println);
    }

    private static void mappingChessBoard(final Map<Position, Piece> boardMap, final List<List<String>> chessBoard) {
        for (final Map.Entry<Position, Piece> positionPieceEntry : boardMap.entrySet()) {
            final Position position = positionPieceEntry.getKey();
            final Piece piece = positionPieceEntry.getValue();
            final int column = position.column().value();
            final int row = position.row().value();

            String pieceDisplay = formatPieceDisplay(piece);
            chessBoard.get(8 - row).set(column - 1, pieceDisplay);
        }
    }

    private static List<List<String>> initializeBoard() {
        return IntStream.range(0, 8)
                        .mapToObj(it -> new ArrayList<>(Collections.nCopies(8, EMPTY_PIECE)))
                        .collect(Collectors.toList());
    }

    private static String formatPieceDisplay(final Piece piece) {
        String pieceDisplay = PIECE_VALUE_MAP.get(piece.getClass());

        if (piece.isBlack()) {
            return pieceDisplay.toUpperCase();
        }
        return pieceDisplay;
    }

    public static void printWinner(final Color color, final Score whiteScore, final Score blackScore) {
        System.out.println(Color.WHITE.name() + " " + whiteScore.value());
        System.out.println(Color.BLACK.name() + " " + blackScore.value());

        if (color.isNone()) {
            System.out.println("무승부입니다.");
            return;
        }

        System.out.println("승자는 : " + color.name());
    }

    public static void printWinner(final Color color, final Score winnerScore) {
        System.out.println("승자는 : " + color.name());
        System.out.println("점수는 : " + winnerScore.value());
    }

    public static void printGameCandidates(List<Long> gameIds) {
        System.out.println(gameIds.stream()
                                  .map(String::valueOf)
                                  .collect(Collectors.joining(", ")));
    }
}
