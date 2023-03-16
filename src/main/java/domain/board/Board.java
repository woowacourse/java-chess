package domain.board;

import domain.piece.Piece;
import domain.position.Position;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Board {

    private final Map<Position, Piece> board;

    private Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board create(ChessAlignment chessStrategy) {
        final HashMap<Position, Piece> board = new HashMap<>();

        chessStrategy.addInitialPawns(board);
        chessStrategy.addInitialKings(board);
        chessStrategy.addInitialBishops(board);
        chessStrategy.addInitialKnights(board);
        chessStrategy.addInitialQueens(board);
        chessStrategy.addInitialRooks(board);

        return new Board(board);
    }


    public void move(Position source, Position destination) {
        if (!board.containsKey(source)) {
            throw new IllegalArgumentException("해당 위치에 말이 존재하지 않습니다.");
        }

        final Piece piece = board.get(source);
        if (!piece.isMovable(source, destination)) {
            throw new IllegalArgumentException("해당 위치로 말을 이동할 수 없습니다.");
        }

        //todo: 중간에 말이 있는 경우 예외 던지기
        if (source.isStraight(destination)) {
//            for
        }
    }

    public Map<Position, Piece> getPieces() {
        return Collections.unmodifiableMap(board);
    }
}
