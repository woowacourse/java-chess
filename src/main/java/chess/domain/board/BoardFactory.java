package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.view.PositionDto;
import chess.view.WebViewMapper;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public final class BoardFactory {
    public static final Map<String, String> INITIAL_BOARD_FOR_DB;

    static {
        final List<PositionDto> parse = WebViewMapper.parse(InitialBoard.newInstance());
        INITIAL_BOARD_FOR_DB = parse.stream()
                .collect(Collectors.toMap(PositionDto::getPosition, PositionDto::getPiece));
    }

    private BoardFactory() {
    }

    public static Board newInstance() {
        return new Board(InitialBoard.newInstance());
    }

    public static Board newInstance(Map<Position, Piece> board) {
        return new Board(board);
    }

    public static Map<String, String> newBoardForDB() {
        return INITIAL_BOARD_FOR_DB;
    }

    public static Board newInstanceByDB(Map<String, String> boardByGameId, Color color) {
        return new Board(boardByGameId.entrySet()
                .stream()
                .peek(System.out::println)
                .map(entry -> Map.entry(
                        Position.of(
                                File.from(entry.getKey().substring(0, 1)),
                                Rank.fromOrdinal(entry.getKey().substring(1))
                        ),
                        WebViewMapper.from(entry.getValue())))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue)), color);
    }
}
