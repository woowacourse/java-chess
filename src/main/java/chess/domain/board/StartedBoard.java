package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.piece.position.MovingFlow;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;
import chess.domain.ui.UserInterface;

import java.util.Map;
import java.util.stream.Collectors;

public abstract class StartedBoard implements Board {
    protected final Map<Position, Piece> pieces;
    private final UserInterface userInterface;

    StartedBoard(Map<Position, Piece> pieces, UserInterface userInterface) {
        this.pieces = pieces;
        this.userInterface = userInterface;
    }

    @Override
    public Board movePiece() {
        MovingFlow movingFlow = userInterface.inputMovingFlow();
        return MoveExceptionHandler.handle(this::movePiece, movingFlow, userInterface, this);
    }

    @Override
    public Piece getPiece(Position position) {
        return pieces.get(position);
    }

    @Override
    public Map<Position, Piece> getPieces() {
        return pieces;
    }

    private Board movePiece(Position from, Position to, Board board) {
        Map<Position, Piece> pieces = clonePieces(this.pieces);
        Piece piece = board.getPiece(from);
        piece = piece.move(to, board);
        pieces.put(from, Blank.of());
        pieces.put(to, piece);
        if (piece.attackedKing()) {
            return new FinishedBoard(pieces, userInterface);
        }

        return new RunningBoard(pieces, userInterface);
    }


    protected Score calculateScore(Team team) {
        double sum = pieces.values()
                .stream()
                .filter(Piece::isNotBlank)
                .filter(piece -> piece.isSameTeam(team))
                .map(piece -> piece.calculateScore(this))
                .mapToDouble(Score::getValue)
                .sum();
        return new Score(sum);
    }

    private Map<Position, Piece> clonePieces(Map<Position, Piece> board) {
        return board.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue));
    }
}
