package chess.converter;

import chess.domain.board.Board;
import chess.domain.board.Square;
import chess.domain.piece.ColoredPieces;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class PiecesConverter {
    public static List<ColoredPieces> createColoredPieces(Board board) {
        Map<Color, List<Piece>> coloredPiecesMap = board.getColoredPieces();
        return coloredPiecesMap.keySet().stream()
                .map(color -> new ColoredPieces(coloredPiecesMap.get(color), color))
                .collect(toList());
    }

    public static List<Square> convertSquares(String pieces) {
        final Map<Position, Piece> positionPieceMap = convertPositionMap(pieces);
        return positionPieceMap.keySet().stream()
                .map(key -> new Square(key, positionPieceMap.get(key)))
                .collect(toList());
    }

    private static Map<Position, Piece> convertPositionMap(String pieces) {
        List<Piece> pieceList = parsePiecesString(pieces);
        ArrayList<Position> positionList = Arrays.stream(Rank.values())
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
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());
    }
}
