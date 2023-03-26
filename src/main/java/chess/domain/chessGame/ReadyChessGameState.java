package chess.domain.chessGame;

import chess.domain.Board;
import chess.domain.PieceDto;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.move_rule.*;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadyChessGameState implements ChessGameState {

    private static final String GAME_NOT_START_ERROR_MESSAGE = "아직 게임을 시작하지 않았습니다.";

    @Override
    public Map<Position, PieceDto> move(String currentPosition, String nextPosition) {
        throw new IllegalStateException(GAME_NOT_START_ERROR_MESSAGE);
    }

    @Override
    public List<Double> calculateScore() {
        throw new IllegalStateException(GAME_NOT_START_ERROR_MESSAGE);
    }

    @Override
    public ChessGameState start() {
        Board board = new Board(initializedBoard());
        return new PlayingChessGameState(board);
    }

    @Override
    public void end() {
        throw new IllegalStateException(GAME_NOT_START_ERROR_MESSAGE);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Map<Position, PieceDto> getPrintingBoard() {
        return Collections.emptyMap();
    }

    private Map<Position, Piece> initializedBoard() {
        Map<Position, Piece> board = new HashMap<>();
        board = initializePawnLinePieces(board, Rank.RANK2, Color.WHITE);
        board = initializePawnLinePieces(board, Rank.RANK7, Color.BLACK);

        board = initializeEndLinePieces(board, Rank.RANK1, Color.WHITE);
        board = initializeEndLinePieces(board, Rank.RANK8, Color.BLACK);
        return board;
    }

    private Map<Position, Piece> initializePawnLinePieces(Map<Position, Piece> board, Rank rank, Color color) {
        for (File file : File.values()) {
            board.put(Position.of(file, rank), new Piece(PawnMoveRule.getInstance(color), color));
        }
        return board;
    }

    private Map<Position, Piece> initializeEndLinePieces(Map<Position, Piece> board, Rank rank, Color color) {
        board.put(Position.of(File.FILE_A, rank), new Piece(RookMoveRule.getInstance(), color));
        board.put(Position.of(File.FILE_B, rank), new Piece(KnightMoveRule.getInstance(), color));
        board.put(Position.of(File.FILE_C, rank), new Piece(BishopMoveRule.getInstance(), color));
        board.put(Position.of(File.FILE_D, rank), new Piece(QueenMoveRule.getInstance(), color));
        board.put(Position.of(File.FILE_E, rank), new Piece(KingMoveRule.getInstance(), color));
        board.put(Position.of(File.FILE_F, rank), new Piece(BishopMoveRule.getInstance(), color));
        board.put(Position.of(File.FILE_G, rank), new Piece(KnightMoveRule.getInstance(), color));
        board.put(Position.of(File.FILE_H, rank), new Piece(RookMoveRule.getInstance(), color));
        return board;
    }
}
