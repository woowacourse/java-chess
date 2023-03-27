package chess.board;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import chess.game.PieceScore;
import chess.piece.EmptyPiece;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.piece.Team;

public class ChessBoard {

    private final Map<Position, Piece> board;

    public ChessBoard() {
        this.board = PieceFactory.createPiece();
    }

    private ChessBoard(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static ChessBoard createBoardByRule(final Map<Position, Piece> piecePosition) {
        return new ChessBoard(piecePosition);
    }

    public void movePiece(final Position from, final Position to) {
        final Piece fromPiece = board.get(from);

        fromPiece.validateMove(from, to, board);
        move(from, to);
    }

    private void move(final Position from, final Position to) {
        final Piece piece = board.get(from);
        board.put(from, new EmptyPiece());
        board.put(to, piece);
    }

    public boolean isKingKilled(final Team team) {
        return board.values()
                .stream()
                .filter(piece -> piece.getType() == PieceType.KING)
                .noneMatch(piece -> piece.getTeam() == team);
    }

    public double calculateScore(final Team team) {
        return calculateTotalScore(team) - calculateDuplicatedPawnScore(team);
    }

    private double calculateTotalScore(final Team team) {
        return board.values()
                .stream()
                .filter(piece -> piece.isSameTeam(team))
                .mapToDouble(piece -> PieceScore.findScore(piece.getType()))
                .sum();
    }

    private double calculateDuplicatedPawnScore(final Team team) {
        return countPawnInSameFile(team).values()
                .stream()
                .filter(numberOfPawn -> numberOfPawn >= 2)
                .mapToDouble(pawnScore -> pawnScore * 0.5)
                .sum();
    }

    private Map<File, Long> countPawnInSameFile(final Team team) {
        return Arrays.stream(Rank.values())
                .flatMap(file -> Arrays.stream(File.values())
                        .map(rank -> new Position(rank, file)))
                .filter(position -> board.get(position).isSameTeam(team))
                .filter(position -> board.get(position).isSameType(PieceType.PAWN))
                .collect(Collectors.groupingBy(Position::getFile, Collectors.counting()));
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }
}
