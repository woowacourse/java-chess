package chess.domain.board.score;

import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardScore {

    private final List<ColumnPiece> columnPieces;

    private BoardScore(final List<ColumnPiece> columnPieces) {
        this.columnPieces = columnPieces;
    }

    public static BoardScore flatByColumnFrom(Map<Position, Piece> chessBoard) {

        final Map<Column, List<Piece>> collect = mapToPiecesByColumnFrom(chessBoard);

        return new BoardScore(collect.entrySet()
                                     .stream()
                                     .map(it -> new ColumnPiece(it.getValue()))
                                     .collect(Collectors.toList()));
    }

    private static Map<Column, List<Piece>> mapToPiecesByColumnFrom(final Map<Position, Piece> chessBoard) {
        return chessBoard.entrySet()
                         .stream()
                         .collect(Collectors.groupingBy(
                                 it -> it.getKey().column(),
                                 Collectors.mapping((Map.Entry::getValue),
                                                    Collectors.toList())
                         ));
    }

    public Score calculateBoardScoreBy(final Color color) {

        return columnPieces.stream()
                           .map(it -> it.calculatePiecesScore(color))
                           .reduce(Score.ZERO, Score::plus);
    }
}
