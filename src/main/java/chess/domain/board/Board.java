package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
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

    public Piece getPiece(final Position position) {
        if (!this.board.containsKey(position)) {
            throw new IllegalArgumentException("비어있는 위치를 선택하였습니다.");
        }

        return this.board.get(position);
    }

    public Map<String, String> parse() {
        Map<String, String> parseResult = board.entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey().toString(),
                        entry -> entry.getValue().toSymbol(),
                        (e1, e2) -> e1, LinkedHashMap::new));

        return Collections.unmodifiableMap(parseResult);
    }

//    public List<Tile> tiles() {
//        List<Tile> tiles = this.board.entrySet().stream()
//                .map((entry) -> {
//                    Position position = entry.getKey();
//                    Piece piece = entry.getValue();
//                    PieceType pieceType = piece.
//                })
//                .collect(Collectors.toList());
//    }
}