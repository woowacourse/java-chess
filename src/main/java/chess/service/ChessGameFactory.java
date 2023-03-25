package chess.service;

import chess.dao.dto.ChessGameDto;
import chess.dao.dto.PieceDto;
import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.game.ChessGame;
import chess.domain.game.state.GameState;
import chess.domain.game.state.StartState;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGameFactory {

    public static ChessGame create(final ChessGameDto chessGameDto, final List<PieceDto> pieceDtos) {
        final Board board = createBoard(pieceDtos);
        final GameState gameState = createGameState(chessGameDto);
        return createChessGame(chessGameDto, board, gameState);
    }

    private static ChessGame createChessGame(
            final ChessGameDto chessGameDto,
            final Board board,
            final GameState gameState
    ) {
        final ChessGame chessGame = new ChessGame(board, gameState);
        chessGame.setId(chessGameDto.getId());
        return chessGame;
    }

    private static GameState createGameState(final ChessGameDto chessGameDto) {
        final Color turn = Color.valueOf(chessGameDto.getTurn());
        return new GameState(turn, new StartState());
    }

    private static Board createBoard(final List<PieceDto> pieceDtos) {
        final Map<Square, Piece> table = new HashMap<>();
        for (final PieceDto pieceDto : pieceDtos) {
            addPiece(table, pieceDto);
        }
        return new Board(table);
    }

    private static void addPiece(final Map<Square, Piece> table, final PieceDto pieceDto) {
        final File file = File.valueOf(pieceDto.getFile());
        final Rank rank = Rank.valueOf(pieceDto.getRank());
        final Color color = Color.valueOf(pieceDto.getColor());
        final PieceType pieceType = PieceType.valueOf(pieceDto.getType());

        table.put(new Square(file, rank), new Piece(color, pieceType));
    }
}
