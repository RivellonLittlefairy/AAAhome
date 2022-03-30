// Generated by view binder compiler. Do not edit!
package com.example.aaahome2.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.aaahome2.R;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.zhpan.bannerview.BannerViewPager;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDetailBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button addWishList;

  @NonNull
  public final Button back;

  @NonNull
  public final BannerViewPager bannerView;

  @NonNull
  public final ConstraintLayout baseInfo;

  @NonNull
  public final Toolbar bottomToolbar;

  @NonNull
  public final TextView detailAppraise;

  @NonNull
  public final TextView detailGameName;

  @NonNull
  public final TextView detailGameNameHead;

  @NonNull
  public final TextView detailHighPrice;

  @NonNull
  public final TextView detailNowPrice;

  @NonNull
  public final Toolbar detailToolbar;

  @NonNull
  public final ImageButton expandCollapse;

  @NonNull
  public final ExpandableTextView expandTextView;

  @NonNull
  public final TextView expandableText;

  @NonNull
  public final Button steamIcon;

  private ActivityDetailBinding(@NonNull ConstraintLayout rootView, @NonNull Button addWishList,
      @NonNull Button back, @NonNull BannerViewPager bannerView, @NonNull ConstraintLayout baseInfo,
      @NonNull Toolbar bottomToolbar, @NonNull TextView detailAppraise,
      @NonNull TextView detailGameName, @NonNull TextView detailGameNameHead,
      @NonNull TextView detailHighPrice, @NonNull TextView detailNowPrice,
      @NonNull Toolbar detailToolbar, @NonNull ImageButton expandCollapse,
      @NonNull ExpandableTextView expandTextView, @NonNull TextView expandableText,
      @NonNull Button steamIcon) {
    this.rootView = rootView;
    this.addWishList = addWishList;
    this.back = back;
    this.bannerView = bannerView;
    this.baseInfo = baseInfo;
    this.bottomToolbar = bottomToolbar;
    this.detailAppraise = detailAppraise;
    this.detailGameName = detailGameName;
    this.detailGameNameHead = detailGameNameHead;
    this.detailHighPrice = detailHighPrice;
    this.detailNowPrice = detailNowPrice;
    this.detailToolbar = detailToolbar;
    this.expandCollapse = expandCollapse;
    this.expandTextView = expandTextView;
    this.expandableText = expandableText;
    this.steamIcon = steamIcon;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDetailBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_detail, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDetailBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.addWishList;
      Button addWishList = ViewBindings.findChildViewById(rootView, id);
      if (addWishList == null) {
        break missingId;
      }

      id = R.id.back;
      Button back = ViewBindings.findChildViewById(rootView, id);
      if (back == null) {
        break missingId;
      }

      id = R.id.bannerView;
      BannerViewPager bannerView = ViewBindings.findChildViewById(rootView, id);
      if (bannerView == null) {
        break missingId;
      }

      id = R.id.base_info;
      ConstraintLayout baseInfo = ViewBindings.findChildViewById(rootView, id);
      if (baseInfo == null) {
        break missingId;
      }

      id = R.id.bottom_toolbar;
      Toolbar bottomToolbar = ViewBindings.findChildViewById(rootView, id);
      if (bottomToolbar == null) {
        break missingId;
      }

      id = R.id.detailAppraise;
      TextView detailAppraise = ViewBindings.findChildViewById(rootView, id);
      if (detailAppraise == null) {
        break missingId;
      }

      id = R.id.detailGameName;
      TextView detailGameName = ViewBindings.findChildViewById(rootView, id);
      if (detailGameName == null) {
        break missingId;
      }

      id = R.id.detailGameNameHead;
      TextView detailGameNameHead = ViewBindings.findChildViewById(rootView, id);
      if (detailGameNameHead == null) {
        break missingId;
      }

      id = R.id.detailHighPrice;
      TextView detailHighPrice = ViewBindings.findChildViewById(rootView, id);
      if (detailHighPrice == null) {
        break missingId;
      }

      id = R.id.detailNowPrice;
      TextView detailNowPrice = ViewBindings.findChildViewById(rootView, id);
      if (detailNowPrice == null) {
        break missingId;
      }

      id = R.id.detail_toolbar;
      Toolbar detailToolbar = ViewBindings.findChildViewById(rootView, id);
      if (detailToolbar == null) {
        break missingId;
      }

      id = R.id.expand_collapse;
      ImageButton expandCollapse = ViewBindings.findChildViewById(rootView, id);
      if (expandCollapse == null) {
        break missingId;
      }

      id = R.id.expand_text_view;
      ExpandableTextView expandTextView = ViewBindings.findChildViewById(rootView, id);
      if (expandTextView == null) {
        break missingId;
      }

      id = R.id.expandable_text;
      TextView expandableText = ViewBindings.findChildViewById(rootView, id);
      if (expandableText == null) {
        break missingId;
      }

      id = R.id.steam_icon;
      Button steamIcon = ViewBindings.findChildViewById(rootView, id);
      if (steamIcon == null) {
        break missingId;
      }

      return new ActivityDetailBinding((ConstraintLayout) rootView, addWishList, back, bannerView,
          baseInfo, bottomToolbar, detailAppraise, detailGameName, detailGameNameHead,
          detailHighPrice, detailNowPrice, detailToolbar, expandCollapse, expandTextView,
          expandableText, steamIcon);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
