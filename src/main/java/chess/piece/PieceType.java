package chess.piece;

import chess.Board;
import chess.position.Position;
import chess.validator.*;

import java.util.Arrays;

public enum PieceType {
    KING('K', new KingMoveValidator(), 0),
    QUEEN('Q', new QueenMoveValidator(), 9),
    BISHOP('B', new BishopMoveValidator(), 3),
    ROOK('R', new RookMoveValidator(), 5),
    KNIGHT('N', new KnightMoveValidator(), 2.5),
    PAWN('P', new PawnMoveValidator(), 1),
    NONE('.', null, 0);

    private static final double PAWN_LOWER_SCORE = 0.5;

    private char initialCharacter;
    private MoveValidator moveValidator;
    private double score;

    PieceType(char initialCharacter, MoveValidator moveValidator, double score) {
        this.initialCharacter = initialCharacter;
        this.moveValidator = moveValidator;
        this.score = score;
    }

    public static PieceType of(char initialCharacter) {
        return Arrays.stream(values())
                .filter(pieceType -> pieceType.getInitialCharacter() == initialCharacter)
                .findFirst()
                .orElse(NONE);
    }

    public void validate(Board board, Position source, Position target) {
        moveValidator.validate(board, source, target);
    }

    public void validateThisTurn(Board board, Position source, Position target) {
        moveValidator.validateThisTurn(board, source, target);
    }

    public boolean isKing() {
        return this == KING;
    }

    public boolean isPawn() {
        return this == PAWN;
    }

    public boolean isNone() {
        return this == NONE;
    }

    public static double getPawnLowerScore() {
        return PAWN_LOWER_SCORE;
    }

    public char getInitialCharacter() {
        return initialCharacter;
    }

    public char getUpperCaseInitialCharacter() {
        return Character.toUpperCase(initialCharacter);
    }

    public char getLowerCaseInitialCharacter() {
        return Character.toLowerCase(initialCharacter);
    }

    public MoveValidator getMoveValidator() {
        return moveValidator;
    }

    public double getScore() {
        return score;
    }
}
