package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.File;
import chess.domain.position.Position;

import java.util.*;
import java.util.stream.Collectors;

public class Board {
    private final Map<Position, Piece> board;

    public Board() {
        this(BoardInitializer.initializeAll());
    }

    public Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board webBoard(Map<String, String> pieceOnBoards) {
        return new Board(BoardInitializer.webInitialize(pieceOnBoards));
    }

    public void updateBoard(final Position sourcePosition, final Position targetPosition) {
        Piece selectedPiece = this.board.get(sourcePosition);
        this.board.put(targetPosition, selectedPiece);
        this.board.remove(sourcePosition);
    }

    public BoardScore calculateScore(final Team team) {
        BoardScore totalScore = calculateTotalScore(team);
        return calculatePawnScore(team, totalScore);
    }

    private BoardScore calculateTotalScore(final Team team) {
        double totalScore = board.values().stream()
                .filter(piece -> team.isSameTeamWith(piece.getTeam()))
                .mapToDouble(Piece::getScore)
                .sum();
        return new BoardScore(totalScore);
    }

    private BoardScore calculatePawnScore(final Team team, BoardScore totalScore) {
        for (File file : File.values()) {
            List<Map.Entry<Position, Piece>> sameFilePawns = this.board.entrySet().stream()
                    .filter(entry -> File.of(entry.getKey().getFile()).equals(file))
                    .filter(entry -> entry.getValue().isPawn() && !entry.getValue().isEnemy(team))
                    .collect(Collectors.toList());

            totalScore = totalScore.pawnStrategy(sameFilePawns);
        }
        return totalScore;
    }

    public Optional<Team> getWinner() {
        if (checkWhiteKing() && !checkBlackKing()) {
            return Optional.of(Team.WHITE);
        }
        if (!checkWhiteKing() && checkBlackKing()) {
            return Optional.of(Team.BLACK);
        }
        return Optional.empty();
    }

    private boolean checkWhiteKing() {
        return this.board.values().stream()
                .anyMatch(Piece::isWhiteKing);
    }

    private boolean checkBlackKing() {
        return this.board.values().stream()
                .anyMatch(Piece::isBlackKing);
    }

    public boolean isEmpty(final Position position) {
        return !this.board.containsKey(position);
    }

    public Optional<Piece> getPiece(final Position position) {
        if (this.board.containsKey(position)) {
            return Optional.of(this.board.get(position));
        }
        return Optional.empty();
    }

    public Map<String, String> parse() {
        Map<String, String> parseResult = this.board.entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey().toString(),
                        entry -> entry.getValue().toSymbol(),
                        (e1, e2) -> e1, LinkedHashMap::new));

        return Collections.unmodifiableMap(parseResult);
    }

    public boolean contain(String position) {
        return this.board.containsKey(Position.of(position));
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(this.board);
    }

    public Map.Entry<Position, Piece> getEntry(String position) {
        Position entryPosition = Position.of(position);
        return this.board.entrySet().stream()
                .filter(entry -> entry.getKey().equals(entryPosition))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("위치가 잘못되었습니다."));
    }
}