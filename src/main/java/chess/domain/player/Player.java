package chess.domain.player;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.State;
import chess.domain.position.Position;
import chess.domain.generator.Generator;
import chess.domain.piece.Piece;
import chess.dto.PieceDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Player {

    private final List<Piece> pieces;
    private final Team team;

    public Player(List<Piece> pieces, Team team) {
        this.pieces = new ArrayList<>(pieces);
        this.team = team;
    }

    public Player(Generator generator, Team team) {
        this(generator.generate(), team);
    }

    public static Player of(final List<PieceDto> piecesDto, final Team team) {
        final List<Piece> pieces = toPieces(piecesDto);
        return new Player(pieces, team);
    }

    private static List<Piece> toPieces(final List<PieceDto> piecesDto) {
        List<Piece> pieces = new ArrayList<>();
        for (PieceDto pieceDto : piecesDto) {
            final State state = State.from(pieceDto.getName());
            final Position position = new Position(pieceDto.getPosition());
            pieces.add(createPieceByState(state, position));
        }
        return pieces;
    }

    private static Piece createPieceByState(State state, Position position) {
        if (state == State.BISHOP) {
            return new Bishop(position);
        }
        if (state == State.KING) {
            return new King(position);
        }
        if (state == State.KNIGHT) {
            return new Knight(position);
        }
        if (state == State.PAWN) {
            return new Pawn(position);
        }
        if (state == State.QUEEN) {
            return new Queen(position);
        }
        return new Rook(position);
    }

    public boolean hasPiece(final Position position) {
        return pieces.stream()
                .anyMatch(piece -> piece.exist(position));
    }

    public List<Piece> findAll() {
        return pieces.stream()
                .collect(Collectors.toUnmodifiableList());
    }

    public Position move(final Position current, final Position destination) {
        return findPiece(current)
                .move(current, destination, team);
    }

    public Position capture(final Position current, final Position destination) {
        return findPiece(current)
                .capture(current, destination, team);
    }

    public void remove(final Position position) {
        pieces.remove(findPiece(position));
    }

    private Piece findPiece(final Position position) {
        return pieces.stream()
                .filter(piece -> piece.exist(position))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("체스말이 존재하지 않습니다."));
    }

    public Score calculateScore() {
        return new Score(Collections.unmodifiableList(pieces));
    }

    public boolean hasKing() {
        return pieces.stream()
                .anyMatch(Piece::isKing);
    }

    public String getTeamName() {
        return team.getName();
    }
}
