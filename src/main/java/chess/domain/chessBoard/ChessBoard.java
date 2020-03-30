package chess.domain.chessBoard;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.ChessFile;
import chess.domain.position.ChessRank;
import chess.domain.position.MoveDirection;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public class ChessBoard {

    private final Map<Position, ChessPiece> chessBoard;
    private PieceColor playerTurnState;

    public ChessBoard(Map<Position, ChessPiece> chessBoard) {
        this.chessBoard = chessBoard;
        playerTurnState = PieceColor.BLACK;
    }

    public void move(Position sourcePosition, Position targetPosition) {
        Objects.requireNonNull(sourcePosition, "소스 위치가 null입니다.");
        Objects.requireNonNull(targetPosition, "타겟 위치가 null입니다.");

        ChessPiece sourceChessPiece = findSourceChessPieceFrom(sourcePosition);

        checkLeapablePiece(sourceChessPiece, sourcePosition, targetPosition);
        checkMovableOrCatchable(sourceChessPiece, sourcePosition, targetPosition);
        moveChessPiece(sourceChessPiece, sourcePosition, targetPosition);
        playerTurnState = playerTurnState.getOppositeColor();
    }

    private ChessPiece findSourceChessPieceFrom(Position sourcePosition) {
        ChessPiece sourceChessPiece = chessBoard.get(sourcePosition);

        if (Objects.isNull(chessBoard.get(sourcePosition))) {
            throw new IllegalArgumentException("해당 위치에 체스 피스가 존재하지 않습니다.");
        }
        playerTurnState.validatePlayerTurn(sourceChessPiece);

        return sourceChessPiece;
    }

    private void checkLeapablePiece(ChessPiece sourceChessPiece, Position sourcePosition, Position targetPosition) {
        if (!sourceChessPiece.canLeap()) {
            checkChessPieceRoute(sourcePosition, targetPosition);
        }
    }

    private void checkChessPieceRoute(Position sourcePosition, Position targetPosition) {
        MoveDirection checkingDirection = MoveDirection.findDirectionOf(sourcePosition, targetPosition);
        Position checkingPosition = checkingDirection.move(sourcePosition);

        while (!checkingPosition.equals(targetPosition) && isChessPieceNotExistAt(checkingPosition)) {
            checkingPosition = checkingDirection.move(checkingPosition);
        }
    }

    private boolean isChessPieceNotExistAt(Position checkingPosition) {
        if (Objects.nonNull(chessBoard.get(checkingPosition))) {
            throw new NullPointerException("체스 피스의 이동 경로에 다른 체스 피스가 존재합니다.");
        }
        return true;
    }

    private void checkMovableOrCatchable(ChessPiece sourceChessPiece, Position sourcePosition,
                                         Position targetPosition) {
        ChessPiece targetChessPiece = chessBoard.get(targetPosition);

        if (Objects.nonNull(targetChessPiece)) {
            sourceChessPiece.checkIsSamePieceColorWith(targetChessPiece);
            sourceChessPiece.checkCanCatchWith(sourcePosition, targetPosition);
            targetChessPiece.checkCaughtPieceIsKing();
            return;
        }
        sourceChessPiece.checkCanMoveWith(sourcePosition, targetPosition);
    }

    private void moveChessPiece(ChessPiece sourceChessPiece, Position sourcePosition, Position targetPosition) {
        chessBoard.put(targetPosition, sourceChessPiece);
        chessBoard.remove(sourcePosition);
    }

    public Map<Position, ChessPiece> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }

    public boolean contains(ChessFile chessFile, ChessRank chessRank) {
        return chessBoard.containsKey(Position.of(chessFile, chessRank));
    }

    public ChessPiece getChessPiece(ChessFile file, ChessRank rank) {
        return chessBoard.get(Position.of(file.toString() + rank.toString()));
    }

    public PieceColor getPlayerColor() {
        return playerTurnState;
    }
}
