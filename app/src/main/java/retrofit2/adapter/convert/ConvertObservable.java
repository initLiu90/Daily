package retrofit2.adapter.convert;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class ConvertObservable<T> extends Observable<T> {
    private Observable<T> upstream;

    public ConvertObservable(Observable<T> upstream) {
        this.upstream = upstream;
    }


    public final <R> Observable<R> convert(Function<? super T, ? extends R> function) {
        return map(function);
    }

    @Override
    protected void subscribeActual(Observer<? super T> observer) {
        upstream.subscribe(new ConvertObserve(observer));
    }

    private static class ConvertObserve<T> implements Observer<T> {
        private Observer<? super T> observer;

        public ConvertObserve(Observer<? super T> observer) {
            this.observer = observer;
        }

        @Override
        public void onSubscribe(Disposable d) {
            observer.onSubscribe(d);
        }

        @Override
        public void onNext(T t) {
            observer.onNext(t);
        }

        @Override
        public void onError(Throwable e) {
            observer.onError(e);
        }

        @Override
        public void onComplete() {
            observer.onComplete();
        }
    }
}
