package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.InitPawn;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
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

    private void initializePiece() {
        initializePawnLinePieces(Rank.RANK2, Color.WHITE);
        initializePawnLinePieces(Rank.RANK7, Color.BLACK);

        initializeEndLinePieces(Rank.RANK1, Color.WHITE);
        initializeEndLinePieces(Rank.RANK8, Color.BLACK);

        initializeEmptyLinePieces();
    }

    private void initializePawnLinePieces(Rank rank, Color color) {
        for (File file : File.values()) {
            board.put(Position.of(file, rank), InitPawn.from(color));
        }
    }

    private void initializeEndLinePieces(Rank rank, Color color) {
        board.put(Position.of(File.FILE_A, rank), Rook.from(color));
        board.put(Position.of(File.FILE_B, rank), Knight.from(color));
        board.put(Position.of(File.FILE_C, rank), Bishop.from(color));
        board.put(Position.of(File.FILE_D, rank), Queen.from(color));
        board.put(Position.of(File.FILE_E, rank), King.from(color));
        board.put(Position.of(File.FILE_F, rank), Bishop.from(color));
        board.put(Position.of(File.FILE_G, rank), Knight.from(color));
        board.put(Position.of(File.FILE_H, rank), Rook.from(color));
    }

    private void initializeEmptyLinePieces() {
        List<Rank> rankOfEmptyLine = List.of(Rank.RANK3, Rank.RANK4, Rank.RANK5, Rank.RANK6);
        for (Rank rank : rankOfEmptyLine) {
            initializeEachEmptyLinePieces(rank);
        }
    }

    private void initializeEachEmptyLinePieces(Rank rank) {
        for (File file : File.values()) {
            board.put(Position.of(file, rank), Empty.getInstance());
        }
    }

    // TODO [x]현재 위치가 empty인지 검사, [x]루트 검사, [x]도착지에 아군 기물이 있는지 검사
    //  TODO 폰 : 대각선 이동시 도착지가 비어있으면 예외, 직선 이동시 도착지에 상대 기물이 있으면 예외

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
                .map(position -> board.get(position))
                .anyMatch(Piece::isPiece);
    }
}
