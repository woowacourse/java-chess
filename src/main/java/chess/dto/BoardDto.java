package chess.dto;

import static chess.domain.position.util.PositionUtil.FILES_TOTAL_SIZE;
import static chess.domain.position.util.PositionUtil.RANKS_TOTAL_SIZE;

import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardDto {

    private static final String EMPTY_SQUARE_DISPLAY = ".";

    private final List<String> boardDisplay;

    public BoardDto(ChessGame game) {
        boardDisplay = IntStream.range(0, RANKS_TOTAL_SIZE)
                .mapToObj(rowIdx -> extractCurrentRowChessmen(game, rowIdx))
                .map(BoardDto::initRowDisplay)
                .collect(Collectors.toList());
    }

    private static String initRowDisplay(List<Piece> existingChessMen) {
        return IntStream.range(0, FILES_TOTAL_SIZE)
                .mapToObj(fileIdx -> squareView(existingChessMen, fileIdx))
                .collect(Collectors.joining());
    }

    private static List<Piece> extractCurrentRowChessmen(ChessGame game, int rowIdx) {
        return game.getChessmen()
                .stream()
                .filter(piece -> piece.isAtDisplayRowIdxOf(rowIdx))
                .collect(Collectors.toUnmodifiableList());
    }

    private static String squareView(List<Piece> currentRowChessMEN, int fileIdx) {
        return currentRowChessMEN.stream()
                .filter(piece -> extractFileIdx(piece) == fileIdx)
                .map(Piece::display)
                .findFirst()
                .orElse(EMPTY_SQUARE_DISPLAY);
    }

    private static int extractFileIdx(Piece piece) {
        Position position = piece.position();
        return position.getFileIdx();
    }

    public List<String> getDisplay() {
        return boardDisplay;
    }
}
