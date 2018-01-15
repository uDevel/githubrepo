package com.udevel.githubrepo.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.ViewPropertyTransition;

/**
 * Created by benny_zw on 1/14/2018.
 */
public class GlideUtil {

    public static RequestOptions glideOptions = new RequestOptions()
            .priority(Priority.HIGH);

    public static ViewPropertyTransition.Animator animationObject = new ViewPropertyTransition.Animator() {
        @Override
        public void animate(View view) {
            AnimatorSet bounceScaleSet = getBounceScaleSet(view);
            bounceScaleSet.start();
        }
    };
    public static ViewPropertyTransition.Animator animationObjectDelayed = new ViewPropertyTransition.Animator() {
        @Override
        public void animate(View view) {
            AnimatorSet bounceScaleSet = getBounceScaleSet(view);
            bounceScaleSet.setStartDelay(200L);
            bounceScaleSet.start();
        }
    };
    public static ViewPropertyTransition.Animator animationObjectDelayed2 = new ViewPropertyTransition.Animator() {
        @Override
        public void animate(View view) {
            AnimatorSet bounceScaleSet = getBounceScaleSet(view);
            bounceScaleSet.setStartDelay(400L);
            bounceScaleSet.start();
        }
    };

    @NonNull
    private static AnimatorSet getBounceScaleSet(@NonNull View view) {
        float startScale = 0.0F;
        view.setScaleX(startScale);
        view.setScaleY(startScale);

        AnimatorSet bounceScaleSet = new AnimatorSet();
        ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(view, "scaleX", startScale, 1F);
        ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(view, "scaleY", startScale, 1F);
        OvershootInterpolator overshootInterpolator = new OvershootInterpolator(2F);
        bounceScaleSet.setInterpolator(overshootInterpolator);
        bounceScaleSet.playTogether(scaleXAnim, scaleYAnim);

        bounceScaleSet.setDuration(500L);
        return bounceScaleSet;
    }
}
