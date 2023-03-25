package chess.view;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.movestrategy.BishopMovementStrategy;
import chess.domain.piece.movestrategy.KingMovementStrategy;
import chess.domain.piece.movestrategy.KnightMovementStrategy;
import chess.domain.piece.movestrategy.QueenMovementStrategy;
import chess.domain.piece.movestrategy.RookMovementStrategy;
import chess.domain.piece.movestrategy.pawn.BlackPawnMovementStrategy;
import chess.domain.piece.movestrategy.pawn.WhitePawnMovementStrategy;
import chess.domain.piece.position.File;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.position.Rank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {

    private static final Map<Color, Map<Class<?>, String>> colorIconMapping = new HashMap<>();
    private static final Map<Class<?>, String> whitePieceIcons = new HashMap<>();
    private static final Map<Class<?>, String> blackPieceIcons = new HashMap<>();
    private static final String EMPTY_PIECE_ICON = ".";

    static {
        whitePieceIcons.put(RookMovementStrategy.class, "r");
        whitePieceIcons.put(WhitePawnMovementStrategy.class, "p");
        whitePieceIcons.put(BishopMovementStrategy.class, "b");
        whitePieceIcons.put(KingMovementStrategy.class, "k");
        whitePieceIcons.put(KnightMovementStrategy.class, "n");
        whitePieceIcons.put(QueenMovementStrategy.class, "q");

        blackPieceIcons.put(RookMovementStrategy.class, "R");
        blackPieceIcons.put(BlackPawnMovementStrategy.class, "P");
        blackPieceIcons.put(BishopMovementStrategy.class, "B");
        blackPieceIcons.put(KingMovementStrategy.class, "K");
        blackPieceIcons.put(KnightMovementStrategy.class, "N");
        blackPieceIcons.put(QueenMovementStrategy.class, "Q");

        colorIconMapping.put(Color.WHITE, whitePieceIcons);
        colorIconMapping.put(Color.BLACK, blackPieceIcons);
    }

    public static void showBoard(final List<Piece> chessBoard, final Color color) {
        final Map<PiecePosition, Piece> pieceMap = chessBoard.stream()
                .collect(Collectors.toMap(Piece::piecePosition, Function.identity()));
        for (int rank = Rank.MAX; rank >= Rank.MIN; rank--) {
            System.out.println(makeLineFormat(pieceMap, rank));
        }
        System.out.println();
        System.out.println(color + " 색이 움직일 차례입니다.");
    }

    private static String makeLineFormat(final Map<PiecePosition, Piece> chessBoard, final int rank) {
        return IntStream.rangeClosed(File.MIN, File.MAX)
                .mapToObj(file -> makeFormat(chessBoard, rank, (char) file))
                .collect(Collectors.joining(""));
    }

    private static String makeFormat(final Map<PiecePosition, Piece> chessBoard, final int rank, final char file) {
        return Optional.ofNullable(chessBoard.get(PiecePosition.of(rank, file)))
                .map(OutputView::convertCaseAccordingToColor)
                .orElse(EMPTY_PIECE_ICON);
    }

    private static String convertCaseAccordingToColor(final Piece piece) {
        final Map<Class<?>, String> pieceIconMap = colorIconMapping.get(piece.color());
        return pieceIconMap.get(piece.pieceMovementStrategy().getClass());
    }

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source 위치 target 위치 - 예. move b2 b3");
        System.out.println("> 현재 점수 : status (게임 진행 중에만 볼 수 있습니다.)");
        System.out.println("> 재시작 : restart gameId - 예. restart 1");
    }

    public static void error(final String message) {
        System.out.println();
        System.out.println("[ERROR] " + message);
    }

    public static void printWinColor(final Color winColor) {
        if (winColor == Color.NONE) {
            System.out.println("왕이 죽지 않아서 무승부입니다.");
            return;
        }
        System.out.println(winColor + " 이 이겼습니다.");
    }

    public static void printScore(final Map<Color, Double> colorScoreMapping) {
        System.out.println(colorScoreMapping.entrySet()
                .stream()
                .map(it -> it.getKey() + "색: " + it.getValue() + "점")
                .collect(Collectors.joining(System.lineSeparator())));
    }

    public static void startGame(final Long id) {
        System.out.println(id + "번 게임을 시작합니다");
        System.out.println();
    }

    public static void saveAndEnd() {
        System.out.println("게임 저장 후 종료");
    }
}
