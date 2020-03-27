package chess.domain.board;

import chess.domain.piece.GamePiece;

import java.util.List;

public class Line {
    private final List<GamePiece> line;

    public Line(List<GamePiece> line) {
        this.line = line;
    }

    public List<GamePiece> getGamePieces() {
        return line;
    }
}
