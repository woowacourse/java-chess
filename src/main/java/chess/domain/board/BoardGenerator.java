package chess.domain.board;

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

public class BoardGenerator {
    public Map<Position, Piece> generate() {
        Map<Position, Piece> board = new HashMap<>();
        initializePiece(board);
        return board;
    }

    private void initializePiece(Map<Position, Piece> board) {
        initializePawnLinePieces(board, Rank.RANK2, Color.WHITE);
        initializePawnLinePieces(board, Rank.RANK7, Color.BLACK);

        initializeEndLinePieces(board, Rank.RANK1, Color.WHITE);
        initializeEndLinePieces(board, Rank.RANK8, Color.BLACK);

        initializeEmptyLinePieces(board);
    }

    private void initializePawnLinePieces(Map<Position, Piece> board, Rank rank, Color color) {
        for (File file : File.values()) {
            board.put(Position.of(file, rank), InitPawn.from(color));
        }
    }

    private void initializeEndLinePieces(Map<Position, Piece> board, Rank rank, Color color) {
        board.put(Position.of(File.FILE_A, rank), Rook.from(color));
        board.put(Position.of(File.FILE_B, rank), Knight.from(color));
        board.put(Position.of(File.FILE_C, rank), Bishop.from(color));
        board.put(Position.of(File.FILE_D, rank), Queen.from(color));
        board.put(Position.of(File.FILE_E, rank), King.from(color));
        board.put(Position.of(File.FILE_F, rank), Bishop.from(color));
        board.put(Position.of(File.FILE_G, rank), Knight.from(color));
        board.put(Position.of(File.FILE_H, rank), Rook.from(color));
    }

    private void initializeEmptyLinePieces(Map<Position, Piece> board) {
        List<Rank> rankOfEmptyLine = List.of(Rank.RANK3, Rank.RANK4, Rank.RANK5, Rank.RANK6);
        for (Rank rank : rankOfEmptyLine) {
            initializeEachEmptyLinePieces(board, rank);
        }
    }

    private void initializeEachEmptyLinePieces(Map<Position, Piece> board, Rank rank) {
        for (File file : File.values()) {
            board.put(Position.of(file, rank), Empty.getInstance());
        }
    }
}
