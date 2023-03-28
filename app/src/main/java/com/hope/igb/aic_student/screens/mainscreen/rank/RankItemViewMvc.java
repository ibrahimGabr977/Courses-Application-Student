package com.hope.igb.aic_student.screens.mainscreen.rank;

import android.view.View;

import com.hope.igb.aic_student.R;
import com.hope.igb.aic_student.databinding.MainRankHolderBinding;
import com.hope.igb.aic_student.networking.models.RankedStudent;
import com.hope.igb.aic_student.networking.models.WinnerStudent;
import com.hope.igb.aic_student.common.base.BaseObservable;


 class RankItemViewMvc extends
         BaseObservable<RankItemViewMvc.RankItemListener> {



     protected interface RankItemListener {
         void onRankItemClicked(String student_id);
    }

    private final MainRankHolderBinding view_binding;


    protected RankItemViewMvc(MainRankHolderBinding view_binding) {

         this.view_binding = view_binding;


     }


     protected void bindViewHolder(RankedStudent student, int position){

         bindTitleView(position);

         view_binding.studentName.setText(student.getName());
         view_binding.studentCategory.setText(student.getCategory());
         view_binding.studentImage.setImageResource(Integer.parseInt(student.getImage_url()));


         if (position < 10)
             bindTournamentWinners(((WinnerStudent)student).getPlace(), ((WinnerStudent)student).getPrize());
         else
             bindMonthTopRankStudents(student.getRank(), student.getStudying_points());


         view_binding.clickableView.setOnClickListener(v -> onItemViewClicked(student.getName()));




     }





     private void bindTournamentWinners(int place, int prize) {
         view_binding.itemValue.setText(prize+"ج");
         view_binding.itemValue.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.c_main_rank_prized,
                 0 ,0 ,0);


         view_binding.rankText.setText(null);

         if (place == 1)
             view_binding.rankText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0 ,
                     R.drawable.c_main_rank_first_winnerd,0);

         else if (place == 2)
             view_binding.rankText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0 ,
                     R.drawable.c_main_rank_second_winnerd,0);

         else
             view_binding.rankText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0 ,
                     R.drawable.c_main_rank_third_winnerd,0);
     }



     private void bindMonthTopRankStudents(int place, int points) {
         view_binding.itemValue.setText(""+points);
         view_binding.itemValue.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.c_main_rank_hoursd,0,0,0);

         view_binding.rankText.setText(""+place);

         view_binding.rankText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0 ,
                 R.drawable.c_main_rank_icon,0);

     }




     private void bindTitleView(int position){
         if (position == 0){
             view_binding.itemsTitle.setText(getString(R.string.winners_title));
             view_binding.itemsTitle.setVisibility(View.VISIBLE);


         } else if (position == 10) {
             view_binding.itemsTitle.setText(getString(R.string.ranks_title));
             view_binding.itemsTitle.setVisibility(View.VISIBLE);
         }
         else
             view_binding.itemsTitle.setVisibility(View.GONE);

     }

     private void onItemViewClicked(String student_id){
         for (RankItemListener listener : getListeners())
             listener.onRankItemClicked(student_id);
     }



     private String getString(int string_id){
         return view_binding.getRoot().getContext().getResources().getString(string_id);
     }



}
