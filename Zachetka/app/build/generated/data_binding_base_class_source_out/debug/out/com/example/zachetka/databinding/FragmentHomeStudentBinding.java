// Generated by view binder compiler. Do not edit!
package com.example.zachetka.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.zachetka.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHomeStudentBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView disciplineS;

  @NonNull
  public final TextView gradeS;

  @NonNull
  public final ImageView ivbRecS;

  @NonNull
  public final LinearLayout linearRatingsS;

  @NonNull
  public final TextView nameLateS;

  @NonNull
  public final TextView nameRecBookS;

  @NonNull
  public final TextView quantityLateS;

  @NonNull
  public final TableLayout ratingsS;

  @NonNull
  public final RelativeLayout relativeS;

  @NonNull
  public final ScrollView scrollViewS;

  @NonNull
  public final TextView titleGradesS;

  private FragmentHomeStudentBinding(@NonNull RelativeLayout rootView,
      @NonNull TextView disciplineS, @NonNull TextView gradeS, @NonNull ImageView ivbRecS,
      @NonNull LinearLayout linearRatingsS, @NonNull TextView nameLateS,
      @NonNull TextView nameRecBookS, @NonNull TextView quantityLateS,
      @NonNull TableLayout ratingsS, @NonNull RelativeLayout relativeS,
      @NonNull ScrollView scrollViewS, @NonNull TextView titleGradesS) {
    this.rootView = rootView;
    this.disciplineS = disciplineS;
    this.gradeS = gradeS;
    this.ivbRecS = ivbRecS;
    this.linearRatingsS = linearRatingsS;
    this.nameLateS = nameLateS;
    this.nameRecBookS = nameRecBookS;
    this.quantityLateS = quantityLateS;
    this.ratingsS = ratingsS;
    this.relativeS = relativeS;
    this.scrollViewS = scrollViewS;
    this.titleGradesS = titleGradesS;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHomeStudentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHomeStudentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home_student, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHomeStudentBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.disciplineS;
      TextView disciplineS = ViewBindings.findChildViewById(rootView, id);
      if (disciplineS == null) {
        break missingId;
      }

      id = R.id.gradeS;
      TextView gradeS = ViewBindings.findChildViewById(rootView, id);
      if (gradeS == null) {
        break missingId;
      }

      id = R.id.ivb_recS;
      ImageView ivbRecS = ViewBindings.findChildViewById(rootView, id);
      if (ivbRecS == null) {
        break missingId;
      }

      id = R.id.linearRatingsS;
      LinearLayout linearRatingsS = ViewBindings.findChildViewById(rootView, id);
      if (linearRatingsS == null) {
        break missingId;
      }

      id = R.id.nameLateS;
      TextView nameLateS = ViewBindings.findChildViewById(rootView, id);
      if (nameLateS == null) {
        break missingId;
      }

      id = R.id.nameRecBookS;
      TextView nameRecBookS = ViewBindings.findChildViewById(rootView, id);
      if (nameRecBookS == null) {
        break missingId;
      }

      id = R.id.quantityLateS;
      TextView quantityLateS = ViewBindings.findChildViewById(rootView, id);
      if (quantityLateS == null) {
        break missingId;
      }

      id = R.id.ratingsS;
      TableLayout ratingsS = ViewBindings.findChildViewById(rootView, id);
      if (ratingsS == null) {
        break missingId;
      }

      id = R.id.relativeS;
      RelativeLayout relativeS = ViewBindings.findChildViewById(rootView, id);
      if (relativeS == null) {
        break missingId;
      }

      id = R.id.scrollViewS;
      ScrollView scrollViewS = ViewBindings.findChildViewById(rootView, id);
      if (scrollViewS == null) {
        break missingId;
      }

      id = R.id.titleGradesS;
      TextView titleGradesS = ViewBindings.findChildViewById(rootView, id);
      if (titleGradesS == null) {
        break missingId;
      }

      return new FragmentHomeStudentBinding((RelativeLayout) rootView, disciplineS, gradeS, ivbRecS,
          linearRatingsS, nameLateS, nameRecBookS, quantityLateS, ratingsS, relativeS, scrollViewS,
          titleGradesS);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
