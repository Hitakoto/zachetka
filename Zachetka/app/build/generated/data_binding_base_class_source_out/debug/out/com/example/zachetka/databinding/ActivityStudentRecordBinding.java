// Generated by view binder compiler. Do not edit!
package com.example.zachetka.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public final class ActivityStudentRecordBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final LinearLayout linearRecS;

  @NonNull
  public final TextView nameBookS;

  @NonNull
  public final RelativeLayout relativeRBS;

  @NonNull
  public final ScrollView scrollViewRBS;

  @NonNull
  public final TableLayout tableRecS;

  @NonNull
  public final TextView titleRBS;

  private ActivityStudentRecordBinding(@NonNull RelativeLayout rootView,
      @NonNull LinearLayout linearRecS, @NonNull TextView nameBookS,
      @NonNull RelativeLayout relativeRBS, @NonNull ScrollView scrollViewRBS,
      @NonNull TableLayout tableRecS, @NonNull TextView titleRBS) {
    this.rootView = rootView;
    this.linearRecS = linearRecS;
    this.nameBookS = nameBookS;
    this.relativeRBS = relativeRBS;
    this.scrollViewRBS = scrollViewRBS;
    this.tableRecS = tableRecS;
    this.titleRBS = titleRBS;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityStudentRecordBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityStudentRecordBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_student_record, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityStudentRecordBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.linearRecS;
      LinearLayout linearRecS = ViewBindings.findChildViewById(rootView, id);
      if (linearRecS == null) {
        break missingId;
      }

      id = R.id.nameBookS;
      TextView nameBookS = ViewBindings.findChildViewById(rootView, id);
      if (nameBookS == null) {
        break missingId;
      }

      id = R.id.relativeRBS;
      RelativeLayout relativeRBS = ViewBindings.findChildViewById(rootView, id);
      if (relativeRBS == null) {
        break missingId;
      }

      id = R.id.scrollViewRBS;
      ScrollView scrollViewRBS = ViewBindings.findChildViewById(rootView, id);
      if (scrollViewRBS == null) {
        break missingId;
      }

      id = R.id.tableRecS;
      TableLayout tableRecS = ViewBindings.findChildViewById(rootView, id);
      if (tableRecS == null) {
        break missingId;
      }

      id = R.id.titleRBS;
      TextView titleRBS = ViewBindings.findChildViewById(rootView, id);
      if (titleRBS == null) {
        break missingId;
      }

      return new ActivityStudentRecordBinding((RelativeLayout) rootView, linearRecS, nameBookS,
          relativeRBS, scrollViewRBS, tableRecS, titleRBS);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
