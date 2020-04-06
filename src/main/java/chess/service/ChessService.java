package chess.service;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.MoveInfo;
import chess.domain.position.Path;
import chess.domain.position.Position;

public class ChessService {
	private final Board board;

	private ChessService(Board board) {
		this.board = board;
	}

	public static ChessService of(Board board) {
		return new ChessService(board);
	}

	public void move(MoveInfo moveInfo, Team team) {
		Position from = moveInfo.getFrom();
		Position to = moveInfo.getTo();

		Piece piece = board.get(from);
		validateMove(from, to, team);

		piece.moveTo(to);
		board.update(from, to);
	}

	private void validateMove(Position from, Position to, Team team) {
		Piece piece = board.get(from);
		Piece target = board.get(to);

		if (piece.isNotSameTeam(team)) {
			throw new IllegalArgumentException("아군 기물의 위치가 아닙니다.");
		}
		if (board.hasPieceIn(Path.of(from, to).toList()) || piece.canNotMoveTo(target)) {
			throw new IllegalArgumentException("이동할 수 없는 경로입니다.");
		}
	}
}
