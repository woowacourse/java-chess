package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.dto.ChessBoardDto;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChessBoard {
    private static final Piece EMPTY = new Empty();
    private final Map<Position, Piece> chessBoard = new LinkedHashMap<>();

    public ChessBoard() {
    }

    public void initialBoard() {
        initializeBlackPieces();
        initializeWhitePieces();
    }

    public ChessBoardDto convertToDto() {
        return new ChessBoardDto(chessBoard);
    }

    public void move(Position source, Position target) {
        if (!canMove(source, target)) {
            throw new IllegalArgumentException("올바르지 않은 이동입니다.");
        }
        Piece sourcePiece = chessBoard.get(source);
        chessBoard.put(target, sourcePiece);
        chessBoard.remove(source);
    }

    private boolean canMove(Position source, Position target) {
        if (!chessBoard.containsKey(source)) {
            throw new IllegalArgumentException("이동할 수 있는 말이 없습니다.");
        }

        Piece sourcePiece = chessBoard.get(source);
        Piece targetPiece = EMPTY;

        if (chessBoard.containsKey(target)) {
            targetPiece = chessBoard.get(target);
        }

        if (sourcePiece.canMove(source, target, targetPiece)) {
            return sourcePiece.searchPath(source, target).stream().noneMatch(chessBoard::containsKey);
        }
        return false;
    }

    private void initializeBlackPieces() {
        initializeEdgeRank(Rank.EIGHT, Color.BLACK);
        initializePawnRank(Rank.SEVEN, Color.BLACK);
    }

    private void initializeWhitePieces() {
        initializePawnRank(Rank.TWO, Color.WHITE);
        initializeEdgeRank(Rank.ONE, Color.WHITE);
    }

    private void initializePawnRank(Rank rank, Color color) {
        for (File file : File.values()) {
            chessBoard.put(Position.of(file, rank), new Pawn(color));
        }
    }

    private void initializeEdgeRank(Rank rank, Color color) {
        chessBoard.put(Position.of(File.A, rank), new Rook(color));
        chessBoard.put(Position.of(File.B, rank), new Knight(color));
        chessBoard.put(Position.of(File.C, rank), new Bishop(color));
        chessBoard.put(Position.of(File.D, rank), new Queen(color));
        chessBoard.put(Position.of(File.E, rank), new King(color));
        chessBoard.put(Position.of(File.F, rank), new Bishop(color));
        chessBoard.put(Position.of(File.G, rank), new Knight(color));
        chessBoard.put(Position.of(File.H, rank), new Rook(color));
    }
}
