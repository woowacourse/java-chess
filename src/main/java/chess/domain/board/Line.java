package chess.domain.board;

import chess.domain.piece.ChessPiece;
import chess.domain.piece.GamePiece;
import chess.domain.player.PlayerColor;

import java.util.List;

public class Line {

    private final List<GamePiece> line;

    public Line(List<GamePiece> line) {
        this.line = line;
    }

    public int countPawnOf(PlayerColor playerColor) {
        return (int) line.stream()
                .filter(piece -> piece.is(playerColor))
                .filter(ChessPiece::isPawn)
                .count();
    }

    public List<GamePiece> getGamePieces() {
        return line;
    }
}
