package chess.view;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {

    private static final Map<Class<?>, String> PIECE_VALUE_MAP = new HashMap<>();

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
            final File file = position.file();
            final Rank rank = position.rank();
            final int column = file.value();
            final int row = rank.value();

            String pieceDisplay = formatPieceDisplay(piece);
            chessBoard.get(8 - row).set(column - 1, pieceDisplay);
        }
    }

    private static List<List<String>> initializeBoard() {
        return IntStream.range(0, 8)
                .mapToObj(it -> new ArrayList<>(Collections.nCopies(8, ".")))
                .collect(Collectors.toList());
    }

    private static String formatPieceDisplay(final Piece piece) {
        String pieceDisplay = PIECE_VALUE_MAP.get(piece.getClass());

        if (piece.isBlack()) {
            return pieceDisplay.toUpperCase();
        }
        return pieceDisplay;
    }

    public static void printExceptionMessage(final String message) {
        System.out.println("[ERROR] " + message);
    }

    public static void printScore(final Map<Color, Double> score) {
        System.out.println("BLACK팀(대문자) 점수 : " + score.get(BLACK));
        System.out.println("WHITE팀(소문자) 점수 : " + score.get(WHITE));
    }

    public static void printWinner(final Color winner) {
        System.out.println("승리 팀은 " + winner.name() + "팀 입니다!!! 축하합니다!!!");
    }

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 점수 계산 : status");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }
}
