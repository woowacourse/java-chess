package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Name;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Pawn extends Piece {
    private static final List<Position> INITIAL_BLACK_POSITIONS = Arrays.asList(Position.of('a', '7'),
            Position.of('b', '7'), Position.of('c', '7'), Position.of('d', '7'),
            Position.of('e', '7'), Position.of('f', '7'), Position.of('g', '7'),
            Position.of('h', '7'));
    private static final List<Position> INITIAL_WHITE_POSITIONS = Arrays.asList(Position.of('a', '2'),
            Position.of('b', '2'), Position.of('c', '2'), Position.of('d', '2'),
            Position.of('e', '2'), Position.of('f', '2'), Position.of('g', '2'),
            Position.of('h', '2'));

    public Pawn(Position position, Color color) {
        super(position, Name.PAWN, color, Score.ONE);
    }

    public Pawn(Position position, Name name, Color color, Score score) {
        super(position, name, color, score);
    }

    public static List<Pawn> initialPawns() {
        List<Pawn> blackPawns = INITIAL_BLACK_POSITIONS.stream()
                .map(position -> new Pawn(position, Color.BLACK))
                .collect(Collectors.toList());
        List<Pawn> whitePawns = INITIAL_WHITE_POSITIONS.stream()
                .map(position -> new Pawn(position, Color.WHITE))
                .collect(Collectors.toList());
        blackPawns.addAll(whitePawns);
        return blackPawns;
    }


    @Override
    public void move(Position target, CurrentPieces currentPieces) {
        if (this.position.isDiagonal(target) && (Math.abs(this.position.subtractX(target)) == 1 && Math.abs(this.position.subtractY(target)) == 1)) {
            Piece targetPiece = currentPieces.findByPosition(target);
            if (targetPiece instanceof Empty) {
                throw new IllegalArgumentException("[ERROR] 공격하려는 위치에 상대방 말이 없습니다.");
            }
            currentPieces.removePieceByPosition(target);
            this.position = target;
            return;
        }
        if (this.position.getY() == '7' && this.color == Color.BLACK) { // 블랙 초기화
            if (this.position.subtractY(target) > 0 && this.position.subtractY(target) <= 2) { // 빠꾸 금지 && 2칸 내 이동
                for (int i = 1; i <= position.subtractY(target); i++) { // 장애물 검사
                    Piece piece = currentPieces.findByPosition(Position.of(position.getX(), (char) (position.getY() - i)));
                    if (!(piece instanceof Empty)) {
                        throw new IllegalArgumentException("[ERROR] 기물을 뛰어 넘어 이동할 수 없습니다.");
                    }
                }
                this.position = target;
                return;
            }
        }

        if (this.position.getY() == '2' && this.color == Color.WHITE) { // 화이트
            if (target.subtractY(this.position) > 0 && target.subtractY(this.position) <= 2) {
                for (int i = 1; i <= target.subtractY(this.position); i++) {
                    Piece piece = currentPieces.findByPosition(Position.of(position.getX(), (char) (position.getY() + i)));
                    if (!(piece instanceof Empty)) {
                        throw new IllegalArgumentException("[ERROR] 기물을 뛰어 넘어 이동할 수 없습니다.");
                    }
                }
                this.position = target;
                return;
            }
        }

        if (this.position.subtractY(target) == 1 && this.color == Color.BLACK) { // 블랙
            Piece piece = currentPieces.findByPosition(Position.of(position.getX(), (char) (position.getY() - 1)));
            if (!(piece instanceof Empty)) {
                throw new IllegalArgumentException("[ERROR] 기물을 뛰어 넘어 이동할 수 없습니다.");
            }
            this.position = target;
            return;
        }

        if (target.subtractY(this.position) == 1 && this.color == Color.WHITE) { // 화이트
            Piece piece = currentPieces.findByPosition(Position.of(position.getX(), (char) (position.getY() + 1)));
            if (!(piece instanceof Empty)) {
                throw new IllegalArgumentException("[ERROR] 기물을 뛰어 넘어 이동할 수 없습니다.");
            }
            this.position = target;
            return;
        }
        throw new IllegalArgumentException("[ERROR] 움직일 수 없는 위치입니다.");
    }
}
