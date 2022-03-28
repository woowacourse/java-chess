package chess.view;

import java.util.Map;
import java.util.Optional;

import chess.domain.Board;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public class OutputView {

    private static final String CHESS_GAME_INIT_MESSAGE = "> 체스 게임을 시작합니다.";
    private static final String EXAMPLE_START_MESSAGE = "> 게임 시작 : start";
    private static final String EXAMPLE_END_MESSAGE = "> 게임 종료 : end";
    private static final String EXAMPLE_MOVE_MESSAGE = "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String NONE_PIECE = ".";
    private static final String SCORE_FORMAT = "%s팀 점수 : %.1f";
    private static final String WINNER_FORMAT = "%s팀이 승리했습니다!";
    private static final String BLACK_TEAM_NAME = "black";
    private static final String WHITE_TEAM_NAME = "white";

    public static void printGameInitMessage() {
        System.out.println(CHESS_GAME_INIT_MESSAGE);
        System.out.println(EXAMPLE_START_MESSAGE);
        System.out.println(EXAMPLE_END_MESSAGE);
        System.out.println(EXAMPLE_MOVE_MESSAGE);
    }

    public static void printInitialChessBoard(Board board) {
        for (Rank rank : Rank.values()) {
            printBoardRowLine(rank, board);
        }
        System.out.print(System.lineSeparator());
    }

    public static void printBoardRowLine(Rank rank, Board board) {
        Map<Position, Piece> chessBoard = board.getBoard();
        for (File value : File.values()) {
            Optional<Piece> pieceOptional = Optional.ofNullable(
                chessBoard.get(Position.valueOf(value, rank)));
            String printFormat = pieceOptional.map(PieceMapper::from).orElse(NONE_PIECE);
            System.out.print(printFormat);
        }
        System.out.print(System.lineSeparator());
    }

    public static void printScore(double whiteScore, double blackScore) {
        System.out.printf(SCORE_FORMAT, BLACK_TEAM_NAME, blackScore);
        System.out.print(System.lineSeparator());
        System.out.printf(SCORE_FORMAT, WHITE_TEAM_NAME, whiteScore);
        System.out.print(System.lineSeparator());
    }

    public static void printWinner(Color color) {
        if (color == Color.WHITE) {
            System.out.printf(WINNER_FORMAT, WHITE_TEAM_NAME);
            return;
        }
        System.out.printf(WINNER_FORMAT, BLACK_TEAM_NAME);
    }
}
