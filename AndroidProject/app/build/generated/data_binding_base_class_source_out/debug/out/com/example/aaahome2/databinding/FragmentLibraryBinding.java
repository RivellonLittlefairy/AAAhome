// Generated by view binder compiler. Do not edit!
package com.example.aaahome2.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.aaahome2.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentLibraryBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final RecyclerView recyclerview;

  private FragmentLibraryBinding(@NonNull ConstraintLayout rootView,
      @NonNull RecyclerView recyclerview) {
    this.rootView = rootView;
    this.recyclerview = recyclerview;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentLibraryBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentLibraryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_library, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentLibraryBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.recyclerview;
      RecyclerView recyclerview = ViewBindings.findChildViewById(rootView, id);
      if (recyclerview == null) {
        break missingId;
      }

      return new FragmentLibraryBinding((ConstraintLayout) rootView, recyclerview);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}