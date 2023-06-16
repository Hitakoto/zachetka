// Generated by view binder compiler. Do not edit!
package com.example.zachetka.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.zachetka.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityEditUserModeBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Button btnEdit;

  @NonNull
  public final TextView editModeText;

  @NonNull
  public final EditText loginE;

  @NonNull
  public final EditText nameE;

  @NonNull
  public final EditText passwordE;

  @NonNull
  public final EditText patronymicE;

  @NonNull
  public final Spinner spinnerEditMode;

  @NonNull
  public final Spinner spinnerGroupUser;

  @NonNull
  public final Spinner spinnerRoleUser;

  @NonNull
  public final Spinner spinnerUser;

  @NonNull
  public final EditText surnameE;

  private ActivityEditUserModeBinding(@NonNull RelativeLayout rootView, @NonNull Button btnEdit,
      @NonNull TextView editModeText, @NonNull EditText loginE, @NonNull EditText nameE,
      @NonNull EditText passwordE, @NonNull EditText patronymicE, @NonNull Spinner spinnerEditMode,
      @NonNull Spinner spinnerGroupUser, @NonNull Spinner spinnerRoleUser,
      @NonNull Spinner spinnerUser, @NonNull EditText surnameE) {
    this.rootView = rootView;
    this.btnEdit = btnEdit;
    this.editModeText = editModeText;
    this.loginE = loginE;
    this.nameE = nameE;
    this.passwordE = passwordE;
    this.patronymicE = patronymicE;
    this.spinnerEditMode = spinnerEditMode;
    this.spinnerGroupUser = spinnerGroupUser;
    this.spinnerRoleUser = spinnerRoleUser;
    this.spinnerUser = spinnerUser;
    this.surnameE = surnameE;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityEditUserModeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityEditUserModeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_edit_user_mode, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityEditUserModeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_edit;
      Button btnEdit = ViewBindings.findChildViewById(rootView, id);
      if (btnEdit == null) {
        break missingId;
      }

      id = R.id.edit_mode_text;
      TextView editModeText = ViewBindings.findChildViewById(rootView, id);
      if (editModeText == null) {
        break missingId;
      }

      id = R.id.loginE;
      EditText loginE = ViewBindings.findChildViewById(rootView, id);
      if (loginE == null) {
        break missingId;
      }

      id = R.id.nameE;
      EditText nameE = ViewBindings.findChildViewById(rootView, id);
      if (nameE == null) {
        break missingId;
      }

      id = R.id.passwordE;
      EditText passwordE = ViewBindings.findChildViewById(rootView, id);
      if (passwordE == null) {
        break missingId;
      }

      id = R.id.patronymicE;
      EditText patronymicE = ViewBindings.findChildViewById(rootView, id);
      if (patronymicE == null) {
        break missingId;
      }

      id = R.id.spinnerEditMode;
      Spinner spinnerEditMode = ViewBindings.findChildViewById(rootView, id);
      if (spinnerEditMode == null) {
        break missingId;
      }

      id = R.id.spinnerGroupUser;
      Spinner spinnerGroupUser = ViewBindings.findChildViewById(rootView, id);
      if (spinnerGroupUser == null) {
        break missingId;
      }

      id = R.id.spinnerRoleUser;
      Spinner spinnerRoleUser = ViewBindings.findChildViewById(rootView, id);
      if (spinnerRoleUser == null) {
        break missingId;
      }

      id = R.id.spinnerUser;
      Spinner spinnerUser = ViewBindings.findChildViewById(rootView, id);
      if (spinnerUser == null) {
        break missingId;
      }

      id = R.id.surnameE;
      EditText surnameE = ViewBindings.findChildViewById(rootView, id);
      if (surnameE == null) {
        break missingId;
      }

      return new ActivityEditUserModeBinding((RelativeLayout) rootView, btnEdit, editModeText,
          loginE, nameE, passwordE, patronymicE, spinnerEditMode, spinnerGroupUser, spinnerRoleUser,
          spinnerUser, surnameE);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
