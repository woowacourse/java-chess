package chess.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chess.domain.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;

public class GameStatusDto {

    private static final char FIRST_FILE = 'a';
    private static final char LAST_RANK = '8';
    private static final String EMPTY_SQUARE = ".";

    private final List<String> gameStatus;

    private GameStatusDto(List<String> gameStatus) {
        this.gameStatus = gameStatus;
    }

    public static GameStatusDto from(final Board board) {
        List<String> gameStatus = makeGameStatus(board.getBoard());
        return new GameStatusDto(gameStatus);
    }

    private static List<String> makeGameStatus(Map<Square, Piece> board) {
        List<String> gameStatus = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            char rank = (char) (LAST_RANK - i);
            gameStatus.add(makeRankStatus(board, rank));
        }
        return gameStatus;
    }

    private static String makeRankStatus(Map<Square, Piece> board, char rank) {
        StringBuilder rankStatus = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            char file = (char) (FIRST_FILE + i);
            Square square = Square.of(File.from(file), Rank.from(rank));
            rankStatus.append(makeSquareStatus(board, square));
        }
        return rankStatus.toString();
    }

    private static String makeSquareStatus(Map<Square, Piece> board, Square square) {
        if (board.containsKey(square)) {
            Piece piece = board.get(square);
            String name = piece.getName();
            Color color = piece.getColor();
            return makeNameToUpperCaseWhenBlack(name, color);
        }
        return EMPTY_SQUARE;
    }

    private static String makeNameToUpperCaseWhenBlack(String name, Color color) {
        if (color == Color.BLACK) {
            return name.toUpperCase();
        }
        return name;
    }

    public List<String> getGameStatus() {
        return new ArrayList<>(gameStatus);
    }
}
