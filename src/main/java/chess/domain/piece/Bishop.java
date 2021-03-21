package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Diagonal;
import chess.domain.Name;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Bishop extends Piece {
    private static final List<Position> INITIAL_BLACK_POSITIONS = Arrays.asList(Position.of('c', '8'),
            Position.of('f', '8'));
    private static final List<Position> INITIAL_WHITE_POSITIONS = Arrays.asList(Position.of('c', '1'),
            Position.of('f', '1'));

    public Bishop(Position position, Color color) {
        super(position, Name.BISHOP, color, Score.THREE);
    }

    public Bishop(Position position, Name name, Color color, Score score) {
        super(position, name, color, score);
    }

    public static List<Bishop> initialBishops() {
        List<Bishop> blackBishops = INITIAL_BLACK_POSITIONS.stream()
                .map(position -> new Bishop(position, Color.BLACK))
                .collect(Collectors.toList());
        List<Bishop> whiteBishops = INITIAL_WHITE_POSITIONS.stream()
                .map(position -> new Bishop(position, Color.WHITE))
                .collect(Collectors.toList());
        blackBishops.addAll(whiteBishops);
        return blackBishops;
    }

    @Override
    public void move(Position target, Pieces pieces) {
        checkMoveRule(target);
        Diagonal bishopDiagonal = Diagonal.findDiagonalByTwoPosition(this.position, target);
        bishopDiagonal.hasPieceInPath(this.position, target, pieces);
        this.position = target;
    }

    @Override
    public void checkMoveRule(Position target) {
        if (!this.position.isDiagonal(target)) {
            throw new IllegalArgumentException("[ERROR] 비숍 이동 규칙에 어긋납니다.");
        }
    }
}
