package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Color;
import chess.domain.piece.ColoredPieces;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChessGameManager {
    private final Board board;
    private final List<ColoredPieces> coloredPieces;
    private Color currentColor;

    private ChessGameManager(Board board, List<ColoredPieces> coloredPieces, Color currentColor) {
        this.board = board;
        this.coloredPieces = coloredPieces;
        this.currentColor = currentColor;
    }

    public static ChessGameManager createChessGameManager() {
        List<ColoredPieces> coloredPieces = Arrays.stream(Color.values())
                .map(ColoredPieces::createByColor)
                .collect(Collectors.toList());
        return new ChessGameManager(BoardFactory.createBoard(), coloredPieces, Color.WHITE);
    }
}
