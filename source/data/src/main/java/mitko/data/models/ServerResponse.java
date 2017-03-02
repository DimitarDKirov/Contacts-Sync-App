package mitko.data.models;

public class ServerResponse<T> {
    private Boolean success;
    private String message;
    private T object;

    public ServerResponse(Boolean success, String message, T object) {
        this.setSuccess(success);
        this.setMessage(message);
        this.setObject(object);
    }

    public ServerResponse() {
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
