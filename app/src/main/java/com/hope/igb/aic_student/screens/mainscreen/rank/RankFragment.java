package com.hope.igb.aic_student.screens.mainscreen.rank;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hope.igb.aic_student.R;
import com.hope.igb.aic_student.networking.models.RankedStudent;
import com.hope.igb.aic_student.networking.models.WinnerStudent;

import java.util.ArrayList;


public class RankFragment extends Fragment implements RankRecyclerAdapter.RankAdapterListener {

    private RankRecyclerAdapter adapter;
    private ArrayList<RankedStudent> students_test;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root_view = inflater.inflate(R.layout.common_recycler_adapter, container, false);

        students_test = new ArrayList<>();
        initWinnersTest();
        initTopRankedTest();


        RecyclerView recyclerView = root_view.findViewById(R.id.commonRecyclerView);
        adapter = new RankRecyclerAdapter(inflater, students_test);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        return root_view;
    }






    @SuppressLint("NotifyDataSetChanged")
    private void bindRecyclerView() {

    }



    @Override
    public void onStop() {
        super.onStop();
        bindRecyclerView();
        adapter.registerAdapterListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.unregisterAdapterListener(this);
    }



    private void initWinnersTest() {




        WinnerStudent winner_student1 = new WinnerStudent(
                "", "أحمد فتحي" ,
                "الصف الثالث الثانوي",
                "عام-علمي رياضة",
                "سبحانك لا علم لنا إلا ما علمتنا إنك أنت العليم الحكيم",
                ""+R.drawable.z_test_teacher_image1,
                25, 210, 1, 12000, 730);


        WinnerStudent winner_student2 = new WinnerStudent("","مازن فتحي",
                "الصف الثالث الثانوي",
                "أزهر-علمي",
                "سبحانك لا علم لنا إلا ما علمتنا إنك أنت العليم الحكيم",
                ""+R.drawable.z_test_teacher_image1,
                19, 165, 1, 12000, 700);


        WinnerStudent winner_student3 = new WinnerStudent("","مهند فتحي",
                "الصف الثالث الثانوي",
                "عام-أدبي",
                "سبحانك لا علم لنا إلا ما علمتنا إنك أنت العليم الحكيم",
                ""+R.drawable.z_test_teacher_image1,
                12, 77, 1, 12000, 690);


        WinnerStudent winner_student4 = new WinnerStudent("","إبراهيم حسن",
                "الصف الثالث الثانوي",
                "لغات-علمي علوم",
                "سبحانك لا علم لنا إلا ما علمتنا إنك أنت العليم الحكيم",
                ""+R.drawable.z_test_teacher_image1,
                22, 70, 2, 8000, 650);


        WinnerStudent winner_student5 = new WinnerStudent("","عبدالله محمد",
                "الصف الثالث الثانوي",
                "عام-علمي علوم",
                "سبحانك لا علم لنا إلا ما علمتنا إنك أنت العليم الحكيم",
                ""+R.drawable.z_test_teacher_image1,
                50, 75, 2, 8000, 630);



        WinnerStudent winner_student6 = new WinnerStudent("","حمدي فتحي",
                "الصف الثالث الثانوي",
                "أزهر-أدبي",
                "سبحانك لا علم لنا إلا ما علمتنا إنك أنت العليم الحكيم",
                ""+R.drawable.z_test_teacher_image1,
                43, 95, 2, 8000, 600);

        WinnerStudent winner_student7 = new WinnerStudent("","عمرو إبراهيم",
                "الصف الثالث الثانوي",
                "أزهر-علمي",
                "سبحانك لا علم لنا إلا ما علمتنا إنك أنت العليم الحكيم",
                ""+R.drawable.z_test_teacher_image1,
                33, 83, 3, 5000, 590);


        WinnerStudent winner_student8 = new WinnerStudent("","محمد حسن",
                "الصف الثالث الثانوي",
                "عام-أدبي",
                "سبحانك لا علم لنا إلا ما علمتنا إنك أنت العليم الحكيم",
                ""+R.drawable.z_test_teacher_image1,
                37, 73, 3, 5000, 570);


        WinnerStudent winner_student9 = new WinnerStudent("","حسن عاطف",
                "الصف الثالث الثانوي",
                "لغات-علمي رياضة",
                "سبحانك لا علم لنا إلا ما علمتنا إنك أنت العليم الحكيم",
                ""+R.drawable.z_test_teacher_image1,
                73, 70, 3, 5000, 550);

        WinnerStudent winner_student10 = new WinnerStudent("","حسين نبيل",
                "الصف الثالث الثانوي",
                "عام-علمي علوم",
                "سبحانك لا علم لنا إلا ما علمتنا إنك أنت العليم الحكيم",
                ""+R.drawable.z_test_teacher_image1,
                103, 123, 3, 5000, 530);


        students_test.add(winner_student1);
        students_test.add(winner_student2);
        students_test.add(winner_student3);
        students_test.add(winner_student4);
        students_test.add(winner_student5);
        students_test.add(winner_student6);
        students_test.add(winner_student7);
        students_test.add(winner_student8);
        students_test.add(winner_student9);
        students_test.add(winner_student10);

    }




    private void initTopRankedTest() {



        RankedStudent ranked1_student = new RankedStudent("", "أحمد فتحي",
                "الصف الثالث الثانوي",
                "عام-علمي رياضة",
                "سبحانك لا علم لنا إلا ما علمتنا إنك أنت العليم الحكيم",
                ""+R.drawable.z_test_teacher_image1,
                1,  230);

        RankedStudent ranked2_student = new RankedStudent("", "مازن فتحي",
                "الصف الثالث الثانوي",
                "أزهر-علمي",
                "سبحانك لا علم لنا إلا ما علمتنا إنك أنت العليم الحكيم",
                ""+R.drawable.z_test_teacher_image1,
                2, 221);

        RankedStudent ranked3_student = new RankedStudent("", "مهند فتحي",
                "الصف الثالث الثانوي",

                "عام-أدبي",
                "سبحانك لا علم لنا إلا ما علمتنا إنك أنت العليم الحكيم",
                ""+R.drawable.z_test_teacher_image1,
                3,  215);


        RankedStudent ranked4_student = new RankedStudent("", "إبراهيم حسن",
                "الصف الثالث الثانوي",
                "لغات-علمي علوم",
                "سبحانك لا علم لنا إلا ما علمتنا إنك أنت العليم الحكيم",
                ""+R.drawable.z_test_teacher_image1,
                4,  211);

        RankedStudent ranked5_student = new RankedStudent("", "عبدالله محمد",
                "الصف الثالث الثانوي",
                "عام-علمي علوم",
                "سبحانك لا علم لنا إلا ما علمتنا إنك أنت العليم الحكيم",
                ""+R.drawable.z_test_teacher_image1,
                5, 205);

        RankedStudent ranked6_student = new RankedStudent("", "حمدي فتحي",
                "الصف الثالث الثانوي",
                "أزهر-أدبي",
                "سبحانك لا علم لنا إلا ما علمتنا إنك أنت العليم الحكيم",
                ""+R.drawable.z_test_teacher_image1,
                6,  190);

        RankedStudent ranked7_student = new RankedStudent("", "عمرو إبراهيم",
                "الصف الثالث الثانوي",
                "أزهر-علمي",
                "سبحانك لا علم لنا إلا ما علمتنا إنك أنت العليم الحكيم",
                ""+R.drawable.z_test_teacher_image1,
                7, 170);

        RankedStudent ranked8_student = new RankedStudent("", "محمد حسن",
                "الصف الثالث الثانوي",
                "عام-أدبي",
                "سبحانك لا علم لنا إلا ما علمتنا إنك أنت العليم الحكيم",
                ""+R.drawable.z_test_teacher_image1,
                8,  150);

        RankedStudent ranked9_student = new RankedStudent("", "حسن عاطف",
                "الصف الثالث الثانوي",
                "لغات-علمي رياضة",
                "سبحانك لا علم لنا إلا ما علمتنا إنك أنت العليم الحكيم",
                ""+R.drawable.z_test_teacher_image1,
                9,  145);

        RankedStudent ranked10_student = new RankedStudent("", "حسين نبيل",
                "الصف الثالث الثانوي",
                "عام-علمي علوم",
                "سبحانك لا علم لنا إلا ما علمتنا إنك أنت العليم الحكيم",
                ""+R.drawable.z_test_teacher_image1,
                10,  144);


        students_test.add(ranked1_student);
        students_test.add(ranked2_student);
        students_test.add(ranked3_student);
        students_test.add(ranked4_student);
        students_test.add(ranked5_student);
        students_test.add(ranked6_student);
        students_test.add(ranked7_student);
        students_test.add(ranked8_student);
        students_test.add(ranked9_student);
        students_test.add(ranked10_student);

    }




    @Override
    public void onRankItemClicked(String student_id) {

    }
}
