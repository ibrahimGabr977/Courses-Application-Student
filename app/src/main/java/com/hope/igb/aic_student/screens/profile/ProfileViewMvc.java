package com.hope.igb.aic_student.screens.profile;

import android.annotation.SuppressLint;
import android.text.InputType;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.hope.igb.aic_student.databinding.NavDrawerFragmentProfileBinding;
import com.hope.igb.aic_student.networking.models.Student;
import com.hope.igb.aic_student.common.Constants;
import com.hope.igb.aic_student.common.views.BaseObservableMvc;

import java.util.HashMap;


@SuppressLint("ClickableViewAccessibility")
class ProfileViewMvc extends BaseObservableMvc<ProfileViewMvc.ProfileListener> implements
        View.OnTouchListener, View.OnFocusChangeListener {


   protected interface ProfileListener {
        void onBackClicked();
        void onSaveChangedClicked(HashMap<String, String> changedDataList);
    }


    private final NavDrawerFragmentProfileBinding binding;
    private final HashMap<String, String> changedDataList;
    private final Student currentStudentObject;





    protected ProfileViewMvc(LayoutInflater inflater, Student currentStudentObject) {
        this.currentStudentObject = currentStudentObject;
        changedDataList = new HashMap<>();

        binding = NavDrawerFragmentProfileBinding.inflate(inflater);
        setRootView(binding.getRoot());

//        initViews();






    }





    private void initViews(){
//        initViewsData();
        initClickableViews();
//        initTouchableEditors();

    }





    private void initViewsData(){
        binding.editName.setText(currentStudentObject.getName());
        binding.editAcademicYear.setText(currentStudentObject.getAcademic_year());
        binding.editCategory.setText(currentStudentObject.getCategory());
        binding.editBio.setText(currentStudentObject.getBio());
        binding.editPhoneNumber.setText(currentStudentObject.getPhone_number());
        binding.email.setText(currentStudentObject.getEmail());
        binding.editPhoneNumber.setText(currentStudentObject.getPhone_number());




        //imageLoader.loadImageInto(currentTeacherObject.getProfilePicturePath(), binding.profileImage);
        //videoLoader.loadVideoInto(currentTeacherObject.getBioVideoPath(), binding.bioVideo);

    }




    private void initClickableViews() {
        binding.profileBack.setOnClickListener( v -> onBackClicked());
        binding.saveChanges.setOnClickListener( v -> onSaveChangesClicked());
    }




    private void initTouchableEditors(){
        binding.editName.setOnTouchListener(this);
        binding.editCategory.setOnTouchListener(this);
        binding.editAcademicYear.setOnTouchListener(this);
        binding.editBio.setOnTouchListener(this);
        binding.editPassword.setOnTouchListener(this);
        binding.editPhoneNumber.setOnTouchListener(this);

    }



    private void onBackClicked() {
        for (ProfileListener listener : getListeners())
            listener.onBackClicked();
    }




    private void onSaveChangesClicked(){

        if (changedDataList.size() != 0)
            for (ProfileListener listener : getListeners())
                listener.onSaveChangedClicked(changedDataList);



            unregisterFocusedEditors();
            backEditorsToDisabled();
            updateViewsData();

        binding.saveChanges.setVisibility(View.GONE);
    }



    private void updateViewsData() {
        binding.editName.setText((changedDataList.containsKey("name") ? changedDataList.get("name")
                : currentStudentObject.getName()));


        binding.editBio.setText(changedDataList.containsKey("bio") ? changedDataList.get("bio")
                : currentStudentObject.getBio());


        binding.editPhoneNumber.setText((changedDataList.containsKey("phone_number") ? changedDataList.get("phone_number")
                : currentStudentObject.getPhone_number()));


        binding.editAcademicYear.setText((changedDataList.containsKey("academic_year") ? changedDataList.get("academic_year")
                : currentStudentObject.getAcademic_year()));


        binding.editCategory.setText((changedDataList.containsKey("category") ? changedDataList.get("category")
                : currentStudentObject.getCategory()));
    }




    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if(motionEvent.getAction() == MotionEvent.ACTION_UP) {

                if(motionEvent.getRawX() >= view.getRight() - ((EditText)view).getTotalPaddingRight()) {

                    binding.saveChanges.setVisibility(View.VISIBLE);

                    view.setEnabled(true);

                    currentEditingViewAction(view.getId());


                return true;
            }
        }

        return true;
    }






    private void currentEditingViewAction(int view_id) {

        if (view_id == binding.editName.getId())
            onEditNameClicked();

        else if (view_id == binding.editAcademicYear.getId())
            onEditAcademicYearClicked();

        else if (view_id == binding.editCategory.getId())
            onEditCategoryClicked();

        else if (view_id == binding.editBio.getId())
            onEditBioClicked();

        else if (view_id == binding.editPassword.getId())
            onEditPasswordClicked();

        else if (view_id == binding.editPhoneNumber.getId())
            onEditPhoneNumberClicked();



    }



    private void onEditNameClicked() {

        binding.editName.setOnFocusChangeListener(this);

    }


    private void onEditAcademicYearClicked() {
        binding.editAcademicYear.setOnFocusChangeListener(this);
    }

    private void onEditCategoryClicked() {
        binding.editCategory.setOnFocusChangeListener(this);

    }

    private void onEditPasswordClicked() {
        binding.editPassword.setInputType(InputType.TYPE_CLASS_TEXT);
        binding.editPassword.setOnFocusChangeListener(this);

    }


    private void onEditPhoneNumberClicked() {
        binding.editPhoneNumber.setOnFocusChangeListener(this);

    }


    private void onEditBioClicked() {
        binding.editBio.setOnFocusChangeListener(this);

    }




    @Override
    public void onFocusChange(View view, boolean hasFocused) {
        if (!hasFocused){

            if (view.getId() == binding.editName.getId())
                onNameEdited(binding.editName.getText().toString());

            else if (view.getId() == binding.editAcademicYear.getId())
                onAcademicYearEdited(binding.editAcademicYear.getText().toString());


            else if (view.getId() == binding.editCategory.getId())
                onCategoryEdited(binding.editCategory.getText().toString());


            else if (view.getId() == binding.editPassword.getId())
                onPasswordEdited(binding.editPassword.getText().toString());

            else if (view.getId() == binding.editPhoneNumber.getId())
                onPhoneNumberEdited(binding.editPhoneNumber.getText().toString());

            else if (view.getId() == binding.editBio.getId())
                onBioEdited(binding.editBio.getText().toString());
        }


    }






    private void onNameEdited(String new_name) {

        changedDataList.remove("name");

        if (!currentStudentObject.getName().equals(new_name) &&
                new_name.trim().length() >= Constants.MIN_NAME_LENGTH)

            changedDataList.put("name", new_name);


    }




    private void onAcademicYearEdited(String new_academic_year) {
        changedDataList.remove("academic_year");

        if (!currentStudentObject.getAcademic_year().equals(new_academic_year))
            changedDataList.put("academic_year", new_academic_year);
    }


    private void onCategoryEdited(String new_category) {
        changedDataList.remove("category");

        if (!currentStudentObject.getCategory().equals(new_category))
            changedDataList.put("category", new_category);
    }




    private void onPasswordEdited(String new_password) {
        changedDataList.remove("password");

        if (!currentStudentObject.getPassword().equals(new_password) &&
                new_password.trim().length() >= Constants.MIN_PASSWORD_LENGTH)

            changedDataList.put("password", new_password);
    }




    private void onPhoneNumberEdited(String new_phone_number) {
        changedDataList.remove("phone_number");

        if (!currentStudentObject.getPhone_number().equals(new_phone_number) &&
                isPhoneNumberValid(new_phone_number))

            changedDataList.put("phone_number", new_phone_number);

    }



    private boolean isPhoneNumberValid(String phone_number){
        return Patterns.PHONE.matcher(phone_number).matches();
    }




    private void onBioEdited(String new_bio) {
        changedDataList.remove("bio");

        if (!currentStudentObject.getBio().equals(new_bio) &&
                new_bio.trim().length() <= Constants.MAX_BIO_LENGTH)

            changedDataList.put("bio", new_bio);
    }



    private void unregisterFocusedEditors() {
        binding.editName.setOnFocusChangeListener(null);
        binding.editAcademicYear.setOnFocusChangeListener(null);
        binding.editCategory.setOnFocusChangeListener(null);
        binding.editPassword.setOnFocusChangeListener(null);
        binding.editPhoneNumber.setOnFocusChangeListener(null);
        binding.editBio.setOnFocusChangeListener(null);
    }




    private void backEditorsToDisabled() {
        binding.editName.setEnabled(false);
        binding.editAcademicYear.setEnabled(false);
        binding.editCategory.setEnabled(false);

        binding.editPassword.setEnabled(false);
        binding.editPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        binding.editPhoneNumber.setEnabled(false);
        binding.editBio.setEnabled(false);
    }



}
