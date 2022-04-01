package chess.model.board;

import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;
import chess.model.Team;
import chess.model.piece.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardFactory {

    public static Map<Position, Piece> create() {
        Map<Position, Piece> board = new LinkedHashMap<>(64);
        List<String> files = File.getValues();
        board.putAll(makePieces("8", Team.BLACK));
        board.putAll(makePawns(files, "7", Team.BLACK));
        board.putAll(makeEmpty(files));
        board.putAll(makePawns(files, "2", Team.WHITE));
        board.putAll(makePieces("1", Team.WHITE));

        return board;
    }

    private static Map<Position, Piece> makePieces(String rank, Team team) {
        Map<Position, Piece> pieces = new LinkedHashMap<>();
        pieces.put(Position.from("a" + rank), new Rook(team));
        pieces.put(Position.from("b" + rank), new Knight(team));
        pieces.put(Position.from("c" + rank), new Bishop(team));
        pieces.put(Position.from("d" + rank), new Queen(team));
        pieces.put(Position.from("e" + rank), new King(team));
        pieces.put(Position.from("f" + rank), new Bishop(team));
        pieces.put(Position.from("g" + rank), new Knight(team));
        pieces.put(Position.from("h" + rank), new Rook(team));

        return pieces;
    }

    private static Map<Position, Piece> makePawns(List<String> files, String rank, Team team) {
        return files.stream()
                .map(file -> Position.from(file + rank))
                .collect(Collectors.toMap(position -> position, (position) -> new Pawn(team)));
    }

    private static Map<Position, Empty> makeEmpty(List<String> files) {
        List<String> ranks = Rank.getEmptyRank();

        return files.stream()
                .flatMap(file -> ranks.stream()
                        .map(rank -> Position.from(file + rank))
                ).collect(Collectors.toMap(position -> position, position -> new Empty()));
    }
}
