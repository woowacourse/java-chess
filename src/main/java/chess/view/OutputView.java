package chess.view;

import chess.Board;
import chess.Scores;
import chess.piece.Piece;
import chess.position.File;
import chess.position.Position;
import chess.position.Rank;

import java.util.Map;

public class OutputView {

    private static final String GAME_INTRO_MESSAGE = "> 체스 게임을 시작합니다.";
    private static final String START_COMMAND_INFORMATION_MESSAGE = "> 게임 시작 : start";
    private static final String END_COMMAND_INFORMATION_MESSAGE = "> 게임 종료 : end";
    private static final String MOVE_COMMAND_INFORMATION_MESSAGE = "> 말 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String INVALID_COMMAND_INPUT_MESSAGE = "잘못된 명령어를 입력하셨습니다.";
    private static final String WHITE_SCORE_MESSAGE_FORMAT = "백팀 점수 : %d";
    private static final String BLACK_SCORE_MESSAGE_FORMAT = "흑팀 점수 : %d";

    public static void printGameIntro() {
        System.out.println(GAME_INTRO_MESSAGE);
        System.out.println(START_COMMAND_INFORMATION_MESSAGE);
		System.out.println(END_COMMAND_INFORMATION_MESSAGE);
		System.out.println(MOVE_COMMAND_INFORMATION_MESSAGE);
	}

	public static void invalidCommandInputMessage() {
		System.out.println(INVALID_COMMAND_INPUT_MESSAGE);
	}

    public static void printBoard(Board board) {
        Map<Position, Piece> pieces = board.getPieces();

        StringBuilder builder = new StringBuilder();

        for (Rank rank : Rank.valuesReverseOrder()) {
            for (File file : File.values()) {
                if (pieces.containsKey(Position.of(file, rank))) {
                    Piece piece = pieces.get(Position.of(file, rank));
                    builder.append(findSymbol(piece));
                } else {
                    builder.append(".");
                }
            }
            builder.append("\n");
        }
        System.out.println(builder);
    }

    private static String findSymbol(Piece piece) {
        return piece.getSymbol();

    }

    public static void printExceptionMessage(String message) {
        System.out.println(message);
    }

    public static void requestCorrectCommandMessage() {
        System.out.println("잘못된 명령어를 입력하셨습니다. 다시 입력해주세요.");
    }

    public static void printScores(Scores scores) {
        System.out.println(String.format(WHITE_SCORE_MESSAGE_FORMAT, scores.getWhiteScore()));
        System.out.println(String.format(BLACK_SCORE_MESSAGE_FORMAT, scores.getBlackScore()));
    }
}
