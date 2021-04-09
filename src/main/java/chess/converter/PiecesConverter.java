package chess.converter;

import chess.domain.board.Board;
import chess.domain.board.Square;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class PiecesConverter {
    public static String convertString(Board board) {
        return Rank.asListInReverseOrder().stream().flatMap(rank ->
                rankNotation(board, rank)
        ).collect(joining(""));
    }

    private static Stream<String> rankNotation(Board board, Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> Position.of(file, rank))
                .map(board::findByPosition)
                .map(Square::getNotationText);
    }

    public static List<Square> convertSquares(String pieces) {
        final Map<Position, Piece> positionPieceMap = convertPositionMap(pieces);
        return positionPieceMap.keySet().stream()
                .map(key -> new Square(key, positionPieceMap.get(key)))
                .collect(toList());
    }

    private static Map<Position, Piece> convertPositionMap(String pieces) {
        List<Piece> pieceList = parsePiecesString(pieces);
        ArrayList<Position> positionList = Rank.asListInReverseOrder().stream()
                .flatMap(rank ->
                        Arrays.stream(File.values())
                                .map(file -> Position.of(file, rank))
                )
                .collect(collectingAndThen(toList(), ArrayList::new));

        return IntStream.range(0, pieceList.size())
                .boxed()
                .collect(toMap(positionList::get, pieceList::get));
    }

    private static List<Piece> parsePiecesString(String pieces) {
        return Arrays.stream(pieces.split(""))
                .map(PieceConverter::of)
                .collect(toList());
    }
}
