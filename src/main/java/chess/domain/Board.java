package chess.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    Map<Square, Piece> board;

    public Board() {
        this.board = initializeBoard();
    }

    private static List<Square> generateSquares() {
        return Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(File.values())
                        .map(file -> new Square(file, rank)))
                .collect(Collectors.toList());
    }

    public boolean isTherePieceFromSourceToTarget(final Square source, final Square target) {
        final Move move = Move.calculateDirection(source, target);
        return isTherePiece(source, target, move) && isOtherCamp(source, target);
    }

    private boolean isOtherCamp(final Square source, final Square target) {
        final Camp sourceCamp = board.get(source).getCamp();
        final Camp targetCamp = board.get(target).getCamp();

        return !sourceCamp.equals(targetCamp) || sourceCamp.equals(Camp.EMPTY);
    }

    private boolean isTherePiece(final Square source, final Square target, final Move move) {
        final Square nextSquare = source.nextSquare(source, move.getFile(), move.getRank());
        final boolean isNextSquareEmpty = board.get(nextSquare).equals(new Piece(Role.EMPTY, Camp.EMPTY));
        final boolean isNextSquareTarget = nextSquare.equals(target);
        if (isNextSquareTarget) {
            return true;
        }
        if (isNextSquareEmpty) {
            return isTherePiece(nextSquare, target, move);
        }
        return false;
    }

    private LinkedHashMap<Square, Piece> initializeBoard() {
        final LinkedHashMap<Square, Piece> board = new LinkedHashMap<>();
        final List<Piece> pieces = generatePieces();
        final List<Square> squares = generateSquares();

        for (int i = 0; i < squares.size(); i++) {
            board.put(squares.get(i), pieces.get(i));
        }

        return board;
    }

    private List<Piece> generatePieces() {
        final List<Piece> pieces = new ArrayList<>();

        pieces.addAll(generateFirstLine(Camp.BLACK));
        pieces.addAll(generateSecondLine(Camp.BLACK));
        pieces.addAll(generateEmptyLine());
        pieces.addAll(generateEmptyLine());
        pieces.addAll(generateEmptyLine());
        pieces.addAll(generateEmptyLine());
        pieces.addAll(generateSecondLine(Camp.WHITE));
        pieces.addAll(generateFirstLine(Camp.WHITE));

        return pieces;
    }

    private List<Piece> generateFirstLine(final Camp camp) {
        final List<Piece> pieces = new ArrayList<>();

        pieces.add(new Piece(Role.ROCK, camp));
        pieces.add(new Piece(Role.KNIGHT, camp));
        pieces.add(new Piece(Role.BISHOP, camp));
        pieces.add(new Piece(Role.QUEEN, camp));
        pieces.add(new Piece(Role.KING, camp));
        pieces.add(new Piece(Role.BISHOP, camp));
        pieces.add(new Piece(Role.KNIGHT, camp));
        pieces.add(new Piece(Role.ROCK, camp));

        return pieces;
    }

    private List<Piece> generateSecondLine(final Camp camp) {
        final List<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            pieces.add(new Piece(Role.PAWN, camp));
        }

        return pieces;
    }

    private List<Piece> generateEmptyLine() {
        final List<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            pieces.add(new Piece());
        }

        return pieces;
    }

    public Piece getPiece(final Square square) {
        return board.get(square);
    }

    public List<Piece> getPieces() {
        return new ArrayList<>(board.values());
    }
}
