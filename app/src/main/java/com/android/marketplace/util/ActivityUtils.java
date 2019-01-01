package com.android.marketplace.util;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * This provides methods to help Activities load their UI.
 * <p>
 * Created by ZARA on 12/19/2018.
 */
public class ActivityUtils {

    /**
     * The{@code fragment} is added to the container view with id{@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     */
    @SuppressLint("RestrictedApi")
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment,
                                             String tag,
                                             boolean isAddToBack,
                                             int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        if (isAddToBack) {
            transaction.addToBackStack(tag);
        }
        transaction.commit();
    }

    /**
     * The{@code fragment} is replaced with the container view with id {@code frameId}. The operation
     * is performed by the {@code fragmentManager}.
     *
     * @param fragmentManager
     * @param fragment
     * @param tag
     * @param frameId
     */
    @SuppressLint("RestrictedApi")
    public static void replaceFragmentInActivity(@NonNull FragmentManager fragmentManager,
                                                 @NonNull Fragment fragment,
                                                 String tag,
                                                 boolean isAddToBack,
                                                 int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                .replace(
                        frameId,
                        fragment,
                        tag
                );
        if (isAddToBack) {
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.commit();
    }
}