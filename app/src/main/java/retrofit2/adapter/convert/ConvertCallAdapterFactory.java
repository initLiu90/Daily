package retrofit2.adapter.convert;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.Observable;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class ConvertCallAdapterFactory extends CallAdapter.Factory {

    public static ConvertCallAdapterFactory create() {
        return new ConvertCallAdapterFactory();
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        Class<?> rawType = getRawType(returnType);
        if (rawType == ConvertObservable.class) {
            Type observableType = getParameterUpperBound(0, (ParameterizedType) returnType);
            Class<?> rawObservableType = getRawType(observableType);

            TmpParameterizedType type = new TmpParameterizedType(Observable.class, rawObservableType);

            CallAdapter tmp = RxJava2CallAdapterFactory.create().get(type, annotations, retrofit);
            return new ConvertCallAdapter<>(tmp);
        }
        return null;
    }

    private static class TmpParameterizedType implements ParameterizedType {
        private Type rawType;
        private Type actualType;

        public TmpParameterizedType(Type rawType, Type actualType) {
            this.rawType = rawType;
            this.actualType = actualType;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{actualType};
        }

        @Override
        public Type getRawType() {
            return rawType;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}
