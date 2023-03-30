package domain.game;

import domain.piece.EmptyPiece;
import domain.piece.Piece;
import domain.piece.Position;
import domain.piece.Side;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {

    private final Map<Position, Piece> chessBoard;

    public Board(Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public Side move(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = this.chessBoard.get(sourcePosition);
        Side nextSide = sourcePiece.getSide().nextSide();
        movePiece(sourcePosition, targetPosition, sourcePiece);
        return nextSide;
    }

    public boolean isImpossibleMoving(Position sourcePosition, Position targetPosition, Side side) {
        return !validateMoving(sourcePosition, targetPosition, side);
    }

    public boolean isKing(Position targetPosition) {
        return chessBoard.get(targetPosition).isSameType(PieceType.KING);
    }

    public ScoreBoard makeScoreBoard(GameState gameState) {
        return new ScoreBoard(chessBoard, gameState);
    }

    public Side calculateKingExistSide(Side side) {
        List<Piece> pieces = collectPiecesBySide(side);

        return pieces.stream()
                .filter(piece -> piece.isSameType(PieceType.KING))
                .map(Piece::getSide)
                .findAny()
                .orElse(side.nextSide());
    }

    private boolean validateMoving(Position sourcePosition, Position targetPosition, Side side) {
        Piece sourcePiece = chessBoard.get(sourcePosition);
        if (validateSourcePositionIsEmpty(sourcePosition, sourcePiece) &&
                validateTurn(side, sourcePiece) &&
                validateIsMovable(sourcePosition, targetPosition) &&
                validatePathIncludeAnyPiece(sourcePosition, targetPosition, sourcePiece)) {
            return true;
        }
        return false;

    }

    private boolean validateTurn(Side side, Piece sourcePiece) {
        if (sourcePiece.isIncorrectTurn(side)) {
            throw new IllegalArgumentException("다른 진영의 말은 움직일 수 없습니다.");
        }
        return true;
    }

    private boolean validateSourcePositionIsEmpty(Position sourcePosition, Piece sourcePiece) {
        if (sourcePiece.isEmptyPiece()) {
            throw new IllegalArgumentException(sourcePosition + "에 움직일 수 있는 말이 없습니다.");
        }
        return true;
    }

    private boolean validatePathIncludeAnyPiece(Position sourcePosition, Position targetPosition, Piece sourcePiece) {
        List<Position> path = sourcePiece.collectPath(sourcePosition, targetPosition);
        for (Position position : path) {
            checkIsExistAnyPieceOn(position);
        }
        return true;
    }

    private void checkIsExistAnyPieceOn(Position position) {
        if (!this.chessBoard.get(position).isEmptyPiece()) {
            throw new IllegalArgumentException("경로에 다른 말이 있습니다.");
        }
    }

    private boolean validateIsMovable(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = this.chessBoard.get(sourcePosition);
        Piece targetPiece = this.chessBoard.get(targetPosition);
        if (!sourcePiece.isMovable(targetPiece, sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("올바른 움직임이 아닙니다.");
        }
        return true;
    }

    private void movePiece(Position sourcePosition, Position targetPosition, Piece sourcePiece) {
        this.chessBoard.put(sourcePosition, new EmptyPiece());
        this.chessBoard.put(targetPosition, sourcePiece);
    }

    private List<Piece> collectPiecesBySide(Side side) {
        return chessBoard.values().stream()
                .filter(piece -> piece.isSameSide(side))
                .collect(Collectors.toList());
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard;
    }
}
