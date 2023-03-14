package chess.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        final List<Rank> ranks = Arrays.asList(Rank.values());
        Collections.reverse(ranks);

        return ranks.stream()
                .flatMap(rank -> Arrays.stream(File.values())
                        .map(file -> new Square(file, rank)))
                .collect(Collectors.toList());
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
