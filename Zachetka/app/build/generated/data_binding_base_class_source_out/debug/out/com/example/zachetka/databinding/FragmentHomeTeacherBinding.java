// Generated by view binder compiler. Do not edit!
package com.example.zachetka.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.zachetka.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHomeTeacherBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TableRow attributesMain;

  @NonNull
  public final TextView disciplineT;

  @NonNull
  public final TextView gradeT;

  @NonNull
  public final ImageView ivbRecT;

  @NonNull
  public final LinearLayout linearRatingsT;

  @NonNull
  public final TextView nameRecBookT;

  @NonNull
  public final TextView noneInfoT;

  @NonNull
  public final TableLayout ratingsT;

  @NonNull
  public final RelativeLayout relativeT;

  @NonNull
  public final ScrollView scrollViewT;

  @NonNull
  public final Spinner spinnerGroupsMain;

  @NonNull
  public final Spinner spinnerStudentsMain;

  @NonNull
  public final TextView textGroupMain;

  @NonNull
  public final TextView textStudentMain;

  @NonNull
  public final TextView titleUsersT;

  private FragmentHomeTeacherBinding(@NonNull RelativeLayout rootView,
      @NonNull TableRow attributesMain, @NonNull TextView disciplineT, @NonNull TextView gradeT,
      @NonNull ImageView ivbRecT, @NonNull LinearLayout linearRatingsT,
      @NonNull TextView nameRecBookT, @NonNull TextView noneInfoT, @NonNull TableLayout ratingsT,
      @NonNull RelativeLayout relativeT, @NonNull ScrollView scrollViewT,
      @NonNull Spinner spinnerGroupsMain, @NonNull Spinner spinnerStudentsMain,
      @NonNull TextView textGroupMain, @NonNull TextView textStudentMain,
      @NonNull TextView titleUsersT) {
    this.rootView = rootView;
    this.attributesMain = attributesMain;
    this.disciplineT = disciplineT;
    this.gradeT = gradeT;
    this.ivbRecT = ivbRecT;
    this.linearRatingsT = linearRatingsT;
    this.nameRecBookT = nameRecBookT;
    this.noneInfoT = noneInfoT;
    this.ratingsT = ratingsT;
    this.relativeT = relativeT;
    this.scrollViewT = scrollViewT;
    this.spinnerGroupsMain = spinnerGroupsMain;
    this.spinnerStudentsMain = spinnerStudentsMain;
    this.textGroupMain = textGroupMain;
    this.textStudentMain = textStudentMain;
    this.titleUsersT = titleUsersT;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHomeTeacherBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHomeTeacherBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home_teacher, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHomeTeacherBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.attributesMain;
      TableRow attributesMain = ViewBindings.findChildViewById(rootView, id);
      if (attributesMain == null) {
        break missingId;
      }

      id = R.id.disciplineT;
      TextView disciplineT = ViewBindings.findChildViewById(rootView, id);
      if (disciplineT == null) {
        break missingId;
      }

      id = R.id.gradeT;
      TextView gradeT = ViewBindings.findChildViewById(rootView, id);
      if (gradeT == null) {
        break missingId;
      }

      id = R.id.ivb_recT;
      ImageView ivbRecT = ViewBindings.findChildViewById(rootView, id);
      if (ivbRecT == null) {
        break missingId;
      }

      id = R.id.linearRatingsT;
      LinearLayout linearRatingsT = ViewBindings.findChildViewById(rootView, id);
      if (linearRatingsT == null) {
        break missingId;
      }

      id = R.id.nameRecBookT;
      TextView nameRecBookT = ViewBindings.findChildViewById(rootView, id);
      if (nameRecBookT == null) {
        break missingId;
      }

      id = R.id.noneInfoT;
      TextView noneInfoT = ViewBindings.findChildViewById(rootView, id);
      if (noneInfoT == null) {
        break missingId;
      }

      id = R.id.ratingsT;
      TableLayout ratingsT = ViewBindings.findChildViewById(rootView, id);
      if (ratingsT == null) {
        break missingId;
      }

      id = R.id.relativeT;
      RelativeLayout relativeT = ViewBindings.findChildViewById(rootView, id);
      if (relativeT == null) {
        break missingId;
      }

      id = R.id.scrollViewT;
      ScrollView scrollViewT = ViewBindings.findChildViewById(rootView, id);
      if (scrollViewT == null) {
        break missingId;
      }

      id = R.id.spinnerGroupsMain;
      Spinner spinnerGroupsMain = ViewBindings.findChildViewById(rootView, id);
      if (spinnerGroupsMain == null) {
        break missingId;
      }

      id = R.id.spinnerStudentsMain;
      Spinner spinnerStudentsMain = ViewBindings.findChildViewById(rootView, id);
      if (spinnerStudentsMain == null) {
        break missingId;
      }

      id = R.id.textGroupMain;
      TextView textGroupMain = ViewBindings.findChildViewById(rootView, id);
      if (textGroupMain == null) {
        break missingId;
      }

      id = R.id.textStudentMain;
      TextView textStudentMain = ViewBindings.findChildViewById(rootView, id);
      if (textStudentMain == null) {
        break missingId;
      }

      id = R.id.titleUsersT;
      TextView titleUsersT = ViewBindings.findChildViewById(rootView, id);
      if (titleUsersT == null) {
        break missingId;
      }

      return new FragmentHomeTeacherBinding((RelativeLayout) rootView, attributesMain, disciplineT,
          gradeT, ivbRecT, linearRatingsT, nameRecBookT, noneInfoT, ratingsT, relativeT,
          scrollViewT, spinnerGroupsMain, spinnerStudentsMain, textGroupMain, textStudentMain,
          titleUsersT);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
