package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Cross;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Rook extends Piece {
    private static final List<Position> INITIAL_BLACK_POSITIONS = Arrays.asList(Position.of('a', '8'),
            Position.of('h', '8'));
    private static final List<Position> INITIAL_WHITE_POSITIONS = Arrays.asList(Position.of('a', '1'),
            Position.of('h', '1'));

    public Rook(Position position, String name, Color color) {
        super(position, name, color);
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
        if((Character.isUpperCase(this.name.charAt(0)) && Character.isUpperCase(targetPiece.name.charAt(0))) ||
                (Character.isLowerCase(this.name.charAt(0)) && Character.isUpperCase(targetPiece.name.charAt(0)))) {
            throw new IllegalArgumentException("[ERROR] taget에 같은 편 말이 있습니다.");
        }
        if (!(targetPiece instanceof Empty)) {
            currentPieces.removePieceByPosition(target);
        }
        this.position = target;
    }

    public static List<Rook> generate() {
        List<Rook> blackRooks = INITIAL_BLACK_POSITIONS.stream()
                .map(position -> new Rook(position, "R", Color.BLACK))
                .collect(Collectors.toList());
        List<Rook> whiteRooks = INITIAL_WHITE_POSITIONS.stream()
                .map(position -> new Rook(position, "r", Color.WHITE))
                .collect(Collectors.toList());
        blackRooks.addAll(whiteRooks);
        return blackRooks;
    }
}
