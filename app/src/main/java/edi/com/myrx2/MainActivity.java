package edi.com.myrx2;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // 1
        Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Hello, world!");
                        sub.onCompleted();
                    }
                }
        );

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                Log.d("mySubscriber", s);
            }

            @Override
            public void onCompleted() { }

            @Override
            public void onError(Throwable e) { }
        };

        myObservable.subscribe(mySubscriber);

        //2
       /* Observable<String> myObservable =
                Observable.just("Hello, world!");

        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        };

        myObservable.subscribe(onNextAction);*/


        //3
        Observable.just("Hello, world!")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
//                        System.out.println(s);
                    }
                });

        // 4 lambda
        Observable.just("Hello, world!")
                .subscribe(s ->
                        System.out.println(s)
                );

        // transformation, map
        Observable.just("Hello, world!")
                .map(s -> s + " -Dan")
                .map(s -> s.hashCode())
                .map(i -> Integer.toString(i))
                .subscribe(s -> System.out.println(s));




        ///////////////////////////////////////////////////////////////////////////////////////////////////// part2


    }


}
