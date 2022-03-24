package chess.dto;

import static chess.domain.position.util.PositionUtil.FILES_TOTAL_SIZE;
import static chess.domain.position.util.PositionUtil.RANKS_TOTAL_SIZE;

import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardViewDto {

    private static final String EMPTY_SQUARE_DISPLAY = ".";

    private final List<String> boardDisplay;

    public BoardViewDto(ChessGame game) {
        boardDisplay = IntStream.range(0, RANKS_TOTAL_SIZE)
                .mapToObj(rowIdx -> currentRowChessmen(game, rowIdx))
                .map(BoardViewDto::initRowDisplay)
                .collect(Collectors.toList());
    }

    private static List<Piece> currentRowChessmen(ChessGame game, int rowIdx) {
        return game.getChessmen()
                .findAll()
                .stream()
                .filter(piece -> piece.isAtDisplayRowIdxOf(rowIdx))
                .collect(Collectors.toUnmodifiableList());
    }

    private static String initRowDisplay(List<Piece> chessmen) {
        return IntStream.range(0, FILES_TOTAL_SIZE)
                .mapToObj(colIdx -> squareDisplay(chessmen, colIdx))
                .collect(Collectors.joining());
    }

    private static String squareDisplay(List<Piece> chessmen, int colIdx) {
        return chessmen.stream()
                .filter(piece -> piece.isAtDisplayColumnIdxOf(colIdx))
                .map(Piece::display)
                .findFirst()
                .orElse(EMPTY_SQUARE_DISPLAY);
    }

    public List<String> boardDisplay() {
        return boardDisplay;
    }
}
