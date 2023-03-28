package com.hope.igb.aic_student.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import android.os.Bundle;
import android.view.WindowManager;

import com.hope.igb.aic_student.R;



public class BaseActivity extends AppCompatActivity {






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);


//       getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);



    }









    public NavHostFragment getNavHostFragment(){
        return (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainerView);
    }
    @Override
    protected void onStart() {
        super.onStart();


    }


    @Override
    protected void onStop() {
        super.onStop();


    }






//
//    @Override
//    public void onNavItemSelected(int itemId) {
//
//
//
//        if (itemId == R.id.nav_item_profile) {
//            onProfileItemSelected();
//
//        }else if (itemId == R.id.nav_item_tournaments){
//            onTournamentsSelected();
//
//        }else if (itemId == R.id.nav_item_payments){
//            onPaymentsSelected();
//
//        }else if (itemId == R.id.nav_item_complaints){
//            onComplaintsItemSelected();
//
//
//        }else if (itemId == R.id.nav_item_review_us){
//            onReviewUSItemSelected();
//
//        }else if (itemId == R.id.nav_item_help){
//            onHelpItemSelected();
//
//        }else if (itemId == R.id.nav_item_suggestions){
//            onSuggestionsItemSelected();
//
//        }else
//            onSettingsItemSelected();
//
//    }
//
//
//
//
//
//
//
//
//
//
//
//    private void onProfileItemSelected() {
//
//    }
//
//    private void onTournamentsSelected() {
//
//    }
//
//
//    private void onPaymentsSelected() {
//
//    }
//
//    private void onComplaintsItemSelected() {
//
//    }
//
//    private void onReviewUSItemSelected() {
//
//    }
//
//    private void onHelpItemSelected() {
//
//    }
//
//    private void onSuggestionsItemSelected() {
//
//    }
//
//    private void onSettingsItemSelected() {
//
//    }



}