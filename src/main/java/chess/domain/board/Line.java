package chess.domain.board;

import java.util.List;

import chess.domain.piece.GamePiece;

public class Line {

    private final List<GamePiece> line;

    public Line(List<GamePiece> line) {
        this.line = line;
    }

    public List<GamePiece> getGamePieces() {
        return line;
    }
}
