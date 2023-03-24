package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.File;
import chess.domain.position.Position;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    private final Map<Position, Piece> board;

    public Board() {
        board = new BoardGenerator().generate();
    }

    public Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public Map<Position, String> move(Position currentPosition, Position nextPosition) {
        validateEmpty(currentPosition);
        Piece pieceOfCurrentPosition = board.get(currentPosition);
        if (pieceOfCurrentPosition.isSliding()) {
            validateRoute(currentPosition, nextPosition);
        }

        Piece pieceOfNextPosition = board.get(nextPosition);
        Piece movingPiece = pieceOfCurrentPosition.move(currentPosition, nextPosition, pieceOfNextPosition);
        board.put(currentPosition, Empty.getInstance());
        board.put(nextPosition, movingPiece);
        return getPrintingBoard();
    }

    public Map<Position, String> getPrintingBoard() {
        Map<Position, String> pieceNames = new HashMap<>();
        for (Position position : board.keySet()) {
            Piece piece = board.get(position);
            pieceNames.put(position, piece.formatName());
        }
        return pieceNames;
    }

    public Piece findPieceByPosition(Position position) {
        return board.get(position);
    }


    private void validateEmpty(Position position) {
        Piece piece = board.get(position);
        if (piece.isEmpty()) {
            throw new IllegalArgumentException("현재 위치에 기물이 존재하지 않습니다.");
        }
    }

    private void validateRoute(Position currentPosition, Position nextPosition) {
        List<Position> route = currentPosition.getRoute(nextPosition);
        if (isExistAnotherPiece(route)) {
            throw new IllegalArgumentException("현재 위치와 이동하려는 위치 사이에 다른 기물이 있어 이동할 수 없습니다.");
        }
    }

    private boolean isExistAnotherPiece(List<Position> route) {
        return route.stream()
                .map(board::get)
                .anyMatch(Piece::isPiece);
    }

    public double calculateScoreByColor(Color color) {
        double totalScore = 0;
        for (File file : File.values()) {
            totalScore += calculateScoreOfEachFile(file, color);
        }
        return totalScore;
    }

    private double calculateScoreOfEachFile(File file, Color color) {
        double totalScore = 0;
        Map<PieceType, Integer> pieceCounter = getCountOfTeamPieceByFile(file, color);
        for (PieceType pieceType : pieceCounter.keySet()) {
            int count = pieceCounter.get(pieceType);
            totalScore += calculateScoreOfSameRank(pieceType, count);
        }
        return totalScore;
    }

    private double calculateScoreOfSameRank(PieceType pieceType, int count) {
        if (isDuplicatePawn(pieceType, count)) {
            return count * pieceType.getDuplicatePawnScore();
        }
        return count * pieceType.getScore();
    }

    private boolean isDuplicatePawn(PieceType pieceType, int count) {
        return pieceType.equals(PieceType.PAWN) && count > 1;
    }

    private Map<PieceType, Integer> getCountOfTeamPieceByFile(File file, Color color) {
        List<Position> positions = findPositionByFile(file);
        List<Piece> pieces = filterPiecesByPosition(positions);
        List<Piece> targetPieces = filterPiecesByColor(pieces, color);
        Map<PieceType, Integer> pieceCounter = new EnumMap<>(PieceType.class);
        targetPieces.forEach(piece -> piece.addPieceType(pieceCounter));
        return pieceCounter;
    }

    private List<Piece> filterPiecesByColor(List<Piece> pieces, Color color) {
        return pieces.stream()
                .filter(Piece::isPiece)
                .filter(piece -> piece.isSameColor(color))
                .collect(Collectors.toList());
    }

    private List<Piece> filterPiecesByPosition(List<Position> positions) {
        List<Piece> pieces = positions.stream()
                .map(position -> board.get(position))
                .collect(Collectors.toList());
        return pieces;
    }

    private List<Position> findPositionByFile(File file) {
        List<Position> positions = board.keySet()
                .stream()
                .filter(position -> position.isSameFile(file))
                .collect(Collectors.toList());
        return positions;
    }
}
