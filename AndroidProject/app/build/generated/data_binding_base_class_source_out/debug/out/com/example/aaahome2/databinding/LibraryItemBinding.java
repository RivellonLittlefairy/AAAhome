// Generated by view binder compiler. Do not edit!
package com.example.aaahome2.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.aaahome2.R;
import com.google.android.material.card.MaterialCardView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class LibraryItemBinding implements ViewBinding {
  @NonNull
  private final MaterialCardView rootView;

  @NonNull
  public final TextView appraise;

  @NonNull
  public final TextView gameName;

  @NonNull
  public final ImageView librImg;

  @NonNull
  public final TextView percent;

  @NonNull
  public final TextView price;

  private LibraryItemBinding(@NonNull MaterialCardView rootView, @NonNull TextView appraise,
      @NonNull TextView gameName, @NonNull ImageView librImg, @NonNull TextView percent,
      @NonNull TextView price) {
    this.rootView = rootView;
    this.appraise = appraise;
    this.gameName = gameName;
    this.librImg = librImg;
    this.percent = percent;
    this.price = price;
  }

  @Override
  @NonNull
  public MaterialCardView getRoot() {
    return rootView;
  }

  @NonNull
  public static LibraryItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static LibraryItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.library_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static LibraryItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.appraise;
      TextView appraise = ViewBindings.findChildViewById(rootView, id);
      if (appraise == null) {
        break missingId;
      }

      id = R.id.game_name;
      TextView gameName = ViewBindings.findChildViewById(rootView, id);
      if (gameName == null) {
        break missingId;
      }

      id = R.id.libr_img;
      ImageView librImg = ViewBindings.findChildViewById(rootView, id);
      if (librImg == null) {
        break missingId;
      }

      id = R.id.percent;
      TextView percent = ViewBindings.findChildViewById(rootView, id);
      if (percent == null) {
        break missingId;
      }

      id = R.id.price;
      TextView price = ViewBindings.findChildViewById(rootView, id);
      if (price == null) {
        break missingId;
      }

      return new LibraryItemBinding((MaterialCardView) rootView, appraise, gameName, librImg,
          percent, price);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}