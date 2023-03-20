package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.File;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.position.Rank;
import chess.domain.piece.strategy.BishopMovement;
import chess.domain.piece.strategy.KingMovement;
import chess.domain.piece.strategy.KnightMovement;
import chess.domain.piece.strategy.QueenMovement;
import chess.domain.piece.strategy.RookMovement;
import chess.domain.piece.strategy.pawn.BlackPawnMoveConstraint;
import chess.domain.piece.strategy.pawn.PawnMovement;
import chess.domain.piece.strategy.pawn.VerticalTwoMoveAsRankConstraint;
import chess.domain.piece.strategy.pawn.WhitePawnMoveConstraint;

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
                .mapToObj(file -> new Piece(color, PiecePosition.of(rank, (char) file), byColor(color, rank)))
                .collect(Collectors.toList());
    }

    private PawnMovement byColor(final Color color, final int rank) {
        if (color.isWhite()) {
            return new PawnMovement(new WhitePawnMoveConstraint(), new VerticalTwoMoveAsRankConstraint(Rank.from(rank)));
        }

        return new PawnMovement(new BlackPawnMoveConstraint(), new VerticalTwoMoveAsRankConstraint(Rank.from(rank)));
    }

    private List<Piece> createExcludePawn(final int rank, final Color color) {
        List<Piece> pieces = new ArrayList<>(8);
        pieces.add(new Piece(color, PiecePosition.of(rank, 'a'), new RookMovement()));
        pieces.add(new Piece(color, PiecePosition.of(rank, 'b'), new KnightMovement()));
        pieces.add(new Piece(color, PiecePosition.of(rank, 'c'), new BishopMovement()));
        pieces.add(new Piece(color, PiecePosition.of(rank, 'd'), new QueenMovement()));
        pieces.add(new Piece(color, PiecePosition.of(rank, 'e'), new KingMovement()));
        pieces.add(new Piece(color, PiecePosition.of(rank, 'f'), new BishopMovement()));
        pieces.add(new Piece(color, PiecePosition.of(rank, 'g'), new KnightMovement()));
        pieces.add(new Piece(color, PiecePosition.of(rank, 'h'), new RookMovement()));
        return pieces;
    }
}
