package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.moveRule.*;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> board;

    public Board() {
        board = new HashMap<>();
        initializePiece();
    }

    public Map<Position, String> move(Position currentPosition, Position nextPosition, Color thisTurn) {
        Piece piece = board.get(currentPosition);
        List<Position> routePositions = piece.move(currentPosition, nextPosition);
        validateThisTurnColor(thisTurn, piece);

        if (piece.isPawn()) {
            return movePawn(currentPosition, nextPosition, routePositions);
        }

        return moveGeneralPiece(nextPosition, piece, routePositions);
    }

    private void validateThisTurnColor(Color thisTurn, Piece piece) {
        if (piece.isSameColor(thisTurn)) {
            throw new IllegalArgumentException("이번 차례에 움직일 수 있는 색의 기물이 아닙니다.");
        }
    }

    private Map<Position, String> moveGeneralPiece(Position nextPosition, Piece piece, List<Position> routePositions) {
        validateMiddlePathConflict(routePositions);
        if (piece.isOpponent(board.get(nextPosition))) {
            throw new IllegalArgumentException("이동 위치에 아군기물이 있어 이동할 수 없습니다.");
        }
        return getPrintingBoard();
    }

    private Map<Position, String> movePawn(Position currentPosition, Position nextPosition, List<Position> routePositions) {
        Piece currentPiece = board.get(currentPosition);
        Piece destinationPiece = board.get(nextPosition);
        validateMiddlePathConflict(routePositions);
        if (currentPosition.isDiagonalEqual(nextPosition) && currentPiece.isOpponent(destinationPiece)) {
            updateMovedPiece(currentPosition, nextPosition, currentPiece);
            return getPrintingBoard();
        }
        if (currentPosition.isStraightEqual(nextPosition) && board.containsKey(nextPosition) == false) {
            updateMovedPiece(currentPosition, nextPosition, currentPiece);
            return getPrintingBoard();
        }
        throw new IllegalArgumentException("해당위치에 이동할 수 없습니다. 폰은 적군 기물이 있어야 대각선 이동이, 다른 기물이 없어야 직선이동이 가능합니다.");
    }

    public Map<Position, String> getPrintingBoard() {
        Map<Position, String> pieceNames = new HashMap<>();
        for (Map.Entry<Position, Piece> entry : board.entrySet()) {
            Piece piece = entry.getValue();
            pieceNames.put(entry.getKey(), piece.formatName());
        }
        return pieceNames;
    }

    private void initializePiece() {
        initializePawnLinePieces(Rank.RANK2, Color.WHITE);
        initializePawnLinePieces(Rank.RANK7, Color.BLACK);

        initializeEndLinePieces(Rank.RANK1, Color.WHITE);
        initializeEndLinePieces(Rank.RANK8, Color.BLACK);
    }

    private void initializePawnLinePieces(Rank rank, Color color) {
        for (File file : File.values()) {
            board.put(Position.of(file, rank), new Piece(PawnMoveRule.of(color), color));
        }
    }

    private void initializeEndLinePieces(Rank rank, Color color) {
        board.put(Position.of(File.FILE_A, rank), new Piece(RookMoveRule.getInstance(), color));
        board.put(Position.of(File.FILE_B, rank), new Piece(KnightMoveRule.getInstance(), color));
        board.put(Position.of(File.FILE_C, rank), new Piece(BishopMoveRule.getInstance(), color));
        board.put(Position.of(File.FILE_D, rank), new Piece(QueenMoveRule.getInstance(), color));
        board.put(Position.of(File.FILE_E, rank), new Piece(KingMoveRule.getInstance(), color));
        board.put(Position.of(File.FILE_F, rank), new Piece(BishopMoveRule.getInstance(), color));
        board.put(Position.of(File.FILE_G, rank), new Piece(KnightMoveRule.getInstance(), color));
        board.put(Position.of(File.FILE_H, rank), new Piece(RookMoveRule.getInstance(), color));
    }

    private void validateMiddlePathConflict(List<Position> routePositions) {
        if (routePositions.stream().anyMatch(board::containsKey)) {
            throw new IllegalArgumentException("이동경로 중간에 다른 기물이 있어 이동할 수 없습니다.");
        }
    }

    private void updateMovedPiece(Position currentPosition, Position nextPosition, Piece piece) {
        board.remove(currentPosition);
        board.put(nextPosition, piece);
    }
}
