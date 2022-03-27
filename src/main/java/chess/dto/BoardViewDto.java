package chess.dto;

import static chess.util.PositionUtil.FILES_TOTAL_SIZE;
import static chess.util.PositionUtil.RANKS_TOTAL_SIZE;

import chess.domain.game.Game;
import chess.domain.piece.ActivePieces;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardViewDto {

    private static final String EMPTY_SQUARE_DISPLAY = ".";

    private final List<String> boardDisplay;

    public BoardViewDto(Game game) {
        boardDisplay = toBoard(game);
    }

    private static List<String> toBoard(Game game) {
        ActivePieces chessmen = game.getChessmen();
        List<String> board = IntStream.range(0, RANKS_TOTAL_SIZE)
                .mapToObj(rankIdx -> toRow(chessmen, rankIdx))
                .collect(Collectors.toList());

        Collections.reverse(board);
        return board;
    }

    private static String toRow(ActivePieces chessmen, int rankIdx) {
        return IntStream.range(0, FILES_TOTAL_SIZE)
                .mapToObj(fileIdx -> Position.of(fileIdx, rankIdx))
                .map(position -> toSquare(chessmen, position))
                .collect(Collectors.joining());
    }

    private static String toSquare(ActivePieces chessmen, Position position) {
        return chessmen.findAll()
                .stream()
                .filter(piece -> piece.isAt(position))
                .map(Piece::display)
                .findFirst()
                .orElse(EMPTY_SQUARE_DISPLAY);
    }

    public List<String> boardDisplay() {
        return boardDisplay;
    }
}
