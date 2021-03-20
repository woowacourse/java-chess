package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Cross;
import chess.domain.Name;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Rook extends Piece {
    private static final Score SCORE = new Score(5);
    private static final List<Position> INITIAL_BLACK_POSITIONS = Arrays.asList(Position.of('a', '8'),
            Position.of('h', '8'));
    private static final List<Position> INITIAL_WHITE_POSITIONS = Arrays.asList(Position.of('a', '1'),
            Position.of('h', '1'));

    public Rook(Position position, Color color) {
        super(position, Name.ROOK, color, SCORE);
    }

    public Rook(Position position, Name name, Color color, Score score) {
        super(position, name, color, score);
    }

    public static List<Rook> initialRooks() {
        List<Rook> blackRooks = INITIAL_BLACK_POSITIONS.stream()
                .map(position -> new Rook(position, Color.BLACK))
                .collect(Collectors.toList());
        List<Rook> whiteRooks = INITIAL_WHITE_POSITIONS.stream()
                .map(position -> new Rook(position, Color.WHITE))
                .collect(Collectors.toList());
        blackRooks.addAll(whiteRooks);
        return blackRooks;
    }

    @Override
    public void move(Position target, CurrentPieces currentPieces) {
        // 대각선인지 확인
        if (!this.position.isCross(target)) {
            throw new IllegalArgumentException("[ERROR] 룩 이동 규칙에 어긋납니다.");
        }
        Cross rookCross = Cross.findCrossByTwoPosition(this.position, target);
        // 경로에 장애물이 있는지 확인
        rookCross.hasPieceInPath(this.position, target, currentPieces);

        // 우리편 말이 있으면 예외
        Piece targetPiece = currentPieces.findByPosition(target);
        if (this.color.isSame(targetPiece.color)) {
            throw new IllegalArgumentException("[ERROR] taget에 같은 편 말이 있습니다.");
        }
        if (!(targetPiece instanceof Empty)) {
            currentPieces.removePieceByPosition(target);
        }
        this.position = target;
    }
}
