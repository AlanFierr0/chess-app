package common.logic;

import common.models.*;
import common.moves.Move;
import common.results.CheckResult;
import common.results.MoveResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PieceMover {
    public MoveResult<Board, Boolean> check(Board board, Coordinate initial, Coordinate toSquare, List<Move> movements, Piece pieceMoving, Piece pieceEaten) {
        for (Move move : movements) {
            CheckResult<Coordinate, Boolean> checkResult = move.checkMove(initial, toSquare, board, pieceMoving.getColor());
            if (checkResult.outputResult()) {
                Optional<Coordinate> coordinateEaten = board.getSquareOfPiece(pieceEaten).successfulResult();
                Board b = board.positionPiece(board.getPieceBuilder().createNullPiece(coordinateEaten.get()), coordinateEaten.get());
                Board newBoard = b.positionPiece(pieceMoving, checkResult.successfulResult());
                Board newBoard2 = newBoard.positionPiece(board.getPieceBuilder().createNullPiece(initial), initial);
                if (pawnReachedEnd(pieceMoving, checkResult.successfulResult(), board)) {
                    newBoard2 = newBoard2.positionPiece(board.getPieceBuilder().promotePawn(toSquare, pieceMoving.getColor(), pieceMoving.getId()), checkResult.successfulResult());
                }
                List<MovementHistory> newMovement = new ArrayList<>(board.getMovements());
                MovementHistory movement = new MovementHistory(initial, checkResult.successfulResult(), pieceMoving);
                newMovement.add(movement);

                Board boa = new Board(board.getRows(), board.getColumns(), newBoard2.getPieces(), newBoard2.getSquares(), newMovement, board.getPieceBuilder());
                return new MoveResult<>(boa, false, "Piece Moved");
            }
        }
        return new MoveResult<>(board, true, "Piece not moved");
    }

    private boolean pawnReachedEnd(Piece pieceMoving, Coordinate coordinate, Board board) {
        if (pieceMoving.getName().equals("pawn")) {
            return coordinate.row() == 0 || coordinate.row() == board.getRows();
        }
        return false;
    }
}
