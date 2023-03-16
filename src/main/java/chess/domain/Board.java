package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.moveRule.*;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> piecePosition;

    public Board() {
        piecePosition = new HashMap<>();
        initializeEndLine(Rank.RANK1, Color.WHITE);
        initializeEndLine(Rank.RANK8, Color.BLACK);

        initializePawnLine(Rank.RANK2, Color.WHITE);
        initializePawnLine(Rank.RANK7, Color.BLACK);
    }

    public Map<Position, String> start() {
        return Collections.unmodifiableMap(convertToPieceName());
    }

    private void initializePawnLine(Rank rank, Color color) {
        for (File file : File.values()) {
            piecePosition.put(Position.of(file, rank), new Piece(PawnMoveRule.of(color), color));
        }
    }

    private void initializeEndLine(Rank rank, Color color) {
        piecePosition.put(Position.of(File.FILE_A, rank), new Piece(RookMoveRule.getInstance(), color));
        piecePosition.put(Position.of(File.FILE_B, rank), new Piece(KnightMoveRule.getInstance(), color));
        piecePosition.put(Position.of(File.FILE_C, rank), new Piece(BishopMoveRule.getInstance(), color));
        piecePosition.put(Position.of(File.FILE_D, rank), new Piece(QueenMoveRule.getInstance(), color));
        piecePosition.put(Position.of(File.FILE_E, rank), new Piece(KingMoveRule.getInstance(), color));
        piecePosition.put(Position.of(File.FILE_F, rank), new Piece(BishopMoveRule.getInstance(), color));
        piecePosition.put(Position.of(File.FILE_G, rank), new Piece(KnightMoveRule.getInstance(), color));
        piecePosition.put(Position.of(File.FILE_H, rank), new Piece(RookMoveRule.getInstance(), color));
    }

    private Map<Position, String> convertToPieceName() {
        Map<Position, String> pieceNames = new HashMap<>();
        for (Position position : piecePosition.keySet()) {
            Piece piece = piecePosition.get(position);
            pieceNames.put(position, piece.formatName());
        }
        return pieceNames;
    }
}
