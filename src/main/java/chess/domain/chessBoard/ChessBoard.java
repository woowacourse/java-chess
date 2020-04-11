package chess.domain.chessBoard;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.ChessFile;
import chess.domain.position.ChessRank;
import chess.domain.position.MoveDirection;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class ChessBoard {

    private ChessBoardState chessBoardState;
    private final Map<Position, ChessPiece> chessBoard;

    public ChessBoard(Map<Position, ChessPiece> chessBoard) {
        this.chessBoard = chessBoard;
        chessBoardState = ChessBoardState.of();
    }

    public ChessBoard(Map<Position, ChessPiece> chessBoard, String pieceColor) {
        this.chessBoard = chessBoard;
        chessBoardState = ChessBoardState.of(PieceColor.valueOf(pieceColor));
    }

    public void move(Position sourcePosition, Position targetPosition) {
        Objects.requireNonNull(sourcePosition, "소스 위치가 null입니다.");
        Objects.requireNonNull(targetPosition, "타겟 위치가 null입니다.");

        ChessPiece sourceChessPiece = findSourcePieceSamePlayerColorBy(sourcePosition);

        checkLeapablePiece(sourceChessPiece, sourcePosition, targetPosition);
        checkMovableOrCatchable(sourceChessPiece, sourcePosition, targetPosition);
        moveChessPiece(sourceChessPiece, sourcePosition, targetPosition);
    }

    public ChessPiece findSourcePieceSamePlayerColorBy(Position sourcePosition) {
        ChessPiece sourceChessPiece = chessBoard.get(sourcePosition);

        if (Objects.isNull(chessBoard.get(sourcePosition))) {
            throw new IllegalArgumentException("해당 위치에 체스 피스가 존재하지 않습니다.");
        }
        chessBoardState.validatePlayerTurn(sourceChessPiece);

        return sourceChessPiece;
    }

    private void checkLeapablePiece(ChessPiece sourceChessPiece, Position sourcePosition, Position targetPosition) {
        if (!sourceChessPiece.canLeap()) {
            checkChessPieceRoute(sourcePosition, targetPosition);
        }
    }

    private void checkChessPieceRoute(Position sourcePosition, Position targetPosition) {
        MoveDirection checkingDirection = MoveDirection.findDirectionOf(sourcePosition, targetPosition);
        Position checkingPosition = checkingDirection.moveDirection(sourcePosition);

        while (!checkingPosition.equals(targetPosition) && isChessPieceNotExistAt(checkingPosition)) {
            checkingPosition = checkingDirection.moveDirection(checkingPosition);
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
            sourceChessPiece.checkSamePieceColorWith(targetChessPiece);
            sourceChessPiece.checkPieceCanCatchWith(sourcePosition, targetPosition);
            chessBoardState.checkCaughtPieceIsKing(targetChessPiece);
            return;
        }
        sourceChessPiece.checkCanMoveWith(sourcePosition, targetPosition);
    }

    private void moveChessPiece(ChessPiece sourceChessPiece, Position sourcePosition, Position targetPosition) {
        chessBoard.put(targetPosition, sourceChessPiece);
        chessBoard.remove(sourcePosition);
    }

    public double calculateScore() {
        return ChessCalculator.calculateScoreOf(this);
    }

    public Stream<ChessPiece> generatePlayerChessPieceOnChessFile() {
        return Arrays.stream(ChessFile.values())
                .flatMap(this::getChessPiecesOn);
    }

    private Stream<ChessPiece> getChessPiecesOn(ChessFile chessFile) {
        return chessBoard.entrySet().stream()
                .filter(entry -> entry.getKey().isSameFilePosition(chessFile))
                .filter(entry -> entry.getValue().isSamePieceColorWith(getPlayerColor()))
                .map(Map.Entry::getValue);
    }

    public Map<Position, ChessPiece> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }

    public boolean contains(ChessFile chessFile, ChessRank chessRank) {
        return chessBoard.containsKey(Position.of(chessFile, chessRank));
    }

    public ChessPiece getChessPiece(ChessFile chessFile, ChessRank chessRank) {
        return chessBoard.get(Position.of(chessFile, chessRank));
    }

    public PieceColor getPlayerColor() {
        return chessBoardState.getPlayerTurnState();
    }

    public boolean isCaughtKing() {
        return chessBoardState.isCaughtKing();
    }

    public boolean containsPosition(Position position) {
        return chessBoard.containsKey(position);
    }

    public void playerTurnChange() {
        if (!chessBoardState.isCaughtKing()) {
            chessBoardState.playerTurnChange();
        }
    }
}
