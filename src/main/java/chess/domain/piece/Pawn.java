package chess.domain.piece;

import chess.domain.piece.info.Cross;
import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;
import chess.domain.piece.info.Score;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Pawn extends Piece {
    private static final String PAWN_ERROR = "[ERROR] 폰 이동 규칙에 어긋납니다.";
    private static final Score SCORE = new Score(1);
    private static final List<Position> INITIAL_BLACK_POSITIONS = Arrays.asList(Position.of('a', '7'),
            Position.of('b', '7'), Position.of('c', '7'), Position.of('d', '7'),
            Position.of('e', '7'), Position.of('f', '7'), Position.of('g', '7'),
            Position.of('h', '7'));
    private static final List<Position> INITIAL_WHITE_POSITIONS = Arrays.asList(Position.of('a', '2'),
            Position.of('b', '2'), Position.of('c', '2'), Position.of('d', '2'),
            Position.of('e', '2'), Position.of('f', '2'), Position.of('g', '2'),
            Position.of('h', '2'));

    public Pawn(Position position, String name, Color color) {
        super(position, name, color);
    }

    public Pawn(Position position, String name, Color color, Score score) {
        super(position, name, color, score);
    }

    @Override
    public void move(Position target, CurrentPieces currentPieces) {
        if (isAttackAble(target)) {
            attack(target, currentPieces);
            return;
        }
        Cross pawnCross = Cross.findCrossByTwoPosition(position, target);
        if (isMoveAble(target)) {
            validateFirstTurn(target);
            pawnCross.hasPieceInPath(position, target, currentPieces);
            this.position = target;
            return;
        }
        throw new IllegalArgumentException(PAWN_ERROR);
    }

    private boolean isAttackAble(Position target) {
        if (this.color == Color.BLACK) {
            return Math.abs(this.position.subtractX(target)) == 1 && this.position.subtractY(target) == 1;
        }
        if (this.color == Color.WHITE) {
            return Math.abs(this.position.subtractX(target)) == 1 && this.position.subtractY(target) == -1;
        }
        return false;
    }

    private void attack(Position target, CurrentPieces currentPieces) {
        Piece targetPiece = currentPieces.findByPosition(target);
        validateSameColor(targetPiece);
        currentPieces.removePieceIfNotEmpty(targetPiece);
        this.position = target;
    }

    private boolean isMoveAble(Position target) {
        if (this.color == Color.BLACK) {
            return (Cross.findCrossByTwoPosition(position, target) == Cross.DOWN) && (this.position.subtractY(target) <= 2);
        }
        if (this.color == Color.WHITE) {
            return (Cross.findCrossByTwoPosition(position, target) == Cross.UP) && (target.subtractY(this.position) <= 2);
        }
        return false;
    }

    private void validateFirstTurn(Position target) {
        if (this.color == Color.BLACK && (!(this.position.isFirstTurnIfPawn(color)) && this.position.subtractY(target) == 2)) {
            throw new IllegalArgumentException(PAWN_ERROR);
        }
        if (this.color == Color.WHITE && (!(this.position.isFirstTurnIfPawn(color)) && target.subtractY(this.position) == 2)) {
            throw new IllegalArgumentException(PAWN_ERROR);
        }
    }

    public static List<Pawn> generate() {
        List<Pawn> blackPawns = INITIAL_BLACK_POSITIONS.stream()
                .map(position -> new Pawn(position, "P", Color.BLACK, SCORE))
                .collect(Collectors.toList());
        List<Pawn> whitePawns = INITIAL_WHITE_POSITIONS.stream()
                .map(position -> new Pawn(position, "p", Color.WHITE, SCORE))
                .collect(Collectors.toList());
        blackPawns.addAll(whitePawns);
        return blackPawns;
    }
}
