package chess.domain;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.dto.ChessBoardStatus;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoard {

    private final int id;
    private final Map<Position, Piece> piecesByPosition;
    private boolean isOver;
    private Camp currentTurnCamp;

    public ChessBoard(final int id) {
        this.id = id;
        this.piecesByPosition = PieceInitializer.createPiecesWithPosition();
        this.isOver = false;
        this.currentTurnCamp = Camp.WHITE;
    }

    public ChessBoard(final int id, final Map<Position, Piece> piecesByPosition) {
        this.id = id;
        this.piecesByPosition = piecesByPosition;
        this.isOver = false;
        this.currentTurnCamp = Camp.WHITE;
    }

    public ChessBoard(final int id, final Map<Position, Piece> piecesByPosition, final ChessBoardStatus boardStatus) {
        this.id = id;
        this.piecesByPosition = piecesByPosition;
        this.isOver = boardStatus.isOver();
        this.currentTurnCamp = boardStatus.getCurrentTurn();
    }

    public void move(Position source, Position destination) {
        Piece movingPiece = findPieceAtSourcePosition(source);
        boolean isKingChecked = updatePiecesByPosition(source, destination, movingPiece);
        if (!isKingChecked) {
            switchCampTurn();
        }
        this.isOver = isKingChecked;
    }

    private Piece findPieceAtSourcePosition(final Position source) {
        if (isEmptyPosition(source)) {
            throw new IllegalArgumentException(MoveErrorMessage.WRONG_START_ERROR_MESSAGE);
        }
        Piece piece = piecesByPosition.get(source);
        if (piece.isDifferentCamp(currentTurnCamp)) {
            throw new IllegalArgumentException(MoveErrorMessage.WRONG_PIECE_COLOR_ERROR_MESSAGE);
        }
        return piece;
    }

    private boolean updatePiecesByPosition(final Position source, final Position destination, final Piece movingPiece) {
        movingPiece.validateMove(source, destination, piecesByPosition);
        boolean isKingAttacked = isKingAt(destination);
        piecesByPosition.put(destination, movingPiece);
        piecesByPosition.put(source, Empty.getInstance());
        return isKingAttacked;
    }

    private boolean isKingAt(Position position) {
        Piece target = piecesByPosition.get(position);
        return target.getType() == PieceType.KING;
    }

    private boolean isEmptyPosition(final Position position) {
        return piecesByPosition.get(position).getType() == PieceType.EMPTY;
    }

    private void switchCampTurn() {
        currentTurnCamp = currentTurnCamp.transfer();
    }

    public double calculateScoreByCamp(Camp camp) {
        return positionsByPiece().entrySet()
                .stream()
                .filter(entry -> entry.getKey().isSameCamp(camp))
                .map(entry -> calculatePieceScore(entry.getKey(), entry.getValue()))
                .reduce(0.0, Double::sum);
    }

    private double calculatePieceScore(Piece piece, List<Position> existingPositions) {
        return piece.sumPointsOf(existingPositions);
    }

    private Map<Piece, List<Position>> positionsByPiece() {
        return piecesByPosition.keySet()
                .stream()
                .collect(Collectors.groupingBy(piecesByPosition::get));
    }

    public int getId() {
        return id;
    }

    public boolean isOver() {
        return isOver;
    }

    public ChessBoardStatus status() {
        return new ChessBoardStatus(currentTurnCamp, isOver);
    }

    public Map<Position, Piece> piecesByPosition() {
        return new HashMap<>(piecesByPosition);
    }

}
