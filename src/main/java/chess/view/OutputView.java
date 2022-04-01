package chess.view;

import java.util.Arrays;
import java.util.Map;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class OutputView {

    private static final String CHESS_GAME_INIT_MESSAGE = "> 체스 게임을 시작합니다.";
    private static final String EXAMPLE_START_MESSAGE = "> 게임 시작 : start";
    private static final String EXAMPLE_END_MESSAGE = "> 게임 종료 : end";
    private static final String EXAMPLE_MOVE_MESSAGE = "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String NONE_PIECE = ".";
    private static final String SCORE_FORMAT = "%s팀 점수 : %.1f";
    private static final String WINNER_FORMAT = "%s팀이 승리했습니다!";
    private static final String WINNER_NOT_EXIST = "승리한 팀이 없습니다.";

    public static void printGameInitMessage() {
        System.out.println(CHESS_GAME_INIT_MESSAGE);
        System.out.println(EXAMPLE_START_MESSAGE);
        System.out.println(EXAMPLE_END_MESSAGE);
        System.out.println(EXAMPLE_MOVE_MESSAGE);
    }

    public static void printInitialChessBoard(final Map<Position, Piece> board) {
        Arrays.stream(Rank.values())
            .forEach(rank -> printBoardRowLine(rank, board));
        System.out.print(System.lineSeparator());
    }

    public static void printBoardRowLine(final Rank rank, final Map<Position, Piece> board) {
        Arrays.stream(File.values())
            .map(file -> getPiecePrintFormat(board, rank, file))
            .forEach(System.out::print);
        System.out.print(System.lineSeparator());
    }

    private static String getPiecePrintFormat(final Map<Position, Piece> board, final Rank rank, final File file) {
        String printFormat = NONE_PIECE;
        if (board.containsKey(Position.valueOf(file, rank))) {
            final Piece piece = board.get(Position.valueOf(file, rank));
            printFormat = PieceMapper.from(piece);
        }
        return printFormat;
    }

    public static void printScore(final Map<Color, Double> scores) {
        scores.keySet()
            .forEach(color -> System.out.printf(SCORE_FORMAT + System.lineSeparator(), color, scores.get(color)));
        System.out.print(System.lineSeparator());
    }

    public static void printWinner(final Color color) {
        if (color == Color.NONE) {
            System.out.print(WINNER_NOT_EXIST + System.lineSeparator());
            return;
        }
        System.out.printf(WINNER_FORMAT + System.lineSeparator(), color);
    }
}
