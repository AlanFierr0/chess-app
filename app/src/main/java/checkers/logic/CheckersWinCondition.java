package checkers.logic;

import common.models.Board;
import common.models.Coordinate;
import common.models.Piece;
import common.logic.WinCondition;
import common.results.MoveResult;

public class CheckersWinCondition implements WinCondition {
    private final CheckForWinByNoMorePieces CheckForWinByNoMorePieces = new CheckForWinByNoMorePieces();
    private final CheckForWinByNoMoreMoves CheckForWinByNoMoreMoves = new CheckForWinByNoMoreMoves();

    @Override
    public MoveResult<Board, Boolean> checkWin(Board board, Piece piece, MoveResult<Board, Boolean> move, Coordinate toSquare) {
        Board moveBoard = move.successfulResult();
        if (CheckForWinByNoMorePieces.check(moveBoard, piece.getColor())) {
            return new MoveResult<>(moveBoard, true, "CheckMate");
        }
        if (CheckForWinByNoMoreMoves.check(moveBoard)) {
            return new MoveResult<>(moveBoard, true, "CheckMate");
        } else
            return move;
    }
}
