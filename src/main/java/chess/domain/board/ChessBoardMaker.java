package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ChessBoardMaker {
    private static final int EMPTY_START = 3;
    private static final int EMPTY_END = 6;
    private static final int FILE_SIZE = 8;
    private static final List<Piece> EIGHTH_RANK = List.of(
            Rook.from(Color.BLACK), Knight.from(Color.BLACK), Bishop.from(Color.BLACK), Queen.from(Color.BLACK),
            King.from(Color.BLACK), Bishop.from(Color.BLACK), Knight.from(Color.BLACK), Rook.from(Color.BLACK));
    private static final List<Piece> EMPTY_RANK = new ArrayList<>(
            Collections.nCopies(FILE_SIZE, Empty.getInstance()));
    private static final List<Piece> FIRST_RANK = List.of(
            Rook.from(Color.WHITE), Knight.from(Color.WHITE), Bishop.from(Color.WHITE), Queen.from(Color.WHITE),
            King.from(Color.WHITE), Bishop.from(Color.WHITE), Knight.from(Color.WHITE), Rook.from(Color.WHITE));

    public ChessBoard make() {
        Queue<Piece> orderedPieces = makeOrderedPieces();
        return new ChessBoard(makeInitialPieces(orderedPieces));
    }

    private Queue<Piece> makeOrderedPieces() {
        Queue<Piece> orderedPieces = new ArrayDeque<>();
        orderedPieces.addAll(EIGHTH_RANK);
        orderedPieces.addAll(makePawns(Color.BLACK));
        orderedPieces.addAll(makeEmptyRanks());
        orderedPieces.addAll(makePawns(Color.WHITE));
        orderedPieces.addAll(FIRST_RANK);

        return orderedPieces;
    }

    private List<Piece> makePawns(Color color) {
        List<Piece> pieces = new ArrayList<>();
        for (int file = 0; file < FILE_SIZE; file++) {
            pieces.add(Pawn.createOnStart(color));
        }

        return pieces;
    }

    private List<Piece> makeEmptyRanks() {
        List<Piece> pieces = new ArrayList<>();
        for (int rank = EMPTY_START; rank <= EMPTY_END; rank++) {
            pieces.addAll(EMPTY_RANK);
        }

        return pieces;
    }

    private Map<Position, Piece> makeInitialPieces(Queue<Piece> pieceQueue) {
        Map<Position, Piece> pieces = new HashMap<>();
        for (Rank rank : Rank.values()) {
            pieces.putAll(makeRank(rank, pieceQueue));
        }
        return pieces;
    }

    private Map<Position, Piece> makeRank(Rank rank, Queue<Piece> pieceQueue) {
        Map<Position, Piece> pieces = new HashMap<>();
        for (File file : File.values()) {
            pieces.put(new Position(rank, file), pieceQueue.poll());
        }

        return pieces;
    }
}
