package chess.domain.board;

import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.position.X;
import chess.domain.position.Y;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public void move(String keyFromPosition, String keyToPosition) {
        move(Position.of(keyFromPosition), Position.of(keyToPosition));
    }

    public void move(Position fromPosition, Position toPosition) {
        Piece piece = board.get(fromPosition);

        board.remove(fromPosition);
        board.put(toPosition, piece);
    }

    public boolean isPieceOnBoard(Team team, PieceType pieceType) {
        return this.board.entrySet().stream()
            .anyMatch(entry -> entry.getValue().is(pieceType));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Y y : Y.values()) {
            stringBuilder.append(makeStringByX(y));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private String makeStringByX(Y y) {
        StringBuilder stringBuilder = new StringBuilder();

        for (X x : X.values()) {
            stringBuilder.append(makeStringByPosition(Position.of(x, y)));
        }
        return stringBuilder.toString();
    }

    private String makeStringByPosition(Position position) {
        if (board.containsKey(position)) {
            return board.get(position)
                .getAcronym();
        }
        return ".";
    }
}
