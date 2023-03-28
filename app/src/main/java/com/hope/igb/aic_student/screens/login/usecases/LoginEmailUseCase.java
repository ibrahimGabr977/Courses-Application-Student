package com.hope.igb.aic_student.screens.login.usecases;


import androidx.annotation.NonNull;
import com.hope.igb.aic_student.common.base.BaseObservable;
import com.hope.igb.aic_student.networking.models.Student;
import com.hope.igb.aic_student.networking.myapi.MyApiCalls;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginEmailUseCase extends BaseObservable<LoginEmailUseCase.Listener> {


    public interface Listener {
        void onSuccessfullyRespond();
        void onErrorRespond();
        void onFailure();
    }

    private final MyApiCalls apiCalls;

    public LoginEmailUseCase(MyApiCalls apiCalls) {
        this.apiCalls = apiCalls;
    }




    public void createAccount(Student student){
        apiCalls.createAccount(student)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                        for (Listener listener: getListeners()) {
                            if (response.code() != 200) {
                                listener.onErrorRespond();
                                return;
                            }


                            listener.onSuccessfullyRespond();
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {

                        for (Listener listener: getListeners())
                            listener.onFailure();

                    }
                });
    }


}
