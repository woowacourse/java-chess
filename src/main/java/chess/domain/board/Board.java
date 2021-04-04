package chess.domain.board;

import chess.dao.BackupBoardDao;
import chess.domain.exceptions.InvalidMoveException;
import chess.domain.exceptions.SameTeamException;
import chess.domain.exceptions.UnableCrossException;
import chess.domain.exceptions.UnableMoveTypeException;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceKind;
import chess.domain.piece.strategy.MoveDirection;
import chess.dto.SquareDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private static final String STAY_ERROR_MESSAGE = "지금 위치와 이동하려는 위치가 같습니다.";
    private static final Piece VOID_PIECE = new Piece(PieceKind.VOID, PieceColor.VOID);

    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> existedBoard) {
        this.board = new HashMap<>(existedBoard);
    }

    public void move(Position source, Position target, PieceColor turnColor) {
        checkPath(source, target);

        Piece originalPiece = pieceAtPosition(source);
        originalPiece.movable(source, target);
        checkTurn(originalPiece, turnColor);

        checkPawnCase(source, target, originalPiece);
        checkIsNotSameTeam(source, target);

        movePiece(source, target, originalPiece);
    }

    private void checkPath(Position source, Position target) {
        checkMoving(source, target);
        checkNoJumpPiece(source, target);
    }

    private void movePiece(Position source, Position target, Piece originalPiece) {
        putPieceAtPosition(target, originalPiece);
        putPieceAtPosition(source, VOID_PIECE);
    }

    private void checkPawnCase(Position source, Position target, Piece originalPiece) {
        if (originalPiece.isPawn()) {
            checkDirection(source, target);
            checkMoveType(source, target);
        }
    }

    private void checkMoving(Position source, Position target) {
        if (source.computeHorizontalDistance(target) == 0 && source.computeVerticalDistance(target) == 0) {
            throw new InvalidMoveException(STAY_ERROR_MESSAGE);
        }
    }

    private void checkNoJumpPiece(Position source, Position target) {
        if (source.isLineMove(target) || source.isDiagonalMove(target)) {
            checkClearPath(source, target);
        }
    }

    private void checkIsNotSameTeam(Position source, Position target) {
        Piece sourcePiece = pieceAtPosition(source);
        Piece targetPiece = pieceAtPosition(target);

        if (sourcePiece.isSameColor(targetPiece)) {
            throw new SameTeamException();
        }
    }

    private void checkClearPath(Position source, Position target) {
        MoveDirection moveDirection = MoveDirection.getDirection(source, target);
        int xVector = moveDirection.getXVector();
        int yVector = moveDirection.getYVector();

        Position pathPosition = source.movedPosition(xVector, yVector);
        while (!pathPosition.equals(target)) {
            checkIfClear(pathPosition);
            pathPosition = pathPosition.movedPosition(xVector, yVector);
        }
    }

    private void checkIfClear(Position pathPosition) {
        if (!pieceAtPosition(pathPosition).equals(VOID_PIECE)) {
            throw new UnableCrossException();
        }
    }

    private void checkDirection(Position source, Position target) {
        Piece piece = pieceAtPosition(source);
        if (!piece.isMovingForward(source, target)) {
            throw new UnableMoveTypeException();
        }
    }

    private void checkMoveType(Position source, Position target) {
        if (isVoid(target) && source.isDiagonalMove(target)) {
            throw new UnableMoveTypeException();
        }

        if (!isVoid(target) && source.isLineMove(target)) {
            throw new UnableMoveTypeException();
        }
    }

    private boolean isVoid(Position target) {
        Piece targetPiece = pieceAtPosition(target);
        return targetPiece.equals(VOID_PIECE);
    }

    public boolean kingIsDead() {
        long numberOfAliveKing = board.values()
            .stream()
            .filter(Piece::isKing)
            .count();
        return numberOfAliveKing < 2;
    }

    public void checkTurn(Piece piece, PieceColor turnColor) {
        if (!piece.isSameColor(turnColor)) {
            throw new RuntimeException("해당 턴이 아닙니다.");
        }
    }

    public Piece pieceAtPosition(Position position) {
        return board.get(position);
    }

    public void putPieceAtPosition(Position position, Piece piece) {
        board.put(position, piece);
    }

    public PieceColor winnerColor() {
        return board.values()
            .stream()
            .filter(Piece::isKing)
            .findFirst()
            .get().color();
    }

    public List<SquareDto> squareDtos() {
        List<SquareDto> squareDtos = new ArrayList<>();

        board.keySet()
            .forEach(key -> squareDtos.add(new SquareDto(key.toString(), board.get(key).toString())));

        return squareDtos;
    }

    public void saveBoard(String roomName, PieceColor turnColor) {
        BackupBoardDao backupBoardDao = new BackupBoardDao();
        backupBoardDao.savePlayingBoard(roomName, this.board, turnColor);
    }
}
