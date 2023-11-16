package common.results;

public class CheckResult<T, R> {
    private final T successfulResult;
    private final R errorResult;
    private final String message;

    public CheckResult(T successfulResult, R errorResult,String message) {
        this.successfulResult = successfulResult;
        this.errorResult = errorResult;
        this.message = message;
    }

    public T successfulResult() {
        return successfulResult;
    }


    public Boolean outputResult(){
        return (Boolean) errorResult;
    }
}
