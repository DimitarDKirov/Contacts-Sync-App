package mitko.data.contracts;

import io.reactivex.Observable;
import mitko.data.models.ServerResponse;

public interface IHttpDataTransfer<T> {
    Observable<T[]> getAll();

    Observable<T> getById(Object id);

    Observable<ServerResponse<T>> add(T[] objects);

    Observable<ServerResponse<T>> post(T object);

    Observable<ServerResponse<T>> update(Object id, T object);

    Observable<ServerResponse<T>> delete(Object id);

    void setUrl(String url);

    void setToken(String token);
}
