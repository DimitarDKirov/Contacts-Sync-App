package mitko.data;

import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import mitko.data.contracts.IHttpDataTransfer;
import mitko.data.models.ServerResponse;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by dimki on 01.03.2017 г..
 */

public class HttpDataTransfer<T> implements IHttpDataTransfer<T> {
    private String token;
    private final Class<T> classSingle;
    private final Class<T[]> classArray;
    private String url;
    private OkHttpClient client;


    public HttpDataTransfer(String url, Class<T> classSingle, Class<T[]> classArray) {
        this.url = url;
        this.classSingle = classSingle;
        this.classArray = classArray;
        this.token="";
        this.client = new OkHttpClient();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public Observable<T[]> getAll() {
        return Observable.create(new ObservableOnSubscribe<T[]>() {
            @Override
            public void subscribe(ObservableEmitter<T[]> e) throws Exception {
                Request request = buildGetRequest(url);

                Response response = client.newCall(request).execute();
                String json = response.body().string();

                Gson gson = new Gson();
                T[] result = gson.fromJson(json, classArray);
                e.onNext(result);
            }
        });
    }

    @Override
    public Observable<T> getById(final Object id) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                String finalUrl = url + '/' + id;
                Request request = buildGetRequest(finalUrl);

                Response response = client.newCall(request).execute();
                Gson gson = new Gson();
                T result = gson.fromJson(response.body().string(), classSingle);
                e.onNext(result);
            }
        });
    }

    @Override
    public Observable<ServerResponse<T>> add(final T[] objects) {
        return Observable.create(new ObservableOnSubscribe<ServerResponse<T>>() {
            @Override
            public void subscribe(ObservableEmitter<ServerResponse<T>> e) throws Exception {

                Gson gson = new Gson();
                String json = gson.toJson(objects);
                RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);

                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .header(Constants.TOKEN_KEY, token)
                        .build();

                Response response = client.newCall(request).execute();

                ServerResponse<T> serverResponse = parse(response.body().string());

                e.onNext(serverResponse);
            }
        });
    }

    @Override
    public Observable<ServerResponse<T>> post(final T object) {
        return Observable.create(new ObservableOnSubscribe<ServerResponse<T>>() {
            @Override
            public void subscribe(ObservableEmitter<ServerResponse<T>> e) throws Exception {

                Gson gson = new Gson();
                String json = gson.toJson(object);
                RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);

                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .header(Constants.TOKEN_KEY, token)
                        .build();

                Response response = client.newCall(request).execute();

                ServerResponse<T> serverResponse = parse(response.body().string());

                e.onNext(serverResponse);
            }
        });
    }

    @Override
    public Observable<ServerResponse<T>> update(final Object id, final T object) {
        return Observable.create(new ObservableOnSubscribe<ServerResponse<T>>() {
            @Override
            public void subscribe(ObservableEmitter<ServerResponse<T>> e) throws Exception {
                Gson gson = new Gson();
                String json = gson.toJson(object);
                RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
                String finalUrl = url + '/' + id;

                Request request = new Request.Builder()
                        .url(finalUrl)
                        .put(body)
                        .header(Constants.TOKEN_KEY, token)
                        .build();

                Response response = client.newCall(request).execute();

                ServerResponse<T> parsedResponse = parse(response.body().string());
                e.onNext(parsedResponse);
            }
        });
    }

    @Override
    public Observable<ServerResponse<T>> delete(final Object id) {
        return Observable.create(new ObservableOnSubscribe<ServerResponse<T>>() {
            @Override
            public void subscribe(ObservableEmitter<ServerResponse<T>> e) throws Exception {

                String finalUrl = url + '/' + id;

                Request request = new Request.Builder()
                        .url(finalUrl)
                        .delete()
                        .header(Constants.TOKEN_KEY, token)
                        .build();

                Response response = client.newCall(request).execute();

                ServerResponse<T> parsedResponse = parse(response.body().string());
                e.onNext(parsedResponse);
            }
        });
    }

    private Request buildGetRequest(String url) {
        Request request = new Request.Builder()
                .url(url)
                .header(Constants.TOKEN_KEY, this.token)
                .build();

        return request;
    }

    private ServerResponse parse(String response) {
        Gson gson = new Gson();
        ServerResponse<T> result = gson.fromJson(response, ServerResponse.class);
        return result;
    }

    public void setToken(String token) {
        this.token = token;
    }
}