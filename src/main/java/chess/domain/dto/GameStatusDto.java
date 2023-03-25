package chess.domain.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;

public class GameStatusDto {

    private static final int BOARD_SIZE = 8;
    private static final char FIRST_FILE = 'a';
    private static final char LAST_RANK = '8';
    private static final String EMPTY_MARK = ".";

    private final List<String> gameStatus;

    private GameStatusDto(final List<String> gameStatus) {
        this.gameStatus = gameStatus;
    }

    public static GameStatusDto from(final Board board) {
        List<String> gameStatus = makeGameStatus(board.getBoard());
        return new GameStatusDto(gameStatus);
    }

    private static List<String> makeGameStatus(final Map<Square, Piece> board) {
        List<String> gameStatus = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            char rank = (char) (LAST_RANK - i);
            gameStatus.add(makeRankStatus(board, rank));
        }
        return gameStatus;
    }

    private static String makeRankStatus(final Map<Square, Piece> board, final char rank) {
        StringBuilder rankStatus = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            char file = (char) (FIRST_FILE + i);
            Square square = Square.of(File.from(file), Rank.from(rank));
            rankStatus.append(makeSquareMark(board, square));
        }
        return rankStatus.toString();
    }

    private static String makeSquareMark(final Map<Square, Piece> board, final Square square) {
        if (board.containsKey(square)) {
            Piece piece = board.get(square);
            return Mark.from(piece);
        }
        return EMPTY_MARK;
    }

    public List<String> getGameStatus() {
        return new ArrayList<>(gameStatus);
    }
}
