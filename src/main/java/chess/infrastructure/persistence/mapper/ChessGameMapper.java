package chess.infrastructure.persistence.mapper;

import chess.domain.board.ChessBoard;
import chess.domain.board.Turn;
import chess.domain.game.ChessGame;
import chess.domain.game.state.ChessGameState;
import chess.domain.game.state.EndGame;
import chess.domain.game.state.MovePiece;
import chess.domain.piece.Color;
import chess.infrastructure.persistence.entity.ChessGameEntity;
import chess.infrastructure.persistence.entity.PieceEntity;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ChessGameMapper {

    public static ChessGameEntity fromDomain(final ChessGame chessGame) {
        final Long id = chessGame.id();
        final ChessGameState state = chessGame.state();
        if (state.playable()) {
            return new ChessGameEntity(id, state.turn().color().name(), null);
        }
        return new ChessGameEntity(id, null, state.winColor().name());
    }

    public static ChessGame toDomain(final ChessGameEntity chessGame, final List<PieceEntity> pieces) {
        final ChessBoard board = ChessBoard.from(pieces
                .stream()
                .map(PieceMapper::toDomain)
                .collect(toList()));
        return new ChessGame(chessGame.id(), board, parseState(chessGame));
    }

    private static ChessGameState parseState(final ChessGameEntity chessGame) {
        if (chessGame.winner() == null) {
            return new MovePiece(new Turn(Color.valueOf(chessGame.turn())));
        }
        return new EndGame(Color.valueOf(chessGame.winner()));
    }
}
