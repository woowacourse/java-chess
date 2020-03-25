package chess.view;

import chess.Board;
import chess.piece.Piece;
import chess.position.File;
import chess.position.Position;
import chess.position.Rank;

import java.util.Map;

public class OutputView {

    private static final String GAME_INTRO_MESSAGE = "체스 게임을 시작합니다.\n게임 시작은 start, 종료는 end 명령을 입력하세요.";

    public static void printGameIntro() {
        System.out.println(GAME_INTRO_MESSAGE);
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
}
