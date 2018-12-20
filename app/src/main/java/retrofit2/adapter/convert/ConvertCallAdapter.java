package retrofit2.adapter.convert;

import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Call;
import retrofit2.CallAdapter;

final class ConvertCallAdapter<R> implements CallAdapter<R, Object> {
    private CallAdapter<R, Object> callAdapter;

    public ConvertCallAdapter(CallAdapter<R, Object> callAdapter) {
        this.callAdapter = callAdapter;
    }

    @Override
    public Type responseType() {
        return callAdapter.responseType();
    }

    @Override
    public Object adapt(Call<R> call) {
        Observable<R> tmp = (Observable<R>) callAdapter.adapt(call);
        ConvertObservable<?> observable = new ConvertObservable<>(tmp);
        return RxJavaPlugins.onAssembly(observable);
    }
}
