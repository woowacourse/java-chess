package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Name;
import chess.domain.Score;
import chess.domain.position.Position;

public class Pawn extends Piece {
    public static final char BLACK_INITIAL_Y = '7';
    public static final char WHITE_INITIAL_Y = '2';

    public Pawn(Position position, Color color) {
        super(position, Name.PAWN, color, new Score(1));
    }

    @Override
    public void move(Position target, Pieces pieces) {
        if (this.position.isDiagonal(target) && (Math.abs(this.position.xDistance(target)) == 1 &&
                Math.abs(this.position.yDistance(target)) == 1)) {
            Piece targetPiece = pieces.findByPosition(target);
            if (targetPiece instanceof Empty) {
                throw new IllegalArgumentException("[ERROR] 공격하려는 위치에 상대방 말이 없습니다.");
            }
            pieces.removePieceByPosition(target);
            this.position = target;
            return;
        }
        if (this.position.getY() == BLACK_INITIAL_Y && this.color == Color.BLACK) { // 블랙 초기화
            if (this.position.yDistance(target) > 0 && this.position.yDistance(target) <= 2) { // 빠꾸 금지 && 2칸 내 이동
                for (int i = 1; i <= position.yDistance(target); i++) { // 장애물 검사
                    Piece piece = pieces.findByPosition(Position.of(position.getX(), (char) (position.getY() - i)));
                    if (!(piece instanceof Empty)) {
                        throw new IllegalArgumentException("[ERROR] 기물을 뛰어 넘어 이동할 수 없습니다.");
                    }
                }
                this.position = target;
                return;
            }
        }

        if (this.position.getY() == WHITE_INITIAL_Y && this.color == Color.WHITE) { // 화이트
            if (target.yDistance(this.position) > 0 && target.yDistance(this.position) <= 2) {
                for (int i = 1; i <= target.yDistance(this.position); i++) {
                    Piece piece = pieces.findByPosition(Position.of(position.getX(), (char) (position.getY() + i)));
                    if (!(piece instanceof Empty)) {
                        throw new IllegalArgumentException("[ERROR] 기물을 뛰어 넘어 이동할 수 없습니다.");
                    }
                }
                this.position = target;
                return;
            }
        }

        if (this.position.yDistance(target) == 1 && this.color == Color.BLACK) { // 블랙
            Piece piece = pieces.findByPosition(Position.of(position.getX(), (char) (position.getY() - 1)));
            if (!(piece instanceof Empty)) {
                throw new IllegalArgumentException("[ERROR] 기물을 뛰어 넘어 이동할 수 없습니다.");
            }
            this.position = target;
            return;
        }

        if (target.yDistance(this.position) == 1 && this.color == Color.WHITE) { // 화이트
            Piece piece = pieces.findByPosition(Position.of(position.getX(), (char) (position.getY() + 1)));
            if (!(piece instanceof Empty)) {
                throw new IllegalArgumentException("[ERROR] 기물을 뛰어 넘어 이동할 수 없습니다.");
            }
            this.position = target;
            return;
        }
        throw new IllegalArgumentException("[ERROR] 움직일 수 없는 위치입니다.");
    }
}
