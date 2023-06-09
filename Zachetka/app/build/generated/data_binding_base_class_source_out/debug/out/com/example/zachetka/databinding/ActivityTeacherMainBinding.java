// Generated by view binder compiler. Do not edit!
package com.example.zachetka.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.zachetka.R;
import com.google.android.material.navigation.NavigationView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityTeacherMainBinding implements ViewBinding {
  @NonNull
  private final DrawerLayout rootView;

  @NonNull
  public final AppBarTeacherMainBinding appBarTeacherMain;

  @NonNull
  public final DrawerLayout drawerLayout;

  @NonNull
  public final NavigationView navViewT;

  private ActivityTeacherMainBinding(@NonNull DrawerLayout rootView,
      @NonNull AppBarTeacherMainBinding appBarTeacherMain, @NonNull DrawerLayout drawerLayout,
      @NonNull NavigationView navViewT) {
    this.rootView = rootView;
    this.appBarTeacherMain = appBarTeacherMain;
    this.drawerLayout = drawerLayout;
    this.navViewT = navViewT;
  }

  @Override
  @NonNull
  public DrawerLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityTeacherMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityTeacherMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_teacher_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityTeacherMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.app_bar_teacher_main;
      View appBarTeacherMain = ViewBindings.findChildViewById(rootView, id);
      if (appBarTeacherMain == null) {
        break missingId;
      }
      AppBarTeacherMainBinding binding_appBarTeacherMain = AppBarTeacherMainBinding.bind(appBarTeacherMain);

      DrawerLayout drawerLayout = (DrawerLayout) rootView;

      id = R.id.nav_viewT;
      NavigationView navViewT = ViewBindings.findChildViewById(rootView, id);
      if (navViewT == null) {
        break missingId;
      }

      return new ActivityTeacherMainBinding((DrawerLayout) rootView, binding_appBarTeacherMain,
          drawerLayout, navViewT);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
