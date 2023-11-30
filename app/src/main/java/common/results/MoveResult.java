package common.results;

public record MoveResult<T, R>(T successfulResult, R errorResult, String message) {
}
