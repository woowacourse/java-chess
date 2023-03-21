package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.moveRule.BishopMoveRule;
import chess.domain.piece.moveRule.KingMoveRule;
import chess.domain.piece.moveRule.KnightMoveRule;
import chess.domain.piece.moveRule.PawnMoveRule;
import chess.domain.piece.moveRule.QueenMoveRule;
import chess.domain.piece.moveRule.RookMoveRule;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.HashMap;
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

    public Map<Position, String> move(Position currentPosition, Position nextPosition) {
        Piece piece = board.get(currentPosition);
        piece.move(currentPosition, nextPosition, this.board);
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
}
