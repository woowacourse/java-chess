package chess.domain.board;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.square.Empty;
import chess.domain.square.Square;
import chess.domain.square.piece.Bishop;
import chess.domain.square.piece.Color;
import chess.domain.square.piece.King;
import chess.domain.square.piece.Knight;
import chess.domain.square.piece.Pawn;
import chess.domain.square.piece.Queen;
import chess.domain.square.piece.Rook;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// TODO: 단위 테스트 작성
public class ChessBoardMaker {

    private static final int EMPTY_START = 3;
    private static final int EMPTY_END = 6;
    private static final int FILE_SIZE = 8;
    private static final List<Square> EIGHTH_RANK = List.of(
            Rook.from(Color.BLACK), Knight.from(Color.BLACK), Bishop.from(Color.BLACK), Queen.from(Color.BLACK),
            King.from(Color.BLACK), Bishop.from(Color.BLACK), Knight.from(Color.BLACK), Rook.from(Color.BLACK));
    private static final List<Square> EMPTY_RANK = new ArrayList<>(
            Collections.nCopies(FILE_SIZE, Empty.getInstance()));
    private static final List<Square> FIRST_RANK = List.of(
            Rook.from(Color.WHITE), Knight.from(Color.WHITE), Bishop.from(Color.WHITE), Queen.from(Color.WHITE),
            King.from(Color.WHITE), Bishop.from(Color.WHITE), Knight.from(Color.WHITE), Rook.from(Color.WHITE));

    public ChessBoard make() {
        Queue<Square> orderedSquares = makeOrderedSquares();
        return new ChessBoard(makeInitialSquares(orderedSquares));
    }

    private Queue<Square> makeOrderedSquares() {
        Queue<Square> orderedSquares = new ArrayDeque<>();
        orderedSquares.addAll(EIGHTH_RANK);
        orderedSquares.addAll(makePawns(Color.BLACK));
        orderedSquares.addAll(makeEmptyRanks());
        orderedSquares.addAll(makePawns(Color.WHITE));
        orderedSquares.addAll(FIRST_RANK);

        return orderedSquares;
    }

    private List<Square> makePawns(Color color) {
        List<Square> squares = new ArrayList<>();
        for (int file = 0; file < FILE_SIZE; file++) {
            squares.add(Pawn.createOnStart(color));
        }

        return squares;
    }

    private List<Square> makeEmptyRanks() {
        List<Square> squares = new ArrayList<>();
        for (int rank = EMPTY_START; rank <= EMPTY_END; rank++) {
            squares.addAll(EMPTY_RANK);
        }

        return squares;
    }

    private Map<Position, Square> makeInitialSquares(Queue<Square> squareQueue) {
        Map<Position, Square> squares = new HashMap<>();
        for (Rank rank : Rank.values()) {
            squares.putAll(makeRank(rank, squareQueue));
        }
        return squares;
    }

    private Map<Position, Square> makeRank(Rank rank, Queue<Square> squareQueue) {
        Map<Position, Square> squares = new HashMap<>();
        for (File file : File.values()) {
            squares.put(new Position(rank, file), squareQueue.poll());
        }

        return squares;
    }
}
