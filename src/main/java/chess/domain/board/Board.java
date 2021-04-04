package chess.domain.board;

import chess.domain.dto.PieceDTO;
import chess.domain.dto.TurnDTO;
import chess.domain.exceptions.InvalidMoveException;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceKind;
import chess.domain.piece.strategy.MoveDirection;

import java.util.HashMap;
import java.util.Map;

public class Board {

    public static final String STAY_ERROR_MESSAGE = "지금 위치와 이동하려는 위치가 같습니다.";
    public static final String INVALID_TURN_MESSAGE = "해당 턴이 아닙니다.";
    private static final Piece VOID_PIECE = new Piece(PieceKind.VOID, PieceColor.VOID);

    private final Map<Position, Piece> board;
    private PieceColor deadKingColor;

    public Board(Map<Position, Piece> existedBoard) {
        this.board = existedBoard;
        this.deadKingColor = PieceColor.VOID;
    }

    public static Board loadBoard(Map<Position, PieceDTO> pieceDTOMap) {
        Map<Position, Piece> board = new HashMap<Position, Piece>();
        for (Map.Entry<Position, PieceDTO> element : pieceDTOMap.entrySet()) {
            Position position = element.getKey();
            PieceDTO pieceDTO = element.getValue();
            Piece piece = new Piece(pieceDTO);
            board.put(position, piece);
        }

        return new Board(board);
    }

    public void move(final Position source, final Position target, final PieceColor turnColor) {
        checkPath(source, target);

        Piece originalPiece = pieceAtPosition(source);
        originalPiece.movable(source, target);
        checkTurn(originalPiece, turnColor);

        checkIsNotSameTeam(source, target);
        movePiece(source, target, originalPiece);
    }

    private void checkPath(final Position source, final Position target) {
        checkMoving(source, target);
        checkNoJumpPiece(source, target);
    }

    private void movePiece(final Position source, final Position target,
        final Piece originalPiece) {
        checkPawnCase(source, target, originalPiece);
        judgeKingsState(target);

        putPieceAtPosition(target, originalPiece);
        putPieceAtPosition(source, VOID_PIECE);
    }

    private void checkPawnCase(final Position source, final Position target,
        final Piece originalPiece) {
        if (originalPiece.isPawn()) {
            checkDirection(source, target);
            checkMoveType(source, target);
        }
    }

    private void checkMoving(final Position source, final Position target) {
        if (source.computeHorizontalDistance(target) == 0 &&
            source.computeVerticalDistance(target) == 0) {
            throw new InvalidMoveException(STAY_ERROR_MESSAGE);
        }
    }

    private void checkNoJumpPiece(final Position source, final Position target) {
        if (source.isLineMove(target) || source.isDiagonalMove(target)) {
            checkClearPath(source, target);
        }
    }

    private void checkIsNotSameTeam(final Position source, final Position target) {
        Piece sourcePiece = pieceAtPosition(source);
        Piece targetPiece = pieceAtPosition(target);

        if (sourcePiece.isSameColor(targetPiece)) {
            throw new InvalidMoveException(Piece.SAME_TEAM_MESSAGE);
        }
    }

    private void checkClearPath(final Position source, final Position target) {
        MoveDirection moveDirection = MoveDirection.getDirection(source, target);
        int xVector = moveDirection.getXVector();
        int yVector = moveDirection.getYVector();

        Position pathPosition = source.moveUnit(xVector, yVector);
        while (!pathPosition.equals(target)) {
            checkIfClear(pathPosition);
            pathPosition = pathPosition.moveUnit(xVector, yVector);
        }
    }

    private void checkIfClear(final Position pathPosition) {
        if (!pieceAtPosition(pathPosition).equals(VOID_PIECE)) {
            throw new InvalidMoveException(Piece.UNABLE_CROSS_MESSAGE);
        }
    }

    private void checkDirection(final Position source, final Position target) {
        Piece piece = pieceAtPosition(source);
        if (!piece.isMovingForward(source, target)) {
            throw new InvalidMoveException(Piece.UNABLE_MOVE_TYPE_MESSAGE);
        }
    }

    private void checkMoveType(final Position source, final Position target) {
        if (isVoid(target) && source.isDiagonalMove(target)) {
            throw new InvalidMoveException(Piece.UNABLE_MOVE_TYPE_MESSAGE);
        }

        if (!isVoid(target) && source.isLineMove(target)) {
            throw new InvalidMoveException(Piece.UNABLE_MOVE_TYPE_MESSAGE);
        }
    }

    private boolean isVoid(final Position target) {
        Piece targetPiece = pieceAtPosition(target);
        return targetPiece.equals(VOID_PIECE);
    }

    private void judgeKingsState(final Position target) {
        if (pieceAtPosition(target).isKing()) {
            deadKingColor = pieceAtPosition(target).color();
        }
    }

    public boolean kingIsDead() {
        return deadKingColor != PieceColor.VOID;
    }

    public void checkTurn(final Piece piece, final PieceColor turnColor) {
        if (!piece.isSameColor(turnColor)) {
            throw new InvalidMoveException(INVALID_TURN_MESSAGE);
        }
    }

    public Piece pieceAtPosition(final Position position) {
        return board.get(position);
    }

    public void putPieceAtPosition(final Position position, final Piece piece) {
        board.put(position, piece);
    }

    public PieceColor winnerColor() {
        return this.deadKingColor.oppositeColor();
    }

    public Map<Position, Piece> recentBoard() {
        return this.board;
    }
}
