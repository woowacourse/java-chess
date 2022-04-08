package chess.board.piece;

import chess.board.Team;
import chess.board.piece.position.File;
import chess.board.piece.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

public final class Pieces {
    private final List<Piece> pieces;

    private Pieces(List<Piece> pieces) {
        this.pieces = new ArrayList<>(pieces);
    }

    public static Pieces from(List<Piece> pieces) {
        return new Pieces(pieces);
    }

    public static Pieces createInit() {
        char[] files = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        List<Piece> pieces = new ArrayList<>();
        pieces.addAll(initPieces('8', Team.BLACK));
        pieces.addAll(initPawns(files, '7', Team.BLACK));
        pieces.addAll(initBlanks(files));
        pieces.addAll(initPawns(files, '2', Team.WHITE));
        pieces.addAll(initPieces('1', Team.WHITE));
        return new Pieces(pieces);
    }

    private static List<Piece> initPieces(char rank, Team team) {
        return List.of(new Rook(Position.of('a', rank), team), new Knight(Position.of('b', rank), team),
                new Bishop(Position.of('c', rank), team), new Queen(Position.of('d', rank), team),
                new King(Position.of('e', rank), team), new Bishop(Position.of('f', rank), team),
                new Knight(Position.of('g', rank), team), new Rook(Position.of('h', rank), team));
    }

    private static List<Piece> initPawns(char[] files, char rank, Team team) {
        List<Piece> pawns = new ArrayList<>();
        for (char file : files) {
            pawns.add(new Pawn(Position.of(file, rank), team));
        }
        return pawns;
    }

    private static List<Piece> initBlanks(char[] files) {
        List<Piece> emptys = new ArrayList<>();
        for (char rank = '6'; rank > '2'; rank--) {
            createEmpty(files, emptys, rank);
        }
        return emptys;
    }

    private static void createEmpty(char[] files, List<Piece> emptys, char rank) {
        for (char file : files) {
            emptys.add(new Empty(Position.of(file, rank)));
        }
    }

    public Piece findByPosition(Position position) {
        return pieces.stream()
                .filter(piece -> piece.findPosition(position))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 위치에 말이 없습니다."));
    }

    public long countOfKing() {
        return pieces.stream()
                .filter(Piece::isKing)
                .count();
    }

    public double getTotalScore(Team team) {
        double scoreExcludingPawn = pieces.stream()
                .filter(piece -> !piece.isPawn())
                .filter(piece -> piece.team.equals(team))
                .mapToDouble(Piece::getScore)
                .sum();

        return scoreExcludingPawn + pawnScore(team);
    }

    private double pawnScore(Team team) {
        Map<File, Long> collect = pieces.stream()
                .filter(Piece::isPawn)
                .filter(pawn -> pawn.team.equals(team))
                .collect(Collectors.groupingBy(pawn -> pawn.position.getFile(), counting()));

        return pieces.stream()
                .filter(Piece::isPawn)
                .filter(pawn -> pawn.team.equals(team))
                .map(piece -> new Pawn(piece.position, piece.team))
                .mapToDouble(pawn -> pawn.getScore(collect.get(pawn.position.getFile())))
                .sum();
    }

    public void remove(Piece piece) {
        pieces.remove(piece);
    }

    public void add(Piece piece) {
        pieces.add(piece);
    }

    public List<Piece> getPieces() {
        return pieces;
    }

}
