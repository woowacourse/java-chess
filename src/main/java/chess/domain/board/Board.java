package chess.domain.board;

import chess.domain.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Board {
    private static final String EMPTY_POSITION_ACRONYM = ".";

    private Map<Position, Piece> board;

    Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public List<List<String>> getBoard() {
        List<List<String>> resultBoard = new ArrayList<>();

        for (Rank rank : Rank.values()) {
            resultBoard.add(new ArrayList<>());
            addAcronymToRow(resultBoard, rank);
        }

        return Collections.unmodifiableList(resultBoard);
    }

    private void addAcronymToRow(List<List<String>> result, Rank rank) {
        for (File file : File.values()) {
            result.get(Rank.size() - rank.getRowNumber())
                    .add(acronym(file, rank));
        }
    }

    private String acronym(File file, Rank rank) {
        try {
            return board.get(Position.of(file, rank))
                    .getAcronym();
        } catch (NullPointerException e) {
            return EMPTY_POSITION_ACRONYM;
        }
    }
}
