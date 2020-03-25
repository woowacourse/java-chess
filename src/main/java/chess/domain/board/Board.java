package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    private final Map<Position, Piece> board;

    private Team team;

    public Board() {
        this.board = BoardInitializer.initializeAll();
        this.team = Team.WHITE;
    }

    public Map<String, String> parse() {
        Map<String, String> parseResult = board.entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey().toString(),
                        entry -> entry.getValue().toSymbol(),
                        (e1, e2) -> e1, LinkedHashMap::new));
        return Collections.unmodifiableMap(parseResult);
    }

    public void updateBoard(String source, String target) {
        Position sourcePosition = Position.of(source);
        Position targetPosition = Position.of(target);
        Piece movedPiece = this.board.get(sourcePosition);
        if (!movedPiece.move(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("이동할 수 없는 곳입니다.");
        }
        this.board.put(targetPosition, movedPiece);
        this.board.remove(sourcePosition);
        changeTurn();
    }

    private void changeTurn() {
        this.team = Team.changeTurn(this.team);
    }
}
