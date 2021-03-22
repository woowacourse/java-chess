package chess.domain.piece;

import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;
import chess.domain.piece.info.Score;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Knight extends Piece {
    private static final String KNIGHT_ERROR = "[ERROR] 나이트의 이동 규칙에 어긋났습니다.";
    private static final Score SCORE = new Score(2.5);
    private static final List<Position> INITIAL_BLACK_POSITIONS = Arrays.asList(Position.of('b', '8'),
            Position.of('g', '8'));
    private static final List<Position> INITIAL_WHITE_POSITIONS = Arrays.asList(Position.of('b', '1'),
            Position.of('g', '1'));

    public Knight(Position position, String name, Color color) {
        super(position, name, color);
    }

    public Knight(Position position, String name, Color color, Score score) {
        super(position, name, color, score);
    }

    @Override
    public void move(Position target, CurrentPieces currentPieces) {
        validateKnightMove(target);
        Piece targetPiece = currentPieces.findByPosition(target);
        validateSameColor(targetPiece);
        currentPieces.removePieceIfNotEmpty(targetPiece);
        this.position = target;
    }

    private void validateKnightMove(Position target) {
        if (!((Math.abs(this.position.subtractX(target)) == 2 && Math.abs(this.position.subtractY(target)) == 1) ||
                (Math.abs(this.position.subtractX(target)) == 1 && Math.abs(this.position.subtractY(target)) == 2))) {
            throw new IllegalArgumentException(KNIGHT_ERROR);
        }
    }

    public static List<Knight> generate() {
        List<Knight> blackKnights = INITIAL_BLACK_POSITIONS.stream()
                .map(position -> new Knight(position, "N", Color.BLACK, SCORE))
                .collect(Collectors.toList());
        List<Knight> whiteKnights = INITIAL_WHITE_POSITIONS.stream()
                .map(position -> new Knight(position, "n", Color.WHITE, SCORE))
                .collect(Collectors.toList());
        blackKnights.addAll(whiteKnights);
        return blackKnights;
    }
}
