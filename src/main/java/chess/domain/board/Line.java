package chess.domain.board;

import java.util.List;

import chess.domain.piece.GamePiece;

public class Line {

    private static final int NEXT = 1;

    private final List<GamePiece> line;

    private Line(List<GamePiece> line) {
        this.line = line;
    }

    public static Line of(List<GamePiece> gamePieces, int rowNumber) {
        int columnLength = Column.values().length;
        int eachLineStartIndex = rowNumber * columnLength;
        int nextLineStartIndex = (rowNumber + NEXT) * columnLength;

        return new Line(gamePieces.subList(eachLineStartIndex, nextLineStartIndex));
    }

    public List<GamePiece> getGamePieces() {
        return line;
    }
}
