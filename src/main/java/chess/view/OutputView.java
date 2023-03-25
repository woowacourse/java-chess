package chess.view;

import static java.util.stream.Collectors.toList;

import chess.domain.board.BoardResult;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NEXT_LINE = System.lineSeparator();
    private static final Map<PieceType, String> SYMBOLS;
    private static final List<Rank> RANKS;

    static {
        SYMBOLS = Map.of(
                PieceType.PAWN, "P",
                PieceType.KNIGHT, "N",
                PieceType.BISHOP, "B",
                PieceType.ROOK, "R",
                PieceType.QUEEN, "Q",
                PieceType.KING, "K",
                PieceType.EMPTY, "."
        );
        RANKS = Arrays.stream(Rank.values())
                .collect(toList());
        Collections.reverse(RANKS);
    }

    private OutputView() {
    }

    public static void printGameStart() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 새 게임 시작 : start");
        System.out.println("> 저장된 게임 시작 : load");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 점수 확인 : status");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(final Map<Position, Piece> board) {
        final String result = RANKS.stream()
                .map(rank -> generatePieceSymbols(board, rank))
                .collect(Collectors.joining(NEXT_LINE));
        System.out.println(result);
    }

    private static String generatePieceSymbols(final Map<Position, Piece> board, final Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> Position.of(file, rank))
                .map(position -> generatePieceSymbol(board.get(position)))
                .collect(Collectors.joining());
    }

    private static String generatePieceSymbol(final Piece piece) {
        final String result = SYMBOLS.get(piece.type());
        if (piece.color() == Color.WHITE) {
            return result.toLowerCase();
        }
        return result;
    }

    public static void printScore(final double whiteScore, final double blackScore) {
        System.out.println("> 현재 점수");
        System.out.println("> 백 " + whiteScore);
        System.out.println("> 흑 " + blackScore);
    }

    public static void printResult(final BoardResult boardResult) {
        System.out.println("> 게임이 종료되었습니다.");
        System.out.print("> 결과: " + createWinnerView(boardResult));
    }

    private static String createWinnerView(final BoardResult boardResult) {
        final Color winner = boardResult.calculateWinner();
        if (winner.is(Color.BLACK)) {
            return "흑 승";
        }
        if (winner.is(Color.WHITE)) {
            return "백 승";
        }
        return "무승부";
    }

    public static void printException(final String message) {
        System.out.println("[ERROR] " + message);
    }
}
