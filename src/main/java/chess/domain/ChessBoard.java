package chess.domain;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Team;
import chess.domain.position.File;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ChessBoard {

    private final Map<Position, Piece> cells = new LinkedHashMap<>();

    public ChessBoard() {
        Map<Position, Piece> pieces = PieceFactory.createPieces();

        cells.putAll(pieces);
    }

    public Map<Position, Piece> getCells() {
        return cells;
    }

    public Team findTeam(Position position) {
        Piece piece = cells.get(position);

        if (piece == null) {
            throw new IllegalArgumentException("해당 위치에 말이 없습니다.");
        }

        return piece.getTeam();
    }

    public void move(Command command) {
        Map<String, Position> positions = command.makePositions();

        Position source = positions.get("source");

        Position target = positions.get("target");

        Piece piece = cells.get(source);

        piece.move(source, target, this);

        cells.remove(source);

        cells.put(target, piece);
    }


    public boolean isContainPiece(List<Position> paths) {
        Set<Position> cellsKeySet = cells.keySet();

        long count = paths.stream()
                .filter(cellsKeySet::contains)
                .count();

        return count > 0;
    }

    public double calculateByTeam(Team team) {
        Map<Position, Piece> pawns = getPawnByTeam(team);

        File[] values = File.values();

        int twoPawnCount = Arrays.stream(values)
                .mapToInt(file -> countPawn(pawns, file))
                .sum();

        double decrease = twoPawnCount * 0.5;

        double sum = cells.values()
                .stream()
                .filter(piece -> piece.getTeam() == team)
                .mapToDouble(Piece::getScore)
                .sum();

        return sum - decrease;
    }

    private Map<Position, Piece> getPawnByTeam(Team team) {
        return cells.keySet()
                .stream()
                .filter(position -> cells.get(position).isPawn() && cells.get(position).getTeam() == team)
                .collect(toMap(position -> position, cells::get));
    }

    private int countPawn(Map<Position, Piece> pawns, File file) {
        List<Position> collect = pawns.keySet()
                .stream()
                .filter(position -> position.getFile() == file)
                .collect(toList());

        if (collect.size() >= 2) {
            return collect.size();
        }

        return 0;
    }

    public Piece getPieceByPosition(Position position) {
        return cells.get(position);
    }

    public boolean isExist(Position target) {
        return cells.containsKey(target);
    }

    public boolean isExistKing() {
        long kingCount = cells.values()
                .stream()
                .filter(Piece::isKing)
                .count();

        return kingCount == 2;
    }

    public String findWinTeam(Map<Team, Double> teamScores) {
        Double whiteScore = teamScores.get(WHITE);
        Double blackScore = teamScores.get(BLACK);

        if (isExistKing() || whiteScore.equals(blackScore)) {
            return "무승부";
        }

        Piece winKing = cells.values()
                .stream()
                .filter(Piece::isKing)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        return winKing.getTeam().toString();
    }
}
