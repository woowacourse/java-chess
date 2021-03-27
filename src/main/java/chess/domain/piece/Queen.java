package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;
import chess.domain.piece.info.Score;
import chess.domain.piece.strategy.QueenMoveStrategy;

import java.util.HashMap;
import java.util.Map;

public class Queen extends Move {
    private static final Score SCORE = new Score(9);
    private static final Position INITIAL_BLACK_POSITION = Position.of('d', '8');
    private static final Position INITIAL_WHITE_POSITION = Position.of('d', '1');

    public Queen(String name, Color color) {
        super(name, color, SCORE, new QueenMoveStrategy());
    }

    @Override
    public boolean canMove(Position source, Position target, ChessBoard chessBoard) {
        return moveStrategy.canMove(source, target, chessBoard);
    }

    public static Map<Position, Queen> generate() {
        Map<Position, Queen> queens = new HashMap<>();
        queens.put(INITIAL_BLACK_POSITION, new Queen("Q", Color.BLACK));
        queens.put(INITIAL_WHITE_POSITION, new Queen("q", Color.BLACK));
        return queens;
    }
}
