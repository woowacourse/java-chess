package chess.controller.dao;

import java.util.Objects;

public class ChessBoard {
    private int chessBoardId;

    public ChessBoard(int chessBoardId) {
        this.chessBoardId = chessBoardId;
    }

    public int getChessBoardId() {
        return chessBoardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessBoard user = (ChessBoard) o;
        return Objects.equals(chessBoardId, user.chessBoardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chessBoardId);
    }
}
