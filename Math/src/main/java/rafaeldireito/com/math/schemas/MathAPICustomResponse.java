package rafaeldireito.com.math.schemas;

public class MathAPICustomResponse {

    private String message;
    private boolean success;
    private String errorMessage;

    public MathAPICustomResponse() {
    }

    public MathAPICustomResponse(String message, boolean success, String errorMessage) {
        this.message = message;
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}