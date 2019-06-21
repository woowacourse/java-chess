package chess.domain.board;

import chess.domain.direction.core.Square;
import chess.domain.piece.core.Piece;
import chess.domain.piece.core.Team;
import chess.domain.piece.core.Type;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static chess.domain.piece.core.Type.*;

public enum InitBoard {
    GENERAL(Arrays.asList(ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK)),
    SOLDIER(Arrays.asList(PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN));

    private List<Type> typeList;

    InitBoard(List<Type> typeList) {
        this.typeList = typeList;
    }

    int size() {
        return typeList.size();
    }

    Type get(int index) {
        return typeList.get(index);
    }


    Map<Square, Piece> genertate(int rank, Team team) {
        return IntStream.range(0, typeList.size())
                .boxed()
                .collect(Collectors.toMap(index -> Square.of(index, rank), index -> typeList.get(index).create(team)));

    }
}
