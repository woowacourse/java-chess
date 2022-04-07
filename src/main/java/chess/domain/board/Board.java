package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Board {

    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = new TreeMap<>(board);
    }

    public void movePiece(final Position source, final Position target) {
        board.put(target, getPiece(source));
        board.put(source, new Blank());
    }

    public Team getTeamOfPiece(final Position position) {
        return board.get(position).getTeam();
    }

    public Piece getPiece(final Position position) {
        return board.get(position);
    }

    public Team searchTeamOfDeadKing() {
        List<Piece> aliveKings = board.values().stream()
                .filter(Piece::isKing)
                .collect(Collectors.toList());
        if (aliveKings.size() == 1) {
            return aliveKings.get(0).getTeam().oppositeTeam();
        }
        return Team.NONE;
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public Map<String, Piece> getBoardByRawPosition() {
        return board.keySet().stream()
                .collect(Collectors.toMap(
                        position -> String.valueOf(position.getColumn().getValue()) + position.getRow().getValue(),
                        this::getPiece
                ));
    }
}
