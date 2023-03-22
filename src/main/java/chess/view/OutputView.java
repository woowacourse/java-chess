package chess.view;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void printErrorMessage(final Exception e) {
        System.out.println(e.getMessage() + LINE_SEPARATOR);
    }

    public void printStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public void printBoard(final Board board) {
        final List<String> boardFormats = getBoardFormats(board);
        boardFormats.forEach(System.out::println);
    }

    private List<String> getBoardFormats(final Board board) {
        final List<String> boardFormats = new ArrayList<>();

        initBoardFormats(boardFormats, board);
        addRankFormat(boardFormats);
        boardFormats.add("---------");
        boardFormats.add(getFileFormat());

        return boardFormats;
    }

    private static void initBoardFormats(final List<String> boardFormats, final Board board) {
        final Map<Position, Piece> boardMap = board.getBoard();
        for (Rank rank : Rank.values()) {
            final StringBuilder stringBuilder = new StringBuilder();
            for (File file : File.values()) {
                final Piece piece = boardMap.get(Position.of(file, rank));
                stringBuilder.append(piece.name());
            }
            boardFormats.add(stringBuilder.toString());
        }
    }

    private void addRankFormat(final List<String> boardFormats) {
        for (final Rank rank : Rank.values()) {
            String boardFormat = boardFormats.get(Board.UPPER_BOUNDARY - rank.index());
            boardFormat += " | " + rank.index();
            boardFormats.set(Board.UPPER_BOUNDARY - rank.index(), boardFormat);
        }
    }

    private String getFileFormat() {
        StringBuilder stringBuilder = new StringBuilder();
        for (final File file : File.values()) {
            stringBuilder.append(file.symbol());
        }
        stringBuilder.append(LINE_SEPARATOR);
        return stringBuilder.toString();
    }
}
