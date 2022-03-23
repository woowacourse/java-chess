package chess.dto;

import static chess.domain.piece.position.PositionUtil.FILES_TOTAL_SIZE;
import static chess.domain.piece.position.PositionUtil.RANKS_TOTAL_SIZE;

import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;
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
                .filter(piece -> toRowIdx(piece) == rowIdx)
                .collect(Collectors.toUnmodifiableList());
    }

    private static String squareView(List<Piece> currentRowChessMen, int fileIdx) {
        return currentRowChessMen.stream()
                .filter(piece -> extractFileIdx(piece) == fileIdx)
                .map(Piece::display)
                .findFirst()
                .orElse(EMPTY_SQUARE_DISPLAY);
    }

    private static int toRowIdx(Piece piece) {
        Position position = piece.getPosition();
        int rankIdx = position.getRankIdx();

        return RANKS_TOTAL_SIZE - rankIdx - 1;
    }

    private static int extractFileIdx(Piece piece) {
        Position position = piece.getPosition();
        return position.getFileIdx();
    }

    public List<String> getDisplay() {
        return boardDisplay;
    }
}
