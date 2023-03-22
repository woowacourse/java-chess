package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.File;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.position.Rank;
import chess.domain.piece.movestrategy.BishopMovementStrategy;
import chess.domain.piece.movestrategy.KingMovementStrategy;
import chess.domain.piece.movestrategy.KnightMovementStrategy;
import chess.domain.piece.movestrategy.QueenMovementStrategy;
import chess.domain.piece.movestrategy.RookMovementStrategy;
import chess.domain.piece.movestrategy.pawn.BlackPawnMovementStrategy;
import chess.domain.piece.movestrategy.pawn.PawnMovementStrategy;
import chess.domain.piece.movestrategy.pawn.WhitePawnMovementStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChessBoardFactory {

    public ChessBoard create() {
        List<Piece> pieces = new ArrayList<>(32);
        pieces.addAll(createPawns(2, Color.WHITE));
        pieces.addAll(createPawns(7, Color.BLACK));
        pieces.addAll(createExcludePawn(1, Color.WHITE));
        pieces.addAll(createExcludePawn(8, Color.BLACK));
        return ChessBoard.from(pieces);
    }

    private List<Piece> createPawns(final int rank, final Color color) {
        return IntStream.rangeClosed(File.MIN, File.MAX)
                .mapToObj(file -> new Piece(PiecePosition.of(rank, (char) file), byColor(color, rank)))
                .collect(Collectors.toList());
    }

    private PawnMovementStrategy byColor(final Color color, final int rank) {
        if (color.isWhite()) {
            return new WhitePawnMovementStrategy(color, Rank.from(rank));
        }
        return new BlackPawnMovementStrategy(color, Rank.from(rank));
    }

    private List<Piece> createExcludePawn(final int rank, final Color color) {
        List<Piece> pieces = new ArrayList<>(8);
        pieces.add(new Piece(PiecePosition.of(rank, 'a'), new RookMovementStrategy(color)));
        pieces.add(new Piece(PiecePosition.of(rank, 'b'), new KnightMovementStrategy(color)));
        pieces.add(new Piece(PiecePosition.of(rank, 'c'), new BishopMovementStrategy(color)));
        pieces.add(new Piece(PiecePosition.of(rank, 'd'), new QueenMovementStrategy(color)));
        pieces.add(new Piece(PiecePosition.of(rank, 'e'), new KingMovementStrategy(color)));
        pieces.add(new Piece(PiecePosition.of(rank, 'f'), new BishopMovementStrategy(color)));
        pieces.add(new Piece(PiecePosition.of(rank, 'g'), new KnightMovementStrategy(color)));
        pieces.add(new Piece(PiecePosition.of(rank, 'h'), new RookMovementStrategy(color)));
        return pieces;
    }
}
