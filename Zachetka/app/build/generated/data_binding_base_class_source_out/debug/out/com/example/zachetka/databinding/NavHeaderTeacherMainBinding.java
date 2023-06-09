// Generated by view binder compiler. Do not edit!
package com.example.zachetka.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.zachetka.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class NavHeaderTeacherMainBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView emailUser;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final TextView nameUser;

  private NavHeaderTeacherMainBinding(@NonNull LinearLayout rootView, @NonNull TextView emailUser,
      @NonNull ImageView imageView, @NonNull TextView nameUser) {
    this.rootView = rootView;
    this.emailUser = emailUser;
    this.imageView = imageView;
    this.nameUser = nameUser;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static NavHeaderTeacherMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static NavHeaderTeacherMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.nav_header_teacher_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static NavHeaderTeacherMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.emailUser;
      TextView emailUser = ViewBindings.findChildViewById(rootView, id);
      if (emailUser == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.nameUser;
      TextView nameUser = ViewBindings.findChildViewById(rootView, id);
      if (nameUser == null) {
        break missingId;
      }

      return new NavHeaderTeacherMainBinding((LinearLayout) rootView, emailUser, imageView,
          nameUser);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
